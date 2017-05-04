/**  
 * Project Name:NewFlowPortal  
 * File Name:UUIDGenerator.java  
 * Package Name:com.lenovo.cz.util  
 * Date:2016-4-7下午2:45:21  
 * Copyright (c) 2016 
 *  
*/  
  
package com.general.util;  

import java.util.Arrays;
import java.util.UUID;

/** 
 * @ClassName: UUIDGenerator 
 * @Description: TODO 
 * @author lm
 * @date 2016-4-7 下午2:45:21 
 * 
 * 
 */
public class UUIDGenerator {
	  /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String UUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.replaceAll("-", ""); 
    } 
    /**
     * 
    * @Title: UUID  
    * @param @param len 指定长度
    * @throws
     */
    public static String UUID(int len){
    	return UUID().substring(0,len);
    }
    /** 
     * 获得指定数目的UUID 
     * @param number int 需要获得的UUID数量 
     * @return String[] UUID数组 
     */ 
    public static String[] UUIDList(int number){ 
        if(number < 1){ 
            return null; 
        } 
        String[] ss = new String[number]; 
        for(int i=0;i<number;i++){ 
            ss[i] = UUID(); 
        } 
        return ss; 
    } 
    public static void main(String[] args) {
		System.out.println(Arrays.asList(UUIDList(10)));
	}
}
  
