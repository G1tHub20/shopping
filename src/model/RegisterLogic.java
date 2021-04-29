package model;

import dao.UserDAO;

public class RegisterLogic {
	public boolean execute(UserBean user) {
        // DAOクラスをインスタンス化
		UserDAO dao = new UserDAO();
        // 入力されたIDとパスワードのDB登録を実行
		return dao.registerUser(user); // 結果（真偽値）を返す
	}
}
