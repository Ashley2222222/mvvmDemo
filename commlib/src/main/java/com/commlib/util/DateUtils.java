package com.commlib.util;

import android.util.Log;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类 Copyright jeisai All right reserved.
 *
 * @ClassName: DateUtils
 * @author dengwm
 * @since 2014-2-20 上午11:18:58 Description: // 日期工具类 Modify History: //
 *        代码重构,将DateUtils与DateFormateUtils合并(dengwm)
 */

/**
 * 名称：
 * Copyright  jiesai All right reserved.
 * @ClassName: DateUtils
 * @author liangxueyi
 * @since 2016年11月21日 下午4:21:29
 * Description:
 * Modify History:
 */

public class DateUtils {

    public static final String YMD = "yyyyMMdd";

    public static final String YMDHMS = "yyyyMMdd HH:mm:ss";

    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String Y_M= "yyyy-MM";

    public static final String Y_M_DHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String Y_M_DHM2 = "yyyy_MM_dd_HH_mm_ss";

    public static final String Y_M_DHM = "yyyy年MM月dd日 HH时mm分";

    public static final String Y_M_D2 = "yyyy年MM月dd日";
    public static final String HM = "HH:mm";
    public static final String Y = "yyyy";

    /**
     * 获取当前时间字符串 Copyright jeisai All right reserved.
     *
     * @Title: getCurrTimeForString
     * @author dengwm
     * @since 2014-2-20 上午11:17:45
     * @return String 返回类型 Description: // 获取当前时间字符串 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static String getCurrTimeForString() {
        return formatDate(new Date(), Y_M_DHMS);
    }
    public static String getCurrTimeWith_() {
        return formatDate(new Date(), Y_M_DHM2);
    }
    public static String getCurrHMForString() {
        return formatDate(new Date(), HM);
    }

    public static String getCurrYearForString() {
        return formatDate(new Date(), Y);
    }
    public static String getCurrTimeForString3() {
        return formatDate(new Date(), Y_M_D);
    }

    /**
     * 名称：字符串转为格式为yyyy-mm-dd的字符串
     * Copyright  jiesai All right reserved.
     * @Title: StrToDate
     * @author liangxueyi
     */
    public static String StrToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(Y_M_D,Locale.CHINA);
        Date date= new Date();
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate(date, Y_M_D);
    }

    /**
     * 获取当前时间字符串 Copyright jeisai All right reserved.
     *
     * @Title: getCurrTimeForString
     * @author dengwm
     * @since 2014-2-20 上午11:17:45
     * @return String 返回类型 Description: // 获取当前时间字符串 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static String getCurrTimeForString2() {
        return formatDate(new Date(), Y_M_DHM);
    }

    /**
     * 获取当前日期字符串 Copyright jeisai All right reserved.
     *
     * @Title: getCurrTimeForString
     * @author dengwm
     * @since 2014-2-20 上午11:17:45
     * @return String 返回类型 Description: // 获取当前时间字符串 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static String getCurrDateForForm(String type) {
        if (StringUtil.checkEmptyString(type)) {
            type = Y_M_DHMS;
        }
        return formatDate(new Date(), type);
    }

    /**
     * 获取当前日期字符串 Copyright jeisai All right reserved.
     *
     * @Title: getCurrTimeForString
     * @author dengwm
     * @since 2014-2-20 上午11:17:45
     * @return String 返回类型 Description: // 获取当前时间字符串 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static String getCurrDateForString() {
        return formatDate(new Date(), Y_M_D);
    }

    public static String getCurrDateTimeForString() {
        return formatDate(new Date(), Y_M_DHMS);
    }

    /**
     * 将格式日期的字符串解析成Date Copyright jeisai All right reserved.
     *
     * @Title: parseDate
     * @author dengwm
     * @since 2014-2-20 下午1:43:49
     * @param dateStr
     * @param pattern
     * @return Date 返回类型 Description: // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date parseDate(String dateStr, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern,Locale.CHINA);
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期成字符串 Copyright jeisai All right reserved.
     *
     * @Title: formatDate
     * @author dengwm
     * @since 2014-2-20 下午1:43:29
     * 计算两个时间的时间差 Copyright jeisai All right reserved.
     *
     * @Title: getIntervalForTwoDate
     * @author dengwm
     * @since 2014-2-20 上午11:29:35
     * @param date
     * @param pattern
     * @return String 返回类型 Description: // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern,Locale.CHINA);
        return format.format(date);
    }

    /**
     * @param startDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return String 返回类型 Description: // 计算两个Date类型时间的时间差 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static String getIntervalForTwoDate(Date startDate, Date endDate) {
        if (null != startDate && null != endDate) {
            long t1 = startDate.getTime();
            long t2 = endDate.getTime();
            int hours = (int) ((t2 - t1) / 3600000);
            int minutes = (int) (((t2 - t1) / 1000 - hours * 3600) / 60);
            int second = (int) ((t2 - t1) / 1000 - hours * 3600 - minutes * 60);
            return "" + hours + "小时" + minutes + "分" + second + "秒";
        }
        return "";
    }

    /**
     * 按calendar的类型获取时间数 Copyright jeisai All right reserved.
     *
     * @Title: getDateField
     * @author dengwm
     * @since 2014-2-20 上午11:32:49
     * @param date
     *            日期
     * @param field
     *            Calendar支持字段类型
     * @return int 返回类型 Description: // 按calendar的类型获取时间数，如：年、月、日等calendar支持的类型
     *         Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static int getDateField(Date date, int field) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(field);
    }

    /**
     * 计算两个时间的相差年数 Copyright jeisai All right reserved.
     *
     * @Title: getYearsBetweenDate
     * @author dengwm
     * @since 2014-2-20 上午11:35:44
     * @param begin
     *            开始时间
     * @param end
     *            结束时间
     * @return int 返回类型 Description: // 计算两个时间的相差年数，支持连个Date类型的日期计算 Modify
     *         History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static int getYearsBetweenDate(Date begin, Date end) {
        int bYear = getDateField(begin, Calendar.YEAR);
        int eYear = getDateField(end, Calendar.YEAR);
        return eYear - bYear;
    }

    /**
     * 计算两个时间相差的月数 Copyright jeisai All right reserved.
     *
     * @Title: getMonthsBetweenDate
     * @author dengwm
     * @since 2014-2-20 上午11:35:48
     * @param begin
     *            开始时间
     * @param end
     *            结束时间
     * @return int 返回类型 Description: // 计算两个时间相差的月数 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static int getMonthsBetweenDate(Date begin, Date end) {
        int bMonth = getDateField(begin, Calendar.MONTH);
        int eMonth = getDateField(end, Calendar.MONTH);
        return eMonth - bMonth;
    }

    /**
     * 计算两个时间相差的周数 Copyright jeisai All right reserved.
     *
     * @Title: getWeeksBetweenDate
     * @author dengwm
     * @since 2014-2-20 上午11:35:57
     * @param begin
     *            开始时间
     * @param end
     *            结束时间
     * @return int 返回类型 Description: // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static int getWeeksBetweenDate(Date begin, Date end) {
        int bWeek = getDateField(begin, Calendar.WEEK_OF_YEAR);
        int eWeek = getDateField(end, Calendar.WEEK_OF_YEAR);
        return eWeek - bWeek;
    }

    /**
     * 计算两个时间相差的天数 Copyright jeisai All right reserved.
     *
     * @Title: getDaysBetweenDate
     * @author dengwm
     * @since 2014-2-20 上午11:36:01
     * @param begin
     *            开始时间
     * @param end
     *            结束时间
     * @return int 返回类型 Description: // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static int getDaysBetweenDate(Date begin, Date end) {
        int bDay = getDateField(begin, Calendar.DAY_OF_YEAR);
        int eDay = getDateField(end, Calendar.DAY_OF_YEAR);
        return eDay - bDay;
    }

    /**
     * 获取date年后(前)的amount年的第一天的开始时间 Copyright jeisai All right reserved.
     *
     * @Title: getSpecficYearStart
     * @author dengwm
     * @since 2014-2-20 上午11:40:19
     * @param date
     * @param amount
     * @return Date 返回类型 Description: // 获取date年后的amount年的第一天的开始时间 Modify
     *         History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date getSpecficYearStart(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, amount);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return getStartDate(cal.getTime());
    }

    /**
     * 获取date年后的amount年的最后一天的终止时间 Copyright jeisai All right reserved.
     *
     * @Title: getSpecficYearEnd
     * @author dengwm
     * @since 2014-2-20 上午11:41:43
     * @param date
     * @param amount
     * @return
     * @return Date 返回类型
     *             : // 获取date年后的amount年的最后一天的终止时间 Modify History: //
     *             修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date getSpecficYearEnd(Date date, int amount) {
        Date temp = getStartDate(getSpecficYearStart(date, amount + 1));
        Calendar cal = Calendar.getInstance();
        cal.setTime(temp);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return getFinallyDate(cal.getTime());
    }

    /**
     * 获取date月后的amount月的第一天的开始时间 Copyright jeisai All right reserved.
     *
     * @Title: getSpecficMonthStart
     * @author dengwm
     * @since 2014-2-20 上午11:41:50
     * @param date
     * @param amount
     * @return
     * @return Date 返回类型
     *             : // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *             修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date getSpecficMonthStart(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, amount);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getStartDate(cal.getTime());
    }

    /**
     * 获取当前自然月后的amount月的最后一天的终止时间 Copyright jeisai All right reserved.
     *
     * @Title: getSpecficMonthEnd
     * @author dengwm
     * @since 2014-2-20 上午11:41:54
     * @param date
     * @param amount
     * @return
     * @return Date 返回类型
     *             : // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *             修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date getSpecficMonthEnd(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getSpecficMonthStart(date, amount + 1));
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return getFinallyDate(cal.getTime());
    }

    /**
     * 获取date周后的第amount周的开始时间（这里星期一为一周的开始） Copyright jeisai All right reserved.
     *
     * @Title: getSpecficWeekStart
     * @author dengwm
     * @since 2014-2-20 上午11:41:59
     * @param date
     * @param amount
     * @return Date 返回类型 Description: // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date getSpecficWeekStart(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
        cal.add(Calendar.WEEK_OF_MONTH, amount);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getStartDate(cal.getTime());
    }

    /**
     * 获取date周后的第amount周的最后时间（这里星期日为一周的最后一天） Copyright jeisai All right
     * reserved.
     *
     * @Title: getSpecficWeekEnd
     * @author dengwm
     * @since 2014-2-20 上午11:44:20
     * @param date
     * @param amount
     * @return
     * @return Date 返回类型
     *             : // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *             修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date getSpecficWeekEnd(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
        cal.add(Calendar.WEEK_OF_MONTH, amount);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getFinallyDate(cal.getTime());
    }

    /**
     * 得到指定日期的一天的的最后时刻23:59:59 Copyright jeisai All right reserved.
     *
     * @Title: getFinallyDate
     * @author dengwm
     * @since 2014-2-20 上午11:44:31
     * @param date
     * @return
     * @return Date 返回类型
     *             : // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *             修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date getFinallyDate(Date date) {
        String temp = formatDate(date, YMD);
        temp += " 23:59:59";
        return parseDate(temp, YMDHMS);
    }

    /**
     * 得到指定日期的一天的开始时刻00:00:00 Copyright jeisai All right reserved.
     *
     * @Title: getStartDate
     * @author dengwm
     * @since 2014-2-20 上午11:44:36
     * @param date
     * @return Date 返回类型 Description: // 用于详细说明此程序文件完成的主要功能 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date getStartDate(Date date) {
        String temp = formatDate(date, YMD);
        temp += " 00:00:00";
        return parseDate(temp, YMDHMS);
    }

    /**
     * 拿到当前月的最后一天 Copyright jeisai All right reserved.
     *
     * @Title: getLastDayOfMonth
     * @author dengwm
     * @since 2014-2-20 上午11:48:51
     * @return Date 返回类型 Description: // 拿到当前月的最后一天 Modify History: //
     *         修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述
     */
    public static Date getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 将未指定格式的日期字符串转化成 Copyright jeisai All right reserved.
     *
     * @Title: parseStringToDate
     * @author dengwm
     * @since 2014-2-20 下午2:09:41
     * @param date
     * @return Date 返回类型 Description: // 用于未知日期格式转换 Modify History: //
     *         重构代码（张三军引入）
     */
    public static Date parseStringToDate(String date) {
        Date result = null;
        String parse = date;
        parse = parse.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
        parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
        parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        DateFormat format = new SimpleDateFormat(parse,Locale.CHINA);
        try {
            result = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Date JSONObj2Date(JSONObject dateObj) {
        Date date = new Date();
        String dateStr = "";
        if (dateObj != null) {
            try {
                int year = dateObj.getInt("year");
                if (year != 0) {
                    year += 1900;
                    dateStr = year + "";
                }
                int month = dateObj.getInt("month");
                if (month != 0) {
                    month += 1;
                    if (month < 10) {
                        dateStr = dateStr + "-0" + month;
                    } else {
                        dateStr = dateStr + "-" + month;
                    }
                }
                int day = dateObj.getInt("date");
                if (day < 10) {
                    dateStr = dateStr + "-0" + day;
                } else {
                    dateStr = dateStr + "-" + day;
                }
                int hours = dateObj.getInt("hours");
                if (hours < 10) {
                    dateStr = dateStr + " 0" + hours;
                } else {
                    dateStr = dateStr + " " + hours;
                }
                int minutes = dateObj.getInt("minutes");
                if (minutes < 10) {
                    dateStr = dateStr + ":0" + minutes;
                } else {
                    dateStr = dateStr + ":" + minutes;
                }
                int seconds = dateObj.getInt("seconds");
                if (seconds < 10) {
                    dateStr = dateStr + ":0" + seconds;
                } else {
                    dateStr = dateStr + ":" + seconds;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
        if (!StringUtil.checkEmptyString(dateStr)) {
            date = parseDate(dateStr, DateUtils.Y_M_DHMS);
        }
        return date;

    }
    /**
     * 通过年份和月份 得到当月的日子
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
                    return 29;
                }else{
                    return 28;
                }
            default:
                return  -1;
        }
    }
    /**
     * 返回当前月份1号位于周几
     * @param year
     * 		年份
     * @param month
     * 		月份，传入系统获取的，不需要正常的
     * @return
     * 	日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    public static int getFirstDayWeek(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    //获取一个星期后时间
    public static String get7DaysLater() {
        String str = "";
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        int month = c.get(Calendar.MONTH) + 1;
        String mMonth = String.valueOf(month);// 获取当前月份
        if (mMonth.length()==1)
            mMonth = "0"+mMonth;
        int day = c.get(Calendar.DAY_OF_MONTH) + 7;

        if (((month==1)||(month==3)||(month==5)||(month==7)||(month==8)
                ||(month==10)||(month==12))&&day>31)
            day = day-31;
        else if (((month==4)||(month==6)||(month==9)||(month==11))&&day>30)
            day = day-30;
        String mDay = String.valueOf(day);// 获取当前日份的日期号码
        if (mDay.length()==1)
            mDay = "0"+mDay;
        str = mYear+mMonth+mDay;

        return str;
    }
    //比较2日期先后,d1 在d2前或相等 返回 true
    public static boolean compareDate(String d1, String d2){

        Date date1 = parseStringToDate(d1);
        Date date2 = parseStringToDate(d2);
        if(date1.before(date2)||date1.equals(date2))
            return true;
        else return false;
    }
    public static String getOtherDay(int diff) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DATE, diff);
        return getDateFormat(mCalendar.getTime());
    }
    /**
     * 将date转成yyyy-MM-dd字符串<br>
     *
     * @param date Date对象
     * @return yyyy-MM-dd
     */
    public static String getDateFormat(Date date) {
        return dateSimpleFormat(date, defaultDateFormat.get());
    }
    /**
     * 将date转成字符串
     *
     * @param date   Date
     * @param format SimpleDateFormat
     *               <br>
     *               注： SimpleDateFormat为空时，采用默认的yyyy-MM-dd HH:mm:ss格式
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String dateSimpleFormat(Date date, SimpleDateFormat format) {
        if (null == format)
            format = defaultDateTimeFormat.get();
        return (null == date ? "" : format.format(date));
    }
    /**
     * yyyy-MM-dd HH:mm:ss格式
     */
    public static final ThreadLocal<SimpleDateFormat> defaultDateTimeFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(Y_M_DHMS,Locale.CHINA);
        }

    };

    /**
     * yyyy-MM-dd HH:mm:ss格式
     */
    public static final ThreadLocal<SimpleDateFormat> defaultDateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(Y_M_D,Locale.CHINA);
        }

    };

    /**
     * 将字符串转为时间戳
     */
    public static long toTimeStamp(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        Date date;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return date.getTime() / 1000;
    }

    /**
     * 获取时分秒
     *
     * @param timestamp 时间戳（单位：毫秒）
     *
     * @return
     */
    public static String getHMS(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss",Locale.CHINA);
        try {
            return sdf.format(new Timestamp(timestamp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(timestamp);
    }

    /**
     * 获取年月日 时分秒
     *
     * @param timestamp 时间戳（单位：毫秒）
     *
     * @return
     */
    public static String formatTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        try {
            return sdf.format(new Timestamp(timestamp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(timestamp);
    }

    public static String formatSecond(int second) {
        String format = "%1$,02d:%2$,02d:%3$,02d";
        Integer hours = second / (60 * 60);
        Integer minutes = second / 60 - hours * 60;
        Integer seconds = second - minutes * 60 - hours * 60 * 60;
        Object[] array = new Object[] {hours, minutes, seconds};
        return String.format(Locale.CHINA,format, array);
    }

}
