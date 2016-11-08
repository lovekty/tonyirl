package me.tonyirl.common.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * Created by tony on 2016/10/21.
 */
public class CglibBeanCopierUtils {

    public static final int cacheCapacity = 1 << 10;
    private static final Converter converter = (value, targetClass, context) -> ConvertUtils.convert(value, targetClass);
    private static final Cache<Key, BeanCopier> cache = CacheBuilder.newBuilder().maximumSize(cacheCapacity).build();

    public static <S, T> T copy(S orig, Class<T> targetClass) throws IllegalAccessException, ExecutionException, InstantiationException {
        return copy(orig, targetClass, orig.getClass() == targetClass);
    }

    public static <S, T> T copy(S orig, Class<T> targetClass, boolean useConverter) throws IllegalAccessException, InstantiationException, ExecutionException {
        BeanCopier copier = getBeanCopier(orig.getClass(), targetClass, useConverter);
        T target = targetClass.newInstance();
        copier.copy(orig, target, useConverter ? converter : null);
        return target;
    }

    public static <S, T> void copy(S orig, T target, boolean useConverter) throws ExecutionException {
        BeanCopier copier = getBeanCopier(orig.getClass(), target.getClass(), useConverter);
        copier.copy(orig, target, useConverter ? converter : null);
    }

    private static <S, T> Key<S, T> cacheKeyGen(Class<S> originClass, Class<T> targetClass, boolean useConverter) {
        return new Key<>(originClass, targetClass, useConverter);
    }

    private static <S, T> BeanCopier getBeanCopier(Class<S> originClass, Class<T> targetClass, boolean useConverter) throws ExecutionException {
        Key<S, T> cacheKey = cacheKeyGen(originClass, targetClass, useConverter);
        return cache.get(cacheKey, () -> BeanCopier.create(originClass, targetClass, useConverter));
    }

    private static final class Key<S, T> implements Serializable {

        private static final long serialVersionUID = 1669653788776500583L;

        Class<S> origClass;
        Class<T> targetClass;
        boolean userConverter;

        Key(Class<S> origClass, Class<T> targetClass, boolean userConverter) {
            this.origClass = origClass;
            this.targetClass = targetClass;
            this.userConverter = userConverter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key<?, ?> key = (Key<?, ?>) o;
            return userConverter == key.userConverter &&
                    Objects.equals(origClass, key.origClass) &&
                    Objects.equals(targetClass, key.targetClass);
        }

        @Override
        public int hashCode() {
            return Objects.hash(origClass, targetClass, userConverter);
        }
    }
}
