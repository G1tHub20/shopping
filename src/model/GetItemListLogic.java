package model;

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
}
