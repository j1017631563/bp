package com.general.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**8点是 0
 * @author lin
 *
 */
public class DateFormatHelp {
	
	/** HH:mm:ss **/ 
	public static final SimpleDateFormat HH_mm_ss=new SimpleDateFormat("HH:mm:ss");
	/** MM-dd HH:mm:ss **/ 
	public static final SimpleDateFormat MM_dd_HH_mm_ss=new SimpleDateFormat("MM-dd HH:mm:ss");
	/** yyyy-MM-dd **/ 
	public static final SimpleDateFormat yyyy_MM_dd=new SimpleDateFormat("yyyy-MM-dd");
	/** yyyy-MM-dd HH:mm:ss **/ 
	public static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** yyyyMMddHHmmss **/ 
	public static final SimpleDateFormat yyyyMMddHHmmss=new SimpleDateFormat("yyyyMMddHHmmss");

	public static final SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
	/**yyyy-MM**/
	public static final SimpleDateFormat yyyy_MM = new SimpleDateFormat("yyyy-MM");
	
	/**yyyy年MM月dd日**/
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy年MM月dd日");
	/** 一秒钟有多少毫秒 **/
	public static final int MILLSECOND_OF_SECOND=1000;
	/** 一分钟有多少毫秒 **/ 
	public static final int MILLSECOND_OF_MINUTE=1000 * 60;
	/** 一小时有多少毫秒 **/ 
	public static final int MILLSECOND_OF_HOUR=MILLSECOND_OF_MINUTE * 60;
	/** 一天有多少毫秒 **/ 
	public static final int MILLSECOND_OF_DAY=MILLSECOND_OF_HOUR * 24;
	/** 一星期有多少毫秒 **/ 
	public static final int MILLSECOND_OF_WEEK=MILLSECOND_OF_DAY * 7;
	
	public static final Date parse(SimpleDateFormat sdf,String dateStr){
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			return null;
		}
	};
}
