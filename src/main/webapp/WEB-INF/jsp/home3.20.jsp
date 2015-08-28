<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
    
  <head>
    
    <title>网站</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/table.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="easyui-1.3.2/demo/demo.css">
	
	<link href="bootstrap/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
       <link href="bootstrap/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
       <link href="bootstrap/assets/styles.css" rel="stylesheet" media="screen">
       <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
       <!--[if lt IE 9]>
           <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
       <![endif]-->
  </head>
	<body>
	<input type="hidden" id="url" value="${url}">
	<div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">APPLICATION</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> ${user.descr} <i class="caret"></i>

                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href='javascript:void(0)' onclick='javascript:addTab("修改密码","psdPage.sg");'>修改密码</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1" href="logout.sg">注销</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav">
                        	${navHtml }
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class='container-fluid'> <div class='row-fluid'>
${bodyHtml}
		<div class="span12">
		 	<div class="row-fluid">
                        	<div class="navbar">
                            	<div class="navbar-inner">
	                                <ul class="breadcrumb">
	                                    <li>
	                                        <a href="#" id='a1'></a> <span class="divider">/</span>	
	                                    </li>
	                                    <li>
	                                        <a href="#" id='a2'></a> <span class="divider">/</span>	
	                                    </li>
	                                </ul>
                        	</div>
                    	</div>
            <div class="row-fluid" id='maincenter' style='height:500px;'>
				
			</div>
		</div>
        </div>
        </div>
	</div>
	
       <script src="bootstrap/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
       <script type="text/javascript" src="easyui-1.3.2/jquery-1.8.0.min.js"></script>
        <script src="bootstrap/bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/assets/scripts.js"></script>
	
<script type="text/javascript">

		function addTab(title,href){  
			var tt = $('#maincenter');  
	   		var content = '<iframe scrolling="auto" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>'
	   		tt.html(content);
	   		$('#a2').html(title);
		}
</script>
	
	
  </body>
</html>
