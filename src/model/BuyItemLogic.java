package model;

import dao.HistoryDAO;
import dao.ItemDAO;

public class BuyItemLogic {
  public boolean execute(ItemBean itemBuy) {
    System.out.println("\r''''''''''''''''''''BuyItemLogic''''''''''''''''''''");
    ItemDAO dao = new ItemDAO();
    boolean is_addHistory = dao.buyItem(itemBuy);
    if (is_addHistory) {
      HistoryDAO dao2 = new HistoryDAO();
      Boolean isBuy = Boolean.valueOf(dao2.addHistory(itemBuy));
      if (isBuy.booleanValue()) {
        System.out.println("履歴テーブル更新完了！");
      } else {
        System.out.println("履歴テーブル更新しっぱい");
      } 
    } else {
      System.out.println("履歴テーブル更新しない");
      System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
      return false;
    } 
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return true;
  }
}
