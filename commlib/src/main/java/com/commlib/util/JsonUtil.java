package com.commlib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * 名称：json工具类
 * Copyright  jeisai All right reserved.
 *
 * @author zhangsanjun
 * @ClassName: JsonUtil
 * @since 2015年7月31日 下午1:44:55
 * Description:
 * Modify History:
 */
public class JsonUtil {


    /**
     * 名称：object转化为jsonArray
     * Copyright  jeisai All right reserved.
     *
     * @param object
     * @Title: fromObject_Array
     * @author zhangsanjun
     * @since 2015年7月31日 下午1:46:56
     */
    public static JSONArray fromObject_Array(Object object)
            throws JSONException {
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();
        gsonb.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return new JSONArray(gson.toJson(object));
    }

    /**
     * <code>toBean</code>
     *
     * @param jsonString
     * @param beanclass
     * @description: TODO(json字符串转化为类)
     */
    public static Object toBean(String jsonString, Class beanclass) {
//        GsonBuilder gsonb = new GsonBuilder();
//        gsonb.setDateFormat("yyyy-MM-dd HH:mm:ss");
//		Gson gson = gsonb.create();


        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

// Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        Gson gson = builder.create();
        return gson.fromJson(jsonString, beanclass);
    }

    /**
     * <code>toBean</code>
     *
     * @param object
     * @param beanclass
     * @return
     * @description: TODO(json对象转化为类)
     */
    public static Object toBean(JSONObject object, Class beanclass) {
        return toBean(object.toString(), beanclass);
    }
    /**
     * 转成list
     */
    public static <T> ArrayList<T> stringToList(String gsonString, Class<T> cls) {
        ArrayList<T> list = new ArrayList<>();
        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

// Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        Gson gson = builder.create();
        if (null!=gson) {
            JsonArray array = new JsonParser().parse(gsonString).getAsJsonArray();
            for (final JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        }
        return list;
    }

    /**
     * 名称：json转map
     *
     * @param field
     * @return Map<String,Object>    返回类型
     * @Title: Json2Map
     * @author zhangsanjun
     * @since 2014-6-4 上午7:02:05
     */
    public static Map<String, Object> Json2Map(JSONObject field) {

        Map<String, Object> valueMap = new HashMap<String, Object>();
        if (!StringUtil.checkEmptyString(field.toString())) {
            Iterator<String> keyIter = field.keys();
            String key;
            Object value;
            try {
                while (keyIter.hasNext()) {
                    key = keyIter.next();
                    value = field.get(key);
                    valueMap.put(key.toLowerCase(), value);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return valueMap;
    }

    /**
     * 名称：json日期格式
     *
     * @param dateObj
     * @return String    返回类型
     * @Title: JsonDateForm
     * @author zhangsanjun
     * @since 2014-6-20 上午8:20:37
     */
    public static String JsonDateForm(JSONObject dateObj, String dateForm) {
        if (StringUtil.checkEmptyString(dateForm)) {
            dateForm = DateUtils.Y_M_D;
        }
        String dateStr = DateUtils.getCurrDateForForm(dateForm);
        if (null!=dateObj) {
            try {
                //,"minutes":29,"seconds":28,"hours":6,"month":5,"year":114,"timezoneOffset":0,"day":5,"date":20
                int year = dateObj.getInt("year") + 1900;
                int month = dateObj.getInt("month") + 1;
                int date = dateObj.getInt("date");
                int hours = dateObj.getInt("hours");
                int seconds = dateObj.getInt("seconds");
                int minutes = dateObj.getInt("minutes");
                dateStr = year + "-" + StringUtil.lpad(2, month) + "-" + StringUtil.lpad(2, date);
                if (hours != 0 && minutes != 0 && seconds != 0) {
                    dateStr += " " + StringUtil.lpad(2, hours) + ":" + StringUtil.lpad(2, minutes) + ":" + StringUtil.lpad(2, seconds);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        } else {

        }

        return dateStr;
    }


    /**
     * 名称：JSONArray 删除
     * Copyright  jeisai All right reserved.
     *
     * @Title: remove
     * @author zhangsanjun
     * @since 2014-7-2 上午9:09:08
     */
    public static JSONArray remove(JSONArray array, int index) {
        JSONArray retArray = new JSONArray();
        //判断array不为空
        if (null!=array) {
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = (JSONObject) array.opt(i);
                if (i != index) {
                    retArray.put(obj);
                }
            }
        }
        return retArray;
    }


    /**
     * 名称：JSONArray删除数组中某个字段
     * Copyright  jeisai All right reserved.
     *
     * @Title: remove
     * @author zhangsanjun
     * @since 2014-7-2 上午9:09:08
     */
    public static JSONArray deleteStr(JSONArray array, String str) {
        JSONArray retArray = new JSONArray();
        //判断array不为空
        if (null!=array ) {
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = (JSONObject) array.opt(i);
                obj.remove(str);
                retArray.put(obj);
            }
        }
        return retArray;
    }


    /**
     * 名称：arrayAddArray
     * Copyright  jeisai All right reserved.
     *
     * @Title: arrayAddArray
     * @author zhangsanjun
     * @since 2014-7-16 上午7:36:14
     */
    public static JSONArray arrayAddArray(JSONArray array1, JSONArray array2) {
        if (null!=array1 ) {
            if (null!=array2 ) {
                for (int i = 0; i < array2.length(); i++) {
                    try {
                        array1.put(array2.get(i));
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return array2;
        }

        return array1;
    }

    /**
     * 名称：判断json是否存在某个字段值为对象
     *
     * @return boolean    返回类型
     * @Title: JsonContains
     * @author zhangsanjun
     * @since 2016年4月11日 上午11:53:11
     */
    public static boolean JsonContainsObject(JSONObject obj, String key) {
        boolean result = false;
        try {
            if (JsonContainsString(obj, key)) {
                String str = obj.getString(key);
                if (str.indexOf("{") == 0 || str.indexOf("[") == 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return result;
    }

    /**
     * 名称：判断json是否存在某个字段值为数组
     *
     * @return boolean    返回类型
     * @Title: JsonContains
     * @author zhangsanjun
     * @since 2016年4月11日 上午11:53:11
     */
    public static boolean JsonContainsArray(JSONObject obj, String key) {
        boolean result = false;
        try {
            if (JsonContainsString(obj, key)) {
                String str = obj.getString(key);
                if (str.indexOf("[") == 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return result;
    }

    //判断是否存在某个字段字符串
    public static boolean JsonContainsString(JSONObject obj, String key) {
        boolean result = false;
        if (obj.has(key)) {
            result = true;
        }

        return result;
    }

    //判断是否存在某个字段字符串
    public static boolean JsonContainsStringNotEmpty(JSONObject obj, String key) {
        boolean result = false;
        if (obj.toString().contains(key)) {
            //result = true;
            try {
                String str = obj.getString(key);
                result = !(((null == str) || (str.trim().length() == 0)) || "null".equals(str));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 判断是否存在某个字段字符串,没有值(或空),返回空字符串("")
     *
     * @param obj
     * @param key
     * @return
     */
    public static String JsonContainsStringDefault(JSONObject obj, String key) {
        String val = "";
        if (obj.toString().contains(key)) {
            try {
                String str = obj.getString(key);
                if (((null == str) || (str.trim().length() == 0)) || "null".equals(str)) {
                    val = "";
                } else {
                    val = str;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    /**
     * 对于double类型的转换,null不转为0
     */
    public static final TypeAdapter<Number> formatDOUBLE = new TypeAdapter<Number>() {
        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            if (in.peek() == JsonToken.STRING) {
                in.nextString();
                return null;
            }
            return in.nextDouble();
        }
        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            if(value == null){
                out.value("");
            }else{
                out.value(value);
            }
        }
    };


}
