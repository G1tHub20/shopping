package test;

import dao.ItemDAO;
import model.ItemBean;

public class AdminLogicTest {
  public static void main(String[] args) {
    testExecute1();
  }
  
  public static void testExecute1() {
    ItemBean itemNew = new ItemBean("tie0003", "ネクタイ", 12000, 3);
    ItemDAO dao = new ItemDAO();
    boolean isNew = dao.newItemInfo(itemNew);
    if (isNew) {
      System.out.println("testExecute：成功しました");
    } else {
      System.out.println("testExecute：しっぱい");
    } 
  }
}
