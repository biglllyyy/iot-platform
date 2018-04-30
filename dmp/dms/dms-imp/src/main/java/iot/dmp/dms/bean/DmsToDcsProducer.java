package iot.dmp.dms.bean;

import iot.common.consts.MqConsts;
import iot.common.mq.AbstractIotProducer;
import org.springframework.stereotype.Component;

/**
 * File Name             :  Dms2ApsProducer
 * Author                :  sylar
 * Create Date           :  2018/4/18
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
public class DmsToDcsProducer extends AbstractIotProducer {

    @Override
    protected String getTopic() {
        return MqConsts.TOPIC_DMS_TO_DCS;
    }

    @Override
    protected String getProducerGroupId() {
        return MqConsts.PID_DMS_TO_DCS_GROUP;
    }

}
