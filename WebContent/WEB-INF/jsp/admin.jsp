<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<form action="/shopping/AdminServlet" method="post">
    <table>
        <tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
        <tr>
        <td><select name="item_id">
            <option value="tie0001">tie0001</option>
            <option value="tie0002">tie0002</option>
            <option value="wal0001">wal0001</option>
            <option value="wal0002">wal0002</option>
            <option value="wat0001">wat0001</option>
            <option value="wat0002">wat0002</option>
        </select></td>
        <td><select name="">
            <option value="tie0001" selected>ストライプネクタイ</option>
            <option value="tie0002">ワンカラーネクタイ</option>
            <option value="wal0001">ブラウン長財布</option>
            <option value="wal0002">プレミアム長財布</option>
            <option value="wat0001">シンプルアナログ時計</option>
            <option value="wat0002">スクウェアウォッチ</option>
        </select></td>
        <td><span>¥</span><input type="text" name="price" value="90000"></td>
        <td><input type="number" name="quantity" min="0" max="20" value="3"></td>
        <td><button type="submit" id="edit">変更する</button></td>
    </tr>
    </table>
</form>

<br>

<table>

<tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
<c:forEach var="item" items="${itemList}" >
	<tr><td colspan="3"><img src="img/${item.item_id}.jpg" width="200" height="200"></td></tr>
    <tr>
        <form action="/shopping/AdminServlet" method="post">

	        <td>${item.item_id}</td>
	        <td>${item.name}</td>
	        <td><span>¥</span>${item.price}</td>
		    <td>${item.quantity}<td>
		    <input type="hidden" name="item_id" value="${item.item_id}">
		    <input type="hidden" name="name" value="${item.name}">
		    <input type="hidden" name="price" value="${item.price}">
		    <input type="hidden" name="quantity" value="${item.quantity}">
		    <button type="submit" name="alter" value="${item.item_id}">変更する</button></td>
        </form>
    </tr>
</c:forEach>
</table>

</body>
</html>