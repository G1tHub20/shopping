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
import model.ItemBean;
import model.UserBean;
import test.BuyItemLogic;

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

//		String action = request.getParameter("action");
//		if (action != null && action.equals("search")) {
//        // 「検索」ボタンが押された場合は検索処理（商品絞り込み）
//
//		//■リクエストパラメータの取得
//		request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定
//		String category = request.getParameter("category");
//		String itemName = request.getParameter("itemName");
//		System.out.println("「検索」ボタンが押された");
//		System.out.println("category=" + category + "、itemNname=" + itemName);

		String item_id = "tie0002";
		int purchaseNum = 1;

		//■itemSearchインスタンの生成
		ItemBean itemBuy = new ItemBean(item_id, purchaseNum);

//		HttpSession session = request.getSession();
//		session.setAttribute("itemSearch", itemSearch);

		// 注文を反映
		BuyItemLogic buyItemLogic = new BuyItemLogic();
		boolean isBuy = buyItemLogic.execute(itemBuy);

		RequestDispatcher dispatcher;

		if (isBuy == false) {
			System.out.println("申し訳ありません。注文できません。");
			request.setAttribute("errorMsg", "申し訳ありません。注文できませんでした。");

			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");

		} else {

    	HttpSession session = request.getSession();
    	System.out.println("セッションオブジェクト（cartItems）の削除");
    	session.removeAttribute("cartItems");

//		session.setAttribute("itemList", itemList);
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		}

		dispatcher.forward(request, response);

	}
}
