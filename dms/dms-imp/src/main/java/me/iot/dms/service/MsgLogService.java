package me.iot.dms.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import me.iot.common.msg.DasConnectionMsg;
import me.iot.common.msg.IMsg;
import me.iot.dms.IMsgLogService;
import me.iot.dms.dao.MsgLogDao;
import me.iot.dms.entity.MsgLog;
import me.iot.common.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by sylar on 16/5/26.
 */
@Service
public class MsgLogService implements IDmsMsgProcessor<IMsg>, IMsgLogService {

    private static final Logger LOG = LoggerFactory.getLogger(MsgLogService.class);

    @Autowired
    MsgLogDao dao;

    @Override
    public void processMsg(IMsg msg) {
        saveMsgLog(msg);
    }

    @Override
    public QueryResult<MsgLog> getMsgLogs(String deviceType, String deviceId, String msgType, long beginTime, long endTime, int pageIndex, int pageSize) {
        Page<MsgLog> page = dao.findAll(new Specification<MsgLog>() {
            @Override
            public Predicate toPredicate(Root<MsgLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();
                if (!Strings.isNullOrEmpty(deviceType)) {
                    predicateList.add(PredicateUtil.newPredicateByDeviceType(root, criteriaBuilder, deviceType));
                }
                if (!Strings.isNullOrEmpty(deviceId)) {
                    predicateList.add(PredicateUtil.newPredicateByDeviceId(root, criteriaBuilder, deviceId));
                }
                if (!Strings.isNullOrEmpty(msgType)) {
                    predicateList.add(criteriaBuilder.equal(root.get("msgType").as(String.class), msgType));
                }
                Predicate prTime = PredicateUtil.newPredicateByCreateTime(root, criteriaBuilder, beginTime, endTime);
                if (prTime != null) {
                    predicateList.add(prTime);
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, new PageRequest(pageIndex - 1, pageSize));

        return new QueryResult<>(page.getContent(), page.getTotalElements());
    }


    void saveMsgLog(IMsg msg) {
        if (msg instanceof DasConnectionMsg) {
            return;
        }

        if (Strings.isNullOrEmpty(msg.getSourceDeviceType())
                || Strings.isNullOrEmpty(msg.getSourceDeviceId()
        )) {
            LOG.warn("sourceDeviceType or sourceDeviceId is null:{}", msg);
            return;
        }

        try {
            MsgLog pojo = new MsgLog();
            pojo.setDeviceType(msg.getSourceDeviceType());
            pojo.setDeviceId(msg.getSourceDeviceId());
            pojo.setMsgType(msg.getMsgType().toString());

            String msgContent = msg.toString();
            msgContent = msgContent.replace("\\", "");
            pojo.setMsgContent(msgContent);

            dao.saveAndFlush(pojo);
        } catch (Exception e) {
            LOG.warn("saveMsgLog error. \ndevice msg content:{}\nexception:{}", msg.toString(), e.getMessage());
        }
    }

}
