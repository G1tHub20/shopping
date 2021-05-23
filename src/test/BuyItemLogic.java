package test;

import dao.HistoryDAO;
import dao.ItemDAO;
import model.ItemBean;

public class BuyItemLogic {
	public boolean execute(ItemBean itemBuy) {
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// DAOのメソッド
		boolean is_updateHistory = dao.buyItem(itemBuy);

		// これを取得しないと
		int user_id = 3;

		if (is_updateHistory) {
			HistoryDAO dao2 = new HistoryDAO();
			// DAOのメソッド

			Boolean isBuy = dao2.updateHistory(user_id);

			if (isBuy) {
			System.out.println("履歴テーブル更新完了！");
			} else {
				System.out.println("履歴テーブル更新失敗…");
			}

		} else {
			System.out.println("履歴テーブル更新しない");
			return false;
		}

		return true;
	}
}
