/**  
 * Project Name:lenovo_czsys  
 * File Name:UserController.java  
 * Package Name:com.sys.controller  
 * Date:2016-4-22下午1:39:32  
 * Copyright (c) 2016 
 *  
*/  
  
package com.sys.controller;  

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.general.dao.pager.Pagination;
import com.general.util.DateFormatHelp;
import com.general.util.MD5;
import com.general.util.StringUtil;
import com.general.util.UUIDGenerator;
import com.sys.entity.UserInfo;
import com.sys.service.UserService;

/** 
 * @ClassName: UserController 
 * @Description: 用户信息 Controller 
 * @author lm
 * @date 2016-4-22 下午1:39:32 
 * 
 * 
 */
@SuppressWarnings("all")
@Controller
public class UserController extends ControllerAttributes implements Serializable{
	
	private final Logger log=Logger.getLogger(getClass().getName());
	
	@Resource
	private UserService userService;
	
	
	/**
	 * 用户管理列表
	 * @param request
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param beginDate
	 * @param finishDate
	 * @param username
	 * @param account
	 * @param status
	 * @return
	 */
	@RequestMapping("/loadInfo")
	@ResponseBody
	public JSONObject loadInfo(HttpServletRequest request,
			String page,String rows,String sort,String order, 
			String beginDate,String finishDate,String username, 
			String account,	String status ){
		
		//当前页  
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //每页显示条数  
        int number = Integer.parseInt((rows == null || rows == "0" || "NaN".equals(rows)) ? "15":rows);  
        //每页的开始记录  第一页为1  第二页为number +1   
        int start = (intPage-1)*number;  
        if(StringUtils.isNotBlank(beginDate)){
        	beginDate=beginDate+" 00:00:00";
        }
        if(StringUtils.isNotBlank(finishDate)){
        	finishDate=finishDate+" 23:59:59";
        }
        if(StringUtils.isNotBlank(username)){
        	username=username;
        } 
        if(StringUtils.isNotBlank(account)){
        	account=account;
        } 
        Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", start);
		map.put("number", number); 
		if(StringUtils.isNotBlank(sort)){
			map.put("order", sort+" "+order);
		}
		map.put("beginDate", beginDate);
		map.put("finishDate", finishDate);
		map.put("username",username);
		map.put("account", account);
		map.put("status", "-1".equals(status)?null:status);
		
        Pagination pagination = userService.findPagination(map);//每页的数据，放入list  
        Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
        JSONObject result=new JSONObject();
        jsonMap.put("total", pagination.getTotalCount());//total键 存放总记录数，必须的  
        jsonMap.put("rows", pagination.getList());//rows键 存放每页记录 list  
        result = JSONObject.fromObject(jsonMap);//格式化result   一定要是JSONObject  
        
		return result;
	}
	
	/**
	 * 用户管理新增或编辑
	 */
	@RequestMapping("edit")
	@ResponseBody
	public Map<String,Object> insertUser(UserInfo userInfo){
		Map<String,Object> responseMap=new HashMap<String, Object>();
		responseMap.put("status", STATUS_1);
		responseMap.put("msg", "数据新增或者修改失败");
		
		
		if(StringUtils.isBlank(userInfo.getId())){
			UserInfo user = userService.queryByAccount(userInfo.getAccount().trim());
			if(user!=null){
				responseMap.put("status", STATUS_1);
				responseMap.put("msg", "存在相同账号");
				return responseMap;
			}
			//password = MD5.getMD5Str(password);
			
			userInfo.setAccount(userInfo.getAccount());
			userInfo.setUsername(userInfo.getUsername());
			userInfo.setPassword(MD5.getMD5Str(userInfo.getPassword().trim()));
			userInfo.setDescrit(userInfo.getDescrit());
			userInfo.setId(UUIDGenerator.UUID(32));
			userInfo.setType(1);
			
			userService.insertInto(userInfo);
			
			responseMap.put("status", STATUS_0);
			responseMap.put("msg", "数据新增成功");
		}else{ 
			userInfo.setAccount(userInfo.getAccount());
			userInfo.setUsername(userInfo.getUsername());
			userInfo.setPassword(MD5.getMD5Str(userInfo.getPassword().trim()));
			userInfo.setDescrit(userInfo.getDescrit());
			userInfo.setUpdateDate(DateFormatHelp.yyyy_MM_dd_HH_mm_ss.format(new Date()));
			
			userService.updateSet(userInfo);
			
			responseMap.put("status", STATUS_0);
			responseMap.put("msg", "数据修改成功");
		} 
		return responseMap;
	}
	
	
	/**
	 * 用户删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> deleteUserinfo(String[] ids){
		Map<String,Object> responseMap=new HashMap<String, Object>();
		responseMap.put("status", STATUS_1);
		responseMap.put("msg", "数据删除失败");
		
		try{
			List<UserInfo> list = userService.queryByIds(ids);
			for(UserInfo userInfo:list){
				if(userInfo.getType()==0){
					responseMap.put("status", STATUS_1);
					responseMap.put("msg", userInfo.getUsername()+"是权限管理系统后台管理员，不能删除!");
					return responseMap;
				}
			}
			
			int rs=userService.deleteUser(ids);
			if(rs>0){
				responseMap.put("status", STATUS_0);
				responseMap.put("msg", "数据删除成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			responseMap.put("msg", "error"+e.getMessage());
		}
		return responseMap;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("findById")
	@ResponseBody
	public Map<String,Object> findUserinfo(String id){
		Map<String,Object> responseMap=new HashMap<String, Object>();
		responseMap.put("status", STATUS_1);
		responseMap.put("msg", "数据查询失败");
		
		try{
			UserInfo userInfo=userService.findById(id);
			if(userInfo!=null){
				responseMap.put("status", STATUS_0);
				responseMap.put("user", userInfo);
			}
		}catch(Exception e){
			e.printStackTrace();
			responseMap.put("msg", "error"+e.getMessage());
		}
		return responseMap;
	}
	
	/**
	 * 修改用户状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("setStatus")
	@ResponseBody
	public Map<String,Object> setStatus(String id,String status){
		Map<String,Object> responseMap=new HashMap<String, Object>();
		responseMap.put("status", STATUS_1);
		responseMap.put("msg", "操作失败");
		
		try{
			UserInfo userInfo=userService.findById(id);
			userInfo.setStatus(Integer.parseInt(status));
			userInfo.setUpdateDate(DateFormatHelp.yyyy_MM_dd_HH_mm_ss.format(new Date()));
			int rs=userService.updateSet(userInfo);
			if(rs>0){
				responseMap.put("status", STATUS_0);
				responseMap.put("msg", "操作成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			responseMap.put("msg", "error"+e.getMessage());
		}
		return responseMap;
	}
	
	
	
	
}
  
