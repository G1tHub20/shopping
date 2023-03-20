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
/**
 * 商品カートに関するリクエストを処理するコントローラ
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    String action = request.getParameter("action"); // "delete" or null
	    int item_id = Integer.parseInt(request.getParameter("item_id"));

		// ■カート情報を保持するcartMap（Mapの中にListがネストした構造）
	    // ※挿入順を保持するため、LinkedHashMapを使う
		Map<Integer, List<Object>> cartMap = new LinkedHashMap<>();
		List<Object> item = new ArrayList<>();

		HttpSession session = request.getSession();
//		String cartId = session.getAttribute("cartId").toString(); // toStringで変換が必要？
		String cartId = (String)session.getAttribute("cartId");

	    if (action != null && action.equals("delete")) {
	    	// ■「削除」ボタンが押された場合
	    	String name = request.getParameter("name"); // 商品名
	    	cartMap = (Map<Integer, List<Object>>) session.getAttribute(cartId);
	    	cartMap.remove(item_id);
	    	request.setAttribute("errorMsg", name + "をカートから削除しました。");

	    } else {
	    	// ■「カートに入れるボタン」が押された場合
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			if (session.getAttribute(cartId) == null) {
				// cartセッションが存在しない（初めてカートに入れる）とき
				item.add(name);
				item.add(price);
				item.add(quantity);
				item.add(price * quantity);

			} else {
				// cartセッションが存在するとき
				cartMap = (Map<Integer, List<Object>>) session.getAttribute(cartId);

				// 同じ商品がカートにあるか確認
				if (cartMap.containsKey(item_id)) {
					// ある →個数・合計金額を更新
					item = (List<Object>) cartMap.get(item_id);
  					// 個数に加算
					int sum_quantity = (int)item.get(2) + quantity;
					item.set(2, sum_quantity); //個数（quantity）
					item.set(3, price * sum_quantity); //合計金額（sum_price）
				} else {
					// ない →商品を新規追加
					item.add(name);
					item.add(price);
					item.add(quantity);
					item.add(price * quantity);
				}
			}
			cartMap.put(item_id, item);
			request.setAttribute("errorMsg", name + "を" + quantity + "点カートに追加しました。");
	    }
		// カートをセッションに保存 ※有効期限は1800秒(デフォルト)か？
		session.setAttribute(cartId, cartMap);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ■リンクからカートに遷移
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);
	}

}