package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import model.HistoryBean;
import model.UserBean;

public class HistoryDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysqlpa55";

	// ◆ユーザーの全レコードを取得するメソッド
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

	// ◆ユーザーのレコードを更新するメソッド
	public boolean updateHistory(int id) {

		String purchase_date = "2021-05-22";

//		java.util.Date sqlDate = new java.util.Date();
		java.sql.Date sqlDate = Date.valueOf(purchase_date);


		int user_id = 3;
		String item_id = "tie0002";
		int purchase_num = 1;


		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("historyDAO(updateHistory)");

			String sql = "INSERT INTO history(purchase_date, user_id, item_id, purchase_num)\r\n"
					+ "VALUES(?, ?, ?, ?)";




			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setInt(1, purchase_date);
			pStmt.setDate(1, sqlDate);
			pStmt.setInt(2, user_id);
			pStmt.setString(3, item_id);
			pStmt.setInt(4, purchase_num);

			System.out.println("UPDATEを実行");
			System.out.println("INSERT INTO history(purchase_date, user_id, item_id, purchase_num)\r\n"
					+ "VALUES(" + sqlDate + "," + user_id + "," + item_id + "," + purchase_num + ")");

			// UPDATEを実行
			int result = pStmt.executeUpdate(); // 判定が必要



		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println("historyテーブルにも反映");
		return true;
	}
}
