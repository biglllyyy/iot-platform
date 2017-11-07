package com.vortex.dustbin.das;

import me.iot.common.ZeroApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

@ZeroApplication
public class DustbinDasApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DustbinDasApplication.class, args);
        Server server = ctx.getBean(Server.class);
        server.start();
    }
}
