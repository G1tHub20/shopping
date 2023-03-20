package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegisterLogic;
import model.UserBean;
/**
 * アカウント登録に関するリクエストを処理するコントローラ
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ■リンクからアカウント登録に遷移
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		System.out.println("userName=" + userName + ", pass=" + pass);

		RequestDispatcher dispatcher;

		boolean isValidInput = true;
		UserBean loginUser = null;
		RegisterLogic registerLogic = new RegisterLogic();

		// ■入力チェック
		// ユーザー名のチェックNG
		if (!registerLogic.isValidInput(userName)) {
			request.setAttribute("errorMsg", "ユーザー名が不正です。指定された形式で入力してください。");
			isValidInput = false;
		// パスワードのチェックNG
		} else if (!registerLogic.isValidInput(pass)) {
			request.setAttribute("errorMsg", "パスワードが不正です。指定された形式で入力してください。");
			isValidInput = false;
		}

		// 入力チェックNGなら、アカウント登録しない
		if (!isValidInput) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
			return;
		}

	    // ■アカウント登録
		loginUser = registerLogic.execute(userName, pass);

		if (loginUser == null) {
			// 失敗の場合
			request.setAttribute("errorMsg", "登録できません。別のユーザー名を入力してください。");

		} else {
			// 成功の場合 →アカウント情報をセッション保存
			HttpSession session = request.getSession();
		    session.setAttribute("loginUser", loginUser);

		    request.setAttribute("errorMsg", "アカウント " + userName + " を新規登録しました。");
		}

		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}
}