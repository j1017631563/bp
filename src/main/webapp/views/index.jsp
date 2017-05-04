<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
  <head>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="权限管理平台,用户登录">
	<meta http-equiv="description" content="权限管理平台-用户登录">
	<title>权限管理平台-用户登录</title>
	<%@include file="res/page_res.jsp" %>
  </head>
  
  <body>
  		 
    	<div style="width:500px;margin:0 auto;margin-top:200px;font-size:14px;">
			<div data-options="headerCls:'login'" class="easyui-panel" title="权限管理平台" style="width:400px;height:260px;">
				<form id="loginPanel" method="post" onkeydown="enterSubmit(event)">
					<table cellpadding="5" style="margin:0 auto;width:100%;">
						<tr>
							<td>账&nbsp;号</td>
							<td>
								<input name="account" type="text" class="codeCls"  style="width:90%;height:20px" placeholder="请输入你的账号"/>
							</td>
						</tr>
						<tr>
							<td>密&nbsp;码</td>
							<td>
								<input name="password" type="password" class="codeCls" style="width:90%;height:20px" placeholder="请输入你的密码"/> 
							</td>
						</tr>
						<tr>
							<td>验证码</td>
							<td>
								<input  name="code" type="text" class="codeCls" maxlength="4" placeholder="验证码">
								<img id="imgcode"  src="/security/code" alt="验证码" title="点击刷新验证码"/> 
								<!-- <a href="javaScript:void(0);"  class="btn" onClick="openModel();">修改密码</a> -->
							</td> 
						</tr>
						<tr>
							<td colspan="2" align="center">
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="enterSys(true)" style="width:80%;height:32px;border: 1px solid #95B8E7;">
									登录
								</a> 
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!-- 修改密码  -->
		<!-- <div id="win" class="easyui-window" title="修改密码" style="width:400px;height:260px;" closed="true"  modal="true" collapsible="false" minimizable="false"
        maximizable="false">  
		    <form style="padding:10px 20px 10px 40px;"> 
		    	<p>用户名&nbsp; <input class="easyui-textbox" prompt="输入你的用户名"></p>  
		        <p>原始密码 <input class="easyui-textbox" prompt="输入你的原始密码"></p>  
		        <p>新密码&nbsp; <input class="easyui-textbox" prompt="输入你的新密码"></p> 
		        <p>确认密码 <input class="easyui-textbox" prompt="输入你的确认密码"></p>  
		        <div style="padding:5px;text-align:center;">  
		            <input type="submit" class="easyui-linkbutton" icon="icon-ok" style="width:80px;height:40px" value="修改密码">
		            <input type="reset" class="easyui-linkbutton" icon="icon-cancel" style="width:80px;height:40px" value="取消">
		        </div>  
		    </form>  
		</div> -->
		<script> 
			function enterSys(r){
				var account=$.trim($('input[name=account]').val());
				var password=$.trim($('input[name=password]').val());
				var code=$.trim($('input[name=code]').val());
				if(isBlank(account)){
					$.messager.alert({
						title:'提示框',
						msg:'用户名不能为空'
					});
					return;
				}
				if(isBlank(password)){
					$.messager.alert({
						title:'提示框',
						msg:'密码不能为空'
					}); 
					return;
				}
				if(isBlank(code)){
					$.messager.alert({
						title:'提示框',
						msg:'验证码不能为空'
					});  
					return;
				}
				$.getJSON('/security/checkLogin?ajax=true',{'account':account,'password':password,'code':code},function(rs){
					if(rs.status==0){
						window.location.href='/security/console';
					}else{
						$('#imgcode').attr('src','/security/code?d='+new Date().getTime());
						$.messager.alert({
							title:'提示框',
							msg:rs.msg
						}); 
					}
				});
			}
			function openModel(){
				$('#win').window('open');
			}
			//刷新验证码
			$('#imgcode').click(function(){
				$(this).attr('src','/security/code?d='+new Date().getTime());
			}); 
			function enterSubmit(event){ 
				var e = event ? event :(window.event ? window.event : null); 
				if(e.keyCode==13){ 
					enterSys(true);
				} 
			};
		</script>
  </body>
</html>
