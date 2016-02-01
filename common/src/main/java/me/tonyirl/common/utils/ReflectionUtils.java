package me.tonyirl.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by tony on 16/2/1.
 */
public class ReflectionUtils {

    private static final String TYPE_NAME_PREFIX = "class ";

    public static String getClassName(Type type) {
        if (type == null) {
            return "";
        }
        String className = type.toString();
        if (className.startsWith(TYPE_NAME_PREFIX)) {
            className = className.substring(TYPE_NAME_PREFIX.length());
        }
        return className;
    }

    public static Class<?> getClass(Type type)
            throws ClassNotFoundException {
        String className = getClassName(type);
        if (className == null || className.isEmpty()) {
            return null;
        }
        return Class.forName(className);
    }

    public static <T> Class<T> getSuperClassGenricType(Class<?> clazz, int index) {
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] params = ParameterizedType.class.cast(type).getActualTypeArguments();
            if (index < 0 || index >= params.length) {
                throw new IndexOutOfBoundsException();
            }
            if (params[index] instanceof Class) {
                return Class.class.cast(params[index]);
            }
        }
        return null;
    }

    public static <T> Class<T> getSuperClassGenricType(Class<?> clazz) {
        return getSuperClassGenricType(clazz, 0);
    }
}
