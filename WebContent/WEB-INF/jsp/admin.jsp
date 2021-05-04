<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
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
<form method="POST" action="" name="edit">
    <table>
        <tr></tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
        <tr>
        <td><input type="text" value="wat0001" disabled></td>
        <td><select name="category" disabled>
            <option value="watch">シンプルアナログ時計</option>
            <option value="tie">ストライプネクタイ</option>
            <option value="wallet">ブラウン長財布</option>
        </select></td>
        <td><span>¥</span><input type="text" value="90000"></td>
        <td><input type="num" value="3"></td>
        <td><button type="submit" id="edit">確定する</button></td>
    </tr>
    </table>
</form>

<br>

<table>

<tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
<c:forEach var="item" items="${itemList}" >
	<tr><td colspan="3"><img src="img/${item.item_id}.jpg" width="200" height="200"></td></tr>
    <tr>
        <form>
        <td>${item.item_id}</td>
        <td>${item.name}</td>
        <td><span>¥</span>${item.price}</td>
	    <td>${item.quantity}<td>
	    <button type="submit">変更する</button></td>
        </form>
    </tr>
</c:forEach>
</table>

</body>
</html>