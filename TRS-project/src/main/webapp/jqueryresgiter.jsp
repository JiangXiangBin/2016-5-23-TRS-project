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
<link rel="stylesheet" type="text/css" href="./register/register.css">
<title>注册页面</title>
<style type="text/css">
body {
	background-image: url("<%=basePath%>image/2.jpg");
	background-repeat: no-repeat;
}

table {
	border-collapse: collapse;
	text-align: center;
	width: 350px;
	height: 300px;
	margin: auto;
	margin-top: 50px;
}

td {
	border: 1px solid gray;
}
</style>
<script type="text/javascript">
	//方法--f1到f4是为了实现获取焦点时候样式的转换

	//Ajax方面内容
	function getXhr() {
		//浏览器兼容性
		var hxr = null;
		if (window.XMLHttpRequest) {
			xhr = new XMLHttpRequest();
		} else {
			xhr = new ActiveXObject('Microsoft.XMLHTTP');
		}
		return xhr;
	}
	function gettext() {
		//获取Ajax对象
		var xhr = getXhr();
		//准备发送请求
		xhr.open('post', '/trs1/test/register.do', true);
		//如果要参入参数，要写消息头
		xhr.setRequestHeader('content-type',
				'application/x-www-form-urlencoded');
		//发送请求
		document.getElementById("span1").innerHTML = "正在检查";
		//获取文本框的值并传入到servlet
		var txt1 = document.getElementById("d1").value;

		if (txt1 == "" || txt1 == null) {

			document.getElementById("span1").innerHTML = "不能为空";

			return;
		} else {
			//发送
			xhr.send('name=' + txt1);
			//设置回调函数，接收服务器响应的文本,resposeText属性

			xhr.onreadystatechange = function() {
				//如果 数据接收成功获取请求成功
				if (xhr.readyState == 4 && xhr.status == 200) {

					//接收服务器返回的内容
					var tex1 = xhr.responseText;

					document.getElementById("span1").innerHTML = tex1;
				}
			};
		}
	}
	//验证密码的正则表达式
	$(document).ready(function(){
		$("#d2").blur(function() {
			getpwd();

		});

	});

	function getpwd() {

		var pwd = $("#d2").val();

		var zheng = /^[0-9a-zA-Z]{4,10}$/;

		if (zheng.test(pwd)) {
			$("#span2").html("可以使用").css("color", "blue");
		} else {
			$("#span2").html("密码格式错误").css("color", "red");
		}

	}

	function gettel() {
		//获取文本框的值
		var tel = document.getElementById("d3").value;
		//定义一段正则
		var zheng = /^[0-9]{4,10}$/;
		//判断是否匹配
		var set1 = zheng.test(tel);
		//获取span标签
		var span = document.getElementById("span3");

		if (set1) {
			//正确返回可以使用
			span.innerHTML = "可以使用";

		} else {
			span.innerHTML = "4-10位数字";
		}
	}
	function getemail() {
		//获取文本框的值
		var pwd = document.getElementById("d4").value;
		//定义一段正则
		var zheng = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		//判断是否匹配
		var set1 = zheng.test(pwd);
		//获取span标签
		var span = document.getElementById("span4");

		if (set1) {
			//正确返回可以使用
			span.innerHTML = "可以使用";

		} else {
			span.innerHTML = "例如:123456@qq.com";
		}
	}
</script>
</head>
<body>

	<form action="test/register1.do" method="post">

		<table>
			<tr id="text">
				<td colspan="3"><h2 style="font-style: oblique;">注册页面</h2>
				</td>
			</tr>

			<tr>
				<td>用户名:</td>
				<td><input type="text" name="name" id="d1" class="c1"/>
				</td>
				<td><span id="span1">${fail1}${fail2}${register1}</span>
				</td>

			</tr>

			<tr>
				<td>密&emsp;码:</td>
				<td><input type="password" name="pwd" id="d2" class="c1" />
				</td>
				<td><span id="span2"></span>
				</td>
			</tr>

			<tr>
				<td>电&emsp;话:</td>
				<td><input type="text" name="telphone" id="d3" class="c1"/>
				</td>
				<td><span id="span3"></span>
				</td>
			</tr>

			<tr>
				<td>邮&emsp;箱:</td>
				<td><input type="text" name="email" id="d4" class="c1" />
				</td>
				<td><span id="span4"></span>
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="注    册"
					style="width: 300px;height: 30px;background-color: lime;" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="直接登入"
					onclick="location='login.jsp'"
					style="width: 130px;height: 30px;background-color: lime;" />
				</td>


				<td><input type="reset" value="注    销"
					style="width: 130px;height: 30px;background-color: lime;" />
				</td>
			</tr>

		</table>
	</form>
</body>
</html>