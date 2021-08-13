package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HistoryBean;
import model.ItemBean;
import model.UserBean;

public class HistoryDAO {
  private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping";
  private final String DB_USER = "root";
  private final String DB_PASS = "mysqlpa55";

  //◆ユーザーごとにレコードを取得するメソッド
  public List<HistoryBean> getHistory(UserBean loginUser) {
    System.out.println("...................HistoryDAO(getHistory)...................");
    UserBean user = loginUser;
    List<HistoryBean> historyList = new ArrayList<>();

    // DB接続
	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

		System.out.println("ユーザーの注文履歴を取得");

		// datetime型に対し、DATE_FORMAT 関数で年月日のみのフォーマットを指定
//		String sql = "SELECT order_id, user_id, DATE_FORMAT(purchase_date, '%Y-%m-%d') AS purchase_date, item_id, name, item_price, item_price * purchase_num AS \"subtotal\", purchase_num\r\n"
//				+ "FROM history JOIN item\r\n"
//				+ "ON history.item_id = item.id\r\n"
//				+ "WHERE user_id = ?\r\n ORDER BY order_id ASC";

		// datetime型に対し、DATE_FORMAT 関数で年月日のみのフォーマットを指定
		// 外部結合（LEFT JOIN）　←一致しないデータも取得するため
		String sql = "SELECT order_id, user_id, purchase_date, item_id, name, item_price, item_price * purchase_num AS \"subtotal\", purchase_num\r\n"
				+ "FROM history LEFT JOIN item\r\n"
				+ "ON history.item_id = item.id\r\n"
				+ "WHERE user_id = ?\r\n ORDER BY order_id DESC";

		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, user.getUserId());

		// SQL文を実行
		ResultSet rs = pStmt.executeQuery();

		System.out.println("SELECT order_id, user_id, purchase_date, item_id, name, item_price, item_price * purchase_num AS \"subtotal\", purchase_num FROM history LFTT JOIN item\r\n"
				+ "ON history.item_id = item.id WHERE user_id = \"3\" ORDER BY order_id DESC;");

		// 退避エリア
		String savePurchaseDate = null;
		String purchaseDate = null;

		while (rs.next()) {
			int counter = 0;

			String purchaseDate0 = rs.getString("purchase_date");
			System.out.println("purchaseDate0= " + purchaseDate0);

			if (!purchaseDate0.equals(savePurchaseDate) && (counter == 0) ) {
				savePurchaseDate = rs.getString("purchase_date");
				purchaseDate = purchaseDate0.substring(0, 10);
			} else {
				purchaseDate = "&nbsp;";
			}
			System.out.println("purchaseDate= " + purchaseDate);

			int orderId = rs.getInt("order_id");
			String name = rs.getString("name");
			if (name == null) {
				name = "（販売終了商品）";
			}
			int price = rs.getInt("item_price");
			int subtotal = rs.getInt("subtotal");

			int userId = rs.getInt("user_id");
			String itemId = rs.getString("item_id");
			int purchaseNum = rs.getInt("purchase_num");

			// subStr(10, 6)

			HistoryBean history = new HistoryBean(orderId, purchaseDate, name, price, subtotal, userId, itemId, purchaseNum);
			historyList.add(history);

			counter++;
		}

		System.out.println(historyList);

	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("DB接続しっぱい");
	}
	return historyList;
}


	// ◆レコードを追加するメソッド
	public boolean addHistory(ItemBean itemBuy) {
		System.out.println("...................HistoryDAO(addHistory)...................");

		// java.sql.Dateとクラス名が被りimportできないため、完全限定クラス名を使う
//		java.util.Date today = new java.util.Date();
//		System.out.println(today);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////		String purchase_date = dateFormat.format(today);
//		java.sql.Date sqlDate = Date.valueOf(purchase_date);

		int user_id = itemBuy.getUser_id();
		String item_id = itemBuy.getItem_id();
		int purchaseNum = itemBuy.getQuantity();



		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("historyDAO(addHistory)");

			String sql0 = "SELECT price FROM item WHERE id = ?";
			PreparedStatement pStmt0 = conn.prepareStatement(sql0);
			pStmt0.setString(1, item_id);

			// SELECTを実行
			ResultSet rs = pStmt0.executeQuery();

			int item_price = 0;

			while (rs.next()) {
				item_price = rs.getInt("price");
			}

			System.out.println("item_price=" + item_price);

			String sql = "INSERT INTO history(user_id, item_id, item_price, purchase_num)\r\n"
					+ "VALUES(?, ?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);
			pStmt.setString(2, item_id);
			pStmt.setInt(3, item_price);
			pStmt.setInt(4, purchaseNum);
			pStmt.executeUpdate();

			System.out.println("INSERTを実行");
			System.out.println("INSERT INTO history(user_id, item_id, item_price, purchase_num)\r\n"
					+ "VALUES(" + user_id + "," + item_id + "," + item_price + "," + purchaseNum + ")");


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}
		return true;
	}
}