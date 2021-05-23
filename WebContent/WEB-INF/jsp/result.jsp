<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>注文完了</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<h1>注文完了</h1>
<p>ありがとうございます。注文が確定されました。</p>
<a href="ShoppingServlet?action=itemList">商品リストに戻る</a>

<script src="js/nav.js"></script>

</body>
</html>