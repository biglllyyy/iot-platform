package iot.util.misc;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * @author :  sylar
 * @FileName :
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class ReflectUtils {

    static public <T> T getReflectObj(String clazz) {
        T t = null;
        try {
            if (!Strings.isNullOrEmpty(clazz)) {
                Class<?> c = Class.forName(clazz);
                Object obj = c.newInstance();
                if (obj != null) {
                    t = (T) obj;
                }
            }
        } catch (Exception e) {
        }

        return t;
    }

    static public <T> Class<?> getGenericType(List<T> list) {
        Object[] array = list.toArray();
        return array.getClass().getComponentType();
    }

    static public <T> Class<?> getGenericType(Collection<T> collection) {
        Object[] array = Lists.newArrayList(collection).toArray();
        return array.getClass().getComponentType();
    }

    static public Class<?> getGenericType(Object obj) {
        return getGenericType(obj, 0);
    }

    static public Class<?> getGenericType(Object obj, int index) {
        if (obj == null) {
            return null;
        }

        // 获取泛型父类
        Type type = obj.getClass().getGenericSuperclass();

        // 不是泛型类
        if (!(type instanceof ParameterizedType)) {
            return null;
        }

        ParameterizedType genType = (ParameterizedType) type;
        Type[] types = genType.getActualTypeArguments();

        Preconditions.checkState(index >= 0 && index < types.length);
        Type objType = types[index];

        return (Class<?>) objType;
    }
}
