<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<script type="text/javascript">
function Postcheck(){

	if(window.confirm('投稿を削除しますか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}

}

function Commentcheck(){

	if(window.confirm('コメントを削除しますか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}

}
</script>

	<c:if test="${ not empty loginUser }">
		<a href="post">新規投稿画面</a>
		<a href="userall">管理画面</a>
		<a href="logout">ログアウト</a>
	</c:if>
</div>

<c:if test="${ not empty loginUser }">
	<div class="profile">
		<div class="login_id"><h3>ようこそ<c:out value="${loginUser.login_id}" />さん</h3></div>
	</div>
</c:if>

<div class="form-area">
	<form action="./" method="get">
		<h3><label for="category">カテゴリー検索</label></h3>
         <input type="text" name="category" value="${category}" />
         <br/>
		 <input type="date" name="StartDate" value="${StartDate}" >
		<input type="date" name="EndDate"  value="${EndDate}">
		<input type="submit" value="検索" />
	</form>

	<form action="./" method="get">
	<input type="submit" value="クリア" />
	</form>
</div>
<hr color="#000000" width="790" size="10" />
<div class="form-area">
	<c:forEach items="${posts}" var="post">
		<h4><label for="subject">件名</label></h4>
		<c:out value="${post.subject}" />
		<br/>
		<br/>
		<h4><label for="subject">投稿者</label></h4>
		<c:forEach items="${users}" var="user" >
			<c:if test="${user.id==post.user_id}" >
				<c:out value="${user.name}"></c:out>
			</c:if>
		</c:forEach>
		<br/>
		<br/>
		<h4><label for="text">本文</label></h4>
		<c:out value="${post.text}" />
		<br/>
		<br/>
		<h4><label for="category">カテゴリー</label></h4>
		<c:out value="${post.category}" />
		<br />
		<br/>
		<h4><label for="insertDate">日時</label></h4>
		<fmt:formatDate value="${post.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" />


	<c:if test="${post.user_id == loginUser.id}" >
		<form action="delete" method="post">
			<input type="hidden" name="id" value="${post.id}" id="id">
			<input type="submit" value="削除" onclick="return Postcheck()" /> <br />
		</form>
	</c:if>
   <br/>
		<form action="comment" method="post">
			<input type="hidden" name="postid"  value="${post.id}" id="id"/>
		     <textarea  name="text"  rows="4" cols="40"><c:out value="${comment.text}" /></textarea>
			<br />
			<input type="submit" value="コメントを投稿">（500文字以内）
			<br />
		</form>
	<c:forEach items="${comments}" var="comment">
		<c:if test="${post.id == comment.postId}">
			<h4><label for="text">コメント投稿結果</label></h4>
			<br/>
			<c:forEach items="${users}" var="user" >
				<c:if test="${user.id==comment.userId}" >
					<h4><label for="text">投稿者</label></h4>
					<c:out value="${user.name}"></c:out>
				</c:if>
			</c:forEach>
			<br>
			<h4><label for="text">コメント内容</label></h4>
			<c:forEach var="str" items="${fn:split(comment.text,'
')}" ><c:out value="${str}" /><br></c:forEach>
			<c:if test="${comment.userId == loginUser.id}" >
				<form action="commentdelete" method="post">
					<input type="hidden" name="id" value="${comment.id}" id="id">
					<input type="submit" value="コメント削除" onclick="return Commentcheck()"/> <br />
				</form>
			</c:if>
		</c:if>
	</c:forEach>
			<br />
			<hr color="#000000" width="790" size="10" />
	</c:forEach>

</div>

<div class="copyright">Copyright(c)ryo sekimizu</div>
</div>
</body>
</html>
