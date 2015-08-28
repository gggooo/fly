package common.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
	
	/**
	 * 两个时间相减
	 * @param signinbegindate
	 * @param signinfinishdate
	 * @return
	 */
	public static String timeMinus2(Timestamp signinbegindate, Timestamp signinfinishdate) {
		StringBuffer stb=new StringBuffer();
		long between = (signinfinishdate.getTime()-signinbegindate.getTime())/1000;
		long day=between/(3600*24);   
		long hour=between%(3600*24)/3600;
		stb.append(day);
		stb.append("天");
		stb.append(hour);
		stb.append("时");
		return stb.toString();
	}
	
	/**
	 * 两个时间相减
	 * @param signinbegindate
	 * @param signinfinishdate
	 * @return
	 */
	public static String timeMinus(Timestamp signinbegindate, Timestamp signinfinishdate) {
		StringBuffer stb=new StringBuffer();
		long between = (signinfinishdate.getTime()-signinbegindate.getTime())/1000;
		long hour=between/3600;   
		long minute=between%3600/60;
		long second=between%60;
		stb.append(+hour);
		stb.append("时");
		if(minute<10){
			stb.append("0");
		}
		stb.append(minute);
		stb.append("分");
		if(second<10){
			stb.append("0");
		}
		stb.append(second);
		stb.append("秒");
		return stb.toString();
	}

	public static String formatDate(Date date, String format) {
		if (date != null) {
			SimpleDateFormat outFormat = new SimpleDateFormat(format);
			return outFormat.format(date);
		} else
			return "";
	}
	
	
	public static Date strToDate(String strDate,String format){
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat(format);
		Date formatDate = null;
		try {
			formatDate = simpleDateFormat.parse(strDate);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return formatDate;
	}
	
	
	public static Calendar strToCalendar(String strDate,String format){
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat(format);
		Date formatDate = null;
		try {
			formatDate = simpleDateFormat.parse(strDate);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		Calendar res = Calendar.getInstance();
		res.setTime(formatDate);
		return res;
	}
	

	/**
	 * 根据季度找到该季度的第一天和最后一天
	 * @param year
	 * @param quar
	 * @return
	 */
	public static String[] findQuarFirstAndLastDate(String year, String quar) {
		String st = "";
		String et = "";
		if("1".equals(quar)){
			st = year+"-01-01";
			et = year+"-03-31";
		}else if("2".equals(quar)){
			st= year+"-04-01";
			et = year+"-06-30";
		}else if("3".equals(quar)){
			st = year+"-07-01";
			et = year+"-09-30";
		}else if("4".equals(quar)){
			st = year+"-10-01";
			et = year+"-12-31";
		}
		return new String[]{st,et};
	}


	/**
	 * 返回当月第一天
	 * @return
	 */
	public static String findFirstDayOfMonth() {
		String month = formatDate(new Date(System.currentTimeMillis()), "yyyy-MM");
		return month+"-01";
	}

	/**
	 * 返回某月第一天
	 * @return
	 */
	public static String findFirstDayOfMonth(Date date) {
		String month = formatDate(date, "yyyy-MM");
		return month+"-01";
	}

	/**
	 * 返回昨天
	 * @return
	 */
	public static String findYesterday() {
		String res = formatDate(new Date(System.currentTimeMillis()-Constant.DAY_SPAN), "yyyy-MM-dd");
		return res;
	}

	public static String find26DayOfMonth() {
		String date = formatDate(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
		return find26DayOfMonth(date);
	}
	
	/**
	 * date=2013-02-25 return 2013-01-26
	 * date=2013-02-26 return 2013-02-26
	 * date=2013-02-27 return 2013-02-26
	 * @param date
	 * @return
	 */
	public static String find26DayOfMonth(int month) {
		DecimalFormat df = new DecimalFormat("00");
		String format = df.format(month);
		String date  = "2013-"+format+"-01";
		return find26DayOfMonth(date) + " 06:00";
	}
	
	/**
	 * date=2013-02-25 return 2013-01-26
	 * date=2013-02-26 return 2013-02-26
	 * date=2013-02-27 return 2013-02-26
	 * @param date
	 * @return
	 */
	public static String find26DayOfMonth(String date) {
		Date orandate = strToDate(date, "yyyy-MM-dd");
		Calendar orancal = Calendar.getInstance();
		orancal.setTime(orandate);
		String month = date.substring(0, date.length() - 2);
		String mst = month+"26";
		Date date26 = strToDate(mst, "yyyy-MM-dd");
		Calendar oran26 = Calendar.getInstance();
		oran26.setTime(date26);
		if(orancal.before(oran26))//在本月26号之前,返回上月26
		{
			oran26.add(Calendar.MONTH, -1);
		}
		return formatDate(oran26.getTime(), "yyyy-MM-dd");
	}
	/**
	 * date=2013-12-25 return 2012-12-26
	 * date=2013-12-26 return 2013-12-26
	 * date=2013-12-27 return 2013-12-26
	 * @param date
	 * @return
	 */
	public static String find26DayOfYear(String date) {
		Date orandate = strToDate(date, "yyyy-MM-dd");
		Calendar orancal = Calendar.getInstance();
		orancal.setTime(orandate);
		String year = date.substring(0, date.length() - 5);
		String mst = year+"12-26";
		Date date26 = strToDate(mst, "yyyy-MM-dd");
		Calendar oran26 = Calendar.getInstance();
		oran26.setTime(date26);
		if(orancal.before(oran26))//在本月26号之前,返回上月26
		{
			oran26.add(Calendar.YEAR, -1);
		}
		return formatDate(oran26.getTime(), "yyyy-MM-dd");
	}
	
	public static Calendar find26DayOfThisYear_Calendar() {
		Calendar res = Calendar.getInstance();
		String date = formatDate(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
		res.setTime(DateTimeUtils.strToDate(find26DayOfYear(date)+" 06:00", "yyyy-MM-dd HH:mm"));
		return res;
	}
	
	public static String find26DayOfThisYear_Str() {
		String date = formatDate(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
		return find26DayOfYear(date)+" 06:00";
	}

	/**
	 * 
	 * @param date
	 * @return date 06:00
	 */
	public static String set6clock(String date) {
		return date + " 06:00";
	}

	/**
	 * 找到date属于那个月
	 * 
	 * @param date yyyy-mm-dd hh24:mi
	 */
	public static int findMonth(String date) {
		Date strToDate = strToDate(date, "yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(strToDate);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if(day>26||(day==26&&hour>=6)){
			int i = c.get(Calendar.MONTH)+2;
			if(i>12){
				i=1;
			}
			return i;
			
		}else{
			return c.get(Calendar.MONTH)+1;
		}
	}

	public static String findStartOfYear(String year) {
		Integer yi = Integer.valueOf(year)-1;
		String date  = yi+"-12-26 06:00";
		return date;
	}

	public static String findEndOfYear(String year) {
		String date  = year+"-12-26 06:00";
		return date;
	}

	/**
	 * 
	 * @return 2014-07-01 - 2014-08-01
	 */
	public static String findLastMonth() {
		String date = formatDate(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, -1);
		String date2 = formatDate(new Date(now.getTimeInMillis()), "yyyy-MM-dd");
		return date2+" - "+date;
	}
}
