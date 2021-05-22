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

//			sql = "SELECT * FROM item WHERE name LIKE '%時計%' AND id LIKE 'wat%'";
//			pStmt = conn.prepareStatement(sql); // テスト

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
	public int buyItem(ItemBean itemBuy) {

		int user_id = 0;

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("itemDAO(buyItem)");

//			int quantity = rs.getInt("quantity");
			int purchaseNum = 1;
			String item_id = "tie0002";

			String sql = "UPDATE item\r\n"
					+ "SET quantity = quantity - 1\r\n"
					+ "WHERE id = \"tie0002\"";

			System.out.println("UPDATEを実行");
			System.out.println(sql);

//			String sql = "UPDATE item\r\n"
//					+ "SET quantity = quantity - ?\r\n"
//					+ "WHERE id = \"tie0002\".\r\n";
//
			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setInt(1, purchaseNum);
//			pStmt.setString(2, item_id);


			// これを取得すべし！
			// 購入履歴を反映するユーザーを特定するための値
			user_id = 3;


			// UPDATEを実行
			int result = pStmt.executeUpdate();

			if (result != 1) {
				System.out.println("注文できませんでした");
				return 0;
			}

		} catch (SQLException e) {
			// quantityがマイナスになるとき例外処理したい！
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}
	System.out.println("--------------------------------------------------------------------");
	System.out.println("注文完了（itemテーブルの在庫更新");
	return user_id;
	}
}