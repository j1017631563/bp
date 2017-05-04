/**  
 * Project Name:lenovo_czsys  
 * File Name:GeneralDao.java  
 * Package Name:com.general.dao  
 * Date:2016-4-21下午5:39:05  
 * Copyright (c) 2016 
 *  
*/  
  
package com.general.dao;  

import java.util.List;
import java.util.Map;

import com.general.dao.pager.Pagination;


/** 
 * @ClassName: GeneralDao 
 * @Description: 基类DAO
 * @author lm
 * @date 2016-4-21 下午5:39:05 
 * 
 * 
 */
public abstract interface GeneralDao<T> {
	/**
	 * 
	* @Title: insert 
	* @Description: 插入数据
	* @param @param sql sql 语句 insert
	* @param @param t  
	* @return void   
	* @throws
	 */
	int insert(String insertId, Object t);
	/**
	 * 
	* @Title: update 
	* @Description: 修改数据
	* @param @param updateId
	* @param @param t    
	* @return void   
	* @throws
	 */
	int update(String updateId, Object t);
	/**
	 * 
	* @Title: delete 
	* @Description: 删除数据
	* @param @param deleteId
	* @param @param t
	* @param @return    
	* @return int   
	* @throws
	 */
	int delete(String deleteId, Object t);
	/**
	 * 
	* @Title: batchDelele 
	* @Description: 批量删除
	* @param @param deleteId
	* @param @param objs
	* @param @return    
	* @return int   
	* @throws
	 */
	int batchDelele(String deleteId, Object[] objs);
	/**
	 * 
	* @Title: batchUpdate 
	* @Description: 批量修改
	* @param @param updateId
	* @param @param objs
	* @param @return    
	* @return int   
	* @throws
	 */
	int batchUpdate(String updateId, Object[] objs);
	/**
	 * 
	* @Title: batchInsert 
	* @Description: 批量新增
	* @param @param insertId
	* @param @param objs
	* @param @return    
	* @return int   
	* @throws
	 */
	int batchInsert(String insertId, Object[] objs);
	/**
	 * 
	* @Title: queryOne 
	* @Description: 查询单条数据
	* @param @param selectId
	* @param @param obj
	* @param @return    
	* @return T   
	* @throws
	 */
	T queryOne(String selectId, Object obj);
	/**
	 * 
	* @Title: queryList 
	* @Description: 查询多个数据
	* @param @param selectId
	* @param @param obj
	* @param @return    
	* @return List<T>   
	* @throws
	 */
	List<T> queryList(String selectId, Object obj);
	/**
	 * 
	* @Title: queryRowsNum 
	* @Description: 查询数据的数量
	* @param @param selectId
	* @param @param obj
	* @param @return    
	* @return int   
	* @throws
	 */
	int queryRowsNum(String selectId, Map<String,Object> params);
	/**
	 * 
	* @Title: queryPagination 
	* @Description: 获取分页数据
	* @param @param queryRowsId
	* @param @param queryRowNumId
	* @param @param parms
	* @param @return    
	* @return Pagination   
	* @throws
	 */
	Pagination queryPagination(String queryRowsId, String queryRowNumId,
			Map<String, Object> parms); 
	
}
  
