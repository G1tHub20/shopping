package servlet;

import java.io.IOException;
<<<<<<< HEAD
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.LoginLogic;
import model.UserBean;

@WebServlet({"/LoginServlet"})
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("▼▼「ログイン」ページ");
    System.out.println("--------------------LoginServlet(POST)--------------------");
    request.setCharacterEncoding("UTF-8");
    String userName = request.getParameter("userName");
    String pass = request.getParameter("pass");
    System.out.println("ユーザー名"+ userName + "パスワード="+ pass);
    UserBean user = new UserBean(userName, pass);
    LoginLogic loginLogic = new LoginLogic();
    UserBean loginUser = loginLogic.execute(user);
    if (loginUser.getUserName() == null) {
      System.out.println("ログインしっぱい");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginFailure.jsp");
      System.out.println("▼▼「ログイン失敗」ページ");
      dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    } else {
      HttpSession session = request.getSession();
      session.setAttribute("loginUser", loginUser);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/ShoppingServlet");
      System.out.println("セッションにloginUser情報を保存："+ loginUser.getUserName());
      dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    } 
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("--------------------LoginServlet(GET)--------------------");
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
    if (action != null && action.equals("logout")) {
      HttpSession session = request.getSession();
      session.removeAttribute("itemList");
      session.removeAttribute("itemSearch");
      session.removeAttribute("historyList");
      session.removeAttribute("loginUser");
      System.out.println("ログアウト");
      System.out.println("セッション（itemlist・itemSearch・historyList・loginUser）を削除。カートセッションのみ残す");
      session = request.getSession(false);
      if (session == null) {
        System.out.println("セッションがNULL");
      } else {
        System.out.println("セッションがある");
      } 
      System.out.println("▼▼「ログイン」ページ");
      response.sendRedirect("./");
    } else {
      System.out.println("▼▼「ログイン失敗」ページ");
      System.out.println("ログインチェックエラーのため遷移");
      response.sendRedirect("./loginFailure.jsp");
    } 
  }
}
=======

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
		System.out.println("▼▼「ログイン」ページ");
		System.out.println("--------------------LoginServlet(POST)--------------------");
		//■リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定

		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		System.out.println("ユーザー名=" + userName + "、パスワード=" + pass);

		//■Userインスタンの生成
		UserBean user = new UserBean(userName, pass);
//		System.out.println("フォームの入力情報から生成したUserインスタンス=" + user.getUserName() + "," + user.getPass());

		//■ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		UserBean loginUser = loginLogic.execute(user);

		RequestDispatcher dispatcher;

		if(loginUser.getUserName() == null) {
			System.out.println("ログイン失敗");
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginFailure.jsp");
			System.out.println("▼▼「ログイン失敗」ページ");
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			//■ShoppingServletにフォワード
			dispatcher = request.getRequestDispatcher("/ShoppingServlet");
			System.out.println("セッションにloginUser情報を保存：" + loginUser.getUserName());
			dispatcher.forward(request, response);
		}

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------------------LoginServlet(GET)--------------------");
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            // 「ログアウト」ボタンが押された場合はログアウト処理（セッションの破棄）
        	HttpSession session = request.getSession();
//        	session.invalidate(); // セッションの破棄（全インスタンスが消滅）
        	// カートのセッションのみ残す必要がある
        	session.removeAttribute("itemList");
        	session.removeAttribute("itemSearch");
        	session.removeAttribute("historyList");
        	session.removeAttribute("loginUser");
        	System.out.println("ログアウト");
        	System.out.println("セッション（itemList・itemSearch・historyList・loginUser）を削除。カートのセッションのみ残す");

        	session = request.getSession(false);
            if(session == null){
            	System.out.println("セッションがNULL");
            }else{
            	System.out.println("セッションがある");
            }

//			request.setAttribute("errorMsg", "ログアウトしました"); //リダイレクトではリクエストスコープの引継ぎ不可
    		System.out.println("▼▼「ログイン」ページ");
            response.sendRedirect("./");
        }
	}
}
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
