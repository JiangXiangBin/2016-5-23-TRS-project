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
<title>增加角色</title>
<!-- CSS样式 -->
<style type="text/css">
body{
background: url("<%=basePath%>image/4.jpg") no-repeat;
}
div{
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
td{
border: 1px dashed gray;
}
tr:hover{
background-color: red;
}
.s1{
color: red;
}
.s2{
color: blue;
}
.s3{
color: yellow;
}
</style>
<!-- JS部分 -->
<script type="text/javascript">
var a=0;
var timer;
function huan(){
var color=document.getElementById("d1");
var fontcolor=["s1","s2","s3"];
color.className=fontcolor[a++];
if(a>2){
a=0;
}
}
window.onload=function(){
timer=setInterval(huan,500);

};
</script>
</head>
<!-- 主体部分 -->
<body>
<form action="add.do" method="post">
<div>
<table>
<tr><td colspan="3" id="d1"><h2>添加角色</h2></td></tr>
<tr>
<td>角色名:</td><td colspan="2"><input type="text" name="name"/></td>
</tr>
<tr><td id="d1" style="s1">性&emsp;别:</td><td colspan="2"><input type="text" name="gender"/></td></tr>

<tr><td id="d1">等&emsp;级:</td><td colspan="2"><input type="text" name="grade"/></td></tr>
<tr><td id="d1">血&emsp;量</td><td colspan="2"><input type="text" name="blood"/></td></tr>
<tr>
<td><input type="reset" value="重置"/></td>
<td><input type="submit" value="提交"/></td>
</tr>

</table>
</div>
</form>
</body>
</html>