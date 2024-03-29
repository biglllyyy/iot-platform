package iot.quickstart.dustbin.das;

import iot.common.consts.AppConsts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author :  sylar
 * @FileName :  DustbinDasApplication
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
@SpringBootApplication
@ComponentScan(AppConsts.TOP_PACKAGE_NAME)
public class DustbinDasApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DustbinDasApplication.class, args);
        Server server = ctx.getBean(Server.class);
        server.start();
    }
}
