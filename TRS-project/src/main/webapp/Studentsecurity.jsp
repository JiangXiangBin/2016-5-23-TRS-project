<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>
	<form action="/trs1/test/addson.do">
		<table>
			<tr>
				<td>welcome:</td>
				<td><span>${student.username}</span>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" value="${student.username}" name="username" />
				</td>
			</tr>
			<tr>
				<td>证券号码1:</td>
				<td><input type="text" id="numble1" name="numbleone"/>
				</td>
			</tr>
			<tr>
				<td>证券号码2:</td>
				<td><input type="text" id="numble2" name="numbletwo"/>
				</td>
			</tr>
			
			<tr><td colspan="2"><input type="hidden" name="id" value="${student.id}"/></td></tr>
			<tr>
				<td><input type="submit" value="提交" />
				</td>
				<td><input type="reset" value="重置" />
				</td>
			</tr>
		</table>
	</form>
</body>





</html>