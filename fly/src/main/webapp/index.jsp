<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
<body>
	<script type="text/javascript" src="easyui-1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			window.location.href='home.sg';
		});
	</script>
  </body>
</html>
