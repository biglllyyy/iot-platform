package me.iot.dms.service;


import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by sylar on 16/6/5.
 */
public class PredicateUtil {

    public static <T> Predicate newPredicateByDeviceTypeAndCreateTime(Root<T> root,
                                                                      CriteriaBuilder criteriaBuilder,
                                                                      String deviceType,
                                                                      long beginTime,
                                                                      long endTime) {
        List<Predicate> predicateList = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(deviceType)) {
            predicateList.add(newPredicateByDeviceType(root, criteriaBuilder, deviceType));
        }
        Predicate prTime = newPredicateByCreateTime(root, criteriaBuilder, beginTime, endTime);
        if (prTime != null) {
            predicateList.add(prTime);
        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }

    public static <T> Predicate newPredicateByDeviceIdAndCreateTime(Root<T> root,
                                                                    CriteriaBuilder criteriaBuilder,
                                                                    String deviceId,
                                                                    long beginTime,
                                                                    long endTime) {
        List<Predicate> predicateList = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(deviceId)) {
            predicateList.add(newPredicateByDeviceId(root, criteriaBuilder, deviceId));
        }
        Predicate prTime = newPredicateByCreateTime(root, criteriaBuilder, beginTime, endTime);
        if (prTime != null) {
            predicateList.add(prTime);
        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }


    public static <T> Predicate newPredicateByDeviceType(Root<T> root, CriteriaBuilder criteriaBuilder, String deviceType) {
        return criteriaBuilder.equal(root.get("deviceType").as(String.class), deviceType);
    }

    public static <T> Predicate newPredicateByDeviceId(Root<T> root, CriteriaBuilder criteriaBuilder, String deviceId) {
        return criteriaBuilder.equal(root.get("deviceId").as(String.class), deviceId);
    }

    public static <T> Predicate newPredicateByCreateTime(Root<T> root, CriteriaBuilder criteriaBuilder, long beginTime, long endTime) {
        List<Predicate> predicateList = Lists.newArrayList();
        if (beginTime > 0 && endTime > 0 && endTime > beginTime) {
            predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Long.class), beginTime));
            predicateList.add(criteriaBuilder.lessThan(root.get("createTime").as(Long.class), endTime));
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }


}
