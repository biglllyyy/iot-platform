package iot.dmp.dms.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import iot.common.dto.QueryResult;
import iot.common.msg.DcsConnectionMsg;
import iot.dmp.dms.IDcsConnectionLogService;
import iot.dmp.dms.dao.DcsConnectionLogDao;
import iot.dmp.dms.dto.DcsConnectionLogDto;
import iot.dmp.dms.po.DcsConnectionLog;
import org.springframework.beans.BeanUtils;
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
 * @author :  sylar
 * @FileName :  DcsConnectionLogServiceImpl
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
public class DcsConnectionLogServiceImpl implements IDmsMsgProcessor<DcsConnectionMsg>, IDcsConnectionLogService {
    @Autowired
    DcsConnectionLogDao dao;

    @Autowired
    DcsStatusServiceImpl dasStatusServiceImpl;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processMsg(DcsConnectionMsg msg) {
        DcsConnectionLog log = new DcsConnectionLog();
        log.setNodeId(msg.getDcsNodeId());
        log.setNodeIp(msg.getIp());
        log.setNodePort(msg.getPort());
        log.setConnected(msg.isConnected());
        dao.saveAndFlush(log);

        dasStatusServiceImpl.processMsg(msg);
    }

    @Override
    public QueryResult<DcsConnectionLogDto> getDasConnectionLogsByNodeId(String nodeId, long beginTime, long endTime,
                                                                         int pageIndex, int pageSize) {
        Page<DcsConnectionLog> page = dao.findAll(new Specification<DcsConnectionLog>() {
            @Override
            public Predicate toPredicate(Root<DcsConnectionLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
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

        List<DcsConnectionLogDto> list = Lists.newArrayList();
        page.getContent().forEach(po -> {
            DcsConnectionLogDto dto = new DcsConnectionLogDto();
            BeanUtils.copyProperties(po, dto);
            list.add(dto);
        });

        return new QueryResult<>(list, page.getTotalElements());
    }

}
