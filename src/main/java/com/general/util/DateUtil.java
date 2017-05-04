package com.general.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;


/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *   时间帮助类
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name AppMusicServer </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-3-20  下午04:46:11 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class DateUtil{

	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  取当前时间至第二天凌晨的时间差，秒数
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-20  下午05:01:39 </span><br /> 
	 *
	 */
	public static int nowToNextDaySeconds(){
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE,0);
		long sec = c.getTimeInMillis();
		return (int)(sec - now)/1000;
	}
	
	/**
	 * 计算当前时间与某一时间相隔多少天、或者小时、或者分钟、或者秒
	 * @param now
	 * @param before
	 * @return
	 */
	
	public static String getDisplayDate( Date now, Date before )
	{
		long diff = now.getTime() - before.getTime();
		if(diff>=DateFormatHelp.MILLSECOND_OF_WEEK)
			return DateFormatHelp.MM_dd_HH_mm_ss.format(before);
		if(diff>=DateFormatHelp.MILLSECOND_OF_DAY)
			return (diff / DateFormatHelp.MILLSECOND_OF_DAY) + "天前";
		if(diff>=DateFormatHelp.MILLSECOND_OF_HOUR)
			return (diff / DateFormatHelp.MILLSECOND_OF_HOUR) + "小时前";
		if(diff>=DateFormatHelp.MILLSECOND_OF_MINUTE)
			return (diff / DateFormatHelp.MILLSECOND_OF_MINUTE) + "分钟前";
		return (diff / 1000) + "秒前";
	}
	
	/**
	 * 获取一周中的第一天
	 */
	public static String getFirstDateOfWeek(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
		if (dd == 1)
			dd = 7;
		else if (dd > 1)
			dd = dd - 1;
		c.add(Calendar.DATE, 1 - dd);
		String date = c.get(Calendar.YEAR) + "-" + towStr(1 + c.get(Calendar.MONTH)) + "-" + towStr(c.get(Calendar.DATE));
		return date;
	}

	/**
	 * 获取一周中的最后一天
	 */
	public static String getLastDateOfWeek() {
		Calendar c = Calendar.getInstance();
		int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
		if (dd == 1)
			dd = 7;
		else if (dd > 1)
			dd = dd - 1;
		c.add(Calendar.DATE, 7 - dd);
		String date = c.get(Calendar.YEAR) + "-" + towStr(1 + c.get(Calendar.MONTH)) + "-" + towStr(c.get(Calendar.DATE));
		return date;
	}
	
	/**
	 * 获取一周中的最后一天
	 */
	public static String getStrDate() {
		Calendar c = Calendar.getInstance();
		String date = c.get(Calendar.YEAR) + "-" + towStr(1 + c.get(Calendar.MONTH)) + "-" + towStr(c.get(Calendar.DATE));
		return date;
	}
	
	public static String towStr(int n) {
		if (n < 10)
			return "0" + n;
		else
			return "" + n;
	}
	
	/**
	 * 获取相差月份
	 * @param month
	 * @return
	 */
	public static String getMonth(int month){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		int year = c.get(Calendar.YEAR);
		int m = (c.get(Calendar.MONTH)+1);
		
		if(m>=10){
			return year + "-" + m;
		}else{
			return year + "-0" + m;
		}
//		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-1";
		
	}
	
	/**
	 * 根据日期获取毫秒
	 * @param dateStr
	 * @return
	 */
	public static long getTimeMillis(String dateStr){
		long time = 0;
		try {
			time = DateFormatHelp.yyyy_MM_dd_HH_mm_ss.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	/**
	 * 获取日期,格式为yyyy-MM-dd
	 * @param day 
	 * @return
	 */
	public static String getYesterdayDate(int day){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH,  day);
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 将字符串转换为日期
	 * @param str 需要转换的日期字符串
	 * @param format 指定格式
	 * @return
	 */
	public static Date string2Date(String str,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 两个时间相减，得到相差的毫秒数
	 * @param date1 较大的日期
	 * @param date2 较小的日期
	 * @return
	 */
	public static long getTwoDate(Date date1,Date date2){
		long day = (date1.getTime()-date2.getTime());
		return day;
	}
	
	
	// 获取相隔分钟数
	public static long getDistinceMinute(String beforeDateTime,String afterDateTime) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeCount = 0;
		try {
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 1000);

		} catch (ParseException e) {
			throw e;
		}
		return timeCount;
	}
	
	/**
	 * 根据日期date和格式，变为String类型格式的时间
	 * @param date
	 * @param rule 如"yyyy-MM-dd HH:mm:ss"、"yyyy-MM-dd"
	 * @return
	 */
	public static String getDateTimeStr(Date date,String rule) {
		SimpleDateFormat format = new SimpleDateFormat(rule);
		if(date!=null){
			return format.format(date);
		}else
			return "";
	}
	
	/**
	 * 
	 * @param monthNum 0为当前月份，-1为前一月份，1为下一月份
	 * @param dayNum 该月份第几天,数字不在该月份天数内，则默认为该月份的最后一天
	 * @return
	 */
	public static String getMonthDay(int monthNum,int dayNum,String rule){
		SimpleDateFormat format = new SimpleDateFormat(rule);
		
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, monthNum);
        
        if(dayNum<1||dayNum>c.getActualMaximum(Calendar.DAY_OF_MONTH)){
        	c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        }else{
        	c.set(Calendar.DAY_OF_MONTH,dayNum);
        }
        String date = format.format(c.getTime());
        
		return date;
	}
	
	/**
	 * 增减分钟数
	 * @param date
	 * @param minuteCount
	 * @return
	 */
	public static Date getNextDateByMinuteCount(Date date, int minuteCount) {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		scalendar.add(Calendar.MINUTE, minuteCount);
		return scalendar.getTime();
	}
	
	
	
	public static void main(String[] args) {
//		String date = DateUtil.getYesterdayDate(0);
//		long beginTime = DateUtil.getTimeMillis(date + " 14:30:00");
//		long endTime = DateUtil.getTimeMillis(date + " 15:30:00");
//		
//		long currentTime = System.currentTimeMillis();
//		if(currentTime >= beginTime && currentTime <= endTime){
//			System.out.println((endTime - currentTime)/1000);
//		}
		
		System.out.println(getNextDateByMinuteCount(new Date(),-180));
		System.out.println("该月第一天"+getMonthDay(0,1,"yyyy-MM-dd"));
		System.out.println("该月最后一天"+getMonthDay(1,1,"yyyy-MM-dd"));
		
		System.out.println(getDateTimeStr(new Date(),"yyyy-MM-dd HH:mm"));
		
		System.out.println(getMonth(1));
		
		Date startModifyDate = DateUtil.string2Date(DateUtil.getDateTimeStr(DateUtil.getNextDateByMinuteCount(new Date(), -15*60), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
		Date endModifyDate = DateUtil.string2Date(DateUtil.getDateTimeStr(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
		
		System.out.println(startModifyDate);
		System.out.println(endModifyDate);
	}
	
}
