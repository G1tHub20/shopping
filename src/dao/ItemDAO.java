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
		System.out.println("...................ItemDAO(getItem)...................");

		List<ItemBean> itemList = new ArrayList<>();

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
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
		return itemList;
	}


	// ◆検索条件に一致するレコードを取得するメソッド
	public List<ItemBean> searchItem(ItemBean itemSearch) {
		System.out.println("...................ItemDAO(searchItem)...................");

		List<ItemBean> itemList = new ArrayList<>();

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
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
	return itemList;
	}

	// ◆注文された商品の在庫数を変更するメソッド
	public boolean buyItem(ItemBean itemBuy) {
		System.out.println("...................ItemDAO(buyItem)...................");

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("itemDAO(buyItem)");

			int purchaseNum = itemBuy.getQuantity();
			String item_id = itemBuy.getItem_id();

			String sql0 = "SELECT quantity FROM item where id = ?";
			System.out.println("SELECT quantity FROM item where id = " + item_id + "\"");

			PreparedStatement pStm0 = conn.prepareStatement(sql0);
			pStm0.setString(1, item_id);

			ResultSet rs0 = pStm0.executeQuery();

			int stock = 0;
			if(rs0.next()){
				stock = Integer.parseInt(rs0.getString("quantity"));
			}

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

			System.out.println("在庫数= " + stock);
			System.out.println("注文数= " + purchaseNum);


			if (stock - purchaseNum >= 0) {
				System.out.println("在庫が足りてる！");

				System.out.println("UPDATEを実行");
				pStmt.executeUpdate();
				System.out.println("UPDATE item SET quantity = CASE WHEN 0 <= quantity - " + purchaseNum + " THEN quantity - " + purchaseNum + " ELSE quantity END WHERE id = " + item_id + ";");

				System.out.println("注文完了（itemテーブルの在庫更新");

			} else {
				System.out.println("在庫が足りていない…");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}

	return true;
	}

	// ◆商品情報を変更するメソッド
	public boolean changeItemInfo(ItemBean itemChange) {
		System.out.println("...................ItemDAO(changeItemInfo)...................");

		int price = itemChange.getPrice();
		int quantity = itemChange.getQuantity();
		String item_id = itemChange.getItem_id();

		System.out.println("item_id=" + item_id);

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// 存在チェック
			String sql0 = "SELECT EXISTS(SELECT * FROM item WHERE id = ? AND price = ? AND quantity= ?) AS item_check";
			PreparedStatement pStmt0 = conn.prepareStatement(sql0);
			pStmt0.setString(1, item_id);
			pStmt0.setInt(2, price);
			pStmt0.setInt(3, quantity);
			ResultSet rs = pStmt0.executeQuery();

			String item_check = "0";
			if(rs.next()){
				item_check = rs.getString("item_check");
			}

			// 実行前のレコードチェック
			if (item_check.equals("1")) {
				System.out.println("商品情報の値が同じです。変更処理できません…");
				return false;
			}

			String sql = "UPDATE item SET price = ?, quantity = ? WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, price);
			pStmt.setInt(2, quantity);
			pStmt.setString(3, item_id);

			System.out.println("UPDATE item SET price = \"" + price + "\", quantity = \"" + quantity + "\", WHERE id = \"" + item_id + "\"");
			int result = pStmt.executeUpdate(); //resultには追加された行数(「1」になるはず)が入る

			// 変更しっぱい…
			if (result != 1) {
				return false;
			}

			System.out.println(item_id + "の商品情報を変更した！");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}
		return true;
	}

	// ◆商品情報を変更するメソッド
		public boolean deleteItemInfo(ItemBean itemChange) {
			System.out.println("...................ItemDAO(changeItemInfo)...................");

			String item_id = itemChange.getItem_id();

			// DB接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				String sql = "DELETE FROM item WHERE id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, item_id);

				System.out.println("DELETE FROM item WHERE id = \"" + item_id + "\"");
				int result = pStmt.executeUpdate(); //resultには削除された行数(「1」になるはず)が入る

				// 変更しっぱい…
				if (result != 1) {
					return false;
				}

				System.out.println(item_id + "の商品情報を削除した！");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("DB接続しっぱい");
			}
			return true;
		}

	// ◆新商品を追加するメソッド
	public boolean newItemInfo(ItemBean itemNew) {
		System.out.println("...................ItemDAO(newItemInfo)...................");

		String item_id = itemNew.getItem_id();
		String name = itemNew.getName();
		int price = itemNew.getPrice();
		int quantity = itemNew.getQuantity();

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql0 = "SELECT EXISTS(SELECT * FROM item WHERE id = ?) AS item_check";
			PreparedStatement pStmt0 = conn.prepareStatement(sql0);
			pStmt0.setString(1, item_id);
			ResultSet rs = pStmt0.executeQuery();

			String item_check = "0";
			if(rs.next()){
				item_check = rs.getString("item_check");
			}

			// 実行前のレコードチェック
			if (item_check.equals("1")) {
				System.out.println("同じ商品コードが既に存在します。登録処理できません…");
				return false;
			}

			System.out.println("新規商品です。登録処理を行います！");


			String sql = "INSERT INTO item(id, name, price, quantity) VALUES(?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, item_id);
			pStmt.setString(2, name);
			pStmt.setInt(3, price);
			pStmt.setInt(4, quantity);

			System.out.println("INSERT INTO item(id, name, price, quantity) VALUES(" + item_id + "," + name + "," + price + "," + quantity);
			int result = pStmt.executeUpdate(); //resultには追加された行数(「1」になるはず)が入る

			// 変更しっぱい…
			if (result != 1) {
				return false;
			}

			System.out.println(item_id + "新商品を追加した！");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい");
		}
		return true;
	}


}