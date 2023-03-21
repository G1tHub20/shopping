package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBconnect;
import model.HistoryBean;

/**
 * 注文履歴テーブルを担当するDAO
 */
public class HistoryDAO {
	// ◆注文履歴を取得するメソッド
    public List<HistoryBean> getHistory(int user_id) {
		List<HistoryBean> historyList = new ArrayList<>();

	    try (Connection con = DBconnect.getConnection() ) {
    		String sql = "SELECT history.id, item_id, item_price, order_num, order_date, name AS item_name, item_price * order_num AS 'sum_price' "
    				+ "FROM history LEFT JOIN item " //外部結合のLEFT JOIN（左側に指定された表のすべての行を表示）
    				+ "ON history.item_id = item.id "
    				+ "WHERE user_id=? ORDER BY id DESC";

            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, user_id);

    		// SQL文を実行
    		ResultSet rs = pStmt.executeQuery();

    		// 退避エリア
    		String save_order_date = null;
    		String order_date = null;

    		while (rs.next()) {
    			int id = rs.getInt("id");
    			int item_id = rs.getInt("item_id");
    			int item_price = rs.getInt("item_price");
    			int order_num = rs.getInt("order_num");
    			String item_name = rs.getString("item_name");
    			int sum_price = rs.getInt("sum_price");

    			// 注文単位ごとに注文履歴を区切って表示するため、
    			// 2行目以降は注文日時を空にする
    			order_date = rs.getString("order_date").substring(0, 16);
    			if(save_order_date == null) {
    				// 最初
					save_order_date = order_date; //退避エリアに保持

    			} else {
    				// 2行目以降
    				if (order_date.equals(save_order_date)) {
    					// 前回と同じ注文日時
    					order_date = "";
    				} else {
    					// 前回と異なる注文日時
    					save_order_date = order_date; //退避エリアに保持
    				}
    			}
				HistoryBean history = new HistoryBean(id, user_id, item_id, item_price, order_num, order_date, item_name, sum_price);
				historyList.add(history);
			}

        } catch (SQLException e) {
        	e.printStackTrace();
        	return null;
        }

        return historyList;
    }


    // ◆注文履歴に追加するメソッド
    public Boolean addHistory(int user_id, Map<Integer, List<Object>> cartMap) {
 		Connection con = null;
        con = DBconnect.getConnection();

        try {
        	 // カートの情報を分解し、処理に使用できる形にする
        	 for (Object key : cartMap.keySet()) {
        		 int item_id = (int)key;
        		 int item_price = (int) cartMap.get(key).get(1);
        		 int order_num = (int) cartMap.get(key).get(2);

	       		// ■注文履歴を追加する
	     		String sql = "INSERT INTO history(user_id, item_id, item_price, order_num) VALUES (?,?,?,?)";
//	     		System.out.println("INSERT INTO history(user_id, item_id, item_price, order_num) VALUES (" + user_id + ", " + item_id + ", " + item_price + ", " + order_num + ")");

	            PreparedStatement pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, user_id);
	            pstmt.setInt(2, item_id);
	            pstmt.setInt(3, item_price);
	            pstmt.setInt(4, order_num);

	    		if (pstmt.executeUpdate() != 1) {
//	    			System.out.println("DBレコード追加エラー"); // DBの更新に失敗した場合の処理も必要か？
	    		}
        	 }

        } catch (SQLException e) {
	         	e.printStackTrace();
	         	return false;
	    }
 		return true;
    }
}