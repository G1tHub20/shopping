<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>注文履歴</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<h1>注文履歴</h1>
<table>
    <tr><th>注文日</th><th>商品名</th><th>金額</th><th>数量</th></tr>
    <c:forEach var="history" items="${historyList}" >
    <tr>
        <td>${history.purchaseDate}</td>
        <td>${history.name}</td>
        <td><span>¥</span>${history.price}</td>
        <td>${history.purchaseNum}</td>
    </tr>
    </c:forEach>
</table>
<c:forEach var="history" items="${historyList}" >
${history.price += history.price} </c:forEach>

<p><span>合計金額：¥</span>110,000（仮）</p></tr>
<a href="ShoppingServlet?action=itemList">商品リストに戻る</a>

<script src="js/nav.js"></script>

</body>
</html>