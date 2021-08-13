package model;

import dao.UserDAO;

public class RegisterLogic {
<<<<<<< HEAD
  public boolean execute(UserBean user) {
    System.out.println("\r''''''''''''''''''''RegisterLogic''''''''''''''''''''");
    UserDAO dao = new UserDAO();
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return dao.registerUser(user);
  }
=======
	public boolean execute(UserBean user) {
		System.out.println("\r''''''''''''''''''''RegisterLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		UserDAO dao = new UserDAO();
        // 入力されたIDとパスワードのDB登録を実行
		System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
		return dao.registerUser(user); // 結果（真偽値）を返す
	}
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
}
