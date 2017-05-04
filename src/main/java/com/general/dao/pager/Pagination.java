/**  
 * Project Name:Demo  
 * File Name:Pagination.java  
 * Package Name:org.lm.page  
 * Date:2015-10-28上午10:03:49  
 * Copyright (c) 2015 
 *  
*/  
  
package com.general.dao.pager;  

import java.io.Serializable;
import java.util.List;

/**  
 * ClassName:Pagination <br/>  
 * Function: 分页对象 <br/>   
 * Date:     2015-10-28 上午10:03:49 <br/>  
 * @author   lm  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */  
@SuppressWarnings("serial")
public class Pagination extends SimplePage implements Serializable,Paginable {
	public Pagination() {
	}

	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}
 
	@SuppressWarnings("rawtypes")
	public Pagination(int pageNo, int pageSize, int totalCount, List list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 当前页的数据
	 */ 
	@SuppressWarnings("rawtypes")
	private List list;
 
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}
 
	public void setList(@SuppressWarnings("rawtypes") List list) {
		this.list = list;
	}
}
  
