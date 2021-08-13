<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Coreタグライブラリを利用する --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングサイト</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>ようこそ</h1>
</body>
<form action="/shopping/LoginServlet" method="post">
<<<<<<< HEAD
ユーザー名：<input type="text" name="userName" autofocus required><br>
パスワード：<input type="password" name="pass" required><br>
<button type="submit">ログイン</button>
</form>
<br>
=======
ユーザー名：<input type="text" name="userName" value="admin" autofocus required><br>
パスワード：<input type="password" name="pass" value="adminpassword" required><br>
<button type="submit">ログイン</button>
</form>
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
<a href="/shopping/RegisterServlet">アカウント登録</a>

</html>