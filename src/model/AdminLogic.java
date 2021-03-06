package model;

import dao.ItemDAO;

public class AdminLogic {
  public String execute1(ItemBean newItem) {
    System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
    // DAOクラスをインスタンス化
    ItemDAO dao = new ItemDAO();
    // ■itemテーブルに商品の注文を反映
    String item_id = dao.newItemInfo(newItem);
    return item_id;
  }

  public int execute2(ItemBean changeItem) {
    System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
    // DAOクラスをインスタンス化
    ItemDAO dao = new ItemDAO();
    // ■itemテーブルに商品の注文を反映
    int isChange = dao.changeItemInfo(changeItem);
    return isChange;
  }

  public boolean execute3(ItemBean changeItem) {
    System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
    // DAOクラスをインスタンス化
    ItemDAO dao = new ItemDAO();
    // ■itemテーブルに商品の注文を反映
    boolean isChange = dao.deleteItemInfo(changeItem);
    if (!isChange)
      return false;
    return true;
  }
}
