package org.spring.springboot;

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
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback
@Transactional(transactionManager = "transactionManager")
public class MockMvcTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void saveHotelMessageInfo() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.put("/test11")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("result:"+result);
    }

}
