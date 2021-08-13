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

import model.ItemBean;

@WebServlet({"/AdminServlet"})
public class AdminServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("--------------------AdminServlet(POST)--------------------");
    System.out.println("フォームの切替用");
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
    System.out.println("action= " + action);
    if (action != null && action.equals("back")) {
      System.out.println("追加フォーム（new）");
      request.setAttribute("name", "new");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
      dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    } else if (action != null && action.equals("change")) {
      String UPDorDEL = request.getParameter("UPDorDEL");
      String item_id = request.getParameter("item_id");
      String item_name = request.getParameter("item_name");
      int price = Integer.parseInt(request.getParameter("price"));
      int quantity = Integer.parseInt(request.getParameter("quantity"));
      System.out.println("★UPDorDEL= " + UPDorDEL);
      System.out.println("変更する商品が選択された→"+ item_id);
      ItemBean itemChange = new ItemBean(item_id, item_name, price, quantity);
      System.out.println("itemChangeインスタンスの生成" + item_id +"、"+ item_name + "、" + "+ price + " + "、" + quantity);
      request.setAttribute("itemChange", itemChange);
      if (UPDorDEL.equals("update")) {
        System.out.println("更新フォーム（update）");
        request.setAttribute("name", "update");
      } else if (UPDorDEL.equals("delete")) {
        System.out.println("削除フォーム（delete）");
        request.setAttribute("name", "delete");
      }
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
      dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
  }
}
