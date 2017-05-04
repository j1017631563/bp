/**  
 * Project Name:lenovo_czsys  
 * File Name:UserService.java  
 * Package Name:com.sys.service  
 * Date:2016-4-22下午1:44:07  
 * Copyright (c) 2016 
 *  
*/  
  
package com.sys.service;  

import java.util.List;
import java.util.Map;

import com.general.dao.pager.Pagination;
import com.sys.entity.UserInfo;

/** 
 * @ClassName: UserService 
 * @Description: TODO 
 * @author lm
 * @date 2016-4-22 下午1:44:07 
 * 
 * 
 */
public interface UserService {

	public Pagination findPagination(Map<String,Object> map);

	public int insertInto(UserInfo userInfo);

	public int updateSet(UserInfo userInfo);

	public int deleteUser(String[] ids);

	public UserInfo findById(String id);
	
	public UserInfo queryByAccount(String account);
	
	public List<UserInfo> queryByIds(String[] ids);
	

}
  
