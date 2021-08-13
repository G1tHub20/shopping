package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/AdminServlet", "/AdminServlet2", "/BuyItemServlet", "/CartServlet", "/ShoppingServlet"})
public class LoginCheckFilter implements Filter {
  public void init(FilterConfig fConfig) throws ServletException {}

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    System.out.println("★ログインチェック");
    HttpSession session = ((HttpServletRequest)request).getSession(false);
    if (session != null) {
//      System.out.println("★何かしらセッションがある");
      Object loginCheck = session.getAttribute("loginUser");
      if (loginCheck == null) {
//        System.out.println("★loginCheckセッションがNull");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");
        dispatcher.forward(request, response);
      } else {
//        System.out.println("loginCheckセッションがある");
        chain.doFilter(request, response);
      }
    } else {
//      System.out.println("★セッションがNull");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");
      dispatcher.forward(request, response);
    }
  }

  public void destroy() {}
}
