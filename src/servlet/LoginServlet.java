package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.UserBean;

@WebServlet("/LoginServlet") //URLパターンの設定
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//■リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		System.out.println("▼▼「ログイン」ページ");
		System.out.println("ユーザー名=" + userName + "、パスワード=" + pass);

		//■Userインスタンの生成
		UserBean user = new UserBean(userName, pass);
//		System.out.println("フォームの入力情報から生成したUserインスタンス=" + user.getUserName() + "," + user.getPass());

		//■ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		UserBean loginUser = loginLogic.execute(user);

		RequestDispatcher dispatcher;

		if(loginUser.getUserName() == null) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginFailure.jsp");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);

			if(loginUser.getUserName().equals("admin") && loginUser.getPass().equals("adminpassword")) {
				System.out.println("管理者としてログイン");
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			} else {
				System.out.println("一般ユーザーとしてログイン");
				System.out.println("▼▼「商品リスト」ページ");

				//■商品リスト画面にフォワード
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");

			}
	}
		System.out.println(loginUser.getUserName());

		dispatcher.forward(request, response);
}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
        if (action.equals("logout")) {
            // クリックされたボタンが「logout」の場合はログアウト処理（セッションの破棄）を実施


            response.sendRedirect("./");
        }
	}
}