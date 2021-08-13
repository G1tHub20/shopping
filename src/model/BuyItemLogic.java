package model;

import dao.HistoryDAO;
import dao.ItemDAO;

public class BuyItemLogic {
  public boolean execute(ItemBean itemBuy) {
    System.out.println("\r''''''''''''''''''''BuyItemLogic''''''''''''''''''''");
    boolean is_addHistory = false;

    ItemDAO dao = new ItemDAO();
    is_addHistory = dao.buyItem(itemBuy);


    if (is_addHistory) {
		System.out.println("②商品テーブル更新完了！");
		HistoryDAO dao2 = new HistoryDAO();
		Boolean isBuy = Boolean.valueOf(dao2.addHistory(itemBuy));

      if (isBuy.booleanValue()) {
    	  System.out.println("③履歴テーブル更新完了！");
      } else {
	      System.out.println("③履歴テーブル更新しっぱい");
	      System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
	      return false;

      }

    } else {
    	System.out.println("②商品テーブル更新しっぱい");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        return false;
    }
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return true;
  }
}