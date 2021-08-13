package model;

import java.io.Serializable;

public class UserBean implements Serializable {
  private int userId;
  
  private String userName;
  
  private String pass;
  
  public UserBean() {}
  
  public UserBean(String userName, String pass) {
    this.userName = userName;
    this.pass = pass;
  }
  
  public UserBean(int userId, String userName, String pass) {
    this.userId = userId;
    this.userName = userName;
    this.pass = pass;
  }
  
  public int getUserId() {
    return this.userId;
  }
  
  public String getUserName() {
    return this.userName;
  }
  
  public String getPass() {
    return this.pass;
  }
}
