package com.yuhao.TeachingServiceSystem.util;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;

public class MyBeanUtils extends BeanUtils {
    public MyBeanUtils() {
    }

    public static void copyProperties(Object target, Object source) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.copyProperties(target, source);
    }

    static {
        Date defaultValue = null;
        DateConverter converter = new DateConverter(defaultValue);
        ConvertUtils.register(converter, Date.class);
        ConvertUtils.register(new IntegerConverter((Object)null), Integer.class);
        ConvertUtils.register(new LongConverter((Object)null), Long.class);
        ConvertUtils.register(new DoubleConverter((Object)null), Double.class);
        ConvertUtils.register(new FloatConverter((Object)null), Float.class);
        ConvertUtils.register(new BigDecimalConverter((Object)null), BigDecimal.class);
    }
}
