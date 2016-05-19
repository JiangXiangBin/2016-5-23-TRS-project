<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆页面</title>
<style type="text/css">
#div1 {
	border: 1px solid gray;
	width: 250px;
	height: 400px;
	text-align: center;
	margin-top: 100px;
	margin: 0px auto;
	margin-top: 100px;
}

body {
	background: url("<%=basePath%>image/2.jpg") no-repeat;
}

.cc {
	background-color: blue;
}

#ul1 {
	list-style-image: url("image/down.png");
}

.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript">
	function f1() {

		var a = document.getElementById("d1").className = "c1";

	}
	function f2() {

		var a1 = document.getElementById("d2").className = "c1";

	}
	function f3() {
		var a2 = document.getElementById("d3").className = "c1";

	}
	$.validator.setDefaults({
		submitHandler : function(form) {
			alert("提交事件!");
			form.submit();
		}
	});

	$().ready(
			function() {
				// 提交时验证表单
				var validator = $("#form1").validate(
						{
							errorPlacement : function(error, element) {
								// Append error within linked label
								$(element).closest("#form1").find(
										"label[for='" + element.attr("id")
												+ "']").append(error).css(
										"color", "red");
							},
							//
							errorElement : "span",
							messages : {
								name : {
									//必选字段
									required : " (必需字段)",
									minlength : " (不能少於2個字母)"
								},
								pwd : {
									required : " (必需字段)",
									minlength : " (字母不能少於 5 個且不能大於 12 個)",
									maxlength : " (字母不能少於 5 個且不能大於 12 個)"
								}
							}
						});
			});
</script>
</head>
<body>
	<!-- 
	<form action="/trs1/test/login.do" method="post" id="form1">
		<div id="div1">
			<table>

				<tr>
					<td colspan="4"><h1>登陆页面</h1></td>
				</tr>

				<tr>
					<td colspan="4"><span style="font-style: oblique;color: red;">${fail}${emp}</span>
					</td>
				</tr>
				<tr>
					<td colspan="4"><label for="d1"></label>
					</td>
				</tr>
				<tr>
					<td>用户名:</td>
					<td colspan="3"><input type="text" id="d1" name="name"
						value="請輸入用戶名" onfocus="this.value='';this.className='cc'"
						onblur="if(this.value==''){this.value='請輸入用戶名'};f1();" required
						minlength="2" /></td>


				</tr>

				<tr />
				<tr />
				<tr />
				<tr />
				<tr>
					<td colspan="4"><label for="d2"></label>
					</td>
				</tr>
				<tr>
					<td>密&emsp;码:</td>
					<td colspan="3"><input type="text" id="d2" name="pwd"
						value="請輸入密碼" onfocus="this.value='';this.className='cc'"
						onblur="if(this.value==''){this.value='請輸入密碼'};f2();" required
						minlength="5" /></td>
					<td><span></span>
					</td>

				</tr>
				<tr />
				<tr />
				<tr />
				<tr />


				<tr />
				<tr />
				<tr />
				<tr />

				<tr>
					<td><a href="find.jsp">忘记密码?</a></td>
				</tr>
				<tr />
				<tr />
				<tr />
				<tr />

				<tr>
					<td colspan="4"><input type="submit" value="登陆"
						style="width: 200px;height: 30px;background-color: lime;font-size: 15pt;" />
					</td>
				</tr>
				<tr />
				<tr />
				<tr />
				<tr />

				<tr>
					<td colspan="4"><input type="reset" value="重置"
						style="width: 200px;height: 30px;background-color:lime; font-size: 15pt;" />
					</td>
				</tr>
				<tr />
				<tr />
				<tr />
				<tr />

				<tr>
					<td colspan="4"><a href="/trs1/test/registerout.do">没有账号？点击注册</a>
					</td>
				</tr>
			</table>
		</div>
	</form>

 -->


	<form:form method="POST" commandName="student" 
		action="/trs1/test/login.do">
		<table>
			<tr>
				<td>Enter your username:</td>
				<td><form:input path="username" />
				</td>
				<td><form:errors path="username" cssStyle="color: #ff0000;" />
				</td>
			</tr>
			<tr>
				<td>Enter your password:</td>
				<td><form:input path="pwd" />
				</td>
				<td><form:errors path="pwd" cssStyle="color: #ff0000;" />
				</td>
			</tr>

			<tr>
				<td><input type="submit" name="submit" value="Submit">
				</td>
			</tr>
			<tr>
		</table>
	</form:form>






</body>
</html>