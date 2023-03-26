package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * DB接続を担当するクラス
 */
//NOTE: 当ファイルは追跡対象外(gitignore)
public class DBconnect_copy {
	// ■データベースと接続
	public static Connection getConnection() {
	    // ローカル用
		final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping";
		final String DB_USER = "root";
		
		final String DB_PASS = "*****";
//		System.out.println("JDBC_URL：" + JDBC_URL + ", DB_USER：" + DB_USER + ", DB_PASS：" + DB_PASS);
	    try {
	    	// JDBCドライバの有効化（登録）
	    	Class.forName("com.mysql.cj.jdbc.Driver"); //Java6以降、Class.forNameは不要だが後方互換のため残す
	    } catch (ClassNotFoundException e) {
            e.printStackTrace();
	    }

	    // 接続の確立
	    Connection con = null;
	    try {
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    	System.out.println("DB接続失敗");
	    }
	    return con;
	}

}


