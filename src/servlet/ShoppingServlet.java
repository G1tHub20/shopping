package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetHistoryLogic;
import model.GetItemListLogic;
import model.HistoryBean;
import model.ItemBean;
import model.SearchItemLogic;
import model.UserBean;

@WebServlet({"/ShoppingServlet"})
public class ShoppingServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("--------------------ShoppingServlet(GET)--------------------");
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
    RequestDispatcher dispatcher = null;
    if (action != null && action.equals("history")) {
      System.out.println("action='" + action + "'");
      HttpSession session = request.getSession();
      UserBean loginUser = (UserBean)session.getAttribute("loginUser");
      GetHistoryLogic getHistoryLogic = new GetHistoryLogic();
      List<HistoryBean> historyList = getHistoryLogic.execute(loginUser);
      session.setAttribute("historyList", historyList);
      dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/history.jsp");
    } else if (action != null && action.equals("itemList")) {
      dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
      System.out.println("▼▼「商品リスト」ページ");
    }
    dispatcher.forward((ServletRequest)request, (ServletResponse)response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("--------------------ShoppingServlet(POST)--------------------");
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
    if (action != null && action.equals("search")) {
      String category = request.getParameter("category");
      String itemName = request.getParameter("itemName");
      System.out.println("「検索」ボタンが押された");
      System.out.println("category=" + category + "itemName=" + itemName);
      ItemBean itemSearch = new ItemBean(category, itemName);
      HttpSession session = request.getSession();
      session.setAttribute("itemSearch", itemSearch);
      SearchItemLogic searchItemLogic = new SearchItemLogic();
      List<ItemBean> itemList = searchItemLogic.execute(itemSearch);
      session.setAttribute("itemList", itemList);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
      dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    } else {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
      HttpSession session = request.getSession();
      UserBean loginUser = (UserBean)session.getAttribute("loginUser");
      System.out.println("LoginServeltからShoppingServletに遷移");
      GetItemListLogic getItemListLogic = new GetItemListLogic();
      List<ItemBean> itemList = getItemListLogic.execute();
      session.setAttribute("itemList", itemList);
      if (loginUser.getUserName().equals("Administrator") && loginUser.getPass().equals("adminpass1")) {
        System.out.println("管理者としてログイン");
        request.setAttribute("name", "new");
        System.out.println("▼▼「管理者画面」ページ");
        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
      } else {
        System.out.println("一般ユーザーとしてログイン");
        System.out.println("▼▼「商品リスト」ページ");
        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
      }
      dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
  }
}
