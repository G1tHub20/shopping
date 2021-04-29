<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Coreタグライブラリを利用する --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント登録</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>アカウント登録</h1>
</body>
<form action="/shopping/RegisterServlet" method="post">
ユーザー名：<input type="text" name="userName" autofocus required><br>
パスワード：<input type="password" name="pass" required><br>
<c:if test="${errorMsg!='アカウントを新規登録しました'}">
	<button type="submit">アカウント登録</button>
</c:if>
</form>
<p>${errorMsg}</p>
<a href="/shopping">TOPに戻る</a>
</html>