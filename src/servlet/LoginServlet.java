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
import model.LoginLogic;
import model.UserBean;
/**
 * ログイン・ログアウトに関するリクエストを処理するコントローラ
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 入力されたユーザ名とパスワードをDBのデータと照合しログイン処理を行う
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");

		LoginLogic loginLogic = new LoginLogic();
		UserBean loginUser = loginLogic.execute(userName, pass);

		// ■ログイン判定
		if (loginUser == null) {
			// ログイン失敗
			RequestDispatcher dispatcher = request.getRequestDispatcher("./loginFailure.jsp");
			dispatcher.forward(request, response);

		} else {
			// ログイン成功
			HttpSession session = request.getSession();
		    session.setAttribute("loginUser", loginUser);

		    // カート情報（cartMap)の属性名を準備
		    //   ユーザー単位で管理するため、ユニークにする（"cartId_" + ユーザーID） 例:cartId_2
			String cartId = "cartId_" + loginUser.getId();
			session.setAttribute("cartId", cartId);

			// 商品リストを取得
		    GetItemListLogic getItemListLogic = new GetItemListLogic();
		    int userId = loginUser.getId();
		    List<ItemBean> itemList = getItemListLogic.execute(userId);
		    //リクエストスコープで渡す
		    session.setAttribute("itemList", itemList);

		    RequestDispatcher dispatcher;

		    if (loginUser.getId() == 1) {
		    	// 管理者の場合
		    	dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
		    } else {
		    	// 一般ユーザーの場合
		    	dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
		    }
			dispatcher.forward(request, response);
		}
	}

	/**
	 * ログアウト処理または商品一覧ページへの遷移を行う
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;

		if (action != null && action.equals("logout")) {
			// ■ログアウト処理
			// 全てのセッションを破棄（カート情報を除く //例:cartId_2）
			session = request.getSession();
			session.removeAttribute("loginUser");
			session.removeAttribute("itemList");
			session.removeAttribute("historyList");
			session.removeAttribute("cartId");
			session.removeAttribute("cartMap"); // cart.jspのEL式で使用 ※中身はcartIdセッションと同一
//			session.invalidate(); //全セッションを破棄
			response.sendRedirect("./");

		} else if (action != null && action.equals("itemList")) {
			// ■リンクから商品リストに遷移
			// 商品リストのセッションを最新化
		    GetItemListLogic getItemListLogic = new GetItemListLogic();
		    List<ItemBean> itemList = getItemListLogic.execute();
		    session.setAttribute("itemList", itemList);

			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
			dispatcher.forward(request, response);

		} else {
			// ■ログインチェックでエラー
			response.sendRedirect("./loginFailure.jsp"); //URL表示を考慮しリダイレクト、ファイルはWebContent直下なので注意
		}
	}
}