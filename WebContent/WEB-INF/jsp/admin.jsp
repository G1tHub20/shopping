<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.ItemBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<body>
<h1>管理者画面</h1>

<h2>商品の追加／更新／削除</h2>

<%
// ■デフォルトは新規追加用フォーム
String name = (String) request.getAttribute("name");

String input1Header = "商品カテゴリ";
String input1 = "<select name='item_id' required><option value=''>カテゴリを選択</option><option value='tie'>ネクタイ</option><option value='wal'>財布</option><option value='wat'>腕時計</option></select>";
String item_id = "";
String item_name = "";
int price = 0;
int quantity = 0;
String action = "new";
String disabled1 = "";
String disabled2 = "";
String placeholder1 = "tie0003";
String placeholder2 = "白ネクタイ";
String confirmButton = "商品を新規追加します。よろしいですか？";
String str = "追加する";
String backButton = "";
String aboutCode = "<span class='small'></span>";
String aboutFile = "<span class='small'>※画像サイズは：400×400(px)推奨</span>";

String picUp = aboutCode + "<br><input type='file' name='image' id='image' accept='.jpg, .png, .gif' required><br>" + aboutFile;



//■変更用フォームに切替
if (name != null && !(name.equals("new"))) {
	ItemBean item = (ItemBean) request.getAttribute("itemChange");

	input1Header = "商品コード";
    item_id = item.getItem_id();
	input1 = "<input type='text' name='item_id' value='" + item_id + "' disabled required>";

    item_name = item.getName();
    price = item.getPrice();
    quantity = item.getQuantity();

    if (name.equals("update")) {
	    // 要修正
	    action = "update";
	    disabled1 = "disabled";
	    confirmButton = "商品情報を更新します。よろしいですか？";
	    picUp = "";
		str = "更新する";

	} else if (name.equals("delete")) {
	    action = "delete";
	    disabled1 = "disabled";
	    disabled2 = "disabled";
	    confirmButton = "商品情報を削除します。よろしいですか？";
	    picUp = "";
		str = "削除する";
	}
	// 表示／非表示できるように
    backButton = "<button type='submit'>戻る</button>";

}

    String filename = (String) request.getAttribute("filename");
%>


<form action="/shopping/AdminServlet2?action=<%= action %>" enctype="multipart/form-data" method="post">
    <table>
        <tr><th><%= input1Header %></th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
        <tr>
        <td><%= input1 %></td>
        <td><input type="text" pattern=".{3,20}" title="3文字以上20文字以内" name="item_name" value="<%= item_name %>" placeholder="<%= placeholder2 %>" <%= disabled1 %> required></td>
        <td><span>¥</span><input type="number" name="price" min="100" max="50000" step="100" value="<%= price %>" <%= disabled2 %> required></td>
        <td><input type="number" name="quantity" min="0" max="50" value="<%= quantity %>" <%= disabled2 %> required></td>
		<td>
		<button type='submit' onclick="return confirm('<%= confirmButton %>')"><%= str %></button>

		<%-- inputにdisabled属性を付けると、値を送信できない！ --%>
		<input type="hidden" name="item_id2" value="<%= item_id %>">
		<input type="hidden" name="item_name2" value="<%= item_name %>">
		<input type="hidden" name="price" value="<%= price %>">
		<input type="hidden" name="quantity" value="<%= quantity %>">
		</td>
    </tr>
    <tr><td colspan="2">
		<%= picUp %>
	</td></tr>
    </table>
</form>

<form action='/shopping/AdminServlet?action=back' method='post'><%= backButton %></form>
<c:if test="${not empty adminMsg}">
	<p>${adminMsg}</p>
</c:if>
<br>

<table id="admintable">
<thead id="adminthead">
<tr><th>商品コード</th><th>商品名</th><th>価格</th><th>在庫数</th><th>ボタン</th></tr>
</thead>
<tbody>
<c:forEach var="item" items="${itemList}" >
    <tr>
        <form action="/shopping/AdminServlet?action=change" method="post">

	        <td>${item.item_id}</td>
	        <td>${item.name}</td>
	        <td><span>¥</span>${item.price}</td>
		    <td id="stock">${item.quantity}<td>
		    <input type="hidden" name="item_id" value="${item.item_id}">
		    <input type="hidden" name="item_name" value="${item.name}">
		    <input type="hidden" name="price" value="${item.price}">
		    <input type="hidden" name="quantity" value="${item.quantity}">
		    <button type="submit" name="UPDorDEL" value="update">更新</button>
		    <button type="submit" name="UPDorDEL" value="delete">削除</button></td>
        </form>
    </tr>
    <tr><td colspan="3"><img src="/shopping/upload/${item.image}" width="200" height="200"></td></tr>

    <tr><td>&nbsp;</td></tr>
    <tr><td colspan="5"><hr></td></tr>
</c:forEach>
</tbody>
</table>

</body>


<script>
window.addEventListener('DOMContentLoaded', function() {
// 指定されると動くメソッド
document.querySelector("#image").addEventListener('change', function(e) {
// ブラウザーがFile APIを利用できるか確認
if (window.File) {
// 指定したファイルの情報を取得
var input = document.querySelector('#image').files[0];
// 最後に、反映
document.querySelector('#name').innerHTML = input.name;
}
}, true);
});
</script>


</html>