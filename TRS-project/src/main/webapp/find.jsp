<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>找回密码</title>
<style type="text/css">
#div1 {
	border: 1px solid gray;
	width: 400px;
	height: 250px;
	text-align: center;
	margin-top: 100px;
	margin: 0px auto;
	margin-top: 20pt;
}

body {
	background: url("image/2.jpg") no-repeat;
}
</style>

</head>
<body>

	<form action="test/find.do" method="post">
		<div id="div1">
			<table>

				<tr>
					<td>*邮  箱</td>
					<td><input type="text" name="email" /></td>
				</tr>

				<tr>
					<td>*密码类型:</td>
					<td><input type="radio" name="pwd" />账号密码</td>
				</tr>

				<tr>
					<td><input type="submit" value="下一步"  />
					</td>
				</tr>

			</table>
		</div>
	</form>
</body>
</html>