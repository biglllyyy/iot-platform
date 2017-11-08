package me.iot.jetty.config;


import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @FileName             :  WebServerConfig
 * @Author                :  luhao
 * @CreateDate           :  2017/7/6
 * @Description           :  jetty http请求post默认不支持压缩，需要修改默认配置
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) www.envcloud.com.cn (环境云) All Rights Reserved
 * *******************************************************************************************
 */
@Configuration
public class WebServerConfig {
    @Bean
    public JettyEmbeddedServletContainerFactory jettyFactory() {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        //定制gzip压缩支持GET和POST
        factory.addServerCustomizers(server -> {
            GzipHandler gzipHandler = new GzipHandler();
            Handler handler = server.getHandler();
            gzipHandler.setHandler(handler);
            gzipHandler.setIncludedMethods(HttpMethod.GET.asString(), HttpMethod.POST.asString());
            server.setHandler(gzipHandler);
        });
        return factory;
    }

}
