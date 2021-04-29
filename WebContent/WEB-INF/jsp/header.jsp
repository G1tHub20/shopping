<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- header --%>
<header>
	<%-- ログイン後のページでは、「ユーザー名」「注文履歴」「ログアウト」を共通で表示 --%>
	<p>「${loginUser.userName}さん」
	<a href="ShoppingServlet?action=history">購入履歴</a> <%-- 要修正 --%>
	<a href="LoginServlet?action=logout">ログアウト</a> <%-- 要修正 --%>
	</p>
</header>