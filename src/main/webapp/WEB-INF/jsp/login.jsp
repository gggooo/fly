<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Admin Login</title>
    
	<link href="bootstrap/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/assets/styles.css" rel="stylesheet" media="screen">
     <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="bootstrap/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
  </head>
  <body id="login">
    <div class="container">
    <form class="form-signin" id="form1" action="login.sg" method="post">
    <input type="hidden" name="url" id="url">
    <input type="hidden" id="SignOut" value="${user.id}">
      <span id="inputSpan">
						<font size="2" color="red"> 用户名/密码错误，或登陆会话失效，请重新登录！</font>
						</span>
        <h2 class="form-signin-heading">请登录</h2>
        <input id="txtUserName" name="user.loginId" type="text" class="input-block-level" placeholder="工号">
        <input id="txtPassword" type="password" class="input-block-level" placeholder="密码">
                        <input type="hidden" name="user.psd" id='txtPasswordVal'>
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> 记住我
        </label>
        <button class="btn btn-large btn-primary" type="submit" onclick="return checkValue();">登陆</button>
      </form>

    </div> <!-- /container -->
    <script src="bootstrap/vendors/jquery-1.9.1.min.js"></script>
    <script src="bootstrap/bootstrap/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="js/jQuery.md5.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#inputSpan").hide();
		var so = $("#SignOut").val();
    	if(so=='0'){
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
		 }
	</script>
    
  </body>
</html>
