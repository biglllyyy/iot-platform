package iot.quickstart.dustbin.data.bean;

import com.google.common.collect.Lists;
import iot.common.consts.MqConsts;
import iot.common.mq.AbstractIotConsumer;
import iot.common.mq.MqMsgUtils;
import iot.common.msg.DeviceDataMsg;
import iot.common.msg.DeviceMsg;
import iot.common.msg.IMsg;
import iot.dmp.dms.IDeviceManageService;
import iot.quickstart.dustbin.common.DeviceTypes;
import iot.quickstart.dustbin.common.MsgCodes;
import iot.quickstart.dustbin.common.MsgParams;
import iot.quickstart.dustbin.data.dao.IDustbinDeviceDataDao;
import iot.quickstart.dustbin.data.dao.IDustbinParamDao;
import iot.quickstart.dustbin.data.entity.DustbinDeviceData;
import iot.quickstart.dustbin.data.entity.DustbinParam;
import iot.util.mq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * File Name             :  DmsToApsConsumer
 * Author                :  sylar
 * Create Date           :  2018/4/23
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
@Component
public class DmsToApsConsumer extends AbstractIotConsumer {

    @Autowired
    private IDustbinParamDao dustbinParamDao;

    @Autowired
    private IDustbinDeviceDataDao dustbinDeviceDataDao;

    @Autowired
    private IDeviceManageService dms;


    @Override
    protected String getTopic() {
        return MqConsts.TOPIC_DMS_TO_APS;
    }

    @Override
    protected String[] getTags() {
        return new String[]{DeviceTypes.DEVICE_TYPE_DUSTBIN};
    }

    @Override
    protected String getConsumerGroupId() {
        return "cid-iot-dms-to-dustbin-group";
    }

    @Override
    protected void onConsume(Message message) {
        IMsg msg = MqMsgUtils.convert(message);
        if (msg == null) {
            return;
        }

        int msgCode = Integer.parseInt(msg.getMsgCode());

        try {
            switch (msgCode) {
                case MsgCodes.REPORT_PARAMS:
                    onReportParams(msg);
                    break;
                case MsgCodes.REPORT_STATUS:
                    onReportStatus(msg);
                    break;
                default:
                    LOG.info("will not process msg\n{} ", msg);
                    break;
            }
        } catch (Exception e) {
            LOG.warn(e.getMessage());
        }

    }


    /**
     * 保存垃圾桶上传的状态数据，包括高度、温度、湿度、电池电压等
     *
     * @param msg
     */
    private void onReportStatus(IMsg msg) {
        DeviceDataMsg deviceDataMsg = (DeviceDataMsg) msg;
        if (deviceDataMsg != null) {
            dustbinDeviceDataDao.save(dto2Entity(deviceDataMsg));
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
            DustbinParam po = dustbinParamDao.getByDeviceId(msg.getSourceDeviceType() + msg.getSourceDeviceId());
            LOG.info("onReportParams. dustbin param is {}", po);
            if (po != null) {
                fullThreshold = po.getFullThreshold();
                halfFullThreshold = po.getHalfFullThreshold();
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

            LOG.info("send msg to device {}", msg.getSourceDeviceId());
            dms.sendMsg(res);
        }
    }


    /**
     * dto转换成entity
     *
     * @param deviceDataMsg
     * @return
     */
    private List<DustbinDeviceData> dto2Entity(DeviceDataMsg deviceDataMsg) {
        List<DustbinDeviceData> deviceDataMsgList = Lists.newArrayList();
        Map<String, Object> params = deviceDataMsg.getParams();
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            DustbinDeviceData deviceData = new DustbinDeviceData();
            deviceData.setCorrectValue(Double.valueOf(String.valueOf(entry.getValue())));
            deviceData.setOriginalValue(Double.valueOf(String.valueOf(entry.getValue())));
            deviceData.setFactorCode(entry.getKey());
            deviceData.setDeviceId(deviceDataMsg.getSourceDeviceId());
            deviceData.setDeviceType(deviceDataMsg.getSourceDeviceType());
            deviceData.setCreateTime(deviceDataMsg.getTimestamp());

            deviceDataMsgList.add(deviceData);
        }

        return deviceDataMsgList;
    }

}
