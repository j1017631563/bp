<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="../res/taglib.jsp" %>
<!DOCTYPE html>
<html>
  <head> 
    <title>用户管理</title> 
	<%@include file="../res/iframe_res_css.jsp" %>
	<style type="text/css">
		.panel-body{
			border:none
		} 
	</style>
  </head>
  
  <body class="easyui-layout" > 
	<div data-options="region:'center'">
		<div id="tb" style="padding:5px;height:auto">    
		    <div style="margin-bottom:5px;float: right">    
		        <a href="javaScript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openDialog('add','新增用户信息');"></a>    
		        <a href="javaScript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openDialog('edit','修改用户信息');"></a> 
		        <a href="javaScript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delUserInfo();"></a>    
		        <a href="javaScript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reloadPage();"></a>    
		    </div>     
		       
	                   创建时间 : <input class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,prompt:'开始时间'" id="beginDate" style="width:100px" placeholder="开始时间" >    
	        - <input class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,prompt:'截止时间'" id="finishDate" style="width:100px" placeholder="结束时间">    
	                   账号:<input class="easyui-textbox" placeholder="账号" id="account" prompt='账号'/>  
	                   姓名:<input class="easyui-textbox" placeholder="姓名" id="username" prompt='姓名'>
	                   状态:<select id="status" class='easyui-combobox'>
	                <option value='-1'>所有</option>
	        		<option value='1'>正常</option>
	        		<option value='0'>禁用</option>
	        	</select>      
	        <a href='javaScript:void(0);' class="easyui-linkbutton" onclick="queryUser();">查&nbsp;询</a>     
		</div>   
		<table id="userInfo_list"> 
    		 
		</table> 
	</div> 
	
	<div id="dialog" class="easyui-window" closed="true" modal="true" collapsible="false" minimizable="false" maximizable="false">  
	    <form style="padding:10px;" id="userFrom"> 
	    	<table>
	    		<tr>
	    			<td><label for="username">姓名</label></td>
	    			<td>
	    			<input type="hidden" name="id"/>
	    			<input class="easyui-textbox" name="username" prompt="用户名" ><font color="red">*</font></td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<label for="account">账&nbsp;号</label>
	    			</td>
	    			<td><input class="easyui-textbox" name="account" id="accountid"  prompt="账号"><font color="red">*</font></td>
	    		</tr>
	    		<tr>
	    			<td><label for="password">密&nbsp;码</label></td>
	    			<td><input class="easyui-textbox" name="password" id="password"  prompt="密码"><font color="red">*</font></td>
	    		</tr>
	    		<tr>
	    			<td><label for="sex">性&nbsp;别</label></td>
	    			<td>
		    			<input type="radio" name="sex" value="1" checked="checked">男
		        	 	<input type="radio" name="sex" value="0">女
		        	 </td>
	    		</tr>
	    		<tr>
	    			<td><label for="status">状&nbsp;态</label> </td>
	    			<td>
		    			<select name="status" class='easyui-combobox' id='statusbox'>
			        		<option value='1'>正常</option>
			        		<option value='0'>禁用</option>
			        	</select>
		        	</td>
	    		</tr>
	    		<tr>
	    			<td><label for="descrit">备&nbsp;注</label></td>
	    			<td>
		    			<input class="easyui-textbox" data-options="multiline:true" style="height:60px;width:300px" name="descrit" id="descrit" prompt="人员职责描述..."/>
		        		<!-- <font color="red">*</font> -->
		        	</td>
	    		</tr>
	    		<tr>	
	    			<td colspan="2" align="right">
	           	 		<a href='javaScript:void(0);' class="easyui-linkbutton" icon="icon-save" onclick="saveOrUpdate();">确认保存</a>
	    			</td>
	    		</tr>
	    	</table>  
	    </form>  
	</div>
	
	<%@include file="../res/iframe_res_js.jsp" %> 
	<script src='${pageContext.request.contextPath}/resource/js/userinfo.js'></script> 
	
  </body>
</html>
