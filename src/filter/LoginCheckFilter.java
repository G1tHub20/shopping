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
/**
 * ログインチェック用フィルター
 */
//フィルタを設定するサーブレットクラス・JSPファイルを指定
@WebFilter(urlPatterns = {"/HistoryServlet", "/CartServlet", "/OrderServlet"})
public class LoginCheckFilter implements Filter {
	// ■フィルタがインスタンス化された直後
	public void init(FilterConfig fConfig) throws ServletException {}

	// ■指定のサーブレットクラスをリクエストしたとき
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// ★前処理
        HttpSession session = ((HttpServletRequest)request).getSession(false); // セッションが存在しない場合NULLを返す
        RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");

        // セッションが何か存在する場合
        if(session != null){
        	Object loginCheck = session.getAttribute("loginUser"); // nullなら未ログイン
        	// ログイン済み →通常どおりの遷移
        	if (loginCheck != null) {
        		chain.doFilter(request, response);

    	// ★後処理
    		// 未ログイン →ログイン画面へ飛ばす
        	} else {
        		dispatcher.forward(request, response);
        	}

		// セッションが何も存在しない場合 →ログイン画面へ飛ばす
        } else {
            dispatcher.forward(request, response);
        }
	}

	// ■フィルタのインスタンスが破棄される直前
	public void destroy() {}
}