package model;

import java.util.List;

import dao.ItemDAO;

public class GetItemListLogic {
	public List<ItemBean> execute() {
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// DAOのメソッド
		List<ItemBean> itemList = dao.getItem();

		return itemList;
	}
}
