package me.iot.dms.ui.service;

import me.iot.dms.dto.BindLocationParamsDto;
import me.iot.common.dto.QueryResult;
import me.iot.common.dto.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @FileName             :  MqttConst
 * @Author                :  sylar
 * @CreateDate           :  2017/11/08
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@FeignClient(name = "dms", fallback = DmsFallCallback.class)
public interface IDmsFeignClient {
    @RequestMapping(value = "getDasConnectionLogsByNodeId",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDasConnectionLogsByNodeId(@RequestParam("nodeId") String nodeId,
                                           @RequestParam("beginTime") long beginTime,
                                           @RequestParam("endTime") long endTime,
                                           @RequestParam("pageIndex") int pageIndex,
                                           @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "getDasStatus",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDasStatus(@RequestParam("nodeId") String nodeId);

    @RequestMapping(value = "countOfDeviceAlarm",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceAlarm(@RequestParam("beginTime") long beginTime,
                                 @RequestParam("endTime") long endTime);

    @RequestMapping(value = "/countOfDeviceAlarmByDeviceType",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceAlarmByDeviceType(@RequestParam("deviceType") String deviceType,
                                             @RequestParam("beginTime") long beginTime,
                                             @RequestParam("endTime") long endTime);

    @RequestMapping(value = "/countOfDeviceAlarmByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceAlarmByDeviceId(@RequestParam("deviceId") String deviceId,
                                           @RequestParam("beginTime") long beginTime,
                                           @RequestParam("endTime") long endTime);

    @RequestMapping(value = "/getDeviceAlarmsByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceAlarmsByDeviceId(@RequestParam("deviceId") String deviceId,
                                        @RequestParam("alarmCodes") List<String> alarmCodes,
                                        @RequestParam("beginTime") long beginTime,
                                        @RequestParam("endTime") long endTime,
                                        @RequestParam("pageIndex") int pageIndex,
                                        @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/getDeviceConnectionLogsByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceConnectionLogsByDeviceId(@RequestParam("deviceId") String deviceId,
                                                @RequestParam("beginTime") long beginTime,
                                                @RequestParam("endTime") long endTime,
                                                @RequestParam("pageIndex") int pageIndex,
                                                @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/countOfDeviceEvent", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceEvent(@RequestParam("beginTime") long beginTime, @RequestParam("endTime") long endTime);

    @RequestMapping(value = "/countOfDeviceEventByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceEventByDeviceType(@RequestParam("deviceType") String deviceType,
                                             @RequestParam("beginTime") long beginTime,
                                             @RequestParam("endTime") long endTime);

    @RequestMapping(value = "/countOfDeviceEventByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceEventByDeviceId(@RequestParam("deviceId") String deviceId,
                                           @RequestParam("beginTime") long beginTime,
                                           @RequestParam("endTime") long endTime);

    @RequestMapping(value = "/getDeviceEventsByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceEventsByDeviceId(@RequestParam("deviceId") String deviceId,
                                        @RequestParam("eventCodes") List<String> eventCodes,
                                        @RequestParam("beginTime") long beginTime,
                                        @RequestParam("endTime") long endTime,
                                        @RequestParam("pageIndex") int pageIndex,
                                        @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/countOfDeviceInfo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceInfo();

    @RequestMapping(value = "/countOfDeviceInfoByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceInfoByDeviceType(@RequestParam("deviceType") String deviceType);

    @RequestMapping(value = "/countOfDeviceInfoByDeviceTypeAndVersionCode", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceInfoByDeviceTypeAndVersionCode(@RequestParam("deviceType") String deviceType,
                                                          @RequestParam("versionCode") int versionCode);

    @RequestMapping(value = "/getDeviceInfoById", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfoById(@RequestParam("id") long id);

    @RequestMapping(value = "/getDeviceInfoByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfoByDeviceId(@RequestParam("deviceId") String deviceId);

    @RequestMapping(value = "/getDeviceInfoByMac", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfoByMac(@RequestParam("mac") String mac);

    @RequestMapping(value = "/getDeviceInfosByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfosByDeviceType(@RequestParam("deviceType") String deviceType,
                                         @RequestParam("pageIndex") int pageIndex,
                                         @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/getDeviceInfosByDeviceTypeAndVersion", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfosByDeviceTypeAndVersion(@RequestParam("deviceType") String deviceType,
                                                   @RequestParam("versionCode") int versionCode,
                                                   @RequestParam("pageIndex") int pageIndex,
                                                   @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/getLocation", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getLocation(@RequestParam("deviceId") String deviceId, @RequestParam("coorType") int coorType);

    @RequestMapping(value = "/bindLocation", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> bindLocation(@RequestBody BindLocationParamsDto params);

    @RequestMapping(value = "/getDeviceLogsByTime", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceLogsByTime(@RequestParam(required = false) String deviceId,
                                  @RequestParam(required = false, value = "deviceType") String deviceType,
                                  @RequestParam(required = false, value = "logType") String logType,
                                  @RequestParam("beginTime") long beginTime,
                                  @RequestParam("endTime") long endTime,
                                  @RequestParam("pageIndex") int pageIndex,
                                  @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/getDeviceOtaFilesByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceOtaFilesByDeviceType(@RequestParam("deviceType") String deviceType,
                                            @RequestParam("pageIndex") int pageIndex,
                                            @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/uploadOtaFile", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> uploadOtaFile(@RequestBody Map<String, Object> params);

    @RequestMapping(value = "/findUpDocument", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<QueryResult<?>> findUpDocument(@RequestParam("deviceType") String deviceType,
                                          @RequestParam("connected") boolean connected,
                                          @RequestParam("deviceCode") String deviceCode,
                                          @RequestParam("beginVersion") int beginVersion,
                                          @RequestParam("endVersion") int endVersion,
                                          @RequestParam("pageIndex") int pageIdnex,
                                          @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/bindDevice", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> bindDevice(@RequestParam("ownerId") String ownerId, @RequestParam("deviceId") String deviceId);

    @RequestMapping(value = "/bindDeviceList", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> bindDeviceList(@RequestBody Map<String, Object> paramMap);

    @RequestMapping(value = "/unBindDevice", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> unbindDevice(@RequestParam("ownerId") String ownerId,
                           @RequestParam(value = "deviceArray", required = false) String[] deviceArray);

    @RequestMapping(value = "/getDeviceInfoByOwnerId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceInfoByOwnerId(@RequestParam("ownerId") String ownerId, @RequestParam("pageIndex") int
            pageIndex, @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/findDeviceByParams", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> findDeviceByParams(@RequestParam(value = "ownerIds", required = false) String[] ownerIds,
                                 @RequestParam(value = "deviceType", required = false) String deviceType,
                                 @RequestParam(value = "connected", required = false) boolean connected,
                                 @RequestParam("pageIndex") int pageIdnex,
                                 @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> deviceUpdate(@RequestParam("list") List list);

    @RequestMapping(value = "/getDeviceStatus", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceStatus(@RequestParam("deviceId") String deviceId);

    @RequestMapping(value = "/getDeviceStatusBatch", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceStatusBatch(@RequestParam("deviceIds") String[] deviceIds);

    @RequestMapping(value = "/countOfDeviceToken", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> countOfDeviceToken();

    @RequestMapping(value = "/generateDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> generateDeviceId(@RequestParam("deviceType") String deviceType, @RequestParam("token") String token);

    @RequestMapping(value = "/getDeviceTokenByDeviceId", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceTokenByDeviceId(@RequestParam("deviceId") String deviceId);

    @RequestMapping(value = "/getDeviceTokenByToken", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceTokenByToken(@RequestParam("token") String token);

    @RequestMapping(value = "/getDeviceTokensByDeviceType", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<?> getDeviceTokensByDeviceType(@RequestParam("deviceType") String deviceType,
                                          @RequestParam("pageIndex") int pageIndex,
                                          @RequestParam("pageSize") int pageSize);
}
