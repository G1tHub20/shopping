package model;

import dao.UserDAO;

public class LoginLogic {
	public UserBean execute(UserBean user) {
		System.out.println("\r''''''''''''''''''''LoginLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		UserDAO dao = new UserDAO();

        // 入力されたIDとパスワードをもとにDB検索を実行
		UserBean loginUser = dao.findUser(user);
		System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
		return loginUser;
	}
}
