package model;

import dao.ItemDAO;

public class AdminLogic {
	public boolean execute1(ItemBean newItem) {
		System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// ■itemテーブルに商品の注文を反映
		boolean isNew = dao.newItemInfo(newItem);

		if (isNew == false) {
			return false;
		}
		return true;
	}


	public boolean execute2(ItemBean changeItem) {
		System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// ■itemテーブルに商品の注文を反映
		boolean isChange = dao.changeItemInfo(changeItem);

		if (isChange == false) {
			return false;
		}
		return true;
	}


}
