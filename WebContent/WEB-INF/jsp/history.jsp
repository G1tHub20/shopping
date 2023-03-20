<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=0.7">
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<title>注文履歴｜ショッピング</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<h1>注文履歴</h1>

<table>
<thead>
<tr>
<th>注文日時</th><th>商品名</th><th>単価</th><th>数量</th><th>合計(商品ごと)</th>
</tr>
</thead>
<tbody>
<c:forEach var="history" items="${historyList}">
<tr>
		<td>${history.order_date}</td>
    <td>${history.item_name}</td>
    <td>¥${history.item_price}</td>
    <td>${history.order_num}</td>
    <td>¥${history.sum_price}</td>
<tr>
</c:forEach>
</tbody>
</table>

<c:if test="${empty historyList}">
<p>お客様の注文履歴はありません。</p>
</c:if>

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>