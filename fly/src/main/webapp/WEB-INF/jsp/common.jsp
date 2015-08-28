<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${page_title}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="kalendae/css/kalendae.css">
	<link rel="stylesheet" type="text/css" href="css/table.css"/>
	<link rel="stylesheet" type="text/css" href="css/link.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/demo/demo.css">
	<link href="bootstrap/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/assets/styles.css" rel="stylesheet" media="screen">
    <style type="text/css">
     * {font-size: 15px;}
      .row-fluid [class*="span"]:first-child {
   		margin-left: 2.12766%;
		}
	</style>
	<style media=print>
		.noprint{display:none;}
	</style> 
	<script type="text/javascript" src="kalendae/js/kalendae.js"></script>
	<script type="text/javascript" src="easyui-1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>  
	<script type="text/javascript" src="easyui-1.3.2/easyloader.js"></script>
<script>
	${jsHtml}
</script>
  </head>
  <body>
  	${bodyHtml}
  	
  	<script src="bootstrap/bootstrap/js/bootstrap.min.js"></script>
  	<script>
  	$('#dg').datagrid({
  		onDblClickCell: function(index,field,value){
  			$(this).datagrid('beginEdit', index);
  			var ed = $(this).datagrid('getEditor', {index:index,field:field});
  			$(ed.target).focus();
  		}
  	});
	</script>
  </body>
</html>

