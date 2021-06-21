package model;

import dao.HistoryDAO;
import dao.ItemDAO;

public class BuyItemLogic {
	public boolean execute(ItemBean itemBuy) {
		System.out.println("\r''''''''''''''''''''BuyItemLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// ■itemテーブルに商品の注文を反映
		boolean is_addHistory = dao.buyItem(itemBuy);

		//■historyテーブルに注文履歴を反映
		if (is_addHistory) {
			HistoryDAO dao2 = new HistoryDAO();
			// これを取得しないと
			// javaからではsessionを取得できないので、オブジェクトでもらう必要がある
			Boolean isBuy = dao2.addHistory(itemBuy);

			if (isBuy) {
			System.out.println("履歴テーブル更新完了！");
			} else {
				System.out.println("履歴テーブル更新失敗…");
			}

		} else {
			System.out.println("履歴テーブル更新しない");
			System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
			return false;
		}
		System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
		return true;
	}
}
