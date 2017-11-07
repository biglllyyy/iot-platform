package me.iot.das.common.bean;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * key : clientId
 * clientId = deviceType + deviceId
 */
@Component
public class ChannelCache {

    private Cache<String, Channel> channelCache = CacheBuilder.newBuilder()
            .maximumSize(1000000L).concurrencyLevel(16).build();

    public Channel get(String key) {
        return channelCache.getIfPresent(key);
    }

    public void put(String key, Channel channel) {
        if (channel != channelCache.getIfPresent(key)) {
            channelCache.put(key, channel);
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

//    private class ChannelRemovalListener implements RemovalListener<String, Channel> {
//        @Override
//        public void onRemoval(RemovalNotification<String, Channel> removal) {
//            Channel channel = removal.getValue();
//            if (channel != null && channel.isActive()) {
//                channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
//            }
//        }
//    }
}
