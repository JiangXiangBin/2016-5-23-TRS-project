<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示列表</title>

<script type="text/javascript">
	
<%--Object object = request.getAttribute("ok");
			if (object == null) {
				response.sendRedirect("login.jsp");
				return;
			}--%>
	
</script>
<style type="text/css">
body {
	background: url("<%=basePath%>image/2.jpg") no-repeat;
	
}

table {

	border-collapse: collapse;
	text-align: center;
	width: 800px;
	height: 300px;
	border: 1px solid gray;
	margin-top: 20%;
}

div {
	margin: 0px auto;
	width: 800px;
	height: 300px;
}

tr:hover {
	background-color: blue;
}

td {
	border: 1px solid gray;
}

#dd {
	float: left;
}

#dd1 {
	float: right;
}
</style>

</head>
<body>
	<form action="#" method="post">
		<div>

			<table>
				<tr>
					<td colspan="6"><input type="button" value="首页"
						onclick="location='pageType.do?flag=0'" id="dd" /> <input
						type="button" value="上一页" onclick="location='pageType.do?flag=1'"
						id="dd" /> <input type="button" value="下一页"
						onclick="location='pageType.do?flag=2'" id="dd" /> <input
						type="button" value="尾页" onclick="location='pageType.do?flag=3'"
						id="dd" /> <input type="button" value="创建角色"
						onclick="location='addLoL.do'" id="dd1" /> <input type="button"
						value="登出" onclick="location='loginout.do'" id="dd1" />
					</td>
				</tr>

				<tr>
					<td colspan="6"><h2>角色信息</h2>
					</td>
				</tr>
				<tr>
					<td>ID</td>
					<td>角色</td>
					<td>性别</td>
					<td>等级</td>
					<td>血量</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${roles}" var="r">
					<tr>
						<td>${r.id}</td>
						<td>${r.username}</td>
						<td>${r.gender}</td>
						<td>${r.grade}</td>
						<td>${r.blood}</td>
						<td>
							<!-- <input type="button" value="删除"onclick="delete.list?id=${r.id};"/> -->
							<!--  <input type="button" value="修改"/>--> <a
							href="delete.do?id=${r.id}"
							onclick="return confirm('你确定要删除${r.username}?')">删除</a> <a
							href="findById.do?id=${r.id}">更新</a></td>
					</tr>
				</c:forEach>

			</table>
			<p></p>
		</div>
	</form>
</body>
</html>