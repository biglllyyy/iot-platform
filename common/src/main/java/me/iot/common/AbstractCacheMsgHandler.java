package me.iot.common;


import com.lmax.disruptor.EventHandler;
import me.iot.common.msg.IMsg;
import me.iot.util.disruptor.IMessaging;
import me.iot.util.disruptor.LmaxDiscuptorMessaging;
import me.iot.util.disruptor.ValueEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * 缓存消息处理器:从缓存队列中取出消息并处理
 */

@Deprecated
public abstract class AbstractCacheMsgHandler<T extends IMsg> implements EventHandler<ValueEvent> {
//    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final IMessaging messagingService = new LmaxDiscuptorMessaging(this);

    public abstract T getMsgFromCache();

    public abstract void handleMsg(T msg);

    @PostConstruct
    public void init() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        T msg = getMsgFromCache();
                        if (msg != null) {
                            messagingService.publish(msg);
                        }
                        Thread.sleep(10);
                    } catch (Exception e) {
//                        LOG.error("getMsgFromCache error: {}", e);
                    }
                }
            }
        });
    }

    @PreDestroy
    private void onDestroy() {
        messagingService.stop();
        executorService.shutdown();
    }

    @Override
    public void onEvent(ValueEvent event, long sequence, boolean endOfBatch) throws Exception {
        T msg;
        try {
            msg = (T) (event.getValue());
            if (msg != null) {
                handleMsg(msg);
            }
        } catch (Exception e) {
//            LOG.error("handleMsg error. \nmsg content: {}\nexception:{}", msg, e.getMessage());
        }
    }


}
