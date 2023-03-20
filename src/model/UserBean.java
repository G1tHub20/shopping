package model;

import java.io.Serializable;
/**
 * ユーザの情報を保持するデータモデル
 */
public class UserBean implements Serializable {
	private int id;
	private String userName;
	private String pass;

	public UserBean() {}

	// userテーブル ※ログイン時に、管理者 or 一般ユーザーの判定で使用
	public UserBean(int id, String userName, String pass) {
		this.id = id;
		this.userName = userName;
		this.pass = pass;
	}

	// 上記以外で使用
	public UserBean(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getPass() {
		return pass;
	}
}
