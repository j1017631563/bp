/**  
 * Project Name:lenovo_czsys  
 * File Name:GeneralDaoImpl.java  
 * Package Name:com.general.dao  
 * Date:2016-4-21下午5:41:30  
 * Copyright (c) 2016 
 *  
*/  
  
package com.general.dao;  

import java.util.List;
import java.util.Map;
import java.lang.reflect.ParameterizedType;  
import java.lang.reflect.Type;  
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.general.dao.pager.Pagination;

/** 
 * @ClassName: GeneralDaoImpl 
 * @Description: TODO 
 * @author lm
 * @date 2016-4-21 下午5:41:30 
 * 
 * 
 */
@ResponseBody
@Transactional
public abstract class GeneralDaoImpl<T> implements GeneralDao<T>{
	private final Logger log=Logger.getLogger(this.getClass());
	
	private Class<T> entityClass;   
	
    public GeneralDaoImpl() {  
        Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
        entityClass = (Class) params[0];  
    }  
    
	@Autowired
	public SqlSession sqlSession;  
	
	@Override
	public int insert(String insertId,Object t){ 
		return sqlSession.insert(insertId,t);
	}
	
	@Override
	public int batchInsert(String insertId,Object[] objs){
		int i=0;
		if(objs!=null&&objs.length>0){
			for(Object t:objs){
				i++;
				insert(insertId, t);
			}
		}
		return i;
	}
	
	@Override
	public int update(String updateId,Object t){
		return sqlSession.update(updateId, t);
	}
	
	@Override
	public int batchUpdate(String updateId,Object[] objs){
		int i=0;
		if(objs!=null&&objs.length>0){
			for(Object t:objs){
				i++;
				update(updateId, t);
			}
		}
		return i;
	}
	
	@Override
	public int delete(String deleteId,Object t){ 
		return sqlSession.delete(deleteId, t);
	}
	
	@Override
	public int batchDelele(String deleteId,Object[] objs){
		int i=0;
		if(objs!=null&&objs.length>0){
			for(Object t:objs){
				i++;
				delete(deleteId, t);
			}
		}
		return i;
	}
	
	@Override
	public T queryOne(String selectId,Object obj){
		return sqlSession.selectOne(selectId, obj);
	}
	
	@Override
	public List<T> queryList(String selectId,Object obj){
		return sqlSession.selectList(selectId, obj);
	} 
	 
	@Override
	public int queryRowsNum(String selectId,Map<String,Object> params){ 
		List<T> list=queryList(selectId,params); 
		return (list==null||list.size()==0)?0:Integer.parseInt(list.get(0).toString());
	} 
	 
	@Override
	public Pagination queryPagination(String queryRowsId,String queryRowNumId,Map<String,Object> params){ 
		Pagination pagination=new Pagination();
		pagination.setPageNo(Integer.valueOf(params.get("start").toString()));
		pagination.setPageSize(Integer.valueOf(params.get("number").toString()));
		pagination.setList(queryList(queryRowsId,params)); 
		pagination.setTotalCount(queryRowsNum(queryRowNumId,params));
		return pagination;
	}
	
	
	
	
	 
}
  
