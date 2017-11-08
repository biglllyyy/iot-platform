package me.iot.dms.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import me.iot.common.msg.DasConnectionMsg;
import me.iot.dms.IDasConnectionLogService;
import me.iot.dms.dao.DasConnectionLogDao;
import me.iot.dms.entity.DasConnectionLog;
import me.iot.common.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by sylar on 16/5/25.
 */
@Service
public class DasConnectionLogService implements IDmsMsgProcessor<DasConnectionMsg>, IDasConnectionLogService {
    @Autowired
    DasConnectionLogDao dao;

    @Autowired
    DasStatusService dasStatusService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processMsg(DasConnectionMsg msg) {
        DasConnectionLog log = new DasConnectionLog();
        log.setNodeId(msg.getDasNodeId());
        log.setNodeIp(msg.getIp());
        log.setNodePort(msg.getPort());
        log.setConnected(msg.isConnected());
        dao.saveAndFlush(log);

        dasStatusService.processMsg(msg);
    }

    @Override
    public QueryResult<DasConnectionLog> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime, int pageIndex, int pageSize) {
        Page<DasConnectionLog> page = dao.findAll(new Specification<DasConnectionLog>() {
            @Override
            public Predicate toPredicate(Root<DasConnectionLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();
                if (!Strings.isNullOrEmpty(nodeId)) {
                    predicateList.add(criteriaBuilder.equal(root.get("nodeId").as(String.class), nodeId));
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

}
