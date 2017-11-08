package me.iot.util.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author :  sylar
 * @FileName :  ICentralCacheService
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
public interface ICentralCacheService {
    /**
     * common
     */
    /**
     * @param key
     * @return
     */
    boolean containsKey(String key);

    /**
     * @param key
     */
    void removeObject(String key);

    /**
     * @param keys
     */
    void removeObjects(Collection<String> keys);

    /**
     * Value
     */
    /**
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getObject(String key, Class<T> clazz);

    /**
     * @param key
     * @param t
     * @param <T>
     */
    <T> void putObject(String key, T t);

    /**
     * @param key
     * @param t
     * @param expireTime
     * @param <T>
     */
    <T> void putObjectWithExpireTime(String key, T t, long expireTime);

    /**
     * Map
     */
    /**
     * @param key
     * @param fieldName
     * @param t
     * @param expireTime
     * @param <T>
     */
    <T> void putMapValue(String key, String fieldName, T t, long expireTime);

    /**
     * @param key
     * @param fieldName
     * @param t
     * @param <T>
     */
    <T> void putMapValue(String key, String fieldName, T t);

    /**
     * @param key
     * @param fieldName
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getMapField(String key, String fieldName, Class<T> clazz);

    /**
     * @param key
     * @param fieldName
     * @param t
     * @param <T>
     */
    <T> void updateMapField(String key, String fieldName, T t);

    /**
     * @param key
     * @param fields
     * @param <T>
     */
    <T> void updateMapFields(String key, Map<String, T> fields);

    /**
     * @param key
     * @param fieldName
     */
    void removeMapField(String key, String fieldName);

    /**
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> Map<String, T> getAll(String key, Class<T> clazz);

    /**
     * Set
     */
    /**
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    <T> long putObjectToSet(String key, T t);

    /**
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> Set<T> getObjectsFromSet(String key, Class<T> clazz);

    /**
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    <T> long removeObjectFromSet(String key, T t);

    /**
     * ZSet
     */
    /**
     * @param key
     * @param t
     * @param score
     * @param <T>
     * @return
     */
    <T> boolean putObjectToZSet(String key, T t, double score);

    /**
     * @param key
     * @param t
     * @param score
     * @param expireTime
     * @param <T>
     */
    <T> void putObjectToZSetWithExpireTime(String key, T t, double score, long expireTime);

    /**
     * @param key
     * @param minScore
     * @param maxScore
     * @param clazz
     * @param <T>
     * @return
     */
    <T> Set<T> getObjectsFromZSet(String key, double minScore, double maxScore, Class<T> clazz);

    /**
     * @param key
     * @param minScore
     * @param maxScore
     */
    void removeObjectFromZSet(String key, double minScore, double maxScore);

    /**
     * @param key
     * @param t
     * @param <T>
     */
    <T> void removeObjectFromZSet(String key, T t);

    /**
     * List
     */
    /**
     * @param key
     * @param t
     * @param limit
     * @param expireTime
     * @param <T>
     */
    <T> void putObjectToListWithLimitAndExpireTime(String key, T t, int limit, long expireTime);

    /**
     * @param key
     * @param start
     * @param end
     * @param clazz
     * @param <T>
     * @return
     */
    <T> List<T> getObjectsFromList(String key, int start, int end, Class<T> clazz);

    /**
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    <T> long removeObjectFormList(String key, T t);

}


