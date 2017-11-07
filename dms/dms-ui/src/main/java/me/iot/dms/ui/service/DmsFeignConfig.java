package me.iot.dms.ui.service;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by tyf on 2017/4/10.
 */
@Configurable
@EnableHystrix
@EnableZuulProxy
@EnableFeignClients(basePackages = {"me.iot.dms.ui.service"})
public class DmsFeignConfig {
}
