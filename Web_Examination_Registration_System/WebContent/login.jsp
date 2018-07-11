<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String path =request.getContextPath();
	String basePath=request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath %>">
	<title>职称计算机考试报名</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="网上报名，职称考试，计算机">
	<meta http-equiv="description" content="网上报名首页">
	<link href="css/mystylesheet.css" rel="stylesheet" type="text/css"/>
</head>
<body class="twoColHybLtHdr">
	<div id="container">
		<div id="header">
			<%@ include file="txtfile/header.txt" %>
		</div>
		<div id="sidebar1">
			<%@ include file="txtfile/left.txt" %>
		</div>
		<div id="mainContent">
			<center>
				<p><font size=4 color=red>欢迎您使用登录功能！</font></p>
			</center>
			<blockquote>
				<center>
				<form action="helpLogin" name="form" method="post">
				<p>输入您的信息，身份证号必须由字母或数字组成
				<p>
				<table bgcolor="#CCFFCC">
				<tr><th colspan="2">请您登录</th></tr>
				<tr>
				<td>身份证号：</td><td><Input name="loginName" type=text id="loginName">*</td></tr>
				<tr>
				<td>输入密码：</td><td><Input name="password" type=password id="password"></td></tr>
				<tr align="center"><td colspan="2"><input type="submit" value="提交" name="submit"></td></tr>
				</table>
				</form>
				</center>
			</blockquote>
		</div>
		<br class="clearfloat" />
		<%@ include file="txtfile/footer.txt" %>
	</div>

</body>
</html>