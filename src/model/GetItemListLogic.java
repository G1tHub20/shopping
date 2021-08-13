package model;

import dao.ItemDAO;
import java.util.List;

public class GetItemListLogic {
  public List<ItemBean> execute() {
    System.out.println("\r''''''''''''''''''''GetItemListLogic''''''''''''''''''''");
    ItemDAO dao = new ItemDAO();
    List<ItemBean> itemList = dao.getItem();
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return itemList;
  }
}
