package me.iot.util.rocketmq.ons.http;

import me.iot.util.rocketmq.AbsProcuder;
import me.iot.util.rocketmq.IProducer;
import me.iot.util.rocketmq.IProducerConfig;
import me.iot.util.rocketmq.msg.RocketMsg;
import me.iot.util.rocketmq.ons.AbsOnsFactory;

/**
 * Created by sylar on 2017/1/6.
 */
public class OnsHttpProducer extends AbsProcuder implements IProducer {

    AbsOnsFactory factory;

    protected OnsHttpProducer(AbsOnsFactory factory, IProducerConfig config) {
        super(config);
        this.factory = factory;
        init();
    }

    void init() {
    }

    @Override
    public Object syncSend(RocketMsg msg) throws Exception {
        HttpResult result = HttpUtil.sendMsg(
                factory.getServerEndpoint(),
                factory.getAccessKey(),
                factory.getSecretKey(),
                config.getProducerId(),
                msg.getTopic(),
                msg.getTags(),
                msg.getKeys(),
                msg.getContent());

        if (result.getStatusCode() == HttpStatusCode.OK_WRITE) {
            return result;
        } else {
            throw new Exception(result.toString());
        }
    }

    @Override
    public void shutdown() {

    }
}
