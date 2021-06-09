package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ItemBean;

@WebServlet("/AdminServlet") //URLパターンの設定
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------------------AdminServlet(POST)--------------------");
		//■リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定

		String action = request.getParameter("action");
		System.out.println("action= " + action);

		RequestDispatcher dispatcher;

	if (action != null && action.equals("back")) {
		System.out.println("新規追加フォーム（new）");
		request.setAttribute("name", "new");

		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
		dispatcher.forward(request, response);

	} else if (action != null && action.equals("change")) {
			String item_id = request.getParameter("item_id");
			String item_name = request.getParameter("item_name");
			int price = Integer.parseInt(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			System.out.println("変更する商品が選択された" + item_id);

			ItemBean itemChange = new ItemBean(item_id, item_name, price, quantity);

//			■itemインスタンの生成
			System.out.println("itemChangeインスタンスの生成" + item_id + "、" + item_name + "、" + price + "、" + quantity);
			request.setAttribute("itemChange", itemChange);

			System.out.println("変更フォーム（change）");
			request.setAttribute("name", "change");

			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			dispatcher.forward(request, response);
	}

	}
}