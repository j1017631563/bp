package com.general.util;

public class PhoneUtil {
	
	public static int matchesPhoneNumber(String phone_number) {
	    String cm = "^((13[4-9])|(147)|(15[0-2,7-9])|(18[2-4,7-8])|(178))\\d{8}$"; //移动
	    String cu = "^((13[0-2])|(145)|(15[5-6])|(18[5,6])|(176))\\d{8}$"; //联通
	    String ct = "^((133)|(153)|(18[0-1,9])|(177))\\d{8}$"; //电信
	    int flag = 0; 
    	if (phone_number.matches(cm)) {
    		flag = 1; 
	    } else if (phone_number.matches(cu)) {
	    	flag = 2; 
	    } else if (phone_number.matches(ct)) { 
	    	flag = 3; 
	    } else { 
	    	flag = 4; 
	    } 
	    return flag; 
    } 
	
	
	
}
