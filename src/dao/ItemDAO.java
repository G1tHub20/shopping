package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBconnect;
import model.ItemBean;
/**
 * 商品テーブルを担当するDAO
 */
public class ItemDAO {
	// ◆商品一覧を取得するメソッド
    public List<ItemBean> getItem(int userId) {
		List<ItemBean> itemList = new ArrayList<>();

		String sql = null;
		if (userId == 1) {
			// 管理者の場合 →全ての商品を取得
			sql = "SELECT id, name, type, price, stock, image, state, created_at "
					+ "FROM item ORDER BY id DESC";
		} else {
			// 一般ユーザーの場合 →販売中商品のみを取得
			sql = "SELECT id, name, type, price, stock, image, state, created_at "
					+ "FROM item WHERE state=1 ORDER BY id DESC"; //state=0（販売中止）,state=1（販売中）
		}

	    try (Connection con = DBconnect.getConnection() ) {
            PreparedStatement pstmt = con.prepareStatement(sql);
    		// SQL文を実行
    		ResultSet rs = pstmt.executeQuery();
    		// System.out.println(sql);

    		while (rs.next()) {
    			int id = rs.getInt("id");
    			String name = rs.getString("name");
    			String type = rs.getString("type");
    			int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String image = rs.getString("image");
				int state = rs.getInt("state");
				String created_at = rs.getString("created_at");

				ItemBean item = new ItemBean(id, name, type, price, stock, image, state, created_at);
				itemList.add(item);
			}

        } catch (SQLException e) {
        	e.printStackTrace();
        	return null;
        }

        return itemList;
    }


	// ◆在庫数をチェックするメソッド
	public String checkStock(Map<Integer, List<Object>> cartMap) {
 		String short_stock = null; //在庫不足の商品名
 		// 注文可能かを商品ごとにループでチェック
	    try (Connection con = DBconnect.getConnection() ) {
        	for (Object key : cartMap.keySet()) {
        		int item_id = (int)key;
        		int quantity = (int) cartMap.get(key).get(2);

	        	// ■在庫数のチェック
	        	String sql0 = "SELECT name, "
				        	 + "CASE WHEN stock<" + quantity +  " THEN false "
				        	 + "ELSE true "
				        	 + "END AS is_orderable "
				        	 + "FROM item WHERE id=" + item_id; //※パラメータ指定だと文法エラーになったため、値を直で記述した
//	        	System.out.println("SELECT name, CASE WHEN stock<" + quantity + " THEN false ELSE true END AS is_orderable FROM item WHERE id=" + item_id);

	        	PreparedStatement pstmt0 = con.prepareStatement(sql0);
	       		ResultSet rs = pstmt0.executeQuery(sql0);

	       		boolean is_orderable = false;
	       		if (rs.next()) {
	       			is_orderable = rs.getBoolean("is_orderable");
	       		}
	       		// 注文不可（在庫不足） 商品があった場合→ループを抜けて商品名を返す
	       		if (!is_orderable) {
	       			short_stock = rs.getString("name");
	       			return short_stock;
	       		}
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        }

		return short_stock;
	}

	// ◆在庫数を減算するメソッド
    public boolean reduceStock(Map<Integer, List<Object>> cartMap) {
	    try (Connection con = DBconnect.getConnection() ) {
        	 for (Object key : cartMap.keySet()) {
        		 int item_id = (int)key;
        		 int quantity = (int) cartMap.get(key).get(2);

	       		// ■在庫数を減らす
	     		String sql = "UPDATE item SET stock=stock-? WHERE id=?";
//	     		System.out.println("UPDATE item SET stock=stock-" + quantity + " WHERE id=" + item_id);

	             PreparedStatement pstmt = con.prepareStatement(sql);
	             pstmt.setInt(1, quantity);
	             pstmt.setInt(2, item_id);

	     		// SQL文を実行
	     		int result = pstmt.executeUpdate();

	     		// 更新エラー
	     		if (result != 1) {
	     			return false;
	     		}

        	 }
         } catch (SQLException e) {
        	 e.printStackTrace();
         }

         return true;
     }


	// ◆在庫を初期設定するメソッド（管理者用）
	public boolean restoreItemList() {
 		boolean result = true;
	    try (Connection con = DBconnect.getConnection() ) {
        	String sql = "UPDATE item SET stock=? WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            int[] stocks = {5, 1, 5, 5, 5, 5, 5}; // TODO:在庫数を自由に変更可能にする

            for (int i=0; i<stocks.length; i++) {
	            pstmt.setInt(1, stocks[i]);
	            pstmt.setInt(2, i+1);
//	            System.out.println("UPDATE item SET stock=" + stocks[i] + " WHERE id=" + (i+1));
	            int r = pstmt.executeUpdate();
	            if (r != 1) {
	            	result = false;
	            }
            }

        } catch (SQLException e) {
       	 e.printStackTrace();
        }

        return result;
    }

}