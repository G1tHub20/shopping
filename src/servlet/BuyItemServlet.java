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
import model.GetItemListLogic;
import model.HistoryBean;
import model.ItemBean;
import model.UserBean;

@WebServlet("/BuyItemServlet")
public class BuyItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("--------------------ShoppingServlet(GET)--------------------");


		String action = request.getParameter("action");
		RequestDispatcher dispatcher = null;

		// ■注文履歴
		if (action != null && action.equals("history")) {
			System.out.println("action='" + action + "'");

			HttpSession session = request.getSession();
			UserBean loginUser = (UserBean)session.getAttribute("loginUser");

			// 商品一覧を取得
			GetHistoryLogic getHistoryLogic = new GetHistoryLogic();
			List<HistoryBean> historyList = getHistoryLogic.execute(loginUser);

			session.setAttribute("historyList", historyList);

			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/history.jsp");
    		System.out.println("▼▼「注文履歴」ページ");

		} else if (action != null && action.equals("itemList")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
    		System.out.println("▼▼「商品リスト」ページ");
		}
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------------------ShoppingServlet(POST)--------------------");


		HttpSession session = request.getSession();
    	UserBean loginUser = (UserBean)session.getAttribute("loginUser");
    	String userName = loginUser.getUserName();

		Map<String, List<Object>> cart = (Map<String, List<Object>>) session.getAttribute(userName);
		// 格納された順に取り出したい
    	System.out.println("セッションオブジェクト（cart）の中身を全て出力");
		for (Object key : cart.keySet()) {
		    System.out.println(key + " => " + cart.get(key));
		}
    	boolean isBuy = false;

		for (Object key : cart.keySet()) {
			String item_id = (String) key;
			int purchaseNum = (int) cart.get(key).get(2);
			int userId = (int) cart.get(key).get(4);

			//■itemSearchインスタンの生成
			ItemBean itemBuy = new ItemBean(item_id, purchaseNum, userId);

			// itemテーブルで在庫チェック→更新→historyテーブル更新
			BuyItemLogic buyItemLogic = new BuyItemLogic();
			isBuy = buyItemLogic.execute(itemBuy);

		}

		RequestDispatcher dispatcher;

		if (isBuy == false) {
			System.out.println("在庫数が足りないため、注文できませんでした。");
			request.setAttribute("cartMsg", "在庫数が足りないため、注文できませんでした。");

			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
			System.out.println("▼▼「カート」ページ");

		} else {

	    	System.out.println("セッションオブジェクト（cart）の削除");
	    	session.removeAttribute(userName);

			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			System.out.println("▼▼「注文完了」ページ");
		}

		// 変更後の商品リストを取得
		GetItemListLogic getItemListLogic = new GetItemListLogic();
		List<ItemBean> itemList = getItemListLogic.execute();
		session.setAttribute("itemList", itemList);

		dispatcher.forward(request, response);

	}
}
