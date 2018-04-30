package iot.quickstart.dustbin.data.service.impl;

import iot.quickstart.dustbin.data.dao.IDustbinParamDao;
import iot.quickstart.dustbin.data.dto.DustbinParamDto;
import iot.quickstart.dustbin.data.entity.DustbinParam;
import iot.quickstart.dustbin.data.service.IDustbinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author :  sylar
 * @FileName :  DustbinParamServiceImpl
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
public class DustbinServiceImpl implements IDustbinService {

    private static final Logger LOG = LoggerFactory.getLogger(DustbinServiceImpl.class);

    @Autowired
    private IDustbinParamDao dustbinParamDao;

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

}
