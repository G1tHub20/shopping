<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
<a href="HistoryServlet">注文履歴</a>
<a href="CartServlet">カート</a>
<a href="LoginServlet?action=logout" onclick="return confirm('ログアウトします。よろしいですか?')">ログアウト</a>
（${loginUser.userName}さん）
</header>