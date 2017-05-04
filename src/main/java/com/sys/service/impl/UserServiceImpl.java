/**  
 * Project Name:lenovo_czsys  
 * File Name:UserServiceImpl.java  
 * Package Name:com.sys.service.impl  
 * Date:2016-4-22下午1:44:27  
 * Copyright (c) 2016 
 *  
*/  
  
package com.sys.service.impl;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.general.dao.GeneralDaoImpl;
import com.general.dao.pager.Pagination;
import com.sys.entity.UserInfo;
import com.sys.service.UserService;

/** 
 * @ClassName: UserServiceImpl 
 * @Description: TODO 
 * @author lm
 * @date 2016-4-22 下午1:44:27 
 * 
 * 
 */
@SuppressWarnings("all")
@Service(value="userService")
public class UserServiceImpl extends GeneralDaoImpl<UserInfo> implements UserService{
	private final String INSERT="userinfo.insertuser";
	private final String UPDATE="userinfo.updateuser"; 
	private final String DELETE="userinfo.deleteuser";
	private final String QUERYROWS="userinfo.queryrows";
	private final String QUERYRID="userinfo.queryById";
	private final String QUERYROWNUM="userinfo.queryrownum";
	
	@Override
	public Pagination findPagination(Map<String,Object> map) {
		return super.queryPagination(QUERYROWS, QUERYROWNUM, map);
	}

	@Override
	public int insertInto(UserInfo userInfo) { 
		return super.insert(INSERT, userInfo);
	}

	@Override
	public int updateSet(UserInfo userInfo) { 
		return super.update(UPDATE, userInfo);
	}

	@Override
	public int deleteUser(String[] ids) { 
		return super.delete(DELETE,ids);
	}

	@Override
	public UserInfo findById(String id) { 
		return super.queryOne(QUERYRID, id);
	}

	@Override
	public UserInfo queryByAccount(String account) {
		return super.queryOne("userinfo.queryByAccount", account);
	}

	@Override
	public List<UserInfo> queryByIds(String[] ids) {
		return super.queryList("userinfo.queryByIds", ids);
	}
	
	
	

	 

}
  
