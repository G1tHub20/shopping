<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- header --%>
<header>
<%-- ログイン後のページでは、「ユーザー名」「注文履歴」「ログアウト」を共通で表示 --%>

<%--
<script type="text/javascript">
function check() {
	if(window.confirm('ログアウトします。よろしいですか？')){ // 確認ダイアログを表示
		return true; // 「OK」時は実行
	}
	else{
		return false; //  // 「キャンセル」時は中止
	}
}
</script>
--%>

	<p id="header">「${loginUser.userName}さん」
	<c:if test="${loginUser.userName != 'Administrator'}"><%-- <c:if test="${action != 'history'}"> 文字列の比較だがequalsを使わない --%>
	<a href="ShoppingServlet?action=history">注文履歴</a>
	<a href="CartServlet">ショッピングカート</a>
	</c:if>
	<a href="LoginServlet?action=logout" onclick="return confirm('ログアウトします。よろしいですか?')">ログアウト</a>
	</p>

</header>