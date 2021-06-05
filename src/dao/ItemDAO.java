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

			String sql1 = "SELECT quantity FROM item where id = ?";
			System.out.println("SELECT quantity FROM item where id = " + item_id + "\"");

			PreparedStatement pStm1 = conn.prepareStatement(sql1);
			pStm1.setString(1, item_id);

			ResultSet rs1 = pStm1.executeQuery();

			int stock = 0;
			if(rs1.next()){
				stock = Integer.parseInt(rs1.getString("quantity"));
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

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "UPDATE item SET price = ?, quantity = ? WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, price);
			pStmt.setInt(2, quantity);
			pStmt.setString(3, item_id);

			System.out.println("UPDATE item SET price = \"" + price + ", quantity = \\" + quantity + "WHERE id = \"" + item_id + "\"");
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

}