package com.yuhao.TeachingServiceSystem.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.Date;

public class JsonUtils {
   // private static final JSONSerializerMap mapping = new JSONSerializerMap();
    private static final SerializerFeature[] features;

    public JsonUtils() {
    }

//    public static String toJson(Object object) {
//        return JSON.toJSONString(object, mapping, features);
//    }

    public static String toJson(Object object, PropertyFilter filter) {
        return JSON.toJSONString(object, filter, features);
    }

    public static PropertyFilter setFiler(final String[] fileds) {
        return new PropertyFilter() {
            public boolean apply(Object arg0, String name, Object arg2) {
                for(int i = 0; i < fileds.length; ++i) {
                    if (fileds[i].equals(name)) {
                        return false;
                    }
                }

                return true;
            }
        };
    }

    public static String ok() {
        JSONObject json = new JSONObject();
        json.put("statusCode", Integer.valueOf(200));
        json.put("message", "操作成功");
        return json.toJSONString();
    }

    public static String error(String msg) {
        JSONObject json = new JSONObject();
        json.put("statusCode", Integer.valueOf(300));
        json.put("message", msg);
        return json.toJSONString();
    }

    static {
        //mapping.put(Date.class, new JSONLibDataFormatSerializer());
        features = new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNullStringAsEmpty};
    }
}
