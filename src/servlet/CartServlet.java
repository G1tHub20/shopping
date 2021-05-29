package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
		String item_id = request.getParameter("item_id");
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));		// getParamterで数値を受け取るときは型変換が必要
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int subtotal = price * quantity;

		System.out.println("「カートに入れる」ボタンが押された");
		System.out.println("item_id=" + item_id + "、price=" + price + "、quantity=" + quantity);

		// カート画面に表示させるメッセージ
		request.setAttribute("cartMsg", name + " " + quantity + "点 が追加されました");


//		■HashMap：1つのキー、複数の値(price, quantity、subtotal)
//		MapのListを作成する

//		Map<String, List<Integer>> cartItems = null;
		Map<String, List<Object>> cartItems = null;
//		List<Integer> item1 = null; // ←微妙…
		List<Object> item1 = null; // ←微妙…
		HttpSession session = request.getSession();
//		UserBean loginUser = (UserBean) request.getAttribute("loginUser"); // × request → ○ session
		UserBean loginUser = (UserBean) session.getAttribute("loginUser");


		int userId = loginUser.getUserId();

		cartItems = (Map<String, List<Object>>) session.getAttribute("cartItems");

		// cartItemsが存在しない（初めてカートに入れる）とき
		if (cartItems == null || cartItems.size() == 0) { // サイズチェックは必要？
			cartItems = new HashMap<String, List<Object>>();

			item1 = new ArrayList<Object>();
			System.out.println(item_id + "の情報を" + "item1インスタンスに格納");
			item1.add(name);//Listの末尾に値を追加
			item1.add(price);
			item1.add(quantity);
			item1.add(subtotal);
			item1.add(userId);// 改善できないか？各商品にいちいち同じユーザーidを渡している…

			System.out.println(item1 + "（" + item_id + "）を" + "cartItemsに追加");
			cartItems.put(item_id, item1); //Map型にデータを追加

		} else {
			cartItems = new HashMap<String, List<Object>>();
			item1 = null;

			session = request.getSession();
	//		cartItems = (Map<String, List<Integer>>) session.getAttribute("cartItems");
			cartItems = (Map<String, List<Object>>) session.getAttribute("cartItems");
			item1 = cartItems.get(item_id);

			if (cartItems.get(item_id) != null) {
				// カートに入れた商品の個数を変更するとき
				item1.set(2, ((int)item1.get(2) + quantity));
				item1.set(3, ((int)item1.get(3) + subtotal));
				cartItems.put(item_id, item1);
			} else {
//				List<Integer> item2 = new ArrayList<Integer>();
				List<Object> item2 = new ArrayList<Object>();
				item2.add(name);
				item2.add(price);
				item2.add(quantity);
				item2.add(price * quantity);
				item2.add(userId);
				cartItems.put(item_id, item2);

			//■itemインスタンの生成
	//		ItemBean item = new ItemBean(item_id, name, price, quantity, subtotal);
			}
			// 各キーの値
			System.out.println("「tie0001」キーの値 = " + cartItems.get("tie0001"));
			System.out.println("「tie0002」キーの値 = " + cartItems.get("tie0002"));
			System.out.println("「wal0001」キーの値 = " + cartItems.get("wal0001"));
		}

		// cartItemsをセッションに保存
		System.out.println("cartItemsをセッションに保存");
		session.setAttribute("cartItems", cartItems);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 「削除」ボタンが押された場合
		String item_id = request.getParameter("item_id");
		String item_name = request.getParameter("item_name");

		// カート画面に表示させるメッセージ
		if (item_id != null) {
			request.setAttribute("cartMsg", item_name + " はカートから削除されました");
		}

		HttpSession session = request.getSession();
		Map<String, List<Object>> cartItems = (Map<String, List<Object>>) session.getAttribute("cartItems");

		if (cartItems != null) { // 例外エラー対策 // ←？？
			cartItems.remove(item_id);
			System.out.println(item_id + "をカートから削除した");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);
	}
}
