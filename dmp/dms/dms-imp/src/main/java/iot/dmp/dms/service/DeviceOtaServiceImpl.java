package iot.dmp.dms.service;

import com.google.common.collect.Lists;
import com.google.common.io.BaseEncoding;
import iot.common.dto.QueryResult;
import iot.dmp.dms.DmsConfig;
import iot.dmp.dms.IDeviceOtaService;
import iot.dmp.dms.dao.DeviceOtaFileDao;
import iot.dmp.dms.dto.DeviceOtaFileDto;
import iot.dmp.dms.po.DeviceConnectionLog;
import iot.dmp.dms.po.DeviceInfo;
import iot.dmp.dms.po.DeviceOtaFile;
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
import java.util.Date;
import java.util.List;

/**
 * @author :  sylar
 * @FileName :  DeviceOtaServiceImpl
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
public class DeviceOtaServiceImpl implements IDeviceOtaService {

    @Autowired
    DeviceOtaFileDao dao;
    @Autowired
    DmsConfig dmsConfig;

    @Override
    public QueryResult<DeviceOtaFileDto> getDeviceOtaFilesByDeviceType(String deviceType, int pageIndex, int pageSize) {
        Page<DeviceOtaFile> page = dao.findAll(new Specification<DeviceOtaFile>() {
            @Override
            public Predicate toPredicate(Root<DeviceOtaFile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                return PredicateUtil.newPredicateByDeviceType(root, criteriaBuilder, deviceType);
            }
        }, new PageRequest(pageIndex - 1, pageSize));

        List<DeviceOtaFileDto> list = Lists.newArrayList();
        page.getContent().forEach(po -> {
            DeviceOtaFileDto dto = new DeviceOtaFileDto();
            BeanUtils.copyProperties(po, dto);
            list.add(dto);
        });

        return new QueryResult<>(list, page.getTotalElements());
    }


    @Override
    public void uploadOtaFile(String otaFullName, String deviceType, int versionCode, String versionName, String
            description, String content) {
        //解码文件内容并获取文件决定路径
        byte[] bytes = BaseEncoding.base64().decode(content);
        String filePath = null;

        //保存上传文件信息
        DeviceOtaFile deviceOtaFile = new DeviceOtaFile();
        deviceOtaFile.setFileName(otaFullName);
        deviceOtaFile.setDeviceType(deviceType);
        deviceOtaFile.setVersionCode(versionCode);
        deviceOtaFile.setVersionName(versionName);
        deviceOtaFile.setDescription(description);
        deviceOtaFile.setFilePath(filePath);

        dao.save(deviceOtaFile);
    }

    /**
     * 根据参数查询设备升级文档信息
     *
     * @param deviceType   设备类型
     * @param connected    设备状态
     * @param deviceCode   设备编码
     * @param beginVersion 开始版本号
     * @param endVersion   结束版本号
     * @return 设备升级文档信息
     */
    @Override
    public QueryResult<?> findUpDocument(String deviceType, boolean connected, String deviceCode, int beginVersion,
                                         int endVersion, int pageIndex, int pageSize) {
        Page<DeviceOtaFile> page = dao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            Root<DeviceInfo> diRoot = query.from(DeviceInfo.class);
            Root<DeviceConnectionLog> dclRoot = query.from(DeviceConnectionLog.class);
            predicateList.add(cb.equal(root.get("deviceType").as(String.class), diRoot.get("deviceType")));
            predicateList.add(cb.equal(diRoot.get("deviceId").as(String.class), dclRoot.get("deviceId")));
            predicateList.add(cb.equal(root.get("deviceType").as(String.class), deviceType));
            predicateList.add(cb.greaterThanOrEqualTo(root.get("versionCode").as(int.class), beginVersion));
            predicateList.add(cb.lessThan(root.get("versionCode").as(int.class), endVersion));
            if (deviceCode != null) {
                predicateList.add(cb.equal(diRoot.get("deviceId").as(String.class), deviceCode));
            }
            predicateList.add(cb.equal(dclRoot.get("connected").as(boolean.class), connected));
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, new PageRequest(pageIndex, pageSize));
        return new QueryResult<>(page.getContent(), page.getTotalElements());
    }
}
