<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <tr>
        <td>2020/11/28</td>
        <td>シンプルアナログ時計</td>
        <td><span>¥</span>90,000</td>
        <td>1</td>
    </tr>
    <tr>
        <td>2020/11/28</td>
        <td>ワンカラーネクタイ</td>
        <td><span>¥</span>20,000</td>
        <td>1</td>
    </tr>
</table>
<p><span>合計金額：¥</span>110,000</p></tr>
<a href="ShoppingServlet?action=itemList">商品リストに戻る</a>

<script src="js/nav.js"></script>

</body>
</html>