package com.commlib.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**  
 * 正则表达式工具类
 * Copyright  jeisai All right reserved.
 * @ClassName: RegexpUtils   正则表达式工具类
 * @author zhangwei
 * @since 2014-2-20 上午11:17:09 
 * Description:  // 用于详细说明此程序文件完成的主要功能
 * Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述  
 */

public class RegexpUtils {
	
	/** 
	  * @Fields icon_regexp : TODO(匹配图像)
	  * 格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)     
	  * 匹配 : /forum/head_icon/admini2005111_ff.gif 或 admini2005111.dmp
	  * 不匹配: c:/admins4512.gif  
	  * @author  zhangwei
	  * @since 2014-2-20 上午11:19:18
	*/ 
	public static final String icon_regexp = "^(/{0,1}\\w){1,}\\.(gif|dmp|png|jpg)$|^\\w{1,}\\.(gif|dmp|png|jpg)$";
	
	 /** 
	  * @Fields email_regexp : TODO(email 地址)
	  * @author  zhangwei
	  * @since 2014-2-20 上午11:21:03
	*/ 
//	public static final String email_regexp = "(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*";
	public static final String email_regexp = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

	/** 
	  * @Fields url_regexp : TODO(匹配URL地址) 
	  * @author  zhangwei
	  * @since 2014-2-20 上午11:31:24
	*/ 
	public static final String url_regexp = "(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";
	
	/** 
	  * @Fields http_regexp : TODO(匹配http地址) 
	  * @author  zhangwei
	  * @since 2014-2-20 上午11:31:43
	*/ 
	public static final String http_regexp = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";
	
    /** 
      * @Fields date_regexp : TODO(匹配日期) 
      * @author  zhangwei
      * @since 2014-2-20 上午11:32:32
    */ 
    public static final String date_regexp = "^((((19){1}|(20){1})d{2})|d{2})[-\\s]{1}[01]{1}d{1}[-\\s]{1}[0-3]{1}d{1}$";// 匹配日期
	 
    /** 
      * @Fields phone_regexp : TODO(匹配电话) 
      * @author  zhangwei
      * @since 2014-2-20 上午11:35:09
    */ 
    public static final String phone_regexp = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";
    
    /** 
      * @Fields ID_card_regexp : TODO(匹配身份证号码) 
      * @author  zhangwei
      * @since 2014-2-20 上午11:35:29
    */ 
    public static final String ID_card_regexp = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";
	 
    /** 
      * @Fields ZIP_regexp : TODO(匹配邮编代码) 
      * @author  zhangwei
      * @since 2014-2-20 上午11:39:11
    */ 
    public static final String ZIP_regexp = "^[0-9]{6}$";
    
    /** 
      * @Fields non_negative_integers_regexp : TODO(匹配非负整数（正整数 + 0)) 
      * @author  zhangwei
      * @since 2014-2-20 上午11:39:30
    */ 
    public static final String non_negative_integers_regexp = "^\\d+$";
    
    /** 
      * @Fields non_zero_negative_integers_regexp : TODO(匹配不包括零的非负整数（正整数 > 0) ) 
      * @author  zhangwei
      * @since 2014-2-20 上午11:40:55
    */ 
    public static final String non_zero_negative_integers_regexp = "^[1-9]+\\d*$";
    
    /** 
      * @Fields Ip_regexp : TODO(IP地址) 
      * @author  zhangwei
      * @since 2014-2-20 上午11:55:52
    */ 
    public static final String Ip_regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    /**  
      * 匹配邮箱地址
      * Copyright  jeisai All right reserved.
      * @Title: isEmail  
      * @author zhangwei
      * @since 2014-2-20 上午11:49:11
      * @param s
      * @return  
      * @return boolean    返回类型  
      * @throws 
      * @Description:  // 用于详细说明此程序文件完成的主要功能
      * Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述  
    */
    public static boolean isEmail(String s)
    {
        return validByRegex(email_regexp, s);
    }

    /**  
      * 匹配手机号码
      * Copyright  jeisai All right reserved.
      * @Title: isPhone  
      * @author zhangwei
      * @since 2014-2-20 上午11:52:34
      * @param s
      * @return  
      * @return boolean    返回类型  
      * @throws 
      * @Description:  // 用于详细说明此程序文件完成的主要功能
      * Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述  
    */
    public static boolean isPhone(String s)
    {
        return validByRegex(phone_regexp, s);
    }

    /**  
      * 匹配邮政编码
      * Copyright  jeisai All right reserved.
      * @Title: isZip  
      * @author zhangwei
      * @since 2014-2-20 上午11:53:05
      * @param s
      * @return  
      * @return boolean    返回类型  
      * @throws 
      * @Description:  // 用于详细说明此程序文件完成的主要功能
      * Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述  
    */
    public static boolean isZip(String s)
    {
        return validByRegex(ZIP_regexp, s);
    }

    /**  
      * 匹配ip地址
      * Copyright  jeisai All right reserved.
      * @Title: isIp  
      * @author zhangwei
      * @since 2014-2-20 上午11:56:49
      * @param s
      * @return  
      * @return boolean    返回类型  
      * @throws 
      * @Description:  // 用于详细说明此程序文件完成的主要功能
      * Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述  
    */
    public static boolean isIp(String s)
    {
        return validByRegex(Ip_regexp, s);
    }


    /**  
      * 匹配整数
      * Copyright  jeisai All right reserved.
      * @Title: isInteger  
      * @author zhangwei
      * @since 2014-2-20 上午11:58:59
      * @param s
      * @return  
      * @return boolean    返回类型  
      * @throws 
      * @Description:  // 用于详细说明此程序文件完成的主要功能
      * Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述  
    */
    public static boolean isInteger(String s)
    {
      boolean rtn = validByRegex("^[-+]{0,1}\\d*$", s);
      return rtn;
    }

    /**  
      * 匹配URL地址
      * Copyright  jeisai All right reserved.
      * @Title: isUrl  
      * @author zhangwei
      * @since 2014-2-20 上午11:57:01
      * @param url
      * @return  
      * @return boolean    返回类型  
      * @throws 
      * @Description:  // 用于详细说明此程序文件完成的主要功能
      * Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述  
    */
    public static boolean isUrl(String url)
    {
      return validByRegex(http_regexp, url);
    }
    /**  
      * 正则表达式匹配
      * Copyright  jeisai All right reserved.
      * @Title: validByRegex  
      * @author zhangwei
      * @since 2014-2-20 上午11:44:55
      * @param regex
      * @param input
      * @return  
      * @return boolean    返回类型  
      * @throws 
      * @Description:  // 用于详细说明此程序文件完成的主要功能
      * Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述  
    */
    public static boolean validByRegex(String regex, String input)
    {
      Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
      Matcher regexMatcher = p.matcher(input);
      return regexMatcher.find();
    }


    
}
