<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>注文</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<h1>注文</h1>
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

<%
int total = 0;
int[] historyPrice = {500000, 4000};
for (int subtotal : historyPrice) {
	total += subtotal;
}
%>


<tr>
<p><span>合計：¥</span><%= total %>(仮)</p>
</tr>
<button type="submit">注文確定</button>
<a href="ShoppingServlet?action=itemList">商品リストに戻る</a>

<script src="js/nav.js"></script>

</body>
</html>