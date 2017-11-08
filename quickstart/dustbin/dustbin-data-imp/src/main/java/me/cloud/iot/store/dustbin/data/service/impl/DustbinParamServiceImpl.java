package me.cloud.iot.store.dustbin.data.service.impl;

import com.alibaba.fastjson.JSON;

import me.iot.store.dustbin.common.MsgCodes;
import me.iot.store.dustbin.common.MsgParams;
import me.iot.store.dustbin.data.config.DustbinConfig;
import me.iot.store.dustbin.data.dao.IDustbinParamDao;
import me.iot.store.dustbin.data.dto.DustbinParamDto;
import me.iot.store.dustbin.data.entity.DustbinParam;
import me.iot.store.dustbin.data.service.IDustbinParamService;
import me.iot.common.constant.DeviceTypes;
import me.iot.common.msg.DeviceMsg;
import me.iot.common.msg.IMsg;
import me.iot.common.pojo.CacheMsgWrap;
import me.iot.dms.DmsTopics;
import me.iot.dms.IDeviceManageService;
import me.iot.util.redis.AbstractMessageListener;
import me.iot.util.redis.ISubscribePublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Service
public class DustbinParamServiceImpl extends AbstractMessageListener implements IDustbinParamService {

    private static final Logger LOG = LoggerFactory.getLogger(DustbinParamServiceImpl.class);

    @Autowired
    private IDustbinParamDao dustbinParamDao;

    @Autowired
    private DustbinConfig dustbinConfig;

    @Autowired
    private ISubscribePublishService sps;

    private IDeviceManageService dms;

    @PostConstruct
    private void init() {
        dms = dustbinConfig.getDms();

        List<String> topics = Collections.singletonList(DmsTopics.getTopicByDeviceType(DeviceTypes.DEVICE_TYPE_DUSTBIN));
        sps.subscribeMessage(this, topics);
        LOG.info("subscribe DeviceMessage.  topics: {}", topics);
    }

    @PreDestroy
    private void dispose() {
        sps.unsubscribeMessage(this, null);
        LOG.info("unsubscribe DeviceMessage");
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

    @Override
    protected void handleMessage(String topic, String jsonMsg) {
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
    void onReportParams(IMsg msg) {
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
            res.put(MsgParams.Limit1, fullThreshold);
            res.put(MsgParams.Limit2, halfFullThreshold);
            res.put(MsgParams.ReportInterval, reportInterval);
            res.put(MsgParams.ConnectStringLen, connectStringLen);
            res.put(MsgParams.RC, rc);

            dms.sendMsg(res);
        }
    }
}
