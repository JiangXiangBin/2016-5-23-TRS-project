<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆成功的页面</title>

<script type="text/javascript">
	
<%--Object object = session.getAttribute("hello1");

			if (object == null) {

				response.sendRedirect("login.jsp");

				return;
				
			}--%>
function huan(){
document.getElementById("div1").style.display="block";

}
function huan1(){
document.getElementById("div1").style.display="none";
}


</script>
<style type="text/css">
#div1{
background-color: orange;
width: 50px;
height: 80px;
color: white;
text-align: center;
display: none;
}	

</style>
</head>
<body><a href="logout.do">注销</a>
	<h1>欢迎${success.username}</h1>
	<a href="finduser.do?name=${success.username}&pwd=${success.pwd}" >我的资料</a><br/><br/>
	
	<div><a onmouseover="huan();" onclick="huan1();">操&emsp;&emsp;作</a>
	<div id="div1">
	<a href="findsontwo.do?id=${success.id}">查&emsp;询</a><br/><br/>
	<a href="findsonone.do?id=${success.id}">增&emsp;加</a><br/><br/>
	</div>
	</div>
</body>
</html>