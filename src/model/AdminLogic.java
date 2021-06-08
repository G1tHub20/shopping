package model;

import dao.ItemDAO;

public class AdminLogic {
	public boolean execute(ItemBean itemChange) {
		System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// ■itemテーブルに商品の注文を反映
		boolean isChange = dao.changeItemInfo(itemChange);

		if (isChange == false) {
			return false;
		}
		return true;
	}

	public boolean execute2(ItemBean itemNew) {
		System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// ■itemテーブルに商品の注文を反映
		boolean isNew = dao.newItemInfo(itemNew);

		if (isNew == false) {
			return false;
		}
		return true;
	}

}
