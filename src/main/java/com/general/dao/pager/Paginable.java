/**  
 * Project Name:Demo  
 * File Name:Paginable.java  
 * Package Name:org.lm.page  
 * Date:2015-10-28上午9:56:12  
 * Copyright (c) 2015 
 *  
*/  
  
package com.general.dao.pager;  
/**  
 * ClassName:Paginable <br/>  
 * Function: 数据分类接口 <br/> 
 * Date:     2015-10-28 上午9:56:12 <br/>  
 * @author   lm  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface Paginable {
	/**
	 * 总记录数
	 * 
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 总页
	 * 
	 * @return
	 */
	public int getTotalPage();

	/**
	 * 每页记录
	 * 
	 * @return
	 */
	public int getPageSize();

	/**
	 * 当前页号
	 * 
	 * @return
	 */
	public int getPageNo();

	/**
	 * 是否第一
	 * 
	 * @return
	 */
	public boolean isFirstPage();

	/**
	 * 是否后一
	 * 
	 * @return
	 */
	public boolean isLastPage();

	/**
	 * 返回下页的页
	 */
	public int getNextPage();

	/**
	 * 返回上页的页
	 */
	public int getPrePage();
}
  
