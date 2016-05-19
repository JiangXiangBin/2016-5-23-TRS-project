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
body{
background: url("<%=basePath%>image/4.jpg") no-repeat;
}
#div1{

	width: 400px;
	height: 200px;
	margin: 0px auto;
	text-align: center;
	margin-top: 100px;
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
				<tr id="tr1">
					<td>编号</td>
					<td>姓名</td>
					<td>密码</td>
					<td>号码</td>
					<td>邮箱</td>
                    <td>操作</td>
                    <td><a href="loginout.do">登出</a></td>
				</tr>

				<c:forEach items="${find}" var="r">
					<tr>
						<td>${r.id}</td>
						<td>${r.username}</td>
						<td>${r.pwd}</td>
						<td>${r.telphone}</td>
						<td>${r.email}</td>
                       <td><a href="findid.do?id=${r.id}"
                        onclick="return confirm('你确定要修改${r.username}?')">修改</a>
                        <a href="zhuanmoney.do?id=${r.id}">转账</a>
                        <a href="findstuid.do?id=${r.id}">添加</a>
                        </td>
					</tr>
				</c:forEach>

			</table>

		</div>
	</form>
</body>
</html>