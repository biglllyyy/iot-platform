package me.iot.util.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICentralCacheService {
    /**
     * common
     */
    boolean containsKey(String key);

    void removeObject(String key);

    void removeObjects(Collection<String> keys);

    /**
     * Value
     */
    <T> T getObject(String key, Class<T> clazz);

    <T> void putObject(String key, T t);

    <T> void putObjectWithExpireTime(String key, T t, long expireTime);

    /**
     * Map
     */
    <T> void putMapValue(String key, String fieldName, T t, long expireTime);

    <T> void putMapValue(String key, String fieldName, T t);

    <T> T getMapField(String key, String fieldName, Class<T> clazz);

    <T> void updateMapField(String key, String fieldName, T t);

    <T> void updateMapFields(String key, Map<String, T> fields);

    void removeMapField(String key, String fieldName);

    <T> Map<String, T> getAll(String key, Class<T> clazz);

    /**
     * Set
     */
    <T> long putObjectToSet(String key, T t);

    <T> Set<T> getObjectsFromSet(String key, Class<T> clazz);

    <T> long removeObjectFromSet(String key, T t);

    /**
     * ZSet
     */
    <T> boolean putObjectToZSet(String key, T t, double score);

    <T> void putObjectToZSetWithExpireTime(String key, T t, double score, long expireTime);

    <T> Set<T> getObjectsFromZSet(String key, double minScore, double maxScore, Class<T> clazz);

    void removeObjectFromZSet(String key, double minScore, double maxScore);

    <T> void removeObjectFromZSet(String key, T t);

    /**
     * List
     */
    <T> void putObjectToListWithLimitAndExpireTime(String key, T t, int limit, long expireTime);

    <T> List<T> getObjectsFromList(String key, int start, int end, Class<T> clazz);

    <T> long removeObjectFormList(String key, T t);

}


