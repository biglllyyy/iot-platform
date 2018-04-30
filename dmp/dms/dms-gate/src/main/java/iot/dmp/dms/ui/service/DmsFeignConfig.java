package iot.dmp.dms.ui.service;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author :  sylar
 * @FileName :
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
@Configurable
@EnableHystrix
@EnableZuulProxy
@EnableFeignClients(basePackages = {"me.iot.dms.ui.service"})
public class DmsFeignConfig {
}
