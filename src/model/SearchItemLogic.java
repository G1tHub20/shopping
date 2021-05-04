package model;

import java.util.List;

import dao.ItemDAO;

public class SearchItemLogic {
	public List<ItemBean> execute(ItemBean itemSearch) {
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// DAOのメソッド
		List<ItemBean> itemList = dao.searchItem(itemSearch);

		return itemList;
	}
}
