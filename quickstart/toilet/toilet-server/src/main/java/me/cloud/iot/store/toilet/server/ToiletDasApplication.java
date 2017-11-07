package me.cloud.iot.store.toilet.server;

import me.iot.common.ZeroApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

@ZeroApplication
public class ToiletDasApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ToiletDasApplication.class, args);
        Server server = ctx.getBean(Server.class);
        server.start();
    }
}
