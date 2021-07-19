<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.LinkedHashMap, java.util.List, java.util.Map"%>
<%@ page import="model.UserBean, model.ItemBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
UserBean loginUser = (UserBean) session.getAttribute("loginUser");
String userName = loginUser.getUserName();

Map<String, List<Object>> cart = (Map<String, List<Object>>) session.getAttribute(userName); %>
<% System.out.println("カート=" + cart); %>

<% session.setAttribute("cart", cart); %>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>ショッピングカート</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<h1>ショッピングカート</h1>

<table>
    <tr><th>商品名</th><th>価格</th><th>数量</th><th>小計</th><th>ボタン</th></tr>

    <c:forEach var="item" items="${cart}">
    	<form action="/shopping/CartServlet?action=delete" method="post">
    	<tr>
        <td>${item.value[0]}</td>
        <td>${item.value[1]}</td>
        <td>${item.value[2]}</td>
        <td><span>¥</span>${item.value[3]}</td>
    	<input type="hidden" name="item_name" value="${item.value[0]}">
    	<input type="hidden" name="item_id" value="${item.key}">
        <td><button type="submit" name="${item.key}">削除</button></td>
       </tr>
       </form>
    </c:forEach>

</table>

<form action="/shopping/BuyItemServlet" method="post">
<c:choose>
	<c:when test="${not empty cart}">
		<p><span>合計金額：¥</span>${total}</p>
		<button type="submit" onclick='return confirm("注文を確定します。よろしいですか？")'>注文確定</button>
		<%-- <button type="submit" disabled>お買い物を続ける</button> --%>

	</c:when>
	<c:otherwise>
		<button type="submit" disabled>注文確定</button>
		<p>お客様のカートに商品はありません。</p>
	</c:otherwise>
</c:choose>
		</form>

<c:if test="${not empty cartMsg}">
	<p>${cartMsg}</p>
</c:if>

<a href="ShoppingServlet?action=itemList">商品リストに戻る</a>

<script src="js/nav.js"></script>

</body>
</html>