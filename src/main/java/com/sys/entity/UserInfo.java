/**  
 * Project Name:lenovo_czsys  
 * File Name:UserInfo.java  
 * Package Name:com.sys.entity  
 * Date:2016-4-21下午8:46:27  
 * Copyright (c) 2016 
 *  
*/  
  
package com.sys.entity;  

import java.io.Serializable;

/** 
 * @ClassName: UserInfo 
 * @Description: 用户信息表 
 * @author lm
 * @date 2016-4-21 下午8:46:27 
 * 
 * 
 */
@SuppressWarnings("serial")
public class UserInfo implements Serializable {
	private String id;
	private String username; 
	private String password;
	private String sex;
	private String account;
	private String descrit;
	private String createDate;
	private String updateDate;
	private Integer status;
	private Integer type;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDescrit() {
		return descrit;
	}
	public void setDescrit(String descrit) {
		this.descrit = descrit;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}  
	
	
	
	
}
  
