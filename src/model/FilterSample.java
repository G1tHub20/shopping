package model;

import java.io.IOException;
<<<<<<< HEAD
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter({"/*"})
public class FilterSample implements Filter {
  public void init(FilterConfig fConfig) throws ServletException {}
  
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    chain.doFilter(request, response);
  }
  
  public void destroy() {}
}
=======

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/*")
public class FilterSample implements Filter {

	public void init(FilterConfig fConfig) throws ServletException { } //フィルタがインスタンス化された直後に実行※1
	public void doFilter(ServletRequest request,ServletResponse response, FilterChain chain) //設定したサーブレットクラスをリクエストしたとき
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}
	public void destroy() { } //フィルタのインスタンスが破棄される直前に実行※1

}

//※1 これらのメソッドは必ず実装する必要がある。今回特に処理を行わないが、コンパイルエラーを防ぐため空の処理を実装している
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
