<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=0.7">
<title>管理者画面｜ショッピング</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<header>
<a href="LoginServlet?action=logout" onclick="return confirm('ログアウトします。よろしいですか?')">ログアウト</a>
（${loginUser.userName}さん）
</header>

<h1>管理者画面</h1>

<h2>商品リスト</h2>
<table>
<thead>
		<tr>
				<th>商品名</th><th>価格</th><th>数量</th><th>販売状態</th>
		</tr>
</thead>
<tbody>
<c:forEach var="item" items="${itemList}">
		<tr>
		    <td>${item.name}</td>
		    <td>¥${item.price}</td>
		    <td>${item.stock}</td>
			<c:if test="${item.state == 0}">
				<td>販売中止</td>
			</c:if>
		    <input type="hidden" name="item_id" value="${item.id}">
		    <input type="hidden" name="name" value="${item.name}">
		    <input type="hidden" name="price" value="${item.price}">
		</tr>
		<tr><td colspan="4"><img src="/shopping/upload/${item.image}" width="100" height="100"></td></tr>
    <tr><td colspan="4"><hr></td></tr>
</c:forEach>
</tbody>
</table>

<p><a href="AdminServletTest"><button type="button">在庫の初期設定</button></a></p>
<p>${errorMsg}</p>
</body>
</html>