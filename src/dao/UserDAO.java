package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBconnect;
import model.UserBean;
/**
 * ユーザテーブルを担当するDAO
 */
public class UserDAO {
    public UserBean findUser(UserBean user) {
		UserBean loginUser = null;

	    try (Connection con = DBconnect.getConnection() ) {
        	String sql = "SELECT id, userName, pass FROM user WHERE userName = ?  AND pass = ?";
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setString(1, user.getUserName());
            pStmt.setString(2, user.getPass());
//            System.out.println("SELECT id, userName, pass FROM user WHERE userName = \"" + user.getUserName() + "\" AND pass = \"" + user.getPass() + "\"");

    		// SQL文を実行
    		ResultSet rs = pStmt.executeQuery();

    		// DBにユーザー情報がある場合
    		if (rs.next()) {
    			int id = rs.getInt("id");
    			String userName = rs.getString("userName");
    			String pass = rs.getString("pass");
    			loginUser = new UserBean(id, userName, pass);
    		}
	    } catch (SQLException e) {
              e.printStackTrace();
              return null;
        }

        return loginUser;
    }

    public boolean registerUser(UserBean user) {
		boolean insertResult = false; // アカウント追加完了のフラグ

	    try (Connection con = DBconnect.getConnection() ) {
        	// DBに重複するユーザーIDが無いかチェック
        	String sql0 = "SELECT COUNT(*) FROM user WHERE userName= ? LIMIT 1;";
            PreparedStatement pstmt0 = con.prepareStatement(sql0);
            pstmt0.setString(1, user.getUserName());

    		ResultSet rs = pstmt0.executeQuery();

    		int check_dup = 999; //重複チェック用フラグ（重複無しなら0）
    		if(rs.next()) {
    			check_dup = rs.getInt(1);
    		}

    		// 重複が無い場合 →アカウントをDBに追加
    		if (check_dup == 0) {
	        	String sql = "INSERT INTO user(userName, pass) VALUES(?, ?)";
	            PreparedStatement pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, user.getUserName());
	            pstmt.setString(2, user.getPass());
//	            System.out.println("INSERT INTO user(userName, pass) VALUES(" + user.getUserName() + ", " + user.getPass() + ")");
	            int result = pstmt.executeUpdate();

	            // 正常（1レコードのみ追加）の場合
	    		if (result == 1) {
	    			insertResult = true;
	    		}
    		}
        } catch (SQLException e) {
              e.printStackTrace();
        }

        return insertResult;
    }
}