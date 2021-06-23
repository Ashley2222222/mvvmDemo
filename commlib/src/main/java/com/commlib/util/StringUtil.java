package com.commlib.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串处理工具类
 *
 * @author zhangwei
 * @ClassName: StringUtil
 */

public class StringUtil {
    public static boolean checkEmptyString(String str) {
        return ((null == str) || (str.trim().length() == 0)) || "null".equals(str) || TextUtils.isEmpty(str);
    }

    public static String getNotNullText(String str) {
        if (checkEmptyString(str)) {
            return "";
        } else {
            return str;
        }
    }

    public static String stringToHtmlEntity(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '\n':
                    sb.append(c);
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;                case '&':

                    sb.append("&amp;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                case '\\':
                    sb.append("%2f");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                case '+':
                    sb.append("%2b");
                    break;
                default:
                    if ((c < ' ') || (c > '~')) {
                        sb.append("&#x");
                        sb.append(Integer.toString(c, 16));
                        sb.append(';');
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }
    //去除字符串中的空格\t、回车\n、换行符\r、制表符\t
    public static String replaceBlank(String str) {
        String dest = "";
        if (null!= str) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
            dest=dest.replaceAll("null","");
        }else dest ="";
        return dest;
    }

    /**
     * 时间字符串格式化 适应于两个timestamp相减返回值处理
     *
     * @param timeString
     * @return
     * @author dengwm
     */
    public static String timeStringToFormat(String timeString) {
        String formatString = "";
        if (null != timeString && !"".equals(timeString)) {
            timeString = timeString.substring(0, timeString.indexOf(".")); // 去掉毫秒
            String dateString = timeString.substring(0, timeString.indexOf(" "));
            String[] subTime = timeString.substring(timeString.indexOf(" ")).split(":");
            formatString = dateString + "天" + subTime[0] + "时" + subTime[1] + "分" + subTime[2] + "秒";
        }
        return formatString;
    }

    /**
     * 字符串进行编码
     *
     * @param src 源字符串
     * @Title: escape
     * @author zhangwei
     * @since 2014-2-20 下午1:19:43
     */
    public static String escape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);

        for (int i = 0; i < src.length(); i++) {
            char j = src.charAt(i);

            if ((Character.isDigit(j)) || (Character.isLowerCase(j)) || (Character.isUpperCase(j))) {
                tmp.append(j);
            } else if (j < 'Ā') {
                tmp.append("%");
                if (j < '\020')
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String replaceVariable(String template, Map<String, String> map) throws Exception {
        Pattern regex = Pattern.compile("\\{(.*?)\\}");
        Matcher regexMatcher = regex.matcher(template);
        while (regexMatcher.find()) {
            String key = regexMatcher.group(1);
            String toReplace = regexMatcher.group(0);
            String value = map.get(key);
            if (null!= value )
                template = template.replace(toReplace, value);
            else {
                throw new Exception(new StringBuilder().append("没有找到[").append(key).append("]对应的变量值，请检查表变量配置!").toString());
            }
        }
        return template;
    }

    /**
     * 字符串转对象
     *
     * @param valueString
     * @param fieldType
     * @param format
     * @return
     * @author dengwm
     */
    public static Object stringToObject(String valueString, String fieldType, String format) throws Exception {
        if (fieldType.equals("varchar")) {
            return valueString;
        } else if (fieldType.equals("number")) {
            return Double.parseDouble(valueString);
        } else if (fieldType.equals("date")) {
            if ("".equals(format)) {
                SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);
                return sdf.parse(valueString);
            } else {
                return valueString;
            }
        } else {
            return valueString;
        }
    }

    /**
     * 字符全替换
     *
     * @param toReplace 待替换字符串
     * @param replace   新串
     * @param replaceBy 旧串
     * @return
     * @author dengwm
     */
    public static String replaceAll(String toReplace, String replace, String replaceBy) {
        replaceBy = replaceBy.replaceAll("\\$", "\\\\\\$");
        return toReplace.replaceAll(replace, replaceBy);
    }

    /**
     * 数字格式化
     *
     * @param value
     * @return
     * @author dengwm
     */
    public static String comdify(String value) {
        DecimalFormat df = null;
        if (value.indexOf(".") > 0) {
            int i = value.length() - value.indexOf(".") - 1;
            switch (i) {
                case 0:
                    df = new DecimalFormat("###,##0");
                    break;
                case 1:
                    df = new DecimalFormat("###,##0.0");
                    break;
                case 2:
                    df = new DecimalFormat("###,##0.00");
                    break;
                case 3:
                    df = new DecimalFormat("###,##0.000");
                    break;
                case 4:
                    df = new DecimalFormat("###,##0.0000");
                    break;
                default:
                    df = new DecimalFormat("###,##0.00000");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0D;
        try {
            number = Double.parseDouble(value);
        } catch (Exception e) {
            number = 0.0D;
        }
        return df.format(number);
    }

    /**
     * 字符串进行解码 Copyright jeisai All right reserved.
     *
     * @param src 十六进制字符串
     * @return String 解码后的字符
     * @Title: unescape
     * @author zhangwei
     * @since 2014-2-20 下午1:23:18
     */
    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0;
        int pos = 0;

        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    char ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);

                    tmp.append(ch);
                    lastPos = pos + 6;
                    continue;
                }
                char ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);

