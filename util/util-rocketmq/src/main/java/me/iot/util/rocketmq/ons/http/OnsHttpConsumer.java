package me.iot.util.rocketmq.ons.http;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import me.iot.util.rocketmq.AbsConsumer;
import me.iot.util.rocketmq.IConsumer;
import me.iot.util.rocketmq.IConsumerConfig;
import me.iot.util.rocketmq.msg.IRocketMsgListener;
import me.iot.util.rocketmq.msg.RocketMsg;
import me.iot.util.rocketmq.ons.AbsOnsFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sylar on 2017/1/6.
 */
public class OnsHttpConsumer extends AbsConsumer implements IConsumer {
    final static int MaxMessageCount = 32;
    final static int PollInterval = 100;

    AbsOnsFactory factory;
    ExecutorService executorService;

    protected OnsHttpConsumer(AbsOnsFactory factory, IConsumerConfig config) {
        super(config);
        this.factory = factory;
        init();
    }

    void init() {
    }

    @Override
    public void subscribe(String topic, String[] topicFilters, IRocketMsgListener listener) {
        Preconditions.checkNotNull(listener, "callback is null");
        unsubscribe();

        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        List<HttpMsgExt> list = HttpUtil.receiveMsg(
                                factory.getServerEndpoint(),
                                factory.getAccessKey(),
                                factory.getSecretKey(),
                                config.getConsumerId(),
                                topic,
                                MaxMessageCount
                        );

                        List<RocketMsg> msgs = Lists.newArrayList();
                        for (HttpMsgExt ext : list) {
                            RocketMsg msg = new RocketMsg(topic, ext.getBody());
                            msg.setTags(ext.getTag());
                            msg.setExt(ext);
                            msgs.add(msg);
                        }
                        listener.onSuccess(msgs);

                        Thread.sleep(PollInterval);
                    } catch (Exception e) {
                        listener.onFaild(e);
                    }
                }
            }
        });
    }

    @Override
    public void unsubscribe() {
        if (executorService != null) {
            executorService.shutdown();
            executorService = null;
        }
    }
}
