<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規登録画面</title>
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
<form action="signup" method="post"><br />

	<label for="login_id">ログインID</label>
	<input name="login_id" value="${users.login_id}" id="login_id"/><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

		<label for="passwordconfirm">パスワード(確認用)</label>
	<input name="passwordconfirm" type="password" id="passwordconfirm"/> <br />

	<label for="name">名称</label>
	<input name="name" value="${users.name}" id="name"/><br />

 	<label for="branch_id">支店</label>
	<select name="branch_id">
		<c:forEach items="${branches}" var="branche" >
		<option value="${branche.id}"> <c:out value="${branche.name}"></c:out> </option>
		</c:forEach>
	</select>

	<label for="department_id">部署・役職</label>
	<select name="department_id">
		<c:forEach items="${departments}" var="department" >
			<option value="${department.id}"> <c:out value="${department.name}"></c:out> </option>
		</c:forEach>
	</select>

	<input type="hidden" name="is_working" value="${users.is_working}" id="is_working"/> <br />
	<input type="submit" value="登録" /> <br />
	<a href="userall">戻る</a>
</form>
<div class="copyright">Copyright(c)ryo sekimizu</div>
</div>
</body>
</html>
