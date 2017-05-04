package com.general.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * <p> MD5加密。</p>
 * 
 * @author 肖新慧
 * @version 1.00
 */
public final class MD5 {
	
	/**
	 * 将一个字符串进行MD5加密
	 * @param string
	 * @return
	 */
	public static String getMD5Str(String str) {
		StringBuffer buf = new StringBuffer(""); 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(str.getBytes()); 
			byte b[] = md.digest(); 
			int i = 0; 
			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if (i < 0) 
					i += 256; 
				if (i < 16) 
					buf.append("0"); 
					buf.append(Integer.toHexString(i)); 
			} 
		}catch(NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
		} 
		return buf.toString().toUpperCase(); //32位的加密
	}
	
	
	public static void main(String[] args) {
		String str = "gzylcq"+"8943425121"+"1440561192960";
//		72d4a5ff2cb53b5e7443c5134c4ff576
		System.out.println(str);
		System.out.println(getMD5Str(str));
	}
	
}
