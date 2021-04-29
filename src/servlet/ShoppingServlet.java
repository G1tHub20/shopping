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

import model.GetItemListLogic;
import model.ItemBean;

@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
		if (action != null) {

			if (action.equals("history")) {
				System.out.println("action='" + action + "'");
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/history.jsp");

			} else if (action.equals("itemList")) {
//			} else {

				System.out.println("action='" + action + "'");

				// 商品一覧を取得
				GetItemListLogic getItemListLogic = new GetItemListLogic();
				List<ItemBean> itemList = getItemListLogic.execute();

				System.out.println(itemList);

				HttpSession session = request.getSession();
				session.setAttribute("itemList", itemList);

				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
			}
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
		dispatcher.forward(request, response);
	}

}
