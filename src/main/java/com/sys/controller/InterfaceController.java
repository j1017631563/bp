package com.sys.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.general.util.JsonUtil;
import com.general.util.MD5;
import com.general.util.StringUtil;
import com.sys.entity.UserInfo;
import com.sys.service.UserService;

/**
 * 对外接口
 * @author j448339706
 *
 */

@SuppressWarnings("all")
@Controller
@RequestMapping("/interface")
public class InterfaceController extends ControllerAttributes implements Serializable {

	private final Logger log=Logger.getLogger(getClass().getName());
	
	@Value("#{configProperties['queryManagentImformationKey']}")
	private String queryManagentImformationKey;
	
	
	@Resource
	private UserService userService;
	
	
	
	/**
	 * 获取管理员信息接口
	 * @param request
	 * @param response
	 * @param timestamp
	 * @param userkey
	 * @param account
	 * @throws Exception
	 */
	@RequestMapping(value="/queryManagentImformation.do")
	public void queryManagentImformation(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "timestamp", required = false) String timestamp,
			@RequestParam(value = "userkey", required = false) String userkey,
			@RequestParam(value = "account", required = false) String account
			) throws Exception{
		Map<String,Object> responseMap=new HashMap<String, Object>();
		
		responseMap.put("status", STATUS_1);
		responseMap.put("msg", "数据新增或者修改失败");
		try{
			if(StringUtil.isNullOrTrimEmpty(timestamp)){
				responseMap.put("status", STATUS_1);
				responseMap.put("msg", "时间戳缺失");
				return;
			}
			if(StringUtil.isNullOrTrimEmpty(userkey)){
				responseMap.put("status", STATUS_1);
				responseMap.put("msg", "userkey参数缺失");
				return;
			}
			if(StringUtil.isNullOrTrimEmpty(account)){
				responseMap.put("status", STATUS_1);
				responseMap.put("msg", "用户名缺失");
				return;
			}
			
			if(!checkUserKey(timestamp,userkey)){
				responseMap.put("status", STATUS_1);
				responseMap.put("msg", "认证错误");
				return;
			}
			
			
			UserInfo userInfo = userService.queryByAccount(account);
			
			if(userInfo==null){
				responseMap.put("status", STATUS_1);
				responseMap.put("msg", "用户不存在");
				return;
			}
			
			//userInfo.setPassword(MD5.getMD5Str(userInfo.getPassword()));//密码加密
			
			responseMap.put("status", STATUS_0);
			responseMap.put("msg", userInfo);
			
		}catch (Exception e) {
			e.printStackTrace();
			log.info("---获取管理员信息异常---"+e.getMessage());
		}finally{
			log.info("--queryManagentImformation.do--"+JsonUtil.toJson(responseMap).toString());
			JsonUtil.write(response, responseMap);
		}
		
	}
	
	/**
	 * 加密认证
	 * @param timestamp
	 * @param userkey
	 * @return
	 */
	public boolean checkUserKey(String timestamp,String userkey){
		
		String key = queryManagentImformationKey;
		String strkey = MD5.getMD5Str(key + timestamp);
		
		if(strkey.equalsIgnoreCase(userkey)){
			return true;
		}else{
			return false;
		}
	}
	
	
	

}
