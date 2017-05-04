/**  
 * Project Name:lenovo_czsys  
 * File Name:MainController.java  
 * Package Name:com.sys.controller  
 * Date:2016-4-21下午3:38:20  
 * Copyright (c) 2016 
 *  
*/  
  
package com.sys.controller;  

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.comm.SessionKeyContent;
import com.sys.entity.UserInfo;

/** 
 * @ClassName: MainController 
 * @Description: TODO 
 * @author lm
 * @date 2016-4-21 下午3:38:20 
 * 
 * 
 */
@SuppressWarnings("all")
@Controller 
public class MainController extends ControllerAttributes {
	
	private final String IFRAME="iframe/";
	
	
	@RequestMapping("console")
	public String console(HttpServletRequest request){
		Object obj = request.getSession().getAttribute(SessionKeyContent.SESSION_KEY_OBJ_USER_BEAN);
		if(obj==null){
			return "index";
		}
		UserInfo user = (UserInfo) obj;
		request.setAttribute("user", user);
		return "main";
	}
	
	
	
	@RequestMapping("console_{menucode}")
	public String consoleByCode(@PathVariable(value="menucode")String menucode){ 
		return IFRAME+menucode;
	}
	
	
	
	
	
	
}
  
