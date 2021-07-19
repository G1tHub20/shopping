package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RegisterLogic;
import model.UserBean;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------------------RegisterServlet(GET)--------------------");
		request.removeAttribute("errorMsg"); //初期化?

		System.out.println("▼▼「アカウント登録」ページ");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------------------RegisterServlet(POST)--------------------");
		//■リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");

		System.out.println("ユーザー名=" + userName + "、パスワード=" + pass);

		//■Userインスタンの生成
		UserBean user = new UserBean(userName, pass);
		//■ログイン処理
		RegisterLogic registerLogic = new RegisterLogic();
		Boolean isSuccess = registerLogic.execute(user);

		//登録後そのままログイン状態にすべきか？

		if (isSuccess == true) {
			System.out.println("入力情報でアカウントを新規登録");
			request.setAttribute("registerMsg", "アカウントを新規登録しました");
		} else {
			System.out.println("別のユーザー名を使用してください");
			request.setAttribute("registerMsg", "登録できません。別のユーザー名を入力してください");
		}

		System.out.println("▼▼「アカウント登録」ページ");
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");

		dispatcher.forward(request, response);
	}

}