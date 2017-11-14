package me.cloud.iot.store.dustbin.data.service.impl;

import com.alibaba.fastjson.JSON;
import me.cloud.iot.store.dustbin.common.DeviceTypes;
import me.cloud.iot.store.dustbin.common.MsgCodes;
import me.cloud.iot.store.dustbin.common.MsgParams;
import me.cloud.iot.store.dustbin.data.config.DustbinConfig;
import me.cloud.iot.store.dustbin.data.dao.IDustbinParamDao;
import me.cloud.iot.store.dustbin.data.dto.DustbinParamDto;
import me.cloud.iot.store.dustbin.data.entity.DustbinParam;
import me.cloud.iot.store.dustbin.data.service.IDustbinParamService;
import me.iot.common.msg.DeviceMsg;
import me.iot.common.msg.IMsg;
import me.iot.common.pojo.CacheMsgWrap;
import me.iot.common.usual.TopicConsts;
import me.iot.dms.IDeviceManageService;
import me.iot.util.redis.ISubscribePublishService;
import me.iot.util.rocketmq.IConsumer;
import me.iot.util.rocketmq.IConsumerConfig;
import me.iot.util.rocketmq.msg.IRocketMsgListener;
import me.iot.util.rocketmq.msg.RocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @author :  sylar
 * @FileName :  DustbinParamServiceImpl
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
@Service
public class DustbinParamServiceImpl implements IDustbinParamService {

    private static final Logger LOG = LoggerFactory.getLogger(DustbinParamServiceImpl.class);

    @Autowired
    private IDustbinParamDao dustbinParamDao;

    @Autowired
    private DustbinConfig dustbinConfig;

    @Autowired
    private ISubscribePublishService sps;

    private IDeviceManageService dms;

    private IConsumer consumer;

    private static final String IOT_DUSTBIN_GROUP = "IOT-DUSTBIN-GROUP";

    @PostConstruct
    private void init() {
        dms = dustbinConfig.getDms();

        consumer = dustbinConfig.getFactory().createConsumer(new IConsumerConfig() {
            @Override
            public String getConsumerId() {
                return IOT_DUSTBIN_GROUP;
            }
        });

        consumer.subscribe(TopicConsts.DMS_TO_APS, new String[]{DeviceTypes
                .DEVICE_TYPE_DUSTBIN}, new IRocketMsgListener() {
            @Override
            public void onSuccess(List<RocketMsg> messages) throws Exception {
                for (RocketMsg rocketMsg : messages) {
                    handleMessage(rocketMsg.getTopic(), rocketMsg.getContent());
                }
            }

            @Override
            public void onFaild(Throwable throwable) {
                LOG.error("deal fail");
            }
        });

        LOG.info("subscribe DeviceMessage.  topics: {}", TopicConsts.DMS_TO_APS);
    }

    @PreDestroy
    private void dispose() {
    }

    @Override
    public void add(DustbinParamDto dustbinParamDto) {
        if (dustbinParamDto == null) {
            LOG.error("add. input param record is null");
            return;
        }

        String deviceId = dustbinParamDto.getDeviceId();
        if (StringUtils.isEmpty(deviceId)) {
            LOG.error("add. device code is empty");
            return;
        }

        DustbinParam dustbinParam = dustbinParamDao.getByDeviceId(deviceId);
        if (dustbinParam != null) {
            LOG.error("add. code[{}] already existed", deviceId);
            return;
        }

        dustbinParam = new DustbinParam();
        BeanUtils.copyProperties(dustbinParamDto, dustbinParam);
        dustbinParam.setUserId(dustbinParamDto.getUserId());
        dustbinParam.setCreateUserId(dustbinParamDto.getUserId());
        dustbinParam.setCreateTime(System.currentTimeMillis());
        dustbinParamDao.saveAndFlush(dustbinParam);
    }

    @Override
    public DustbinParamDto getByDeviceId(String deviceId) {
        DustbinParam dustbinParam = dustbinParamDao.getByDeviceId(deviceId);
        if (dustbinParam == null) {
            String msg = String.format("get. no record found for code[%s]", deviceId);
            LOG.error(msg);
            return null;
        }

        DustbinParamDto dustbinParamDto = new DustbinParamDto();
        BeanUtils.copyProperties(dustbinParam, dustbinParamDto);
        return dustbinParamDto;
    }

