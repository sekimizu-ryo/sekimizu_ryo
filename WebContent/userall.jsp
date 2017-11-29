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
		<a href='signup'>新規登録</a>
		<a href="./">戻る</a>
<br />

	<table border=1>
		<tr><th>ログインID</th><th>名称</th><th>支店</th><th>部署・役職</th><th>編集</th><th>停止・復活</th></tr>
		<c:forEach items="${users}" var="user">
			 <tr>
				<td><c:out value="${user.login_id}"/></td><td><c:out value="${user.name}"/></td>

			<c:forEach items="${branches}" var="branche" >
				<c:if test="${user.branch_id==branche.id}" >
					<td><c:out value="${branche.name}"></c:out></td>
				</c:if>
			</c:forEach>

			<c:forEach items="${departments}" var="department" >
				<c:if test="${user.department_id==department.id}" >
					<td><c:out value="${department.name}"></c:out></td>
				</c:if>
			</c:forEach>

				<td><a href='settings?id=<c:out value="${user.id}"/>'>編集</a></td>
			 	<td>
				 	<form action="stop" method="post">
						<input type="hidden" name="id" value="${user.id}" id="id">
						<c:if test="${user.id != loginUser.id}" >
							<c:if test="${user.is_working==0}" >
								<input type="hidden" name="is_working" value="1" id="is_working">
								<input type="submit" value="停止" onclick="stopcheck()"/> <br />
							</c:if>

					        <c:if test="${user.is_working==1}">
								<input type="hidden" name="is_working" value="0" id="is_working">
								<input type="submit" value="復活" onclick="Resurrectioncheck()"/> <br />
							</c:if>
						</c:if>
						<c:if test="${user.id == loginUser.id}" >
						ログイン中
						</c:if>
					</form>
				</td>
			 </tr>

		</c:forEach>
	</table>


   <br/>
<div class="copyright">Copyright(c)ryo sekimizu</div>
</div>
</body>
</html>