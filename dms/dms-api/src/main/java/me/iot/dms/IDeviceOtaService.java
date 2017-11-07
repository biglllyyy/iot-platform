package me.iot.dms;

import me.iot.dms.entity.DeviceOtaFile;
import me.iot.common.dto.QueryResult;

/**
 * Created by sylar on 16/5/25.
 */
public interface IDeviceOtaService {
    QueryResult<DeviceOtaFile> getDeviceOtaFilesByDeviceType(String deviceType, int pageIndex, int pageSize);

    void uploadOtaFile(String otaFullName, String deviceType, int versionCode, String versionName, String description, String content);

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
    QueryResult<?> findUpDocument(String deviceType, boolean connected, String deviceCode, int beginVersion, int endVersion, int pageIndex, int pageSize);

}
