package model;

import dao.UserDAO;

public class LoginLogic {
	public UserBean execute(UserBean user) {
        // DAOクラスをインスタンス化
		UserDAO dao = new UserDAO();

        // 入力されたIDとパスワードをもとにDB検索を実行
		UserBean loginUser = dao.findUser(user);

		return loginUser;
	}
}
