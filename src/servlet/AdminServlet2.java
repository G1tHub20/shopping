package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminServlet2") //URLパターンの設定
public class AdminServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------------------AdminServlet2(POST)--------------------");
		//■リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8"); //リクエストパラメータの文字コードを指定

		String action = request.getParameter("action");
		String item_id = request.getParameter("item_id");
		String item_name = request.getParameter("item_name");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));


		if (action != null && action.equals("new")) {
			System.out.println("フォームの「追加する」ボタンが押された");

			System.out.println("ここに新商品追加の処理を入れる");
			// itemテーブルから取得。変更があるならupdate
//			System.out.println("とりあえず商品情報を変更");

			//■商品情報変更処理
//			AdminLogic adminLogic = new AdminLogic();
//			Boolean isSuccess = adminLogic.execute(itemChange);


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			dispatcher.forward(request, response);

		} else if (action != null && action.equals("change")) {
			System.out.println("フォームの「追加する」ボタンが押された");

			System.out.println("ここに商品情報変更の処理を入れる");
			// itemテーブルから取得。変更があるならupdate
//			System.out.println("とりあえず商品情報を変更");

			//■商品情報変更処理
//			AdminLogic adminLogic = new AdminLogic();
//			Boolean isSuccess = adminLogic.execute(itemChange);


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			dispatcher.forward(request, response);
	}

}
}