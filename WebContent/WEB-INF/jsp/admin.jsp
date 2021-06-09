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

<form method="POST" action="" id="search">
<select name="category">
    <option value="">全て</option>
    <option value="watch">時計</option>
    <option value="tie">ネクタイ</option>
    <option value="wallet">財布</option>
</select>
<input type="text" placeholder="商品コードまたは商品名">
<button type="submit">検索</button>
</form>


<h2>商品リストの変更</h2>
<%--
<form action="/shopping/AdminServlet?action=new" method="post">
    <table>
        <tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
        <tr>
        <td><input type="text" name="item_id" value="tie0003" required></td>
        <td><input type="text" name="item_name" value="白ネクタイ" required></td>
        <td><span>¥</span><input type="text" name="price" min="100" max="50000" value=12000 required></td>
        <td><input type="number" name="quantity" min="0" max="50" value=4 required></td>
        <td><button type="submit" id="new" onclick='return confirm("商品を新規追加します。よろしいですか？")'>追加する</button></td>
</td>
    </tr>
    </table>
</form>

<form action="/shopping/AdminServlet?action=change" method="post">
    <table>
        <tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
        <tr>
        <td><input type="text" name="item_id" value="tie0001" required></td>
        <td><input type="text" name="item_name" value="ストライプネクタイ" required></td>
        <td><span>¥</span><input type="text" name="price" min="100" max="50000" value=3000 required></td>
        <td><input type="number" name="quantity" min="0" max="50" value=3 required></td>
        <td><button type="submit" id="change" onclick='return confirm("商品情報を変更します。よろしいですか？")'>変更する</button></td>
    </tr>
    </table>
</form>

--%>




<%
String name = (String) request.getAttribute("name");

String item_id = "";
String item_name = "";
int price = 0;
int quantity = 0;
String action = "new";
String confirmButton = "<button type='submit' onclick='return confirm('商品を新規追加します。よろしいですか？')'>追加する</button>";
String backButton = "";

if (name.equals("change")) {
	ItemBean item = (ItemBean) request.getAttribute("itemChange");

    item_id = item.getItem_id();
    item_name = item.getName();
    price = item.getPrice();
    quantity = item.getQuantity();
    action = "change";
    confirmButton = "<button type='submit' onclick='return confirm('商品情報を変更します。よろしいですか？'')'>変更する</button>";
    backButton = "<button type='submit'>戻る</button>";

}
%>

<form action="/shopping/AdminServlet2?action=<%= action %>" method="post">
    <table>
        <tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
        <tr>
        <td><input type="text" name="item_id" value="<%= item_id %>" required></td>
        <td><input type="text" name="item_name" value="<%= item_name %>" required></td>
        <td><span>¥</span><input type="text" name="price" min="100" max="50000" value=<%= price %> required></td>
        <td><input type="number" name="quantity" min="0" max="50" value=<%= quantity %> required></td>
		<td><%= confirmButton %></td>
    </tr>
    </table>
</form>

<form action='/shopping/AdminServlet?action=back' method='post'><%= backButton %></form>

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