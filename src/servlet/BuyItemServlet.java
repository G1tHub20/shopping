package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BuyItemLogic;
import model.GetHistoryLogic;
import model.HistoryBean;
import model.ItemBean;
import model.UserBean;

@WebServlet("/BuyItemServlet")
public class BuyItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		RequestDispatcher dispatcher = null;

		// ■注文履歴
		System.out.println("jspからShoppingServletに遷移");
		if (action != null && action.equals("history")) {
			System.out.println("action='" + action + "'");

			HttpSession session = request.getSession();
			UserBean loginUser = (UserBean)session.getAttribute("loginUser");

			// 商品一覧を取得
			GetHistoryLogic getHistoryLogic = new GetHistoryLogic();
			List<HistoryBean> historyList = getHistoryLogic.execute(loginUser);

			session.setAttribute("historyList", historyList);

			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/history.jsp");

		} else if (action != null && action.equals("itemList")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
		}
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Map<String, List<Object>> cartItems = (Map<String, List<Object>>) session.getAttribute("cartItems");
		// 格納された順に取り出したい
//		cartItems.get("tie0001")

    	System.out.println("セッションオブジェクト（cartItems）の中身を全て出力");
		for (Object key : cartItems.keySet()) {
		    System.out.println(key + " => " + cartItems.get(key));
		}
    	boolean isBuy = false;

		for (Object key : cartItems.keySet()) {
			String item_id = (String) key;
			int purchaseNum = (int) cartItems.get(key).get(2);
			int userId = (int) cartItems.get(key).get(4);

			//■itemSearchインスタンの生成
			ItemBean itemBuy = new ItemBean(item_id, purchaseNum, userId);

			BuyItemLogic buyItemLogic = new BuyItemLogic();
			isBuy = buyItemLogic.execute(itemBuy);

		}

//		String item_id = "tie0002";
//		int purchaseNum = 1;

		//■itemSearchインスタンの生成
//		ItemBean itemBuy = new ItemBean(item_id, purchaseNum);

//		HttpSession session = request.getSession();
//		session.setAttribute("itemSearch", itemSearch);

		// 注文を反映
//		BuyItemLogic buyItemLogic = new BuyItemLogic();
//		boolean isBuy = buyItemLogic.execute(itemBuy);

		RequestDispatcher dispatcher;

		if (isBuy == false) {
			System.out.println("申し訳ありません。注文できません。");
			request.setAttribute("errorMsg", "申し訳ありません。注文できませんでした。");

			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");

		} else {

    	System.out.println("セッションオブジェクト（cartItems）の削除");
    	session.removeAttribute("cartItems");

//		session.setAttribute("itemList", itemList);
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		}

		dispatcher.forward(request, response);

	}
}
