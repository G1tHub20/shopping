package model;

import java.util.List;

import dao.ItemDAO;
/**
 * 商品情報の取得を行うロジックモデル
 */
public class GetItemListLogic {

	// ログイン時に商品リストに遷移するとき
	public List<ItemBean> execute(int userId) {
		ItemDAO dao = new ItemDAO();
		List<ItemBean> itemList = dao.getItem(userId); // userId=1(管理者)か区別するため
		return itemList;
	}

	// リンクから商品リストに遷移するとき（DB更新の反映）
	public List<ItemBean> execute() {
		ItemDAO dao = new ItemDAO();
		List<ItemBean> itemList = dao.getItem(0);
		return itemList;
	}
}