package me.iot.dms;

import me.iot.common.ZeroApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@ZeroApplication
public class DmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DmsApplication.class, args);
    }
}
