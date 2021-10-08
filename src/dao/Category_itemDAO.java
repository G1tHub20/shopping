package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CategoryItemBean;

public class Category_itemDAO {
  private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping";
  private final String DB_USER = "root";
  private final String DB_PASS = "mysqlpa55";

  // ◆category_itemテーブルからレコードを取得するメソッド
  public List<CategoryItemBean> getCategoryInfo() {
    System.out.println("...................category_item(getCategoryInfo)...................");

    List<CategoryItemBean> categoryList = new ArrayList<>();

    // DB接続
	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

		String sql = "SELECT category, name, concat(category, count_num) as item_id FROM category_item";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		System.out.println("全てのカテゴリー情報を取得");
		System.out.println(sql);

		// SQL文を実行
		ResultSet rs = pStmt.executeQuery();

		System.out.println("取得したカテゴリー情報からcategoryInfoインスタンスを生成");
		while (rs.next()){
			String category = rs.getString("category");
			String name = rs.getString("name");
			String item_id = rs.getString("item_id");

			System.out.println("category=" + category + "、name=" + name + "、item_id=" + item_id);
			CategoryItemBean categoryInfo = new CategoryItemBean(category, name, item_id);
			categoryList.add(categoryInfo);
		}

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("DB接続しっぱい");
    }
    return categoryList;
  }

//◆category_itemテーブルから取得するメソッド
public String getNewId(String category) {
	System.out.println("...................category_item(getNewid)...................");
	String item_id = null;
	String category0 = category;

   // DB接続
	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

		String sql = "SELECT concat(category, count_num) as item_id FROM category_item WHERE category = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, category0);
		System.out.println("採番された商品コードを取得");
		System.out.println("SELECT concat(category, count_num) as item_id FROM category_item WHERE category = '" + category0 + "\'");

		// SQL文を実行
		ResultSet rs = pStmt.executeQuery();

		if(rs.next()){
			item_id = rs.getString("item_id");
		}

   } catch (SQLException e) {
     e.printStackTrace();
     System.out.println("DB接続しっぱい");
   }
   return item_id;
 }

//◆category_itemテーブルを更新するメソッド
public String updateId(String category) {
	System.out.println("...................category_item(getNewid)...................");
	String item_id = null;
	String category0 = category;

 // DB接続
	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

		String sql = "SELECT concat(category, count_num) as item_id FROM category_item WHERE category = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, category0);
		System.out.println("採番された商品コードを取得");
		System.out.println("SELECT concat(category, count_num) as item_id FROM category_item WHERE category = '" + category0 + "\'");

		// SQL文を実行
		ResultSet rs = pStmt.executeQuery();

		if(rs.next()){
			item_id = rs.getString("item_id");
		}

 } catch (SQLException e) {
   e.printStackTrace();
   System.out.println("DB接続しっぱい");
 }
 return item_id;
}

}