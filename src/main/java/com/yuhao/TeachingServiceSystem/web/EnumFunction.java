package com.yuhao.TeachingServiceSystem.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;

@SuppressWarnings("unchecked")
public class EnumFunction {
    private static String basePackge = "com.yuhao.TeachingServiceSystem.common";

    public static String parseForList(String name, Integer value) {
        String className = basePackge + "." + name;
        try {
            Class clz = Class.forName(className);
            Method values = clz.getMethod("values");
            Object[] enums = (Object[]) values.invoke(null);
            for (Object e : enums) {
                int eValue = (Integer) e.getClass().getMethod("getValue").invoke(e);
                String remark = (String) e.getClass().getMethod("getRemark").invoke(e);
                if (eValue == value) {
                    return remark;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "ERROR";

    }

    public static String parseForDetail(String packageName, String name, Integer value) {
        String className = basePackge + "." + packageName;
        try {
            Class clz = Class.forName(className);
            Method values = clz.getMethod("values");
            Object[] enums = (Object[]) values.invoke(null);
            String html = "<select class=\"form-control\" name=\"" + name + "\">";
            for (Object e : enums) {
                int eValue = (Integer) e.getClass().getMethod("getValue").invoke(e);
                String remark = (String) e.getClass().getMethod("getRemark").invoke(e);

                String option = "<option value=\"{0}\" {1}>{2}</option>";
                option = MessageFormat.format(option, eValue, (value == null || eValue != value ? "" : "selected") , remark);
                html += option;
            }
            html += "</select>";
            return html;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "ERROR";
    }
}
