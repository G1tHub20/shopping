package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.AdminLogic;
import model.GetItemListLogic;
import model.ItemBean;

@WebServlet({"/AdminServlet2"})
@MultipartConfig
public class AdminServlet2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("--------------------AdminServlet2(POST)--------------------");
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
    String item_id = request.getParameter("item_id");
    String item_id2 = request.getParameter("item_id2");
    String item_name = request.getParameter("item_name");
    String item_name2 = request.getParameter("item_name2");
    int price = Integer.parseInt(request.getParameter("price"));
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    System.out.println("★action= " + action);
    if (action != null && action.equals("new")) {
      System.out.println("「追加」フォームの処理");
      Part part = request.getPart("image");
      String filename = Paths.get(part.getSubmittedFileName(), new String[0]).getFileName().toString();
      String[] image = filename.split("\\.");
      System.out.println("filename= " + filename);
      System.out.println("image= " + image[0]);
      String path = getServletContext().getRealPath("/upload");
      System.out.println("実際にファイルが保存されるパス");
      System.out.println(path);
      part.write(String.valueOf(path) + File.separator + filename);
      request.setAttribute("filename", filename);
      ItemBean newItem = new ItemBean(item_id, item_name, price, quantity, image[0]);
      AdminLogic adminLogic = new AdminLogic();
      boolean isSuccess = adminLogic.execute1(newItem);
      request.setAttribute("name", "new");
      request.setAttribute("itemChange", newItem);
      if (!isSuccess) {
        System.out.println("処理しっぱい");
        request.setAttribute("adminMsg", String.valueOf(item_id2) + "を追加できませんでした。同じ商品コードが既に存在します。");
      } else {
        System.out.println("処理成功");
        request.setAttribute("adminMsg", String.valueOf(item_id2) + "を追加しました");
      }
    } else if (action != null && action.equals("update")) {
      System.out.println("「更新」フォームの処理");
      ItemBean changeItem = new ItemBean(item_id2, item_name2, price, quantity);
      AdminLogic adminLogic = new AdminLogic();
      int isSuccess = adminLogic.execute2(changeItem);
      request.setAttribute("itemChange", changeItem);
      System.out.println("isSuccess= " + isSuccess);
      if (isSuccess == 0) {
        System.out.println("処理成功");
        request.setAttribute("adminMsg", String.valueOf(item_id2) + "を更新しました。");
      } else if (isSuccess == 1) {
        System.out.println("処理しっぱい");
        request.setAttribute("adminMsg", String.valueOf(item_id2) + "を更新できません。商品情報を変更して実行してください。");
      } else {
        System.out.println("処理しっぱい");
        request.setAttribute("adminMsg", "申し訳ありません。更新できませんでした。");
      }
    } else if (action != null && action.equals("delete")) {
      System.out.println("「削除」フォームの処理");
      ItemBean changeItem = new ItemBean(item_id2, item_name2, price, quantity);
      AdminLogic adminLogic = new AdminLogic();
      Boolean isSuccess = Boolean.valueOf(adminLogic.execute3(changeItem));
      request.setAttribute("itemChange", changeItem);
      if (!isSuccess.booleanValue()) {
        System.out.println("申し訳ありません。削除できませんでした。");
        request.setAttribute("adminMsg", "処理しっぱい");
      } else {
        System.out.println("処理成功");
        request.setAttribute("adminMsg", String.valueOf(item_id2) + "を削除しました");
      }
    }
    GetItemListLogic getItemListLogic = new GetItemListLogic();
    List<ItemBean> itemList = getItemListLogic.execute();
    HttpSession session = request.getSession();
    session.setAttribute("itemList", itemList);
    System.out.println("▼▼「管理者」ページ");
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
    dispatcher.forward((ServletRequest)request, (ServletResponse)response);
  }
}
