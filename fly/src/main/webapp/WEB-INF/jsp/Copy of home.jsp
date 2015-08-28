<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>网站</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/table.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/demo/demo.css">
	<script type="text/javascript" src="easyui-1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>  
	
<script type="text/javascript">

		function addTab(title,href){  
			var tt = $('#maincenter');  
			if (tt.tabs('exists', title)){  
			   tt.tabs('select', title);  
	   		} else {  
	   			if(href){
	   				var content = '<iframe scrolling="auto" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>'
	   			}else{
	   				var content = '建设中..';
	   			}
	   			tt.tabs('add',{  
					title:title,  
					closable:true,  
					content:content 
				});  
	   		}
		}
		
		function clsTab(title){
			var tt = $('#maincenter');  
			if (tt.tabs('exists', title)){  
			   tt.tabs('close',title);
			}
		}
		$(function(){
			var isIE=!!window.ActiveXObject;
			var isIE6=isIE&&!window.XMLHttpRequest;
			if (isIE6){
		        alert('该系统不支持ie6,请换高版本的浏览器,谢谢!');
		    }else{
				var ul = $("#url").val();
				if(ul!=""&&ul!=null){
					window.location.href=ul;
				}
		    }
		});
</script>
    <base href="<%=basePath%>">
  </head>
	<body class="easyui-layout">
	<input type="hidden" id="url" value="${url}">
${bodyHtml}
	<div region="center" style="overflow:hidden;">
		<div class="easyui-tabs" fit="true" border="false" id="maincenter">
			<div title="主页" tools="#p-tools" closable="true" style="padding:20px;">
					<s:action name="toWork" executeResult="true" namespace="/flow"></s:action>
				</div>
		</div>
	</div>
  </body>
</html>
