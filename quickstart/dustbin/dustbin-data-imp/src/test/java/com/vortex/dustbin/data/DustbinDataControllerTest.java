package com.vortex.dustbin.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by LiShijun on 16/9/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class DustbinDataControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private final static String DEVICE_CODE = "TRCAN867587029314372";

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void addParams() throws Exception {
        String url = "/device/data/dustbin/saveParams";
        String json = "{\n" +
                "    \"deviceType\": \"TRCAN\",\n" +
                "    \"deviceId\": \"TRCAN867587029314372\",\n" +
                "    \"height\": 100,\n" +
                "    \"fullThreshold\": 20,\n" +
                "    \"halfFullThreshold\": 50, \n" +
                "    \"userId\": \"u0001\"\n" +
                "}";

        mockMvc.perform(post(url, "json")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateParams() throws Exception {
        String url = "/device/data/dustbin/saveParams";
        String json = "{\n" +
                "    \"deviceType\": \"TRCAN\",\n" +
                "    \"deviceId\": \"TRCAN867587029314372\",\n" +
                "    \"height\": 100,\n" +
                "    \"fullThreshold\": 25,\n" +
                "    \"halfFullThreshold\": 50, \n" +
                "    \"userId\": \"u0002\"\n" +
                "}";

        mockMvc.perform(post(url, "json")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getParams() throws Exception {
        String url = "/device/data/dustbin/getParams";
        mockMvc.perform(get(url).param("deviceId", DEVICE_CODE)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
