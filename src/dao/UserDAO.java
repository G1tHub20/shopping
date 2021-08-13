package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserBean;

public class UserDAO {
  private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping";
  private final String DB_USER = "root";
  private final String DB_PASS = "mysqlpa55";

  // ◆userテーブルからレコードを取得するメソッド
  public UserBean findUser(UserBean inputUser) {
    System.out.println("...................UserDAO(findUser)...................");
    UserBean user = inputUser;
    UserBean loginUser = new UserBean();
    // DB接続
	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

		String sql = "SELECT id, user_name, pass FROM user WHERE user_name = ?  AND pass = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, user.getUserName());
		pStmt.setString(2, user.getPass());

		System.out.println("一致するユーザー情報を検索");
		System.out.println("SELECT id, user_name, pass FROM user WHERE user_name = \"" + user.getUserName() + "\" AND pass = \"" + user.getPass() + "\"");

		// SQL文を実行
		ResultSet rs = pStmt.executeQuery();

		while (rs.next()){
			int userId = rs.getInt("id");
			String registeredPass = rs.getString("pass");
			String registeredUserName = rs.getString("user_name");

			System.out.println("取得したユーザー情報からloginUserインスタンスを生成");
			System.out.println("id=" + userId + "、user_name=" + registeredUserName + "、pass=" + registeredPass);
			loginUser = new UserBean(userId, registeredUserName, registeredPass);
		}

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("DB接続しっぱい");
    }
    return loginUser;
  }

  //◆userテーブルにアカウントを新規登録するメソッド
  public boolean registerUser(UserBean inputUser) {
    System.out.println("...................UserDAO(registerUser)...................");
    UserBean user = inputUser;
    // DB接続
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

		String sql0 = "SELECT EXISTS(SELECT * FROM user WHERE user_name = ?) AS user_check";
		PreparedStatement pStmt0 = conn.prepareStatement(sql0);
		pStmt0.setString(1, user.getUserName());
		ResultSet rs = pStmt0.executeQuery();

		String user_check = "0";
		if(rs.next()){
			user_check = rs.getString("user_check");
		}

		// 実行前のレコードチェック
		if (user_check.equals("1")) {
			System.out.println("同じユーザーIDが既にあります。登録処理できません…");
			return false;
		}
		System.out.println("登録可能です。登録処理を行います！");

		String sql = "INSERT INTO user(user_name, pass) VALUES(?, ?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, user.getUserName());
		pStmt.setString(2, user.getPass());

		System.out.println("新規ユーザー情報を登録");
		System.out.println("INSERT INTO user(user_name, pass) VALUES(\"" + user.getUserName() + "\" , \"" + user.getPass() + "\")");

		// SQL文を実行
		int result = pStmt.executeUpdate(); //resultには追加された行数(「1」になるはず)が入る
		if (result != 1) {
			System.out.println("登録できませんでした…");
			return false;
		}
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("DB接続しっぱい");
    }
    System.out.println("登録完了");
    return true;
  }
}
