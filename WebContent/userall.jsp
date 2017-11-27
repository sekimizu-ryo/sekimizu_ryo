<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<script type="text/javascript">
function stopcheck(){

	if(window.confirm('このユーザーのアカウント停止させますか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}

}
function Resurrectioncheck(){

	if(window.confirm('このユーザーのアカウント復活させますか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}

}


</script>

<form action="userall" method="post">

		<label for="login_id">ログインID</label>
		<label for="name">名称</label>
		<label for="branch_id">支店</label>
		<label for="department_id">部署・役職</label>
		<a href='signup'>新規登録</a>
		<a href="./">戻る</a>
<br />
	<c:forEach items="${users}" var="user">
		<c:out value="${user.login_id}"/>
		<c:out value="${user.name}"/>
		<c:out value="${user.branch_id}"/>
		<c:out value="${user.department_id}"/>
		<a href='settings?id=<c:out value="${user.id}"/>'>編集</a>
		<br/>
	</c:forEach>
	</form>

	<form action="stop" method="post">
	<c:forEach items="${users}" var="user">
	<input type="hidden" name="id" value="${user.id}" id="id">
			<c:if test="${user.is_working==0}" >
			<input type="hidden" name="is_working" value="1" id="is_working">
			<input type="submit" value="停止" onclick="stopcheck()"/> <br />

			</c:if>

            <c:if test="${user.is_working==1}">
			<input type="hidden" name="is_working" value="0" id="is_working">
			<input type="submit" value="復活" onclick="Resurrectioncheck()"/> <br />
			</c:if>
	</c:forEach>
	   </form>


   <br/>
<div class="copyright">Copyright(c)ryo sekimizu</div>
</div>
</body>
</html>