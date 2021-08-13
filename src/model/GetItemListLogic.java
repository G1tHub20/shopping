package model;

<<<<<<< HEAD
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
=======
import java.util.List;

import dao.ItemDAO;

public class GetItemListLogic {
	public List<ItemBean> execute() {
		System.out.println("\r''''''''''''''''''''GetItemListLogic''''''''''''''''''''");

        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// DAOのメソッド
		List<ItemBean> itemList = dao.getItem();

		System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
		return itemList;
	}
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
}
