<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
		<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>

<style type="text/css">
#div1{
border: 1px solid gray;

width:350px;
height: 300px;
text-align: center;
margin-top:100px;
margin: 0px auto;
margin-top: 100px;
}

body{
background: url("<%=basePath%>image/2.jpg") no-repeat ;


}

</style>
</head>
<body>
<div id="div1">
	<form action="test/updatepwd.do" method="post">
<table>
<tr><td colspan="3"><h1>修改密码</h1></td></tr><tr/><tr/>
<tr><td> 用户名:</td><td><input type="text" name="name" value="${find.username}"/></td><td></td></tr><tr/><tr/>
<tr><td> 新密码:</td><td><input type="password"	name="pwd" /> </td><td><span>${no}${kong}</span></td></tr><tr/><tr/>
<tr><td>再次输入:</td><td><input type="password" name="pwd1" /></td><td><span>${no}${kong}</span></td></tr><tr/><tr/>
<tr><td colspan="3"><input type="submit" value="修改" style="width: 250px ;background-color: lime;"  /></td></tr>

</table>

	</form>

</div>
</body>
</html>