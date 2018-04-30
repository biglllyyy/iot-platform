package iot.dcp.common.bean;

import iot.common.consts.MqConsts;
import iot.common.mq.AbstractIotProducer;
import org.springframework.stereotype.Component;

/**
 * File Name             :  Dcs2DmsSender
 * Author                :  sylar
 * Create Date           :  2018/4/18
 * Description           :  DCS通过MQ发消息给DMS
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
public class DcsToDmsProducer extends AbstractIotProducer {
    @Override
    protected String getTopic() {
        return MqConsts.TOPIC_DCS_TO_DMS;
    }

    @Override
    protected String getProducerGroupId() {
        return MqConsts.PID_DCS_TO_DMS_GROUP;
    }
}
