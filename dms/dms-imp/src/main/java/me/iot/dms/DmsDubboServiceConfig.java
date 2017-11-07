package me.iot.dms;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by sylar on 16/5/1.
 */
@ImportResource("classpath:dubbo-provider.xml")
@Configuration
public class DmsDubboServiceConfig {

}
