package com.vortex.dustbin.das;

import me.iot.common.ZeroApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
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
@ZeroApplication
public class DustbinDasApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DustbinDasApplication.class, args);
        Server server = ctx.getBean(Server.class);
        server.start();
    }
}
