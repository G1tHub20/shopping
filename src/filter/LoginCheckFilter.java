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

// ■フィルタ…サーブレットを呼び出すときに自動的に先に呼び出されるようなプログラム
// @WebFilter("/*") //フィルターを設定するサーブレットクラスを指定
// @WebFilter("/loginServlet") //フィルターを設定するサーブレットクラスを指定
@WebFilter(urlPatterns={"/AdminServlet","/AdminServlet2","/BuyItemServlet","/CartServlet","/RegisterServlet","/ShoppingServlet"}) //フィルターを設定するサーブレットクラスを指定
public class LoginCheckFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException { } //フィルタがインスタンス化された直後に実行　※1
	public void doFilter(ServletRequest request,ServletResponse response, FilterChain chain) //設定したサーブレットクラスをリクエストしたとき
			throws IOException, ServletException {
		System.out.println("★ログインチェック");
		// セッションが存在しない場合NULLを返す ※引数がtrue：セッションが開始されてない場合、新しいセッションを返す
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		if (session != null) {
			System.out.println("★何かしらセッションがある");

			Object loginCheck = session.getAttribute("loginUser");
			if (loginCheck == null) {
				  System.out.println("★loginCheckセッションがNull");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");
		            dispatcher.forward(request, response);
			} else {
				// loginCheckセッションがNULLでなければ、通常どおりの遷移
				System.out.println("loginCheckセッションがある");
	            chain.doFilter(request, response);
				}
		} else {
            // セッションがNullならば、ログイン画面へ飛ばす
    		System.out.println("★セッションがNull");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");
            dispatcher.forward(request, response);
		}

	}

	public void destroy() { } //フィルタのインスタンスが破棄される直前に実行　※1
}
//※1 これらのメソッドは必ず実装する必要がある。今回特に処理を行わないが、コンパイルエラーを防ぐため空の処理を実装している
