package me.iot.dms.ui.service;

import me.iot.common.dto.QueryResult;
import me.iot.common.dto.Result;
import me.iot.dms.dto.BindLocationParamsDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author :  sylar
 * @FileName :  IDmsFeignClient
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
@FeignClient(name = "dms", fallback = DmsFallCallback.class)
public interface IDmsFeignClient {

    /**
     * 通过 nodeId(das节点id) get 设备接入服务的连接日志信息
     *
     * @param nodeId    das节点id
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param pageIndex 页码
     * @param pageSize  每页条数
     * @return
     */
    @RequestMapping(value = "getDasConnectionLogsByNodeId",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDasConnectionLogsByNodeId(@RequestParam("nodeId") String nodeId,
                                           @RequestParam("beginTime") long beginTime,
                                           @RequestParam("endTime") long endTime,
                                           @RequestParam("pageIndex") int pageIndex,
                                           @RequestParam("pageSize") int pageSize);

    /**
     * 过 nodeId(das节点id) get 设备接入服务的状态
     *
     * @param nodeId das节点id
     * @return
     */
    @RequestMapping(value = "getDasStatus",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDasStatus(@RequestParam("nodeId") String nodeId);

    /**
     * 时间段内设备报名数量
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "countOfDeviceAlarm",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceAlarm(@RequestParam("beginTime") long beginTime,
                                 @RequestParam("endTime") long endTime);

    /**
     * 设备类型的设备报警数量
     *
     * @param deviceType
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/countOfDeviceAlarmByDeviceType",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceAlarmByDeviceType(@RequestParam("deviceType") String deviceType,
                                             @RequestParam("beginTime") long beginTime,
                                             @RequestParam("endTime") long endTime);

    /**
     * 单个设备的报警数量
     *
     * @param deviceId
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/countOfDeviceAlarmByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceAlarmByDeviceId(@RequestParam("deviceId") String deviceId,
                                           @RequestParam("beginTime") long beginTime,
                                           @RequestParam("endTime") long endTime);

    /**
     * 单个设备的报警信息
     *
     * @param deviceId
     * @param alarmCodes
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDeviceAlarmsByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceAlarmsByDeviceId(@RequestParam("deviceId") String deviceId,
                                        @RequestParam("alarmCodes") List<String> alarmCodes,
                                        @RequestParam("beginTime") long beginTime,
                                        @RequestParam("endTime") long endTime,
                                        @RequestParam("pageIndex") int pageIndex,
                                        @RequestParam("pageSize") int pageSize);

    /**
     * 单个设备的连接日志信息
     *
     * @param deviceId
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDeviceConnectionLogsByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceConnectionLogsByDeviceId(@RequestParam("deviceId") String deviceId,
                                                @RequestParam("beginTime") long beginTime,
                                                @RequestParam("endTime") long endTime,
                                                @RequestParam("pageIndex") int pageIndex,
                                                @RequestParam("pageSize") int pageSize);

    /**
     * 所有设备事件数量
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/countOfDeviceEvent", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceEvent(@RequestParam("beginTime") long beginTime, @RequestParam("endTime") long endTime);

    /**
     * 指定设备类型的设备事件数量
     *
     * @param deviceType
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/countOfDeviceEventByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceEventByDeviceType(@RequestParam("deviceType") String deviceType,
                                             @RequestParam("beginTime") long beginTime,
                                             @RequestParam("endTime") long endTime);

    /**
     * 单个设备的设备事件数量
     *
     * @param deviceId
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/countOfDeviceEventByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceEventByDeviceId(@RequestParam("deviceId") String deviceId,
                                           @RequestParam("beginTime") long beginTime,
                                           @RequestParam("endTime") long endTime);

    /**
     * 取单个设备的设备事件信息
     *
     * @param deviceId
     * @param eventCodes
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDeviceEventsByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceEventsByDeviceId(@RequestParam("deviceId") String deviceId,
                                        @RequestParam("eventCodes") List<String> eventCodes,
                                        @RequestParam("beginTime") long beginTime,
                                        @RequestParam("endTime") long endTime,
                                        @RequestParam("pageIndex") int pageIndex,
                                        @RequestParam("pageSize") int pageSize);

    /**
     * 设备信息数量
     *
     * @return
     */
    @RequestMapping(value = "/countOfDeviceInfo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceInfo();

    /**
     * 指定设备类型的设备数量
     *
     * @param deviceType
     * @return
     */
    @RequestMapping(value = "/countOfDeviceInfoByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceInfoByDeviceType(@RequestParam("deviceType") String deviceType);

    /**
     * 根据设备类型和版本号查询设备信息数量
     *
     * @param deviceType
     * @param versionCode
     * @return
     */
    @RequestMapping(value = "/countOfDeviceInfoByDeviceTypeAndVersionCode", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceInfoByDeviceTypeAndVersionCode(@RequestParam("deviceType") String deviceType,
                                                          @RequestParam("versionCode") int versionCode);

    /**
     * 根据id获取设备信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDeviceInfoById", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfoById(@RequestParam("id") long id);

    /**
     * 根据设备id获取设备信息
     *
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/getDeviceInfoByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfoByDeviceId(@RequestParam("deviceId") String deviceId);

    /**
     * 根据设备mac获取设备信息
     *
     * @param mac
     * @return
     */
    @RequestMapping(value = "/getDeviceInfoByMac", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfoByMac(@RequestParam("mac") String mac);

    /**
     * 根据设备类型获取设备信息
     *
     * @param deviceType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDeviceInfosByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfosByDeviceType(@RequestParam("deviceType") String deviceType,
                                         @RequestParam("pageIndex") int pageIndex,
                                         @RequestParam("pageSize") int pageSize);

    /**
     * 根据设备类型和版本号获取设备信息
     *
     * @param deviceType
     * @param versionCode
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDeviceInfosByDeviceTypeAndVersion", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfosByDeviceTypeAndVersion(@RequestParam("deviceType") String deviceType,
                                                   @RequestParam("versionCode") int versionCode,
                                                   @RequestParam("pageIndex") int pageIndex,
                                                   @RequestParam("pageSize") int pageSize);

    /**
     * 获取设备的位置信息（经纬度）
     *
     * @param deviceId
     * @param coorType
     * @return
     */
    @RequestMapping(value = "/getLocation", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getLocation(@RequestParam("deviceId") String deviceId, @RequestParam("coorType") int coorType);

    /**
     * 设备绑定位置信息
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/bindLocation", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> bindLocation(@RequestBody BindLocationParamsDto params);

    /**
     * 根据时间查询设备的日志信息
     *
     * @param deviceId
     * @param deviceType
     * @param logType
     * @param beginTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDeviceLogsByTime", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceLogsByTime(@RequestParam(required = false) String deviceId,
                                  @RequestParam(required = false, value = "deviceType") String deviceType,
                                  @RequestParam(required = false, value = "logType") String logType,
                                  @RequestParam("beginTime") long beginTime,
                                  @RequestParam("endTime") long endTime,
                                  @RequestParam("pageIndex") int pageIndex,
                                  @RequestParam("pageSize") int pageSize);

    /**
     * 根据设备类型获取ota升级文件
     *
     * @param deviceType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDeviceOtaFilesByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceOtaFilesByDeviceType(@RequestParam("deviceType") String deviceType,
                                            @RequestParam("pageIndex") int pageIndex,
                                            @RequestParam("pageSize") int pageSize);

    /**
     * 上传ota升级文件
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/uploadOtaFile", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> uploadOtaFile(@RequestBody Map<String, Object> params);

    /**
     * 查找上传的文档信息
     *
     * @param deviceType
     * @param connected
     * @param deviceCode
     * @param beginVersion
     * @param endVersion
     * @param pageIdnex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findUpDocument", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<QueryResult<?>> findUpDocument(@RequestParam("deviceType") String deviceType,
                                          @RequestParam("connected") boolean connected,
                                          @RequestParam("deviceCode") String deviceCode,
                                          @RequestParam("beginVersion") int beginVersion,
                                          @RequestParam("endVersion") int endVersion,
                                          @RequestParam("pageIndex") int pageIdnex,
                                          @RequestParam("pageSize") int pageSize);

    /**
     * 绑定设备与owner
     *
     * @param ownerId
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/bindDevice", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> bindDevice(@RequestParam("ownerId") String ownerId, @RequestParam("deviceId") String deviceId);

    /**
     * 批量绑定设备与owner
     *
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/bindDeviceList", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> bindDeviceList(@RequestBody Map<String, Object> paramMap);

    /**
     * 设备与owner解绑
     *
     * @param ownerId
     * @param deviceArray
     * @return
     */
    @RequestMapping(value = "/unBindDevice", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> unbindDevice(@RequestParam("ownerId") String ownerId,
                           @RequestParam(value = "deviceArray", required = false) String[] deviceArray);

    /**
     * 获取owner下的设备信息
     *
     * @param ownerId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDeviceInfoByOwnerId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfoByOwnerId(@RequestParam("ownerId") String ownerId, @RequestParam("pageIndex") int
            pageIndex, @RequestParam("pageSize") int pageSize);

    /**
     * 根据传入的一系列参数查询设备信息
     *
     * @param ownerIds
     * @param deviceType
     * @param connected
     * @param pageIdnex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findDeviceByParams", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> findDeviceByParams(@RequestParam(value = "ownerIds", required = false) String[] ownerIds,
                                 @RequestParam(value = "deviceType", required = false) String deviceType,
                                 @RequestParam(value = "connected", required = false) boolean connected,
                                 @RequestParam("pageIndex") int pageIdnex,
                                 @RequestParam("pageSize") int pageSize);

    /**
     * 设备升级
     *
     * @param list
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> deviceUpdate(@RequestParam("list") List list);

    /**
     * 获取设备状态
     *
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/getDeviceStatus", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceStatus(@RequestParam("deviceId") String deviceId);

    /**
     * 批量获取设备状态
     *
     * @param deviceIds
     * @return
     */
    @RequestMapping(value = "/getDeviceStatusBatch", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceStatusBatch(@RequestParam("deviceIds") String[] deviceIds);

    /**
     * 设备token数量
     *
     * @return
     */
    @RequestMapping(value = "/countOfDeviceToken", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceToken();

    /**
     * 产生设备id
     *
     * @param deviceType
     * @param token
     * @return
     */
    @RequestMapping(value = "/generateDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> generateDeviceId(@RequestParam("deviceType") String deviceType, @RequestParam("token") String token);

    /**
     * 根据设备id获取设备token
     *
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/getDeviceTokenByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceTokenByDeviceId(@RequestParam("deviceId") String deviceId);

    /**
     * 不明白
     *
     * @param token
     * @return
     * @TODO
     */
    @RequestMapping(value = "/getDeviceTokenByToken", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceTokenByToken(@RequestParam("token") String token);

    /**
     * 根据设备类型获取设备token
     *
     * @param deviceType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDeviceTokensByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceTokensByDeviceType(@RequestParam("deviceType") String deviceType,
                                          @RequestParam("pageIndex") int pageIndex,
                                          @RequestParam("pageSize") int pageSize);
}
