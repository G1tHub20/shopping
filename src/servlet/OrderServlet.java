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

import model.ChangeItemLogic;
import model.GetHistoryLogic;
import model.HistoryBean;
import model.UserBean;
/**
 * 注文処理に関するリクエストを処理するコントローラ
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ■カートの「注文確定」を押したときの処理
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String cartId = (String) session.getAttribute("cartId");
		Map<Integer, List<Object>> cartMap = (Map<Integer, List<Object>>) session.getAttribute(cartId);

		ChangeItemLogic changeItemLogic = new ChangeItemLogic();
		RequestDispatcher dispatcher = null;

		// ■在庫のチェック
		String short_stock = changeItemLogic.checkStock(cartMap); //在庫不足の商品名

		// 在庫不足の場合 →処理を中止しカートに遷移
		if (short_stock != null) {
			request.setAttribute("errorMsg", short_stock + "の在庫が足りないため、注文処理を完了できませんでした。");
		    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
			dispatcher.forward(request, response);
			return; //エラー（レスポンスをコミットした後でフォワードできません）対策
		}

		// ■在庫数の減算
		boolean result = false;
		result = changeItemLogic.reduceStock(cartMap);

		// DBの在庫数更新に失敗した場合 →処理を中止しカートに遷移
		if (!result) {
			request.setAttribute("errorMsg", "申し訳ありません。エラーが発生したため、注文処理を完了できませんでした。");
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// ■注文履歴の追加
		UserBean loginUser = (UserBean) session.getAttribute("loginUser");
		int user_id = loginUser.getId();

		result = false;
		result = changeItemLogic.addHistory(user_id, cartMap); // 必要なパラ（ユーザーID、商品ID、単価、注文数）

		// 注文履歴の追加OK
		if (result) {
			GetHistoryLogic getHistoryLogic = new GetHistoryLogic();
			List<HistoryBean> historyList = getHistoryLogic.execute(loginUser);
			// 最新の注文履歴をセッションに保存
			session.setAttribute("historyList", historyList);
		}

		// ■注文処理完了後、カートを空にする
    	session.removeAttribute(cartId);

		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}

}
