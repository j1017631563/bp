package com.general.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @描述 json 工具类
 * @项目名 Eachtech
 * @包名 com.eachtech.commons.util
 * @文件名 JsonUtil.java
 * @类名 JsonUtil
 * @author LiYuan
 * @version 2013-11-28--下午12:02:47
 *
 */
public class JsonUtil {
	
	private static Gson gson = null;
	static{
		GsonBuilder builder = new GsonBuilder();
        // 不转换没有 @Expose 注解的字段
        builder.enableComplexMapKeySerialization();
        gson = builder.create();
	}
	
	/**
	 * @功能 转换成相应类型
	 * @方法名 fromJson
	 * @version 下午12:04:12
	 * @param json
	 * @param entiyClass
	 * @return
	 */
	public static <T> T fromJson(String json,Type entiyClass){
		return gson.fromJson(json,entiyClass);
	}
	
	/**
	 * @功能 把对象转换成json
	 * @方法名 toJson
	 * @version 下午12:05:41
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		return gson.toJson(obj);
	}

	/**
	 * Json格式JavaBean字符串还原为JavaBean实例对象
	 * 
	 * @param String beanString Json格式JavaBean字符串
	 * 
	 * @param Class <T> beanClass=JavaBean类类型
	 * 
	 * @return Object obj JavaBean实例对象
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static <T> T jsonStringToJavaBean(String beanString, Class<T> beanClass) {
		beanString = beanString.replaceAll("'null'", "''").replaceAll("\"null\"", "\"\"").replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
		return gson.fromJson(beanString, beanClass);
	}

	/**
	 * 将Json格式的JavaBean数据集合字符串转化为标准JavaBean实例对象集合
	 * 
	 * @param String jsonString Json格式的JavaBean数据集合字符串
	 * 
	 * @param Class <T> beanClass=JavaBean类类型
	 * 
	 * @return Collection<T> collection JavaBean对象实例集合
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static <T> List<T> jsonStringToCollection(String jsonString, Class<T> beanClass) {
		jsonString = jsonString.replaceAll("'null'", "''").replaceAll("\"null\"", "\"\"").replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
		return (ArrayList<T>) gson.fromJson(jsonString, beanClass);
	}

	/**
	 * 将Json格式的JavaBean数据集合字符串转化为标准JavaBean实例对象数组
	 * 
	 * @param String jsonString Json格式的JavaBean数据集合字符串
	 * 
	 * @param Class <T> beanClass=JavaBean类类型
	 * 
	 * @return Object bean[] JavaBean对象实例数组
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static <T> T[] jsonStringToArray(String jsonString, Class<T> beanClass) {
		jsonString = jsonString.replaceAll("'null'", "''").replaceAll("\"null\"", "\"\"").replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
		return (T[]) gson.fromJson(jsonString, beanClass);
	}
	
	
	public static HttpServletResponse write(HttpServletResponse response,Object obj) throws IOException{
		response.setContentType("text/html");
		response.getWriter().print(toJson(obj));
		response.setStatus(200);
		response.flushBuffer();
		return response;
	}
	/**
	 * 
	* @Title: writeStr 
	* @Description: 输出单个字符串
	* @param @param response
	* @param @param str 内容
	* @param @return
	* @param @throws IOException    
	* @return HttpServletResponse   
	* @throws
	 */
	public static HttpServletResponse writeStr(HttpServletResponse response,String str) throws IOException{
		response.setContentType("text/plain");
		response.setStatus(200);
		response.getWriter().print(str);
		response.flushBuffer();
		return response;
	}
	
	
	
}
