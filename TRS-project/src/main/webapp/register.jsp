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

.c1 {
	border: 1px solid blue;
}

.cc {
	background-color: orange;
}

tr:hover {
	background-color: blue;
}

h1 {
	text-align: center;
}

span {
	color: red;
}

.ok {
	background-image: url("image/ok.png");
	padding: 12px;
	background-repeat: no-repeat;
	margin-right: 100px;
}

.ok:hover {
	background-color: yellow;
}
</style>
<!-- 引用J Q U E R Y -->
<script type="text/javascript" src="jquery/jquery-1.8.1.js"></script>
<!-- 引用jquery.validate.js -->
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript">
	//使用jquery.validate.js编写的验证信息
	//remote  远程地址只能输出 "true" 或 "false"，不能有其他输出。
	//所有controller里面的out.print()里面的内容得改下
	$(function() {
		//表单验证JS
		$("#myform").validate(
				{
					//出错时添加的标签
					errorElement : "span",
					errorPlacement : function(error, element) {

						$(element).closest("#myform").find(
								"label[for='" + element.attr("id") + "']")
								.append(error);

					},
					errorElement : "span",
					rules : {
						name : {
							required : true,
							minlength : 2,
							maxlength : 5,
							remote : {
								type : "post",
								url : "/trs1/test/register.do",
								data : {
									name : function() {
										{
											return $("#d1").val();
										}
									}
								},
								dataType : "html",
							}
						},
						pwd : {
							required : true,
							minlength : 5,
							maxlength : 10
						},
						pwd1 : {
							required : true,
							minlength : 5,
							maxlength : 10,
							equalTo : "#d2"
						},
						telphone : {
							required : true,
							minlength : 6,
							maxlength : 13

						},

						email : {
							required : true,
							email : true
						},
					},
					messages : {
						name : {
							required : "(请输入账号)",
							minlength : "不能小于2个字符",
							maxlength : "不能大于5个字符",
						//remote    : "用户名不可用"
						//remote : "name=" + $("#d1").val(),
						},

						pwd : {
							required : "(请输入密码)",
							minlength : "不能小于5个字符",
							maxlength : "不能大于10个字符"
						},
						pwd1 : {
							required : "(请输入密码)",
							minlength : "不能小于5个字符",
							maxlength : "不能大于10个字符",
							equalTo : "密码输入不一致"
						},
						telphone : {
							required : "(请输入号码)",
							minlength : "不能小于6个字符",
							maxlength : "不能大于13个字符"

						},

						email : {
							required : "(请输入邮箱)",
							email : "邮箱格式错误"
						},
					}

				});
	});

	//使用jquery边写的验证
	/*
	 $(document).ready(function() {
	 $("#d1").blur(function() {

	 gettext();
	 });
	 });

	 function gettext() {
	 var username = $("#d1").val();

	 if (username == null || username == "") {
	 $("#span1").html("不能为空").css("color", "red");
	 return false;
	 }
	 $.ajax({
	 //请求方式
	 type : "post",
	 //异步刷新
	 async : false,
	 //数据提交路径
	 url : "/trs1/test/register.do",
	 //服务器返回的数据类型
	 dataType : "html",
	 //提交的数据
	 data : "name=" + username,

	 //请求之前发送
	 beforeSend : function(XMLHttpRequest) {

	 $("#span1").html("正在查询..").css("color", "red");
	 },
	 //请求成功之后的回调函数
	 success : function(msg) {

	 //alert(msg == "可以使用");//这句话打印出来的是true

	 if (msg == "可以使用") {
	 $("#span1").html(msg).css("color", "blue");
	 } else {
	 $("#span1").html(msg).css("color", "red");
	 }
	 },
	 error : function(error) {

	 alert("服务器没有返回数据，可能服务器忙，请重试");
	 },
	 });
	 }
	
	//验证密码的正则表达式
	$(function() {

		$("#d2").blur(function() {
			getpwd();

		});
	});
	function getpwd() {

		var pwd = $("#d2").val();

		var zheng = /^[0-9a-zA-Z]{4,10}$/;

		if (pwd.length <= 0) {
			$("#span2").html("不能为空").css("color", "red");
			return false;
		} else if (zheng.test(pwd)) {
			$("#span2").html("可以使用").css("color", "blue");
			return true;
		} else {
			$("#span2").html("密码格式错误").css("color", "red");
			return false;
		}
	}
	//电话号码验证
	$(function() {
		$("#d3").blur(function() {
			gettel();
		});
	});
	function gettel() {

		var tel = $("#d3").val();

		var zheng = /^[0-9]{4,10}$/;

		if (tel.length <= 0) {
			$("#span3").html("不能为空").css("color", "red");
			return false;
		} else if (zheng.test(tel)) {
			$("#span3").html("可以使用").css("color", "blue");
			return true;
		} else {
			$("#span3").html("手机格式错误").css("color", "red");
			return false;
		}
	}
	//电话号码验证

	$(function() {
		$("#d4").blur(function() {
			getemail();
		});
	});
	function getemail() {

		var email = $("#d4").val();
		//定义一段正则
		var zheng = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;

		if (email.length <= 0) {
			$("#span4").html("不能为空").css("color", "red");
			return false;
		} else if (zheng.test(email)) {
			$("#span4").html("可以使用").css("color", "blue");
			return true;
		} else {
			$("#span4").html("邮箱格式错误").css("color", "red");
			return false;
		}
	}
	$(function() {

		$("#myform").submit(function() {

			//alert("gettext()----" + gettext());//这个的到的是没有定义的
			//alert("getpwd()----" + getpwd());
			//alert("gettel()----" + gettel());
			//alert("getemail()----" + getemail());

			if (getpwd() && gettel() && getemail()) {
				alert("注册成功");
				return true;
			} else {
				alert("注册失败");
				return false;
			}
		});
	});
	 */
</script>
</head>
<body>

	<form action="test/register1.do" method="post" id="myform">

		<table>
			<tr id="text">
				<td colspan="3"><h2 style="font-style: oblique;">注册页面</h2></td>
			</tr>

			<tr>
				<td>用户名:</td>
				<td><input type="text" name="name" id="d1" class="c1" /></td>
				<!--  <td><span id="span1" >${fail1}${fail2}${register1}</span></td> -->
				<td><label for="d1"></label></td>

			</tr>

			<tr>
				<td>密&emsp;码:</td>
				<td><input type="password" name="pwd" id="d2" class="c1" /></td>
				<!--  <td><span id="span2"></span>-->
				<!-- 这个jquery里面要使用的 -->
				<td><label for="d2"></label></td>
			</tr>
			<tr>
				<td>密&emsp;码:</td>
				<td><input type="password" name="pwd1" id="d22" class="c1" />
				</td>
				<!--  <td><span id="span2"></span>-->
				<td><label for="d22"></label></td>
			</tr>

			<tr>
				<td>电&emsp;话:</td>
				<td><input type="text" name="telphone" id="d3" class="c1" /></td>
				<!--  <td><span id="span3"></span>-->
				<td><label for="d3"></label></td>
			</tr>

			<tr>
				<td>邮&emsp;箱:</td>
				<td><input type="text" name="email" id="d4" class="c1" /></td>
				<!--  <td><span id="span4"></span>-->
				<td><label for="d4"></label></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="注    册"
					style="width: 300px;height: 30px;background-color: lime;" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="直接登入"
					onclick="location='login.jsp'"
					style="width: 130px;height: 30px;background-color: lime;" /></td>


				<td><input type="reset" value="注    销"
					style="width: 130px;height: 30px;background-color: lime;" /></td>
			</tr>

		</table>
	</form>
</body>
</html>