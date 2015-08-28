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
  	
  	
  	<input type="hidden" id="roleId" value="${bean.id}">
  	<div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
  	<div class="nav-collapse collapse">
  	
  	<ul class='nav'>
  		<li><a id='df_sub'>角色：${bean.descr } 对应的</a></li>
  		<li><a id='df_sub'  href='#org'><i class="icon-ok"></i>组织</a></li>
  		<li><a id='df_sub'  href='#orgAtt'><i class="icon-ok"></i>组织属性</a></li>
  		<li><a id='df_sub'  href='#member'><i class="icon-ok"></i>人员</a></li>
  	<li><a id='df_action_msg'></a></li></ul>
  	
  	</div></div></div></div>
  	<div id='org'>
  		${orgHtml}
	</div>
	<div id='orgAtt' style='margin-top:80px;'>
		${attHtml}
	</div>
  	<div id='member' style='margin-top:80px;'>
  	
	<ul id='orgtree' class="easyui-tree span3" data-options="url:'cfg_treeOrg.sg?isPage=1'" name='mbean.org.id'></ul>
  	<div id="tb">
  	
  		<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='$("#dg").datagrid("selectAll");'>全选</a>
  		<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='subMember(1);'>选择需要加入的人员点击提交</a>
  		<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='subMember(0);'>选择需要退出的人员点击提交</a>
  		<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='sear()'>搜索</a>
  		<p/>
  		名字：<input type='text' id='mdescr'>
  		<input type='checkbox' id='inRoleInput' value='1'>是否已加入
  	</div>
  	<table id="dg" class="easyui-datagrid span7" title="角色对应的人员：" style="height:400px;">
        <thead>
            <tr>
                <th data-options="field:'inRole'">已加入</th>
                <th data-options="field:'id',width:80">ID</th>
                <th data-options="field:'descr',width:100">描述</th>
            </tr>
        </thead>
    </table>
  	</div>
  	
  	<script src="bootstrap/bootstrap/js/bootstrap.min.js"></script>
  	<script>
  		function sear(){
  			var trrVal='';
  			var trr=$('#orgtree').tree('getSelected');
  			if(trr){
  				trrVal=trr.id;
  			}
  			$('#dg').datagrid({toolbar:'#tb',pageList:[100,50,20,10],rownumbers:true,pagination:true,
  				url:'cfg_c_Role_toMemberOrgAtt.sg?isPage=member&bean.id='+$('#roleId').val(),
  				queryParams:{'mbean.org.id':trrVal,'mbean.member.descr':$('#mdescr').val()}
  			
  				});
  		}
  		$(function(){
  			sear();
  			$('#orgtree').tree({onClick:function(node){
				sear();
  			}});
  		});
  		function subMember(iOro){
  			var ss=$('#dg').datagrid('getSelections');
			var lg = ss.length-1;
			var i=0;
			var loseName='人员:';
			var winName='人员:';
			var x=0;
			while(i<=lg){
				var row = ss[i];
				$.post('cfg_c_Role_toMemberOrgAtt.sg?isPage=memberSub&bean.id='+$('#roleId').val()+'&inOro='+iOro,{'mbean.member.id':row.id},function(json){
					if(json=='ok'){
						x++;
						winName+='{'+row.descr+' }';
					}else{
						loseName+='{'+row.descr+' }';
					}
					if(x==lg+1)
						alert('all ok');
					
				},'JSON');
				i++;
			}
  		}
  	</script>
  </body>
</html>

