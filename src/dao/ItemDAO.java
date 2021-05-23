package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ItemBean;

public class ItemDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysqlpa55";

	// ◆全レコードを取得するメソッド
	public List<ItemBean> getItem() {
		List<ItemBean> itemList = new ArrayList<>();

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("itemDAO(getItem)");

			String sql = "SELECT id, name, price, quantity FROM item";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			System.out.println("全ての商品情報を取得");
			System.out.println(sql);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				String itemId = rs.getString("id");
				String itemName = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");

				ItemBean item = new ItemBean(itemId, itemName, price, quantity);
				itemList.add(item);
			}

			System.out.println(itemList);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}
		System.out.println("--------------------------------------------------------------------");
		return itemList;
	}


	// ◆検索条件に一致するレコードを取得するメソッド
	public List<ItemBean> searchItem(ItemBean itemSearch) {

		List<ItemBean> itemList = new ArrayList<>();

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("itemDAO(searchItem)");

			System.out.println("検索した商品情報を取得");

			String searchWord1 = "";
			String searchWord2 = "";

			searchWord1 = itemSearch.getName();
			searchWord2 = itemSearch.getItem_id();

			System.out.println("searchWord1=" + searchWord1 + "、searchWord2=" + searchWord2);

			String sql = "SELECT id, name, price, quantity FROM item WHERE name LIKE ? \r\n"
					+ "AND id LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1,  "%" + searchWord1 + "%");
			pStmt.setString(2,  searchWord2 + "%");

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			System.out.println("SELECTを実行");

			while (rs.next()) {
				String itemId = rs.getString("id");
				String itemName = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");

				ItemBean item = new ItemBean(itemId, itemName, price, quantity);
				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}
	System.out.println("--------------------------------------------------------------------");
	return itemList;
	}

	// ◆注文された商品の在庫数を変更するメソッド
	public boolean buyItem(ItemBean itemBuy) {

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("itemDAO(buyItem)");

			int purchaseNum = 3;
			String item_id = "tie0002";

			String sql1 = "SELECT quantity FROM item where id = ?";
			System.out.println("SELECT quantity FROM item where id = " + item_id + "\"");

			PreparedStatement pStm1 = conn.prepareStatement(sql1);
			pStm1.setString(1, item_id);

			ResultSet rs1 = pStm1.executeQuery();

			int stock = 0;
			if(rs1.next()){
				stock = Integer.parseInt(rs1.getString("quantity"));
			}



			System.out.println("在庫数= " + stock);


			String sql = "UPDATE item SET\r\n"
					+ "  quantity =\r\n"
					+ "    CASE\r\n"
					+ "        WHEN 0 <= quantity-? THEN quantity - ?\r\n"
					+ "        ELSE quantity\r\n"
					+ "    END\r\n"
					+ "WHERE id = ?;\r\n";


			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, purchaseNum);
			pStmt.setInt(2, purchaseNum);
			pStmt.setString(3, item_id);


			int result = 0;

			if (stock - purchaseNum >= 0) {

				System.out.println("在庫数足りてる！");
			System.out.println("UPDATEを実行");
			System.out.println("UPDATE item SET quantity = CASE WHEN 0 <= quantity - " + purchaseNum + " THEN quantity - " + purchaseNum + " ELSE quantity END WHERE id = " + item_id + ";");

				// UPDATEを実行
				result = pStmt.executeUpdate(); // SQLExceptionでエラーが出るため、これはエラー判定に使えない

				System.out.println("注文完了（itemテーブルの在庫更新");

			} else {
				System.out.println("在庫数足りていない…");
				return false;
			}


		} catch (SQLException e) {
			// quantityがマイナスになるとき例外処理したい！
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}
	System.out.println("--------------------------------------------------------------------");

	return true;
	}
}