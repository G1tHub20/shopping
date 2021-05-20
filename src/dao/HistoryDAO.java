package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HistoryBean;
import model.UserBean;

public class HistoryDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysqlpa55";

	// ◆itemテーブルからレコードを取得するメソッド
	public List<HistoryBean> getHistory(UserBean loginUser) {
		UserBean user = loginUser;
		List<HistoryBean> historyList = new ArrayList<>();

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("historyDAO(getHistory)");

//			String sql = "SELECT order_id, purchase_date, user_id, item_id, purchase_num FROM history WHERE user_id = ? ";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setInt(1, user.getId());

			System.out.println("ユーザーの注文履歴を取得");

			String sql = "SELECT order_id, user_id, purchase_date, item_id, name, price, price * purchase_num AS \"subtotal\", purchase_num\r\n"
					+ "FROM history JOIN item\r\n"
					+ "ON history.item_id = item.id\r\n"
					+ "WHERE user_id = ?\r\n"
					+ "GROUP BY item_id";

			System.out.println("sql=" + sql);
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getId());

			// SQL文を実行
			ResultSet rs = pStmt.executeQuery();

			System.out.println("SELECT order_id, user_id, purchase_date, item_id, name, price, price * purchase_num AS \"subtotal\", purchase_num FROM history JOIN item\r\n"
					+ "ON history.item_id = item.id WHERE user_id = \"3\" GROUP BY item_id;");

			while (rs.next()) {

				int orderId = rs.getInt("order_id");
				String purchaseDate = rs.getString("purchase_date");

				String name = rs.getString("name");
				int price = rs.getInt("price");
				int subtotal = rs.getInt("subtotal");

				int userId = rs.getInt("user_id");
				String itemId = rs.getString("item_id");
				int purchaseNum = rs.getInt("purchase_num");

				HistoryBean history = new HistoryBean(orderId, purchaseDate, name, price, subtotal, userId, itemId, purchaseNum);
				historyList.add(history);
			}

			System.out.println(historyList);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}
		System.out.println("--------------------------------------------------------------------");
		return historyList;
	}
}