    @Override
    public void update(DustbinParamDto dustbinParamDto) {
        if (dustbinParamDto == null) {
            LOG.error("update. input param record is null");
            return;
        }

        String deviceId = dustbinParamDto.getDeviceId();
        if (StringUtils.isEmpty(deviceId)) {
            LOG.error("update. device code is empty");
            return;
        }

        DustbinParam dustbinParam = dustbinParamDao.getByDeviceId(deviceId);
        if (dustbinParam == null) {
            LOG.error("update. no record found for code[{}]", deviceId);
            return;
        }

        dustbinParam.setDeviceType(dustbinParamDto.getDeviceType());
        dustbinParam.setHeight(dustbinParamDto.getHeight());
        dustbinParam.setFullThreshold(dustbinParamDto.getFullThreshold());
        dustbinParam.setHalfFullThreshold(dustbinParamDto.getHalfFullThreshold());
        dustbinParam.setUserId(dustbinParamDto.getUserId());
        dustbinParam.setUpdateUserId(dustbinParamDto.getUserId());
        dustbinParam.setUpdateTime(System.currentTimeMillis());
        dustbinParamDao.saveAndFlush(dustbinParam);
    }

    @Override
    public void addOrUpdate(DustbinParamDto dustbinParamDto) {
        if (dustbinParamDto == null) {
            LOG.error("addOrUpdate. input param record is null");
            return;
        }

        String deviceId = dustbinParamDto.getDeviceId();
        if (StringUtils.isEmpty(deviceId)) {
            LOG.error("addOrUpdate. device code is empty");
            return;
        }

        DustbinParam dustbinParam = dustbinParamDao.getByDeviceId(deviceId);
        if (dustbinParam == null) {
            LOG.info("addOrUpdate. will add this record");
            this.add(dustbinParamDto);
        } else {
            LOG.info("addOrUpdate. will update this record");
            this.update(dustbinParamDto);
        }
    }

    private void handleMessage(String topic, String jsonMsg) throws Exception {
        LOG.info("received published msg.  topic:{}\n{}", topic, jsonMsg);

        CacheMsgWrap wrap = JSON.parseObject(jsonMsg, CacheMsgWrap.class);
        if (wrap == null) {
            LOG.error("received msg is not CacheMsgWrap type");
            return;
        }

        IMsg msg = wrap.getMsg();
        if (msg == null) {
            LOG.error("received DeviceMessage is null");
            return;
        }

        int msgCode = Integer.parseInt(msg.getMsgCode());
        switch (msgCode) {
            case MsgCodes.REPORT_PARAMS:
                onReportParams(msg);
                break;
            default:
                LOG.info("will not process msg\n{} ", msg);
                break;
        }
    }


    /**
     * 【处理】垃圾桶设备上报参数。需要回复消息。
     *
     * @param msg
     */
    void onReportParams(IMsg msg) throws Exception {
        DeviceMsg res = DeviceMsg.newMsgFromCloud(String.valueOf(MsgCodes.REPORT_PARAMS_RES),
                msg.getSourceDeviceType(), msg.getSourceDeviceId());

        // 回应指令须使用请求指令的时间戳
        res.setOccurTime(msg.getOccurTime());

        // 设置默认回应，异常情况下的值
        Integer fullThreshold = 30;
        Integer halfFullThreshold = 50;
        Integer reportInterval = 0;
        Integer connectStringLen = 0;
        // 0 - 成功，1 - 失败
        int rc = 1;

        try {
            DustbinParamDto dustbinParamDto = this.getByDeviceId(msg.getSourceDeviceType() + msg.getSourceDeviceId());
            LOG.info("onReportParams. dustbin param is {}", dustbinParamDto);
            if (dustbinParamDto != null) {
                fullThreshold = dustbinParamDto.getFullThreshold();
                halfFullThreshold = dustbinParamDto.getHalfFullThreshold();
                rc = 0;
            }
        } catch (Exception e) {
            LOG.error("onReportParams. exception:", e);
            rc = 1;
        } finally {
            res.put(MsgParams.LIMIT1, fullThreshold);
            res.put(MsgParams.LIMIT2, halfFullThreshold);
            res.put(MsgParams.REPORTINTERVAL, reportInterval);
            res.put(MsgParams.CONNECTSTRINGLEN, connectStringLen);
            res.put(MsgParams.RC, rc);

            dms.sendMsg(res);
        }
    }
}
