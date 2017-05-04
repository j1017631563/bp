<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="res/taglib.jsp" %>
<!DOCTYPE html>
<html> 
	<head>
		<meta charset="utf-8">
		<title>权限管理平台|用户管理</title> 
		<%@include file="res/iframe_res_css.jsp" %>
		<style>   
			article, aside, figure, footer, header, hgroup,	menu, nav, section{display:block;}   
	  		.west {
	  			width:200px; 
	  		    padding:10px;  
	  		   
	  		 }   
	  		 .north {
	  		 	height: 80px ;
	  		 	background-color: #efefef;  
	  		 } 
	  		 .south{
	  		 	height:50px;
	  		 	line-height:50px;
	  		 	vertical-align:middle;
	  		 	text-align: center; 
	  		 }
	  		  
  		</style>
	</head>

	<body class="easyui-layout">
		<!-- 头部 -->
		 <div region="north" class="north"> 
			<h1>权限管理平台V1.0</h1>
			<div style="height:auto;width:180px;float:right;text-align: right;margin-right:10px">
				欢迎${user.username }.<a>用户中心</a>|<a style='color:#76BFF1' href="javaScript:void(0);" onclick="loginout();">退出登录</a>
			</div>
  		</div> 
  		
	    <div region="center" title="平台面板"> 
		    <div class ="easyui-tabs"  fit="true"  border="false" id="tabs"> 
		       <div title ="欢迎使用"> 
			       	欢迎使用,权限管理平台V1.0！
		       </div> 
		     </div> 
	    </div> 
	    
	   <div region="west" class="west"  title="平台菜单"> 
	     	<ul id="sys_menu" data-options="animate:true,dnd:true"></ul>
	     	<ul id="platform_menu" data-options="animate:true,dnd:true"></ul>  
	   </div> 
  
	   <div id="tabsMenu"  class="easyui-menu"  style ="width:120px;">    
	     <div name ="close">关闭 </div>    
	     <div name ="Other">关闭其他 </div>    
	     <div name ="All"> 关闭所有 </div> 
	   </div> 
	   
	   <div region="south" class="south" style="overflow-y: hidden">
	   		&copy; 2016 权限管理平台   
	   </div>
	   
	   <%@include file="res/iframe_res_js.jsp" %>
	   <script src='${pageContext.request.contextPath}/resource/js/console.js'></script> 
	</body>

</html>