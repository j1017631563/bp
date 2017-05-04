/**
 * $RCSfile: ParamUtils.java,v $
 * $Revision: 1.1.1.1 $
 * $Date: 2002/09/09 13:51:07 $
 *
 * New Jive  from Jdon.com.
 *
 * This software is the proprietary information of CoolServlets, Inc.
 * Use is subject to license terms.
 */

package com.general.util;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * This class assists skin writers in getting parameters.
 */
public class ParamUtils {

	private HttpServletRequest r;
	private Map<String,Object> m;
	
	public ParamUtils(HttpServletRequest request) {
		this.r=request;
		m=new HashMap<String,Object>();
		m.put("userId", ParamUtils.getUserId(r));
		m.put("imsidn", ParamUtils.getImsidn(r));
		m.put("start",0);
		m.put("count",10);
	}
	
	public ParamUtils putKey(String key,Object def){
		m.put(key, ParamUtils.getParameter(r, key, def));
		return this;
	}
	
	public Map<String,Object> getParamMap(){
		return m;
	}
	/**
	 * Gets a parameter as a string.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @return The value of the parameter or null if the parameter was not
	 *      found or if the parameter is a zero-length string.
	 */
	
	public static ParamUtils newParamMapUtil(HttpServletRequest request){
		return new ParamUtils(request);
	}
	
	/**
	 * 把传递的参数封装进map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> createRequest(HttpServletRequest request) throws Exception {
        Enumeration<String> paramNames = request.getParameterNames();
        if (paramNames != null) {
            Map<String, String> map = new HashMap<String, String>();
            for (; paramNames.hasMoreElements();) {
                String key = paramNames.nextElement();
 
               String value = request.getParameter(key);
                map.put(key, value == null ? null : value);
            }
            return map;
        }
        return new HashMap<String, String>();
    }

	
	@SuppressWarnings("unchecked")
	private static <T> T castValue(Object value, T def) {
		try {
			if(value==null||"".equals(value))
				return def;
			else if (def instanceof Integer)
				return (T)(Object)Integer.valueOf(value.toString());
			else if (def instanceof Long)
				return (T)(Object)Long.valueOf(value.toString());
			else if (def instanceof Float)
				return (T)(Object)Float.valueOf(value.toString());
			else if (def instanceof Double)
				return (T)(Object)Double.valueOf(value.toString());
			else if (def instanceof Boolean)
				return (T)(Object)Boolean.valueOf(value.toString());
			else if (def instanceof Short)
				return (T)(Object)Short.valueOf(value.toString());
			else if (def instanceof String){
				if("".equals(value))
					return (T)def;
				else
					return (T)value;
			}
		} catch (Exception e) { return  (T)def;}
		return (T)value;
	}
	public static <T> T getParameter(HttpServletRequest request, String name, T def) {
		String temp = request.getParameter(name);
//		if(request.getMethod().equalsIgnoreCase("GET")&&temp!=null&&!"".equals(temp))
//			try {
//				temp=new String(temp.getBytes("ISO-8859-1"),"utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
		return castValue(temp,def);
	}
	
	/**从reuqest 中 获取  parameter 并保存到  requset 的  Attribute中
	 * @param request
	 * @param name	参数名
	 * @param def	默认值
	 * @return
	 */
	public static <T> T getParameterAndSet(HttpServletRequest request, String name, T def) {
		T obj=ParamUtils.getParameter(request,name,def);
		request.setAttribute(name,obj);
		return (T) obj;
	}
	public static <T> T getAttribute(HttpServletRequest request, String name, T def) {
		Object temp = request.getAttribute(name);
		return castValue(temp,def);
	}
	
	public static <T> T getSessionAttribute(HttpServletRequest request, String name, T def) {
		Object temp =  request.getSession().getAttribute(name);
		return castValue(temp,def);
	}

	/**
	 * Gets a list of int parameters.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @param defaultNum The default value of a parameter, if the parameter
	 * can't be converted into an int.
	 */
	public static String[] getParameters(HttpServletRequest request,
			String name, String defaultValue) {
		String[] paramValues = request.getParameterValues(name);
		if(paramValues != null && paramValues.length > 0)
		{
			for(int i = 0; i < paramValues.length; i++)
			{
				String temp = paramValues[i];
				if(temp == null || "".equals(temp))
					paramValues[i] = defaultValue;
			}
		}
		return paramValues;
	}

	/**
	 * Gets a list of int parameters.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @param defaultNum The default value of a parameter, if the parameter
	 * can't be converted into an int.
	 */
	public static int[] getIntParameters(HttpServletRequest request,
			String name, int defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new int[0];
		}
		int[] values = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			}
			catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}



	/**
	 * Gets a list of long parameters.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @param defaultNum The default value of a parameter, if the parameter
	 * can't be converted into a long.
	 */
	public static long[] getLongParameters(HttpServletRequest request,
			String name, long defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new long[0];
		}
		long[] values = new long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Long.parseLong(paramValues[i]);
			}
			catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}



	public static String getImsidn(HttpServletRequest request){
		return request.getHeader("imsidn");
	}
	
	public static String getUserId(HttpServletRequest request){
		return request.getHeader("userId");
	}



	/**
	 * ��ҳ�����String�ı���ת����UTF-8
	 * added by zhu hongjin at 2006/03/09 22:04
	 */
	public static String getUTF8Parameter(HttpServletRequest request,String name, String defaultValue) {
		String temp = request.getParameter(name);
		if(temp == null || "".equals(temp)){
			return "";
		}
		String result = temp;
		try{
			byte[] b = temp.getBytes("ISO-8859-1");
			result = new String(b,"UTF-8");

		} catch(Exception e){
		}

		return result; 
	}
	
	
	//取访问ip
	public static String getIpAddr(HttpServletRequest request) {
           String ip = request.getHeader("x-forwarded-for");
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
               ip = request.getHeader("Proxy-Client-IP");
           }
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
               ip = request.getHeader("WL-Proxy-Client-IP");
           }
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
              ip = request.getRemoteAddr();
          }
          return ip;
      }
	
	
	
	
	
	
	
}
