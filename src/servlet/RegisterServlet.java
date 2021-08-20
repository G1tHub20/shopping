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

import model.RegisterLogic;
import model.UserBean;

@WebServlet({"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("--------------------RegisterServlet(GET)--------------------");
    request.removeAttribute("errorMsg");
    System.out.println("▼▼「アカウント登録」ページ");
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
    dispatcher.forward((ServletRequest)request, (ServletResponse)response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("--------------------RegisterServlet(POST)--------------------");
    request.setCharacterEncoding("UTF-8");
    String userName = request.getParameter("userName");
    String pass = request.getParameter("pass");
    System.out.println("ユーザー名=" + userName + "パスワード=" + pass);
    UserBean user = new UserBean(userName, pass);
    RegisterLogic registerLogic = new RegisterLogic();
    Boolean isSuccess = Boolean.valueOf(registerLogic.execute(user));
    if (isSuccess.booleanValue()) {
      System.out.println("入力情報でアカウントを新規登録");
      request.setAttribute("registerMsg", "アカウント " + userName + " を新規登録しました。");
     pass = null;
     user = new UserBean(userName, pass);
     HttpSession session = request.getSession();
     session.setAttribute("loginUser", user); // ユーザー名のみ保持
    } else {
      System.out.println("別のユーザー名を入力してください");
      request.setAttribute("registerMsg", "登録できません。別のユーザー名を入力してください");
    }
    System.out.println("▼▼「アカウント登録」ページ");
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
    dispatcher.forward((ServletRequest)request, (ServletResponse)response);
  }
}
