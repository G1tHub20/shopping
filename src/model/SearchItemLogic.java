package model;

import dao.ItemDAO;
import java.util.List;

public class SearchItemLogic {
  public List<ItemBean> execute(ItemBean itemSearch) {
    System.out.println("\r''''''''''''''''''''SearchItemLogic''''''''''''''''''''");
    ItemDAO dao = new ItemDAO();
    List<ItemBean> itemList = dao.searchItem(itemSearch);
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return itemList;
  }
}
