package com.yuhao.TeachingServiceSystem.util;

import com.google.common.collect.Lists;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dozer.DozerBeanMapper;

public class BeanMapper {
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public BeanMapper() {
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        Iterator var3 = sourceList.iterator();

        while(var3.hasNext()) {
            Object sourceObject = var3.next();
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }

        return destinationList;
    }

    public static void copy(Object source, Object destinationObject) {
        try {
            MyBeanUtils.copyProperties(destinationObject, source);
        } catch (IllegalAccessException var3) {
            throw new RuntimeException(var3.getMessage(), var3);
        } catch (InvocationTargetException var4) {
            throw new RuntimeException(var4.getMessage(), var4);
        }
    }
}
