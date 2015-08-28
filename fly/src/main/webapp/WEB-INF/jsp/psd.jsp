<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="easyui/demo.css">
<link href="bootstrap/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
       <link href="bootstrap/assets/styles.css" rel="stylesheet" media="screen">
</head>
<body>
	<input value='${msg}' id="msgVal" type="hidden">
	  <div class="span11" id="content">
                      <!-- morris stacked chart -->
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">密码修改</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span11">
                                     <form class="form-horizontal" id="ff" method="post">
                                      <fieldset>
                                        <legend>密码修改</legend>
                                        <div class="control-group">
                                          <label class="control-label">原密码:</label>
                                          <div class="controls">
                                            <input class="span6"   type="password" name="user.psd" required="true"/>
                                          </div>
                                        </div>
                                        
                                        <div class="control-group">
                                          <label class="control-label">密码:</label>
                                          <div class="controls">
                                            <input class="span6"  type="password" name="psd" required="true"/>
                                          </div>
                                        </div>
                                        
                                        <div class="control-group">
                                          <label class="control-label">原密码:</label>
                                          <div class="controls">
                                            <input class="span6"   type="password" name="psd2" required="true"/>
                                          </div>
                                        </div>
                                        
                                         <div class="form-actions">
                                          <button type="submit" class="btn btn-primary">修改密码</button>
                                          <button type="reset" class="btn">重置</button>
                                        </div>
                                        
                                        </fieldset>
                                        </form>
                                    </div>
                                </div>
                              </div>
                         </div>
                      </div>
	  <script type="text/javascript" src="easyui-1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>  
	<script type="text/javascript" src="easyui-1.3.2/easyloader.js"></script>
	    <script src="bootstrap/bootstrap/js/bootstrap.min.js"></script>
	    <script>
	$(function(){
		if($('#msgVal').val()!=''){
			alert($('#msgVal').val());
		}
		$('#ff').form({
			url:'psd.sg',
	        onSubmit:function(){  
	            return $(this).form('validate');  
	        } 
	    });  
	});
</script>
</body>
</html>