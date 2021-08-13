<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Coreタグライブラリを利用する --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント登録</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>アカウント登録</h1>
</body>
<form action="/shopping/RegisterServlet" method="post">
ユーザー名：<input type="text" name="userName" pattern="(?=.*[a-zA-Z])(?=.*[0-9])([a-zA-Z0-9]{6,20})$" title="半角英数字8～20字（いずれかを1字以上含む）で入力して下さい。" minlength="8" maxlength="20" autofocus required><br>
パスワード：<input type="password" name="pass" pattern="(?=.*[a-zA-Z])(?=.*[0-9])([a-zA-Z0-9]{6,20})$" title="半角英数字8～20字（いずれかを1字以上含む）で入力して下さい。" minlength="8" maxlength="20" required><br>
※半角英数字8～20字（いずれかを1字以上含む）で入力してください<br>
<button type="submit">アカウント登録</button>
</form>
<p>${registerMsg}</p>
<a href="/shopping">TOPに戻る</a>
</html>