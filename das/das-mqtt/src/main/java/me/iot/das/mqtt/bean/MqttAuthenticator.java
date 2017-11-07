package me.iot.das.mqtt.bean;

import com.google.common.collect.Maps;
import me.iot.das.common.DasConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;

@Component
public class MqttAuthenticator {
    private final static String ParamKey_MqttUser = "mqttUser";
    private final static String ParamKey_MqttPassword = "mqttPassword";

    @Autowired
    private DasConfig dasConfig;
    private Map<String, String> mapUsers = Maps.newHashMap();

    @PostConstruct
    public void init() {
        String user = dasConfig.getDasProperties().getParam(ParamKey_MqttUser);
        String pwd = dasConfig.getDasProperties().getParam(ParamKey_MqttPassword);
        if (user != null && pwd != null) {
            mapUsers.put(user, pwd);
        }
    }


    public boolean checkValid(String username, String password) {
        if (mapUsers.isEmpty()) {
            return true;
        } else if (username == null || password == null) {
            return false;
        } else {
            return Objects.equals(password, mapUsers.get(username));
        }
    }

}
