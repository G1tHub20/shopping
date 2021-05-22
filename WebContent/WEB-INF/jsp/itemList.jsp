<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品リスト</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<h1>商品リスト</h1>
<h2>商品を探す</h2>
<form action="/shopping/ShoppingServlet?action=search" method="post">
<select name="category">
	<option value="">全て</option>
	<option value="wat">時計</option>
	<option value="tie">ネクタイ</option>
	<option value="wal">財布</option>
</select>
<input type="text" name="itemName" placeholder="商品名">
<button type="submit">検索</button>
</form>

<table>

<%-- 商品コード：${item.item_id}、商品名：${item.name}、価格：{item.price}、数量：{item.quantity} --%>

    <tr><th>商品名</th><th>価格</th><th>数量</th><th>ボタン</th></tr>
<c:forEach var="item" items="${itemList}" >
	<tr><td colspan="3"><img src="img/${item.item_id}.jpg" width="200" height="200"></td></tr>
    <tr>
        <form action="/shopping/CartServlet" method="post">
        <td>${item.name}</td>
        <td><span>¥</span>${item.price}</td>
        <input type="hidden" name="name" value="${item.name}">
        <input type="hidden" name="price" value="${item.price}">

        <c:choose>

        <%-- ■在庫切れのチェック --%>
        <c:when test="${item.quantity > 0}">
	        <td>
	        <select name="quantity">
	        <c:forEach var="i" begin="1" end="${item.quantity}" step="1">
	        <option value="<c:out value="${i}" />"><c:out value="${i}" /></option>
	        </c:forEach>
	        </select>
	        </td>
	        <td><button type="submit" name="item_id" value="${item.item_id}">カートに入れる</button></td>
       </c:when>
        <c:otherwise>
			<td colspan="2">現在、在庫切れです！</td>
	        </c:otherwise>
        </c:choose>
        </form>
    </tr>
</c:forEach>
</table>

</body>
</html>