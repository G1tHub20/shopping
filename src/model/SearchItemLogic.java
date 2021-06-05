package model;

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
}
