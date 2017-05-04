package com.general.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name apps-common </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-6-4  下午04:53:13 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class HttpUtil{
	
	public static Logger log = LoggerFactory.getLogger("com.lenovo.util.HttpUtil");
	public static final int BUFFER_SIZE = 1024 * 16; // stream缓冲大小
	public static final int ARRAY_SIZE = 1024 * 8; // 每次读取缓冲大小
	
	/**
	 * Http发送请求，取得返回值(返回xml格式）
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-6-4  下午04:53:51 </span><br /> 
	 *
	 */
	public static String postRequest(String urlstr, String strXml) {
		StringBuffer buffer = new StringBuffer();
		HttpURLConnection c = null;
		try{
			log.info("URL---------"+urlstr+",data------"+strXml);
			URL url = new URL(urlstr);
			c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("POST");
			c.setRequestProperty("content-type", "text/html");
			c.setRequestProperty("Accept-Charset", "utf-8");
			c.setDoOutput(true);
			c.setDoInput(true);
			c.setConnectTimeout(30000);//设置连接主机超时（单位：毫秒）
			c.setReadTimeout(30000);//设置从主机读取数据超时（单位：毫秒）
			c.connect();
			log.debug("-------begin--------");
			OutputStreamWriter out = new OutputStreamWriter(c.getOutputStream(),"utf-8");// 发送数据
			out.write(strXml);
			
			out.flush();
			out.close();
			int res = 0;
			res = c.getResponseCode();
			log.debug("rst status res=" + res);
			if(res == 200){
				InputStream u = c.getInputStream();// 获取servlet返回值，接收
				BufferedReader in = new BufferedReader(new InputStreamReader(u,"utf-8"));
				String line = "";
				while ((line = in.readLine()) != null){
					buffer.append(line);
				}
				log.debug(">>" + buffer.toString());
			}else{
				log.debug("------接收出错了-------");
			}
			c.disconnect();
			log.debug("-------end--------");
		}catch (Exception e){
			log.error("异常:"+e.toString());
			buffer = null;
		}
		return buffer==null?null:buffer.toString();
	}
	
	
	/**
	 * 推送功能独有请求工具
	 * @param urlstr
	 * @param strXml
	 * @return
	 */
	public static String pushPostRequest(String urlstr, String strXml) {
		StringBuffer buffer = new StringBuffer();
		HttpURLConnection c = null;
		try{
			URL url = new URL(urlstr);
			c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("POST");
			c.setRequestProperty("content-type", "text/html");
			c.setRequestProperty("Accept-Charset", "utf-8");
			c.setDoOutput(true);
			c.setDoInput(true);
			c.setConnectTimeout(30000);//设置连接主机超时（单位：毫秒）
			c.setReadTimeout(30000);//设置从主机读取数据超时（单位：毫秒）
			c.connect();
			log.info("-------begin---URL-----"+urlstr+""+strXml);
			OutputStreamWriter out = new OutputStreamWriter(c.getOutputStream(),"utf-8");// 发送数据
			out.write(strXml);
			
			out.flush();
			out.close();
			int res = 0;
			res = c.getResponseCode();
			log.debug("rst status res=" + res);
			if(res == 200){
				InputStream u = c.getInputStream();// 获取servlet返回值，接收
				BufferedReader in = new BufferedReader(new InputStreamReader(u,"utf-8"));
				String line = "";
				while ((line = in.readLine()) != null){
					
				}
				buffer.append("200");
			}else{
				log.debug("------接收出错了-------");
			}
			c.disconnect();
			log.debug("-------end--------");
		}catch (Exception e){
			log.error("异常:"+e.toString());
			buffer = null;
		}
		return buffer==null?null:buffer.toString();
	}
	
	
	
	/**
	 * url为请求地址，params为jsonobject格式字符串参数
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postMethodRequest(String url,String params){
		
		HttpClient httpClient = new HttpClient();  
		String res = "";
		try{
			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(params);
			Iterator iter = json.keys();
			String str = "";
			int i = 0;
			while(iter.hasNext()){
				String key = (String) iter.next();
				String val = json.get(key).toString();
				if(i==0){
					str += "?"+key+"="+URLEncoder.encode(val,"UTF-8");
				}else{
					str += "&"+key+"="+URLEncoder.encode(val,"UTF-8");
				}
				i++;
			}
			String qurl = url+str;
			log.info("URL-------------------"+qurl);
			PostMethod postMethod = new PostMethod(qurl);
			
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
			postMethod.setRequestHeader("Content-Type","text/html;charset=UTF-8");
			
			
			httpClient.executeMethod(postMethod);
			
			String result = postMethod.getResponseBodyAsString();
			log.info("result-------------------"+res);
			res = result;
			
			postMethod.releaseConnection();
		}catch (Exception e) {
			e.printStackTrace();
			log.info("异常-------------------"+e.getMessage());
		}
		log.info("res-------------------"+res);
		return res;
	}
	
	
	
	public static String httpPostRequest(String url,String params){
		HttpClient httpClient = new HttpClient();  
		String res = "";
		try{
			PostMethod postMethod = new PostMethod(url);
			log.info("URL-------------------"+url+",param----"+params);
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
			postMethod.setRequestHeader("Content-Type","text/html;charset=UTF-8");
			
			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(params);
			Iterator iter = json.keys();
			while(iter.hasNext()){
				String key = (String) iter.next();
				String val = json.get(key).toString();
				postMethod.addParameter(key, val);
			}
			
			
			httpClient.executeMethod(postMethod);
			
			String result = postMethod.getResponseBodyAsString();
			res = result;
			
			postMethod.releaseConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("result-------------------"+res);
		return res;
	}
	
	
	public static String sendGet(String url,String params) {  
        String result = "";  
        BufferedReader in = null;  
        try {
        	net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(params);
			Iterator iter = json.keys();
			String str = "";
			int i = 0;
			while(iter.hasNext()){
				String key = (String) iter.next();
				String val = json.get(key).toString();
				if(i==0){
					str += "?"+key+"="+val;
				}else{
					str += "&"+key+"="+val;
				}
				i++;
			}
			String qurl = url+str;
			log.info(DateUtil.getDateTimeStr(new Date(), "yyyy-MM-dd HH:mm:ss")+",URL-------------------"+qurl);
            URL realUrl = new URL(qurl);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            conn.setConnectTimeout(60000);
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
            // 建立实际的连接  
            conn.connect();  
            // 获取所有响应头字段  
            Map<String, List<String>> map = conn.getHeaderFields();  
            // 遍历所有的响应头字段  
            for (String key : map.keySet()) {  
                log.info(key + "--->" + map.get(key));  
            }  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
              
            e.printStackTrace();  
        }finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        log.info(DateUtil.getDateTimeStr(new Date(), "yyyy-MM-dd HH:mm:ss")+",result-------------------"+result);
        return result;  
    }  
	
	

	public static String sendPost(String url, String param, String token) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(60000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Sign",MD5.getMD5Str(param));
			if(token!=null){
				conn.setRequestProperty("Token", token);
			}
			log.info("URL-------------------"+url+" Token:"+token+" 参数："+param);

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应 ]
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
			result += line;
			}
			

		} catch (Exception e) {
			log.error("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		//  使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		log.info("result-------------------"+result);
		return result;
	}
	
	
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(60000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			log.info("URL-------------------"+url+" 参数："+param);

			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应 ]
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
			result += line;
			}
			

		} catch (Exception e) {
			log.error("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		//  使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		log.info("result-------------------"+result);
		return result;
	}
	
	/**
	 * 武电实业请求方法
	 */
	public static String sendPostByCode(String url, String param, String code) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(60000);
			conn.setRequestProperty("content-type", "text/plain");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("code",code);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			log.info("URL-------------------"+url+" 参数："+param);
			log.info("URL-------------------"+url+" 加密码code参数："+code);

			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应 ]
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
			result += line;
			}
			

		} catch (Exception e) {
			log.error("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		//  使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		log.info("result-------------------"+result);
		return result;
	}
}
