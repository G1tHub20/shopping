package model;

import java.util.List;

import dao.HistoryDAO;

public class GetHistoryLogic {
	public List<HistoryBean> execute(UserBean loginUser) {
        // DAOクラスをインスタンス化
		HistoryDAO dao = new HistoryDAO();
		// DAOのメソッド
		List<HistoryBean> historyList = dao.getHistory(loginUser);

		return historyList;
	}
}
