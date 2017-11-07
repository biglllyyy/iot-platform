package me.iot.das.common.bean;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * key 为 终端设备id
 */
@Component
public class TerminalCache {

    private Cache<String, InetSocketAddress> channelCache = CacheBuilder.newBuilder()
            .maximumSize(1000000L).concurrencyLevel(16).build();

    public InetSocketAddress get(String key) {
        return channelCache.getIfPresent(key);
    }

    public void put(String key, InetSocketAddress address) {
        if (address != channelCache.getIfPresent(key)) {
            channelCache.put(key, address);
        }
    }

    public void remove(String key) {
        channelCache.invalidate(key);
    }

    public boolean containsKey(String key) {
        return channelCache.getIfPresent(key) != null;
    }

    public long size() {
        return channelCache.size();
    }
}
