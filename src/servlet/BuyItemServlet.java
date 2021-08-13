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

import dao.ItemDAO;
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

		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
    	UserBean loginUser = (UserBean)session.getAttribute("loginUser");
    	String userName = loginUser.getUserName();
    	int orderResult = 99; // 最終的な注文処理成功の判定（初期値99は失敗）
    	int counter = 0;
    	ItemBean itemBuy = null;
    	boolean isReslut = false;
	    ItemBean[] itemBuys;
	    int size;


		Map<String, List<Object>> cart = (Map<String, List<Object>>) session.getAttribute(userName);
		// 格納された順に取り出したい
    	System.out.println("セッションオブジェクト（cart）の中身を全て出力");

    	size = cart.size();
    	itemBuys = new ItemBean[size];
    	int index = 0;

		for (Object key : cart.keySet()) {
			System.out.println(key + " => " + cart.get(key));

			String item_id = (String) key;
			int purchaseNum = (int) cart.get(key).get(2);
			int userId = (int) cart.get(key).get(4);

			//■itemSearchインスタンの生成
			itemBuy = new ItemBean(item_id, purchaseNum, userId);

			boolean is_addHistory = false;

			ItemDAO dao = new ItemDAO();

		    // 在庫判定
		    int is_check = dao.checkStock(itemBuy);
		    if (is_check > 0) {
		    	System.out.println("注文処理失敗（在庫不足）");
				counter++;
		    }

		    itemBuys[index] = itemBuy;
		    index++;
		}


for (int i=0; i<itemBuys.length; i++) {
	System.out.println(i + "= " + itemBuys[i]);
}




		if (counter > 0) {
			System.out.println("①在庫数チェック：NG！");
			System.out.println("在庫なし。注文処理しっぱい");
			request.setAttribute("cartMsg", "在庫数が足りないため、注文できません。");
			orderResult = 1;

		} else {
			System.out.println("①在庫数チェック：OK！");

			System.out.println("在庫あり。注文処理へ");


			for (int i=0; i<itemBuys.length; i++) {
				itemBuy = itemBuys[i];

				BuyItemLogic buyItemLogic = new BuyItemLogic();
				isReslut = buyItemLogic.execute(itemBuy);
				if (isReslut) {
					orderResult = 0;
				} else {
					orderResult = 9;
				}

			}
		}

//		    System.out.println("注文処理失敗（在庫不足）判定後、処理は継続している？");



			    if (orderResult == 0) {
				    System.out.println("注文処理成功！");
			    	System.out.println("セッションオブジェクト（cart）の削除");
			    	session.removeAttribute(userName);
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");

					// 変更後の商品リストを取得
					GetItemListLogic getItemListLogic = new GetItemListLogic();
					List<ItemBean> itemList = getItemListLogic.execute();
					session.setAttribute("itemList", itemList);
					System.out.println("▼▼「注文完了」ページ");

			    } else if (orderResult == 1) {
			    	System.out.println("注文処理失敗（在庫不足）");
					request.setAttribute("cartMsg", "在庫数が足りないため、注文できません。");
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
					System.out.println("▼▼「カート」ページ");


				} else {
					System.out.println("注文処理失敗（エラー）");
					request.setAttribute("cartMsg", "申し訳ありません。注文できませんでした。");
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
					System.out.println("▼▼「カート」ページ");

				}

				dispatcher.forward(request, response);
		    }

		}
