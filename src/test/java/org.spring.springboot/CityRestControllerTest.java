package org.spring.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback
@Transactional(transactionManager = "transactionManager")
public class CityRestControllerTest {
    // 注入web环境的ApplicationContext容器
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @Before // 这个注解的作用,在每个方法之前都会执行一遍
    public void before() {
        //获取mockmvc对象实例
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void saveHotelMessageInfo() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.put("/test11")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("result:" + result);
    }

//    @Test
//    public void savePlans() throws Exception {
//        String json = "{\n" +
//                "\t\"id\":1,\n" +
//                "\t\"code\":\"yanwu\",\n" +
//                "\t\"warningClass\":2,\n" +
//                "    \"departmentId\":1,\n" +
//                "     \"planId\":1\n" +
//                "}";
//        mvc.perform(MockMvcRequestBuilders.put("/abnormal-plans/")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(json.getBytes()))
//                .andExpect(status().isOk());
//    }

    @Test
    public void getPlansMessages() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/city")
                .param("cityName", "滨州市")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.cityName").value("滨州市"));
        System.out.println("测试结束");
    }




}
