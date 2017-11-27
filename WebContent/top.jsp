<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム</title>
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
<div class="header">

	<c:if test="${ not empty loginUser }">
		<a href="post">新規投稿画面</a>
		<a href="userall">管理画面</a>
		<a href="logout">ログアウト</a>
		<a href="./">ホーム</a>
	</c:if>
</div>

<c:if test="${ not empty loginUser }">
	<div class="profile">
		<div class="login_id"><h2><c:out value="${loginUser.login_id}" /></h2></div>
		<div class="name">
			<c:out value="${loginUser.name}" />
		</div>
	</div>
</c:if>

<div class="form-area">
	<form action="./" method="get">
		<label for="category">カテゴリー</label>
		<br/>
         <input type="text" name="category" value="${category}" />
		 <input type="date" name="StartDate" value="${StartDate}" >
		<input type="date" name="EndDate"  value="${EndDate}">
		<input type="submit" value="検索" />
	</form>
		<br/>

		<br/>

	<c:forEach items="${posts}" var="post">
		<label for="subject">件名</label>
		<c:out value="${post.subject}" />
		<label for="text">本文</label>
		<c:out value="${post.text}" />
		<label for="category">カテゴリー</label>
		<c:out value="${post.category}" />
		<br />
		<label for="insertDate">日時</label>
		<input type="text" name="insertDate" value="${post.insertDate}">
		<!--<fmt:formatDate value="${post.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" />-->

		<form action="delete" method="post">
			<input type="hidden" name="id" value="${post.id}" id="id">
			<input type="submit" value="削除" /> <br />
		</form>

		<form action="comment" method="post">
			<input type="hidden" name="postid"  value="${post.id}" id="id"/>
			<input name="text"  value="${comment.text}" />
			<br />
			<input type="submit" value="コメントを投稿">（500文字まで）
			<br />
		</form>

	<c:forEach items="${comments}" var="comment">
			<c:if test="${post.id == comment.postId}">
				<label for="text">コメント投稿結果</label>
				<c:out value="${comment.text}" />
		<form action="commentdelete" method="post">
				<input type="hidden" name="id" value="${comment.id}" id="id">
				<input type="submit" value="コメント削除" /> <br />
		</form>
		</c:if>
	</c:forEach>
			<br />
	</c:forEach>

</div>
<div class="copyright">Copyright(c)ryo sekimizu</div>
</div>
</body>
</html>
