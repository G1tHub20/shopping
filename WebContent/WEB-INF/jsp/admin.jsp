<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.ItemBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<body>
<h1>管理者画面</h1>

<h2>商品の追加／変更</h2>


<%
String name = (String) request.getAttribute("name");

String item_id = "";
String item_name = "";
int price = 0;
int quantity = 0;
String action = "new";
String disabled = "";
String placeholder1 = "tie0003";
String placeholder2 = "白ネクタイ";
String confirmButton = "商品を新規追加します。よろしいですか？";
String str = "追加する";
String backButton = "";

if (name.equals("change")) {
	ItemBean item = (ItemBean) request.getAttribute("itemChange");

    item_id = item.getItem_id();
    item_name = item.getName();
    price = item.getPrice();
    quantity = item.getQuantity();
    action = "change";
    disabled = "disabled";
    confirmButton = "商品情報を変更します。よろしいですか？";
	str = "変更する";
    backButton = "<button type='submit'>戻る</button>";

}
%>

<form action="/shopping/AdminServlet2?action=<%= action %>" method="post">
    <table>
        <tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
        <tr>
        <td><input type="text" pattern="^([a-z]{3})([0-9]{4})$" title="英字3桁+数字4桁 ※半角、小文字のみ" name="item_id" value="<%= item_id %>" placeholder="<%= placeholder1 %>" <%= disabled %> required>
        <input type="hidden" name="item_id2" value="<%= item_id %>"></td>
        <td><input type="text" name="item_name" value="<%= item_name %>" placeholder="<%= placeholder2 %>" <%= disabled %> required>
        <input type="hidden" name="item_name2" value="<%= item_name %>"></td>
        <td><span>¥</span><input type="number" name="price" min="100" max="50000" step="100" value="<%= price %>" required></td>
        <td><input type="number" name="quantity" min="1" max="50" value="<%= quantity %>" required></td>
		<td><button type='submit' onclick="return confirm('<%= confirmButton %>')"><%= str %></button></td>
    </tr>
    </table>
</form>

<form action='/shopping/AdminServlet?action=back' method='post'><%= backButton %></form>
<c:if test="${not empty adminMsg}">
	<p>${adminMsg}</p>
</c:if>
<br>

<table>

<tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
<c:forEach var="item" items="${itemList}" >
	<tr><td colspan="3"><img src="img/${item.item_id}.jpg" width="200" height="200"></td></tr>
    <tr>
        <form action="/shopping/AdminServlet?action=change" method="post">

	        <td>${item.item_id}</td>
	        <td>${item.name}</td>
	        <td><span>¥</span>${item.price}</td>
		    <td>${item.quantity}<td>
		    <input type="hidden" name="item_id" value="${item.item_id}">
		    <input type="hidden" name="item_name" value="${item.name}">
		    <input type="hidden" name="price" value="${item.price}">
		    <input type="hidden" name="quantity" value="${item.quantity}">
		    <button type="submit" name="change" value="${item.item_id}">変更する</button></td>
        </form>
    </tr>
</c:forEach>
</table>

</body>
</html>