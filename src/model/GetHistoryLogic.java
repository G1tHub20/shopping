package model;

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
}
