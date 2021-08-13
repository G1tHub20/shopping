package model;

<<<<<<< HEAD
import dao.HistoryDAO;
import java.util.List;

public class GetHistoryLogic {
  public List<HistoryBean> execute(UserBean loginUser) {
    System.out.println("\r''''''''''''''''''''GetHistoryLogic''''''''''''''''''''");
    HistoryDAO dao = new HistoryDAO();
    List<HistoryBean> historyList = dao.getHistory(loginUser);
    System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
    return historyList;
  }
=======
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
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
}
