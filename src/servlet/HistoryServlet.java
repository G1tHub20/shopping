package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetHistoryLogic;
import model.HistoryBean;
import model.UserBean;
/**
 * 注文履歴に関するリクエストを処理するコントローラ
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    // 注文履歴が存在しない（初回）の場合
		if(session.getAttribute("historyList") == null) {
			UserBean loginUser = (UserBean) session.getAttribute("loginUser");
			GetHistoryLogic getHistoryLogic = new GetHistoryLogic();
			List<HistoryBean> historyList = getHistoryLogic.execute(loginUser);
			// 注文履歴をセッション保存
			session.setAttribute("historyList", historyList);
		}

		// 注文履歴に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/history.jsp");
		dispatcher.forward(request, response);
	}
}