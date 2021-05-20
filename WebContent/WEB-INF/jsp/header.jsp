<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- header --%>
<header>
	<%-- ログイン後のページでは、「ユーザー名」「注文履歴」「ログアウト」を共通で表示 --%>
	<p>「${loginUser.userName}さん」

	<c:if test="${loginUser.userName != 'admin'}"><%-- <c:if test="${action != 'history'}"> 文字列の比較だがequalsを使わない --%>
	<a href="ShoppingServlet?action=history">購入履歴</a>
	<a href="CartServlet">カート</a>
	</c:if>
	<a href="LoginServlet?action=logout">ログアウト</a>
	</p>
</header>