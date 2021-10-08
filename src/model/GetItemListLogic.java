package model;

import java.util.List;

import dao.Category_itemDAO;
import dao.ItemDAO;

public class GetItemListLogic {
  public List<ItemBean> execute() {
    System.out.println("\r''''''''''''''''''''GetItemListLogic''''''''''''''''''''");
    ItemDAO dao = new ItemDAO();
    List<ItemBean> itemList = dao.getItem();
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return itemList;
  }

  public List<CategoryItemBean> execute2() {
	    System.out.println("\r''''''''''''''''''''GetItemListLogic''''''''''''''''''''");
	    Category_itemDAO dao = new Category_itemDAO();
	    List<CategoryItemBean> categoryList = dao.getCategoryInfo();
	    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
	    return categoryList;
	}
}
