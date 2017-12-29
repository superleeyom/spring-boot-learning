package com.leeyom.fastweb;

import com.leeyom.fastweb.common.LeeyomProperties;
import com.leeyom.fastweb.web.WebController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Resource
    private LeeyomProperties leeyomProperties;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new WebController()).build();
    }

    @Test
    public void getUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/getUser")).andDo(print());
    }

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/getUsers")).andDo(print());
    }

    @Test
    public void saveUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/saveUser")
                .param("name", "")
                .param("age", "666")
                .param("pass", "test")
        ).andDo(print());
    }

    @Test
    public void testProperties() throws Exception {
        System.out.println("title:" + leeyomProperties.getTitle());
        System.out.println("description:" + leeyomProperties.getDescription());
    }
}
