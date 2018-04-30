package iot.dmp.dms.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import iot.common.dto.QueryResult;
import iot.common.msg.DcsConnectionMsg;
import iot.common.msg.IMsg;
import iot.dmp.dms.IMsgLogService;
import iot.dmp.dms.dao.MsgLogDao;
import iot.dmp.dms.dto.MsgLogDto;
import iot.dmp.dms.po.MsgLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
 * @author :  sylar
 * @FileName :  MsgLogServiceImpl
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
public class MsgLogServiceImpl implements IDmsMsgProcessor<IMsg>, IMsgLogService {

    private static final Logger LOG = LoggerFactory.getLogger(MsgLogServiceImpl.class);

    @Autowired
    MsgLogDao dao;

    @Override
    public void processMsg(IMsg msg) {
        saveMsgLog(msg);
    }

    @Override
    public QueryResult<MsgLogDto> getMsgLogs(String deviceType, String deviceId, String msgType, long beginTime, long
            endTime, int pageIndex, int pageSize) {
        Page<MsgLog> page = dao.findAll(new Specification<MsgLog>() {
            @Override
            public Predicate toPredicate(Root<MsgLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
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

        List<MsgLogDto> list = Lists.newArrayList();
        page.getContent().forEach(po -> {
            MsgLogDto dto = new MsgLogDto();
            BeanUtils.copyProperties(po, dto);
            list.add(dto);
        });

        return new QueryResult<>(list, page.getTotalElements());
    }


    void saveMsgLog(IMsg msg) {
        if (msg instanceof DcsConnectionMsg) {
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
