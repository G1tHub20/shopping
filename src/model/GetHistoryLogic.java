package model;

import java.util.List;

import dao.HistoryDAO;
/**
 * 注文履歴の取得を行うロジックモデル
 */
public class GetHistoryLogic {
	// 注文履歴の取得
	public List<HistoryBean> execute(UserBean loginUser) {
		int user_id = loginUser.getId();
		HistoryDAO dao = new HistoryDAO();
		List<HistoryBean> historyList = dao.getHistory(user_id);
		return historyList;
	}
}