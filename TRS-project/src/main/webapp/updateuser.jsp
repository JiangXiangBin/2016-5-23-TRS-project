<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>修改页面</title>
<!-- css样式 -->
<style type="text/css">
body {
	background: url("<%=basePath%>image/3.jpg") no-repeat;
}

div {
	margin: 0px auto;
	border: 1px solid gray;
	width: 500px;
	height: 350px;
	margin-top: 15%;
}

table {
	width: 500px;
	height: 350px;
	text-align: center;
	border-collapse: collapse;
}

td {
	border: 1px dashed gray;
}

tr:hover {
	background-color: red;
}

.s1 {
	color: red;
}

.s2 {
	color: blue;
}

.s3 {
	color: yellow;
}
</style>
<!-- JS部分 -->
<script type="text/javascript">
	var a = 0;
	var timer;
	function huan() {
		var color = document.getElementById("d1");
		var fontcolor = [ "s1", "s2", "s3" ];
		color.className = fontcolor[a++];
		if (a > 2) {
			a = 0;
		}
	}
	window.onload = function() {
		timer = setInterval(huan, 500);

	};
</script>


</head>
<!-- 主体部分 -->
<body>

	<form action="updateuser.do" method="post">
		<div>
			<table>
				<tr>
					<td colspan="3" id="d1"><h2>更新操作</h2></td>
				</tr>
				<tr>
					<td>用户名:</td>
					<td colspan="2"><input type="text" name=name
						value="${xinxi.username}" /></td>
				</tr>
				<tr>
					<td>密&emsp;码:</td>
					<td colspan="2"><input type="text" name=pwd
						value="${xinxi.pwd}" /></td>
				</tr>
				<tr>
					<td>电&emsp;话:</td>
					<td colspan="2"><input type="text" name=tel
						value="${xinxi.telphone}" /></td>
				</tr>
				<tr>
					<td>邮&emsp;箱:</td>
					<td colspan="2"><input type="text" name=email
						value="${xinxi.email}" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="id" value="${xinxi.id}" /></td>
				</tr>
				<tr>
					<td><input type="reset" value="重置" /></td>
					<td><input type="submit" value="提交" /></td>
				</tr>

			</table>
		</div>
	</form>

</body>
</html>