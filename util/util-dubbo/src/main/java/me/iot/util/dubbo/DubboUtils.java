package me.iot.util.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.google.common.collect.Lists;
import com.google.common.primitives.Primitives;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sylar on 16/5/5.
 */
public class DubboUtils {

    public final static String Registry_Zookeeper = "zookeeper";
    public final static String Registry_Redis = "redis";
    public final static String Prorocol_Dubbo = "dubbo";
    public final static String Prorocol_Rest = "rest";
    public final static String Prorocol_Http = "http";
    public final static String Prorocol_Hessian = "hessian";
    public final static String Prorocol_Webservice = "webservice";

    private final static Map<Class<?>, Object> map = new HashMap<>();


    /**
     * 获取IPC服务引用
     * 注册服务器通讯协议,默认使用zookeeper
     * 服务供应端协议,默认使用dubbo协议
     * 默认懒加载
     *
     * @param appName       IPC服务接口
     * @param connectString 服务器连接字, 如127.0.0.1:2181
     * @param clazz         业务服务接口名称
     * @param <T>
     * @return
     */
    public static <T> T getServcieReference(String appName,
                                            String connectString,
                                            Class<T> clazz) {
        return getServcieReference(appName, connectString, Registry_Zookeeper, Prorocol_Dubbo, clazz, true);
    }

    /**
     * 获取IPC服务引用
     * 注册服务器通讯协议,默认使用zookeeper
     * 服务供应端协议,默认使用dubbo协议
     *
     * @param appName       IPC服务接口
     * @param connectString 服务器连接字, 如127.0.0.1:2181
     * @param clazz         业务服务接口名称
     * @param isLazy        是否懒加载
     * @param <T>
     * @return
     */
    public static <T> T getServcieReference(String appName,
                                            String connectString,
                                            Class<T> clazz,
                                            boolean isLazy) {
        return getServcieReference(appName, connectString, Registry_Zookeeper, Prorocol_Dubbo, clazz, isLazy);
    }

    /**
     * 获取IPC服务引用
     * 注册服务器通讯协议,默认使用zookeeper
     *
     * @param appName           IPC服务接口
     * @param connectString     服务器连接字, 如127.0.0.1:2181
     * @param referenceProrocol 服务供应端通讯协议
     * @param clazz             业务服务接口名称
     * @param <T>
     * @return
     */
    public static <T> T getServcieReference(String appName,
                                            String connectString,
                                            String referenceProrocol,
                                            Class<T> clazz,
                                            boolean isLazy) {
        return getServcieReference(appName, connectString, Registry_Zookeeper, referenceProrocol, clazz, isLazy);
    }

    /**
     * 获取IPC服务引用
     *
     * @param appName           IPC服务接口
     * @param connectString     服务器连接字, 如127.0.0.1:2181
     * @param registryProrocol  注册服务器通讯协议
     * @param referenceProrocol 服务供应端通讯协议
     * @param clazz             业务服务接口名称
     * @param <T>
     * @return
     */
    public static <T> T getServcieReference(String appName,
                                            String connectString,
                                            String registryProrocol,
                                            String referenceProrocol,
                                            Class<T> clazz,
                                            boolean isLazy) {

        if (map.containsKey(clazz)) {
            return (T) map.get(clazz);
        }

        ApplicationConfig app = new ApplicationConfig(appName);
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol(registryProrocol);
        registry.setAddress(connectString);

        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<T> reference = new ReferenceConfig<T>();
        reference.setApplication(app);

        // 多个注册中心可以用setRegistries()
        reference.setRegistry(registry);
        reference.setInterface(clazz);
        reference.setProtocol(referenceProrocol);
        reference.setLazy(isLazy);

        // 和本地bean一样使用xxxService
        // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        T t = reference.get();
        map.put(clazz, t);

        return t;

    }


    public static void declareService(String appName, String connectString, Class serviceInterface, Object ref) {
        declareService(appName, connectString, Registry_Zookeeper, serviceInterface, ref);
    }

    public static <T> void declareService(String appName, String connectString, String registryProrocol, Class<T> serviceInterface, T serviceRef) {

        ApplicationConfig app = new ApplicationConfig(appName);
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(connectString);
        registry.setProtocol(registryProrocol);

        ServiceConfig<T> sc = new ServiceConfig();
        sc.setApplication(app);
        sc.setInterface(serviceInterface);
        sc.setRef(serviceRef);
    }


    /**
     * dubbo 参数及返回值限定类型:
     * 1  原生类型及其数组
     * 2  Serializable接口类型
     *
     * @param obj 要检查的对象
     * @return
     */
    public static boolean checkType(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof Collection) {
            Class clazz = getGenericType((Collection) obj);
            return checkType(clazz);
        } else {
            return checkType(obj.getClass());
        }

    }

    public static boolean checkType(Class clazz) {

        if (Serializable.class.isAssignableFrom(clazz))
            return true;
        if (String.class.isAssignableFrom(clazz))
            return true;
        if (clazz.isPrimitive() || Primitives.isWrapperType(clazz))
            return true;

        return false;
    }


    static <T> Class<?> getGenericType(Collection<T> collection) {
        Object[] array = Lists.newArrayList(collection).toArray();
        return array.getClass().getComponentType();
    }
}
