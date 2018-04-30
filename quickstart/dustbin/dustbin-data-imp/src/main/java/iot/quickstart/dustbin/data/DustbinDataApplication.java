package iot.quickstart.dustbin.data;

import iot.common.consts.AppConsts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author :  sylar
 * @FileName :  DustbinDataApplication
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
public class DustbinDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DustbinDataApplication.class, args);
    }
}
