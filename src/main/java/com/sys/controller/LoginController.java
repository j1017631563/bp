/**  
 * Project Name:lenovo_czsys  
 * File Name:LoginController.java  
 * Package Name:com.sys.controller  
 * Date:2016-4-21下午3:22:19  
 * Copyright (c) 2016 
 *  
*/  
  
package com.sys.controller;  

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.general.util.MD5;
import com.sys.comm.SessionKeyContent;
import com.sys.entity.UserInfo;
import com.sys.service.UserService;

/** 
 * @ClassName: LoginController 
 * @Description: TODO 
 * @author lm
 * @date 2016-4-21 下午3:22:19 
 * 
 * 
 */
@SuppressWarnings("all")
@Controller
public class LoginController extends ControllerAttributes{
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping("checkLogin")
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest request){
		Map<String,Object> responseMap=new HashMap<String, Object>();
		
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		String code=request.getParameter("code");
		password = MD5.getMD5Str(password);
		
		String piccode=(String) request.getSession().getAttribute("code");
		
		UserInfo user = userService.queryByAccount(account);
		if(user==null){
			responseMap.put("status", STATUS_1);
			responseMap.put("msg", "用户名或者密码错误");
		}else if(user.getType()!=0||!user.getPassword().trim().equalsIgnoreCase(password.trim())){
			responseMap.put("status", STATUS_1);
			responseMap.put("msg", "用户名或者密码错误");
		}else if(!piccode.equalsIgnoreCase(code)){
			responseMap.put("status", STATUS_1);
			responseMap.put("msg", "验证码错误或者失效");
		}else{
			request.getSession().setAttribute(SessionKeyContent.SESSION_KEY_OBJ_USER_BEAN, user);
			
			responseMap.put("status", STATUS_0);
			responseMap.put("msg", "用户登陆成功");
		}
		
		return responseMap;
	}
	
	
	@RequestMapping("login")
	public String loginIn(){
		return "index";
	}
	
	
	@RequestMapping("loginout")
	public String loginout(HttpServletRequest request){
		request.getSession().removeAttribute(SessionKeyContent.SESSION_KEY_OBJ_USER_BEAN);
		return "redirect:/security/login";
	}
	
	
	
	
	
	
	
	
	
	
	
}	
  
