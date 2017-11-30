<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>編集画面</title>
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

<form action="settings" method="post"><br />

	<input type="hidden" name="id"  value="${editUser.id}" id="id"/>

	 <label for="login_id">ログインID</label>
	<input name="login_id" value="${editUser.login_id}" id="login_id"/><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

		<label for="passwordconfirm">パスワード(確認用)</label>
	<input name="passwordconfirm" type="password" id="passwordconfirm"/> <br />

	<label for="name">名称</label>
	<input name="name" value="${editUser.name}" id="name"/><br />

<c:if test="${editUser.id != loginUser.id}">
	<label for="branch_id">支店</label>
	<select name="branch_id">
		<c:forEach items="${branches}" var="branche" >
		<option value="${branche.id}" <c:if test="${editUser.branch_id == branche.id}">selected </c:if>> <c:out value="${branche.name}"></c:out> </option>
		</c:forEach>
	</select>

	<label for="department_id">部署・役職</label>
	<select name="department_id">
		<c:forEach items="${departments}" var="department" >
			<option value="${department.id}" <c:if test="${editUser.department_id == department.id}">selected </c:if>> <c:out value="${department.name}"></c:out> </option>
		</c:forEach>
	</select>
</c:if>
<input type="hidden" name="branch_id"   value="${editUser.branch_id}"  id="branche.id"/>
<input type="hidden" name="department_id" value="${editUser.department_id}" id="department.id"/>

	   <br/>
	   <input type="submit" value="編集" />
		<br />
		<a href="userall">戻る</a>
</form>
<div class="copyright">Copyright(c)ryo sekimizu</div>
</div>
</body>
</html>
