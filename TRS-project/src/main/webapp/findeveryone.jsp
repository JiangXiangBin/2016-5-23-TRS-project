<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询结果</title>

<!-- CSS样式 -->
<style type="text/css">
body {
	background: url("<%=basePath%>image/4.jpg") no-repeat;
}

#div1 {
	width: 400px;
	height: 200px;
	margin: 0px auto;
	text-align: center;
	margin-top: 100px;
}

#select {

	margin: 0px auto;
	margin-left: 30px;
	margin-bottom: 200px;
}

table {
	width: 600px;
	height: 200px;
	border-collapse: collapse;
}

#tr1 {
	background-color: yellow;
}

tr:hover {
	background-color: blue;
}

td {
	border: 1px solid gray;
}
</style>
</head>
<!-- 主体部分 -->
<body>
	<form action="#">
		<div id="div1">
			<table>
				<tr>
					<td><a href="loginout.do">登出</a>
					</td>
				</tr>
				<tr id="tr1">
					<td>编号</td>
					<td>姓名</td>
					<td>密码</td>
					<td>号码</td>
					<td>邮箱</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${some}" var="r">
					<tr>
						<td>${r.id}</td>
						<td>${r.username}</td>
						<td>${r.pwd}</td>
						<td>${r.telphone}</td>
						<td>${r.email}</td>
						<td><a href="findnumble.do?id=${r.id}"onclick="return confirm('你确定要删除${r.username}?')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<select id="select" size="5">
			<option>证券号码</option>
			<c:forEach items="${some1}" var="r1">
				<option>${r1.numble}</option>
			</c:forEach>
		</select>


	</form>
</body>
</html>