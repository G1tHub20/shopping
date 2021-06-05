package model;

import java.util.List;

import dao.HistoryDAO;

public class GetHistoryLogic {
	public List<HistoryBean> execute(UserBean loginUser) {
		System.out.println("\r''''''''''''''''''''GetHistoryLogic''''''''''''''''''''");

        // DAOクラスをインスタンス化
		HistoryDAO dao = new HistoryDAO();
		// DAOのメソッド
		List<HistoryBean> historyList = dao.getHistory(loginUser);

		System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
		return historyList;
	}
}
