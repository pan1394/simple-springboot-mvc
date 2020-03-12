package com.linkstec.mvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.linkstec.mvc.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Restful API 单元测试。
 */
@SpringBootTest
@RunWith(SpringRunner.class)
//配置 mock
@AutoConfigureMockMvc
public class ApiControllerTest {

    @Autowired
    private MockMvc mock;

    //使用MockMvc post请求并发送java对象。
    @Test
    public void testUser() throws Exception {
        //请求pram对象
        UserDto reqDto = new UserDto("book", "2", "passwd");
        String jsonStr = JSONObject.toJSONString(reqDto);

        //post 请求url
        mock.perform(MockMvcRequestBuilders.post("/api/user")
                //body 对应@RequestObject的java 对象。
                .content(jsonStr)
                //设置请求头 content-type: application/json
                .contentType(MediaType.APPLICATION_JSON)
                //下面这个没有效果
                .accept(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loginName").value("book"))
                .andExpect(jsonPath("$.id").value("2"));

    }
}
