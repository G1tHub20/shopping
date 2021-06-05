package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminLogic;
import model.ItemBean;

@WebServlet("/AdminServlet") //URLパターンの設定
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------------------AdminServlet(POST)--------------------");
		//■リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定

		String item_id = request.getParameter("item_id");
//		String name = (String) request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		ItemBean itemChange = new ItemBean(price, quantity, item_id);

		// itemテーブルから取得。変更があるならupdate
		System.out.println("とりあえず商品情報を変更");

		//■商品情報変更処理
		AdminLogic adminLogic = new AdminLogic();
		Boolean isSuccess = adminLogic.execute(itemChange);



		//■Userインスタンの生成
//		UserBean user = new UserBean(userName, pass);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");

		dispatcher.forward(request, response);
		}

	}
