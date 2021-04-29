<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品リスト</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<h1>商品リスト</h1>
<h2>商品を探す</h2>
<form action="/Shopping/ShoppingServlet" method="post">
<select name="category">
	<option value="watch">時計</option>
	<option value="tie">ネクタイ</option>
	<option value="wallet">財布</option>
</select>
<input type="text" placeholder="商品コードまたは商品名">
<button type="submit">検索</button>
</form>

<c:forEach var="item" items="${itemList}" >
	商品コード：${item.item_id}、商品名：${item.name}、価格：{item.price}、数量：{item.quantity}
</c:forEach>


<table>
    <tr><th>商品名</th><th>価格</th><th>数量</th><th>ボタン</th></tr>

    <tr><td colspan="3"><img src="img/wat0001.jpg" width="200" height="200"></td></tr>
    <tr>
        <form>
        <td>シンプルアナログ時計</td>
        <td><span>¥</span>90,000</td>
        <td><select><option>0</option><option>1</option></select></td>
        <td><button type="submit">カートに入れる</button></td>
        </form>
    </tr>

    <tr><td colspan="3"><img src="img/tie0001.jpg" width="200" height="200"></td></tr>
     <tr>
        <form>
        <td>ストライプネクタイ</td>
        <td><span>¥</span>3,000</td>
        <td><select><option>0</option><option>1</option></select></td>
        <td><button type="submit">カートに入れる</button></td>
        </form>
    </tr>

    <tr><td colspan="3"><img src="img/wal0001.jpg" width="200" height="200"></td></tr>
    <tr>
        <form>
        <td>ブラウン長財布</td>
        <td><span>¥</span>20,000</td>
        <td><select><option>0</option><option>1</option></select></td>
        <td><button type="submit">カートに入れる</button></td>
        </form>
    </tr>

    <tr><td colspan="3"><img src="img/wal0002.jpg" width="200" height="200"></td></tr>
    <tr>
        <form>
        <td>プレミアム財布</td>
        <td><span>¥</span>500,000</td>
        <td><select><option>0</option><option>1</option></select></td>
        <td><button type="submit">カートに入れる</button></td>
        </form>
    </tr>

    <tr><td colspan="3"><img src="img/tie0002.jpg" width="200" height="200"></td></tr>
    <tr>
        <form>
        <td>ワンカラーネクタイ</td>
        <td><span>¥</span>2,000</td>
        <td><select><option>0</option><option>1</option></select></td>
        <td><button type="submit">カートに入れる</button></td>
        </form>
    </tr>

    <tr><td colspan="3"><img src="img/wat0002.jpg" width="200" height="200"></td></tr>
    <tr>
        <form>
        <td>スクウェアウォッチ</td>
        <td><span>¥</span>40,000</td>
        <td><select><option>0</option><option>1</option></select></td>
        <td><button type="submit">カートに入れる</button></td>
        </form>
    </tr>

</table>


</body>
</html>