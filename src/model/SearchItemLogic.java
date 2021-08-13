package model;

<<<<<<< HEAD
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
=======
import java.util.List;

import dao.ItemDAO;

public class SearchItemLogic {
	public List<ItemBean> execute(ItemBean itemSearch) {
		System.out.println("\r''''''''''''''''''''SearchItemLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// DAOのメソッド
		List<ItemBean> itemList = dao.searchItem(itemSearch);

		System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
		return itemList;
	}
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
}
