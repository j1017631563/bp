/**  
 * Project Name:lenovo_czsys  
 * File Name:Controller.java  
 * Package Name:com.sys.controller  
 * Date:2016-4-21下午3:23:58  
 * Copyright (c) 2016 
 *  
*/  
  
package com.sys.controller;  

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

/** 
 * @ClassName: Controller 
 * @Description: TODO 
 * @author lm
 * @date 2016-4-21 下午3:23:58 
 * 
 * 
 */
@SuppressWarnings("all")
public class ControllerAttributes implements Serializable{
	
	protected int STATUS_1=1,STATUS_0=0,STATUS_2=2;//2其他  1 失败 0成功
	
	protected final String SUCCESS="success";//成功
	protected final String FAIL="fail";//失败
	protected final String ERROR="error";//出错
	
	
	
	
	
	
	
}
  
