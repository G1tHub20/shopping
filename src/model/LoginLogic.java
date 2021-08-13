package model;

import dao.UserDAO;

public class LoginLogic {
<<<<<<< HEAD
  public UserBean execute(UserBean user) {
    System.out.println("\r''''''''''''''''''''LoginLogic''''''''''''''''''''");
    UserDAO dao = new UserDAO();
    UserBean loginUser = dao.findUser(user);
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return loginUser;
  }
=======
	public UserBean execute(UserBean user) {
		System.out.println("\r''''''''''''''''''''LoginLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		UserDAO dao = new UserDAO();

        // 入力されたIDとパスワードをもとにDB検索を実行
		UserBean loginUser = dao.findUser(user);
		System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
		return loginUser;
	}
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
}