                tmp.append(ch);
                lastPos = pos + 3;
                continue;
            }

            if (pos == -1) {
                tmp.append(src.substring(lastPos));
                lastPos = src.length();
                continue;
            }
            tmp.append(src, lastPos, pos);
            lastPos = pos;
        }

        return tmp.toString();
    }

    /**
     * 判断先后顺序 Copyright jeisai All right reserved.
     *
     * @param content
     * @param begin
     * @param end
     * @return
     * @Title: isExist
     * @author zhangwei
     * @since 2014-2-20 下午1:25:15
     */
    public static boolean isExist(String content, String begin, String end) {
        String tmp = content.toLowerCase();
        begin = begin.toLowerCase();
        end = end.toLowerCase();
        int beginIndex = tmp.indexOf(begin);
        int endIndex = tmp.indexOf(end);

        return (beginIndex != -1) && (endIndex != -1) && (beginIndex < endIndex);
    }

    /**
     * 去除前端空格 Copyright jeisai All right reserved.
     *
     * @param toTrim
     * @param trimStr
     * @return String 返回类型
     * @Title: trimPrefix
     * @author zhangwei
     * @since 2014-2-20 下午1:26:16
     */
    public static String trimPrefix(String toTrim, String trimStr) {
        while (toTrim.startsWith(trimStr)) {
            toTrim = toTrim.substring(trimStr.length());
        }
        return toTrim;
    }

    /**
     * 去除后端空格
     *
     * @param toTrim
     * @param trimStr
     * @return
     */
    public static String trimSufffix(String toTrim, String trimStr) {
        while (toTrim.endsWith(trimStr)) {
            toTrim = toTrim.substring(0, toTrim.length() - trimStr.length());
        }
        return toTrim;
    }

    /**
     * 去除前后端空格
     *
     * @param toTrim
     * @param trimStr
     * @return
     */
    public static String trim(String toTrim, String trimStr) {
        return trimSufffix(trimPrefix(toTrim, trimStr), trimStr);
    }

    /**
     * Copyright jeisai All right reserved.
     *
     * @param str 字符串
     * @return String 返回类型
     * @Title: repRegular
     * @author liangxy
     * @since 2014-2-17 上午5:22:02
     */
    public static String repRegularLostSlash(String str) {
        // "(d{4})-([0-1]d)-([0-3]d)s([0-5]d):([0-5]d):([0-5]d)),
        if (str.indexOf("(d{") > 0) {
            str = str.replace("(d{", "(\\d{");
        }
        if (str.indexOf("]d)") > 0) {
            str = str.replace("]d)", "]\\d)");
        }
        if (str.indexOf(")s(") > 0) {
            str = str.replace(")s(", ")\\s(");
        }
        return str;
    }


    /**
     * 替换字符模板 Copyright jeisai All right reserved.
     *
     * @param template
     * @param repaceStr
     * @return String 返回类型
     * @Title: replaceVariable
     * @author zhangwei
     * @since 2014-2-20 下午1:29:36
     */
    public static String replaceVariable(String template, String repaceStr) {
        Pattern regex = Pattern.compile("\\{(.*?)\\}");
        Matcher regexMatcher = regex.matcher(template);
        if (regexMatcher.find()) {
            String toReplace = regexMatcher.group(0);
            template = template.replace(toReplace, repaceStr);
        }
        return template;
    }

    /**
     * 截取字符串 Copyright jeisai All right reserved.
     *
     * @param str
     * @param len
     * @return String 返回类型
     * @Title: subString
     * @author zhangwei
     * @since 2014-2-20 下午1:30:18
     */
    public static String subString(String str, int len) {
        int strLen = str.length();
        if (strLen < len)
            return str;
        char[] chars = str.toCharArray();
        int cnLen = len * 2;
        String tmp = "";
        int iLen = 0;
        for (int i = 0; i < chars.length; i++) {
            int iChar = chars[i];
            if (iChar <= 128)
                iLen += 1;
            else
                iLen += 2;
            if (iLen >= cnLen)
                break;
            tmp = new StringBuilder().append(tmp).append(String.valueOf(chars[i])).toString();
        }
        return tmp;
    }

    //判断是否数字
    public static boolean isNumeric(String str) {
        int i = str.length();
        while (true) {
            i--;
            if (i < 0)
                break;
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 首字母大写 Copyright jeisai All right reserved.
     *
     * @param newStr
     * @return String 返回类型
     * @Title: makeFirstLetterUpperCase
     * @author zhangwei
     * @since 2014-2-20 下午1:32:44
     */
    public static String makeFirstLetterUpperCase(String newStr) {
        if (newStr.length() == 0) {
            return newStr;
        }
        char[] oneChar = new char[1];
        oneChar[0] = newStr.charAt(0);
        String firstChar = new String(oneChar);
        return new StringBuilder().append(firstChar.toUpperCase()).append(newStr.substring(1)).toString();
    }

    /**
     * 首字母小写 Copyright jeisai All right reserved.
     *
     * @param newStr
     * @return String 返回类型
     * @Title: makeFirstLetterLowerCase
     * @author zhangwei
     * @since 2014-2-20 下午1:32:57
     */
    public static String makeFirstLetterLowerCase(String newStr) {
        if (newStr.length() == 0) {
            return newStr;
        }
        char[] oneChar = new char[1];
        oneChar[0] = newStr.charAt(0);
        String firstChar = new String(oneChar);
        return new StringBuilder().append(firstChar.toLowerCase()).append(newStr.substring(1)).toString();
    }

    /**
     * 格式化message Copyright jeisai All right reserved.
     *
     * @param message
     * @param args
     * @return String 返回类型
     * @Title: formatParamMsg
     * @author zhangwei
     * @since 2014-2-20 下午1:34:28
     */
    public static String formatParamMsg(String message, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            message = message.replace(new StringBuilder().append("{").append(i).append("}").toString(), args[i].toString());
        }
        return message;
    }

    public static String formatParamMsg(String message, Map params) {
        if (null == params)
            return message;
        Iterator keyIts = params.keySet().iterator();
        while (keyIts.hasNext()) {
            String key = (String) keyIts.next();
            Object val = params.get(key);
            if (null!= val) {
                message = message.replace(new StringBuilder().append("${").append(key).append("}").toString(), val.toString());
            }
        }
        return message;
    }

    public static StringBuilder formatMsg(CharSequence msgWithFormat, boolean autoQuote, Object[] args) {
        int argsLen = args.length;
        boolean markFound = false;

        StringBuilder sb = new StringBuilder(msgWithFormat);

        if (argsLen > 0) {
            for (int i = 0; i < argsLen; i++) {
                String flag = new StringBuilder().append("%").append(i + 1).toString();
                int idx = sb.indexOf(flag);

                while (idx >= 0) {
                    markFound = true;
                    sb.replace(idx, idx + 2, toString(args[i], autoQuote));
                    idx = sb.indexOf(flag);
                }
            }

            if ((args[(argsLen - 1)] instanceof Throwable)) {
                StringWriter sw = new StringWriter();
                ((Throwable) args[(argsLen - 1)]).printStackTrace(new PrintWriter(sw));
                sb.append("\n").append(sw.toString());
            } else if ((argsLen == 1) && (!markFound)) {
                sb.append(args[(argsLen - 1)].toString());
            }
        }
        return sb;
    }

    public static StringBuilder formatMsg(String msgWithFormat, Object[] args) {
        return formatMsg(new StringBuilder(msgWithFormat), true, args);
    }

    public static String toString(Object obj, boolean autoQuote) {
        StringBuilder sb = new StringBuilder();
        if (null == obj ) {
            sb.append("NULL");
        } else if ((obj instanceof Object[])) {
            for (int i = 0; i < ((Object[]) obj).length; i++) {
                sb.append(((Object[]) obj)[i]).append(", ");
            }
            if (sb.length() > 0)
                sb.delete(sb.length() - 2, sb.length());
        } else {
            sb.append(obj.toString());
        }

        if ((autoQuote) && (sb.length() > 0) && ((sb.charAt(0) != '[') || (sb.charAt(sb.length() - 1) != ']'))
                && ((sb.charAt(0) != '{') || (sb.charAt(sb.length() - 1) != '}'))) {
            sb.insert(0, "[").append("]");
        }
        return sb.toString();
    }

    @SuppressLint("NewApi")
    public static String returnSpace(String str) {
        String space = "";
        if (!str.isEmpty()) {
            String[] path = str.split("\\.");
            for (int i = 0; i < path.length - 1; i++) {
                space = new StringBuilder().append(space).append("&nbsp;&emsp;").toString();
            }
        }
        return space;
    }

    public static synchronized String encryptMd5(String inputStr) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inputStr.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(Integer.toHexString(b & 0xFF));
            }

            return sb.toString();
        } catch (Exception e) {
        }
        return null;
    }

    public static String getArrayAsString(List<String> arr) {
        if ((null == arr) || (arr.size() == 0))
            return "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.size(); i++) {
            if (i > 0)
                sb.append(",");
            sb.append(arr.get(i));
        }
        return sb.toString();
    }

    public static String getArrayAsString(String[] arr) {
        if ((null == arr ) || (arr.length == 0))
            return "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0)
                sb.append(",");
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static String getSetAsString(Set set) {
        if ((null == set ) || (set.size() == 0))
            return "";
        StringBuffer sb = new StringBuffer();
        int i = 0;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (i++ > 0)
                sb.append(",");
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    public static String hangeToBig(double value) {
        char[] hunit = {'拾', '佰', '仟'};
        char[] vunit = {'万', '亿'};
        char[] digit = {38646, '壹', 36144, '叁', 32902, '伍', 38470, '柒', '捌', '玖'};
        long midVal = (long) (value * 100.0D);
        String valStr = String.valueOf(midVal);

        String head = valStr.substring(0, valStr.length() - 2);
        String rail = valStr.substring(valStr.length() - 2);

        String prefix = "";
        String suffix = "";

        if (rail.equals("00")) {
            suffix = "整";
        } else {
            suffix = new StringBuilder().append(digit[(rail.charAt(0) - '0')]).append("角").append(digit[(rail.charAt(1) - '0')]).append("分")
                    .toString();
        }

        char[] chDig = head.toCharArray();
        char zero = '0';
        byte zeroSerNum = 0;
        for (int i = 0; i < chDig.length; i++) {
            int idx = (chDig.length - i - 1) % 4;
            int vidx = (chDig.length - i - 1) / 4;
            if (chDig[i] == '0') {
                zeroSerNum = (byte) (zeroSerNum + 1);
                if (zero == '0') {
                    zero = digit[0];
                }
                if ((idx != 0) || (vidx <= 0) || (zeroSerNum >= 4))
                    continue;
                prefix = new StringBuilder().append(prefix).append(vunit[(vidx - 1)]).toString();
                zero = '0';
            } else {
                zeroSerNum = 0;
                if (zero != '0') {
                    prefix = new StringBuilder().append(prefix).append(zero).toString();
                    zero = '0';
                }
                prefix = new StringBuilder().append(prefix).append(digit[(chDig[i] - '0')]).toString();
                if (idx > 0)
                    prefix = new StringBuilder().append(prefix).append(hunit[(idx - 1)]).toString();
                if ((idx != 0) || (vidx <= 0))
                    continue;
                prefix = new StringBuilder().append(prefix).append(vunit[(vidx - 1)]).toString();
            }
        }

        if (prefix.length() > 0)
            prefix = new StringBuilder().append(prefix).append('圆').toString();
        return new StringBuilder().append(prefix).append(suffix).toString();
    }

    public static String jsonUnescape(String str) {
        return str.replace("&quot;", "\"").replace("&nuot;", "\n");
    }

    public static String htmlEntityToString(String dataStr) {
        dataStr = dataStr.replace("&apos;", "'").replace("&quot;", "\"").replace("&gt;", ">").replace("&lt;", "<").replace("&amp;", "&");

        int start = 0;
        int end = 0;
        StringBuffer buffer = new StringBuffer();

        while (start > -1) {
            int system = 10;
            if (start == 0) {
                int t = dataStr.indexOf("&#");
                if (start != t) {
                    start = t;
                }
                if (start > 0) {
                    buffer.append(dataStr, 0, start);
                }
            }
            end = dataStr.indexOf(";", start + 2);
            String charStr = "";
            if (end != -1) {
                charStr = dataStr.substring(start + 2, end);

                char s = charStr.charAt(0);
                if ((s == 'x') || (s == 'X')) {
                    system = 16;
                    charStr = charStr.substring(1);
                }
            }
            try {
                char letter = (char) Integer.parseInt(charStr, system);
                buffer.append(new Character(letter).toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            start = dataStr.indexOf("&#", end);
            if (start - end > 1) {
                buffer.append(dataStr, end + 1, start);
            }

            if (start == -1) {
                int length = dataStr.length();
                if (end + 1 != length) {
                    buffer.append(dataStr, end + 1, length);
                }
            }
        }
        return buffer.toString();
    }


    public static String encodingString(String str, String from, String to) {
        String result = str;
        try {
            result = new String(str.getBytes(from), to);
        } catch (Exception e) {
            result = str;
        }
        return result;
    }

    /**
     * 补齐不足长度
     *
     * @param length 长度
     * @param number 数字
     * @return
     */
    public static String lpad(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(Locale.CHINA,f, number);
    }

    /**
     * 名称：比较两个字段相似度 Copyright jeisai All right reserved.
     *
     * @param str
     * @param target
     * @return int 返回类型
     * @Title: compare
     * @author zhangsanjun
     * @since 2014-7-29 上午5:35:34
     */
    private static int compare(String str, String target) {

        int d[][]; // 矩阵

        int n = str.length();

        int m = target.length();

        int i; // 遍历str的

        int j; // 遍历target的

        char ch1; // str的

        char ch2; // target的

        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1

        if (n == 0) {

            return m;

        }

        if (m == 0) {

            return n;

        }

        d = new int[n + 1][m + 1];

        for (i = 0; i <= n; i++) { // 初始化第一列

            d[i][0] = i;

        }

        for (j = 0; j <= m; j++) { // 初始化第一行

            d[0][j] = j;

        }

        for (i = 1; i <= n; i++) { // 遍历str

            ch1 = str.charAt(i - 1);

            // 去匹配target

            for (j = 1; j <= m; j++) {

                ch2 = target.charAt(j - 1);

                if (ch1 == ch2) {

                    temp = 0;

                } else {

                    temp = 1;

                }

                // 左边+1,上边+1, 左上角+temp取最小

                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);

            }
        }
        return d[n][m];

    }

    private static int min(int one, int two, int three) {

        return (one = one < two ? one : two) < three ? one : three;

    }

    /**
     * 获取两字符串的相似度
     *
     * @param str
     * @param target
     * @return
     */

    public static float getSimilarityRatio(String str, String target) {

        return 1 - (float) compare(str, target)

                / Math.max(str.length(), target.length());

    }

    //字符串转double
    public static double getDoubleFromString(String str) {
        double result = Double.MIN_VALUE;
        if ((!TextUtils.isEmpty(str)) && (isNumberic(str))) {
            result = Double.parseDouble(str);
        }
        return result;
    }

    //字符串转int
    public static int getIntFromString(String str) {
        int result = Integer.MIN_VALUE;
        if ((!TextUtils.isEmpty(str)) && (isNumberic(str))) {
            result = Integer.parseInt(str);
        }
        return result;
    }


    /**
     * bigdecimal转换为string类型,默认(错误)返回0
     *
     * @param value
     * @return
     */
    public static String bigDecimal2str(BigDecimal value) {
        String strValue = "0";
        if (value != null) {
            strValue = value.toString();
        }
        return strValue;
    }

    /**
     * 传入value,取得对应key
     *
     * @param map
     * @param compareValue
     * @return
     */
    public static String getTreeMapId(TreeMap<String, Object> map, String compareValue) {
        String val = "";
        TreeMap<String, Object> treeMap = map;
        Iterator<String> it = treeMap.keySet().iterator();
        if (!StringUtil.checkEmptyString(compareValue)) {
            while (it.hasNext()) {
                String a = compareValue;
                Object o = it.next();
                if (treeMap.get(o).equals(a)) {
                    // int i = Integer.parseInt(o.toString());
                    // System.out.println(i + "==" +treeMap.get(o));
                    val = o.toString();
                    break;
                }
            }
        }
        return val;
    }

    /**
     * 传入key,取得对应value
     *
     * @param map
     * @param compareValue
     * @return
     */
    public static String getTreeMapVal(TreeMap<String, Object> map, String compareValue) {
        String val = "";
        TreeMap<String, Object> treeMap = map;
        Iterator<String> it = treeMap.keySet().iterator();
        if (!StringUtil.checkEmptyString(compareValue)) {
            while (it.hasNext()) {
                String a = compareValue;
                Object o = it.next();
                if (o.equals(a)) {
                    // int i = Integer.parseInt(o.toString());
                    // System.out.println(i + "==" +treeMap.get(o));
                    val = treeMap.get(o).toString();
                    break;
                }
            }
        }
        return val;
    }

    /**
     * 该方法用于将文字转换为上标
     *
     * @param source 需要转换的文字
     * @return 转换结果，可以让TextView使用
     */
    public Spanned getSuperScript(String source) {
        Spanned result = Html.fromHtml(
                "<sup>" + source +
                        "</sup>");
        return result;
    }

    /**
     * 该方法用于将文字转为细体字
     *
     * @param source 需要转换的文字
     * @return 转换结果，可为TextView使用
     */
    public Spanned getLighterType(String source) {
        Spanned result = Html.fromHtml(
                "<font style='font-size： 100'>" +
                        source +
                        "</font>"
        );
        return result;
    }

    /**
     * 根据存储在XML文件中的字符串的id获得字符串
     *
     * @param context  上下文对象
     * @param stringId 目标字符串的id
     * @return 获得字符串的结果， 如果为null则不存在该id
     */
    public static String getStringById(Context context, int stringId) {
        String str = "";
        if (null != context)
            str = context.getResources().getString(stringId);
        if (null == str) str = "";
        return str;
    }

    /**
     * 名称：判断是否全部为数字（包括小数点）的方法
     * Copyright  jeisai All right reserved.
     *
     * @param message
     * @return boolean    返回类型
     * @throws
     * @Title: isNumberic
     * @author liuxuhao
     * @since 2015-8-12 下午8:04:09
     */
    public static boolean isNumberic(String message) {
        if (null == message) {
            return false;
        }

        char[] numbers = message.toCharArray();
        for (char c : numbers) {
            if (((c <= '9') && (c >= '0')) || (c == '.')) {

            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否数字(包括负数，含有小数点  )
     *
     * @param str
     * @return
     */
    public static boolean isNumberic2(String str) {
        Boolean flag = str.matches("-?[0-9]*.?[0-9]*");
//	    if(flag == true) {
//	      System.out.println("Is Number!");
//	    } else {
//	      System.out.println("Is not Number!");
//	    }
        return flag;
    }
}