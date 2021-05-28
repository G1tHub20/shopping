<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>注文履歴</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<h1>注文履歴</h1>
<table>
    <tr><th>注文日</th><th>商品名</th><th>価格</th><th>数量</th><th>小計</th></tr>
    <c:forEach var="history" items="${historyList}" >
    <tr>
        <td>${history.purchaseDate}</td>
        <td>${history.name}</td>
        <td>${history.price}</td>
        <td>${history.purchaseNum}</td>
        <td><span>¥</span>${history.subtotal}</td>
    </tr>
    </c:forEach>
</table>

<c:if test="${empty historyList}">
<p>お客様の注文履歴はありません。</p>
</c:if>

<a href="ShoppingServlet?action=itemList">商品リストに戻る</a>

<script src="js/nav.js"></script>

</body>
</html>