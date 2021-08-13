package model;

import dao.UserDAO;

public class RegisterLogic {
  public boolean execute(UserBean user) {
    System.out.println("\r''''''''''''''''''''RegisterLogic''''''''''''''''''''");
    UserDAO dao = new UserDAO();
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return dao.registerUser(user);
  }
}
