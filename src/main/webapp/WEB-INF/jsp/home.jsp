<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
    
  <head>
    
    <title>管理·后台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<link href="bootstrap/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
       <link href="bootstrap/assets/styles.css" rel="stylesheet" media="screen">
       <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
       <!--[if lt IE 9]>
           <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
       <![endif]-->
       <style type="text/css">
      * {font-size: 18px;}
		</style>
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
                                        <a tabindex="-1" href='javascript:void(0)' onclick='javascript:addTab("","修改密码","psdPage.sg");'>修改密码</a>
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
                         <ul class="breadcrumb" id='tabUl'>
                            
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
	            <div class="row-fluid" id='maincenter'>
					
				</div>
		</div>
        </div>
        </div>
	</div>
	
       <script type="text/javascript" src="easyui-1.3.2/jquery-1.8.0.min.js"></script>
        <script src="bootstrap/bootstrap/js/bootstrap.min.js"></script>
	
<script type="text/javascript">
		var tabs="";
		var onHref="";
		var onObj;
		function addTab(t1,title,href){  
			var tt = $('#maincenter');  
			var hs=tabs.split(',');
			if(tabs.indexOf(href, 0)==-1){
		   		var content = '<iframe id="'+href+'" scrolling="auto" frameborder="0"  src="'+href+'" style="width:100%;height:100%;padding-top:50px;"></iframe>'
		   		tt.prepend(content);
		   		location.hash=href;
		   		$('#tabUl a').css('color','black');
		   		$('#tabUl').prepend('<li> <a href="javascript:void(0)" onclick="javascript:findTab(this,\''+href+'\')" ondblclick="javascript:closeTab(this,\''+href+'\')" >'+title+'</a> <span class="divider">/</span>	');
		   		tabs=href+','+tabs;
			}else{
				//根据href找到tabs中href的位置，href的位置与<tabUl>中各个<a>的次序一致。
				var ind=0;
				for(;ind<hs.length;ind++){
					if(href==hs[ind])
						break;
				}
				findTab($('#tabUl a').eq(ind)[0],href);
			}
		}
		//有了相关iframe后，只需要显示<iframe>和高亮<a>
		function findTab(obj,href){  
			location.hash=href;
	   		$('#tabUl a').css('color','black');
	   		$(obj).css('color','#08c');
		}
		
		function closeTab(obj,href){  
			var hs=tabs.split(',');
			var ind=0;
			for(;ind<hs.length;ind++){
				if(href==hs[ind])
					break;
			}
			$('#tabUl li').eq(ind).remove();
	   		$('#maincenter iframe').eq(ind).remove();
	   		tabs=tabs.replace(href+',', '');
		}
		$(function(){
			$('#maincenter').css('height',$(window).height()-70);
			$('#zy').click();
		});
</script>
	
	
  </body>
</html>
