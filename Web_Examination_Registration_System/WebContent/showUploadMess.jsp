<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="mybean.data.*"%>
<jsp:useBean id="upFile" type="mybean.data.uploadFileBean"
	scope="request" />
<%
String path=request.getContextPath();
String basePath = 
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+
path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath %>>">
<title>ְ�Ƽ�������Ա���</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="���ϱ�����ְ�ƿ��ԣ������">
<meta http-equiv="description" content="���ϱ�����ҳ">
<link href="css/mystylesheet.css" rel="stylesheet" type="text/css" />
</head>
<body class="twoColHybLtHdr">
	<div id="container">
		<div id="header">
			<%@ include file="txtfile/header.txt"%>
		</div>
		<div id="sidebar1">
			<%@ include file="txtfile/left.txt"%>
		</div>
		<div id="mainContent">
			<center>
				<p>
					<font size=4 color=red>��ӭ��ʹ��ͼƬ�ϴ����ܣ�</font>
				</p>
			</center>
			<center>
				
				<font size=2 color=blue> 
					<br><jsp:getProperty name="upFile" property="backMessage" /> 
						�ϴ����ļ�����<jsp:getProperty name="upFile" property="fileName" /> 
					<br>�������ļ�����<jsp:getProperty name="upFile" property="savedFileName" /> 
					<br>
				</font> 
				<img src="image/<jsp:getProperty name="upFile" property="savedFileName" />" width=150 height=120>ͼ��Ч��
			</center>
		</div>
		<br class="clearfloat" />
		<%@ include file="txtfile/footer.txt"%>
	</div>
</body>
</html>