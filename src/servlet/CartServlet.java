package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//■リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定
		System.out.println("--------------------CartServlet(POST)--------------------");
		String action = request.getParameter("action");
		System.out.println("actionの中身= " + action);

		HttpSession session = request.getSession();

		UserBean loginUser = (UserBean) session.getAttribute("loginUser");
		String userName = loginUser.getUserName(); // ユーザー名をカートのセッション名として使用

		// LinkedHashMapは挿入された順番を保持する → 逆順にしたい
		Map<String, List<Object>> cart = new LinkedHashMap<String, List<Object>>();

		// ■「削除」ボタンが押された場合
		if (action != null && action.equals("delete")) {
			cart = (Map<String, List<Object>>) session.getAttribute(userName);
			String item_id = request.getParameter("item_id");
			String item_name = request.getParameter("item_name");
	//		int total = 0;
			int total = (int)session.getAttribute("total");

			System.out.println(item_id + "の削除ボタンが押された");

			if (cart != null) { // 例外エラー対策 // ←？？
				int subtotal = (int)cart.get(item_id).get(3);
				total -= subtotal;
				session.setAttribute("total", total);
				cart.remove(item_id);
				request.setAttribute("cartMsg", item_name + " はカートから削除されました");
				System.out.println(item_id + "をカートから削除した");
			}

		// ■「カートに入れるボタン」が押された場合
		} else {
			System.out.println("「カートに入れる」ボタンが押された");
			request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定

			cart = (Map<String, List<Object>>) session.getAttribute(userName);

			String item_id = request.getParameter("item_id");
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));		// getParamterで数値を受け取るときは型変換が必要
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int subtotal = price * quantity;

			System.out.println("item_id=" + item_id + "、price=" + price + "、quantity=" + quantity);

			// カート画面に表示させるメッセージ
			request.setAttribute("cartMsg", name + " " + quantity + "点 が追加されました");


	//		■HashMap：1つのキー、複数の値(price, quantity、subtotal)
	//		MapのListを作成する

	//		Map<String, List<Integer>> cart = null;
			List<Object> item0 = null; // ←微妙…
			List<Object> item1 = null; // ←微妙…


			int userId = loginUser.getUserId();

		// cartが存在しない（初めてカートに入れる）とき
		if (cart == null || cart.size() == 0) { // サイズチェックは必要？
			System.out.println("初めてカートに入れる");
//			cart = new HashMap<String, List<Object>>();
			cart = new LinkedHashMap<String, List<Object>>();

			item0 = new ArrayList<Object>();
			System.out.println(item_id + "の情報を" + "item1インスタンスに格納");
			item0.add(name);//Listの末尾に値を追加
			item0.add(price);
			item0.add(quantity);
			item0.add(subtotal);
			item0.add(userId);// 改善できないか？各商品にいちいち同じユーザーidを渡している…

			System.out.println(item0 + "（" + item_id + "）を" + "cartに追加");
			cart.put(item_id, item0); //Map型にデータを追加

		} else {
			System.out.println("さらにカートに追加する");

			session = request.getSession();
			item1 = cart.get(item_id);

			if (cart.get(item_id) != null) {
				// カートに入れた商品の個数を変更するとき
				item1.set(2, ((int)item1.get(2) + quantity));
				item1.set(3, ((int)item1.get(3) + subtotal));
				cart.put(item_id, item1);
			} else {
				// 別の商品を追加するとき
//				List<Integer> item2 = new ArrayList<Integer>();
				List<Object> item2 = new ArrayList<Object>();
				item2.add(name);
				item2.add(price);
				item2.add(quantity);
				subtotal = price * quantity;
				item2.add(subtotal);
				item2.add(userId);
				cart.put(item_id, item2);

			}
		}

		}

		// 合計金額を算出
		int total = 0;
		for (String key : cart.keySet()) {
			total += (int)cart.get(key).get(3);
		}

		// cartをセッションに保存
		System.out.println("cartをセッションID（" + userName + "）に保存");
		System.out.println(cart);
		session.setAttribute(userName, cart); //個別にカートを持たせるため、ユーザー名をセッション名に使用

		// 合計金額totalをセッションパラメータに保存
		session.setAttribute("total", total);
		System.out.println("合計金額（total）= " + total);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		System.out.println("▼▼「カート」ページ");
		dispatcher.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定
		System.out.println("--------------------CartServlet(GET)--------------------");

		String action = request.getParameter("action");
		System.out.println("actionの中身= " + action);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);
	}
}
