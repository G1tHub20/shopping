package servlet;

import java.io.IOException;

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
	      System.out.println("セッションにloginUserを保存："+ loginUser.getUserName());
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
	      System.out.println("セッション（itemlist・itemSearch・historyList・loginUser）を削除。cartセッションのみ残す");
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
