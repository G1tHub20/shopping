package model;

import dao.UserDAO;

public class LoginLogic {
  public UserBean execute(UserBean user) {
    System.out.println("\r''''''''''''''''''''LoginLogic''''''''''''''''''''");
    UserDAO dao = new UserDAO();
    UserBean loginUser = dao.findUser(user);
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return loginUser;
  }
}
