<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>ショッピングカート</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<h1>ショッピングカート</h1>

<table>
    <tr><th>商品名</th><th>価格</th><th>数量</th><th>小計</th><th>ボタン</th></tr>

    <c:forEach var="item" items="${cartItems}">
    	<form action="/shopping/CartServlet" method="get">
    	<tr>
    	<input type="hidden" name="item_id" value="${item.key}">
        <td>${item.value[0]}</td>
        <td>${item.value[1]}</td>

        <%-- <td>
        <select name="quantity">
	        <c:forEach var="i" begin="1" end="5" step="1">
	        <option value="<c:out value="${i}" />"><c:out value="${i}" /></option>
	        </c:forEach>
	    </select>
	    </td> --%>
        <td>${item.value[2]}</td>
        <td>${item.value[3]}</td>
        <td><button type="submit" name="${item.key}">削除</button></td>
       </tr>
       </form>
    </c:forEach>

</table>

<!--  カートが空なら「ショッピングカートに商品はありません」 -->


<%-- int[] cart = {500000, 4000};
for (int subtotal : cart) {
	total += subtotal;

int total += item.subtotal;
} --%>

<c:if test="${item.subtotal > 0}">
<p><span>合計：¥</span>${item.subtotal}(仮)</p>
</c:if>
<form action="/shopping/BuyItemServlet" method="post">
<button type="submit">注文確定</button>
</form>

<c:if test="${errorMsg != null}">
<p>${errorMsg}</p>
</c:if>

<a href="ShoppingServlet?action=itemList">商品リストに戻る</a>

<script src="js/nav.js"></script>

</body>
</html>