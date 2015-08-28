<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>网站</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="sso_themes/login.css">
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/demo/demo.css">
	<script type="text/javascript" src="easyui-1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="easyui-1.3.2/jquery.easyui.min.js"></script>
	<style type="text/css">
		*{font-size:9pt;}
	</style>
	<script type="text/javascript" src="easyui/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/jQuery.md5.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#inputSpan").hide();
		var so = $("#SignOut").val();
    	if(so=='nobody'){
    		$("#inputSpan").show();
    	}
    	$("#url").val(location.href);
	});
	function checkValue() {
		var username = document.getElementById('txtUserName').value;
		var password = document.getElementById('txtPassword').value;
		if (username.length == 0) {
			alert('请输入用户名');
			document.getElementById('txtUserName').focus();
			return false;
		}
		if (password.length == 0) {
			alert('请输入密码');
			document.getElementById('txtPassword').focus();
			return false;
		}
		var md = $.md5($.md5(password));
		$('#txtPasswordVal').val(md);
		return true;
	} 
	</script>
	<script type="text/javascript">
		 window.onload=function(){
				if(top.location != self.location){
					top.location.href="loginPage.sg";
				}
				$("input").get(0).focus();
		 }
	</script>
  <base href="<%=basePath%>">
  </head>
  <body id="login">
    <form id="form1" action="login.sg" method="post">
    <input type="hidden" name="url" id="url">
    <div class="container">
        <div class="header">
           <div class="header-item">
                <table cellspacing="0" cellpadding="0" border="0">
                    <tbody><tr>
                        <td>
                            <img alt="logo" src="">
                        </td>
                        <td style=" font-size: 23px;">
                            ·网站
                        </td>
                    </tr>
                </tbody></table>
            </div>
        </div>
        <div class="content">
            <table cellspacing="0" cellpadding="0">
                <thead>
                    <tr>
                        <th>
                        </th>
                        <td style="vertical-align: top; padding-top: 16px;">
                            请输入您的用户名和密码登录内部系统<br>
						<span id="inputSpan">
						<font size="2" color="red"> 用户名/密码错误，或登陆会话失效，请重新登录！</font>
						</span>
                            <h2>
                            </h2>
                            <!---->
                        </td>
                    </tr>
                </thead>
                <tbody><tr>
                    <th>
                        用户名：
                    </th>
                    <td>
                        <input type="text" class="login_text" id="txtUserName" name="user.loginId">
                    </td>
                </tr>
                <tr>
                    <th>
                        密&nbsp;&nbsp;&nbsp;&nbsp;码：
                    </th>
                    <td>
                        <input type="password" class="login_text" id="txtPassword">
                        <input type="hidden" name="user.psd" id='txtPasswordVal'>
                    </td>
                </tr>
                <tr>
                    <th>
                    </th>
                    <td>
                        
                    </td>
                </tr>
                <tr>
                    <th>
                    </th>
                </tr>
                </tbody><tfoot>
                    <tr>
                        <th>
                        </th>
                        <td style="padding: 0;">
                             <input type="submit" class="inputbtn" id="btnLogin" onclick="return checkValue();" value="登录">
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
        <div class="footer">
        	
            <p>
            </p>
        </div>
        <div id="extraDiv1">
            <span></span>
        </div>
        <div id="extraDiv2">
            <span></span>
        </div>
    </div>
    <script type="text/javascript">
    </script>
    </form>
    <input type="hidden" id="SignOut" value="${user.code}">
    
  </body>
</html>
