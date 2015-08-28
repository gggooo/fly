<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<link href="bootstrap/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
       <link href="bootstrap/assets/styles.css" rel="stylesheet" media="screen">
  </head>
  <body style='padding-top: 10px;'>
   <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">差异分析</div>
                                <div class="pull-right"><span class="badge badge-warning">更多</span>

                                </div>
                            </div>
                            <div class="block-content collapse in">
  	${bodyHtml}
  	</div>
                        </div>
                        <!-- /block -->
                    </div>
                    
                    
                     <div class="row-fluid">
                      <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">总体分析</div>
                                <div class="pull-right"><span class="badge badge-warning">更多</span>

                                </div>
                            </div>
                            <div class="block-content collapse in">
                     ${bodyHtml2}
                     </div>
                        </div>
                        <!-- /block -->
                    </div>
  	<script src="echarts/build/dist/echarts.js"></script>
<script type="text/javascript">
require.config({
    paths: {
        echarts: 'echarts/build/dist'
    }
});

	${jsHtml}

</script>
	<script type="text/javascript" src="easyui-1.3.2/jquery-1.8.0.min.js"></script>
    <script src="bootstrap/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>

