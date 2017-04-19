package twitter.webapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@WebAppConfiguration("/resources/mywebcontext.xml")
//@WebAppConfiguration("/WEB-INF/webContext.xml")
//@WebAppConfiguration("file:src/main/webapp/WEB-INF/webContext.xml")
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("classpath:/spring.xml"),
        @ContextConfiguration("classpath:/service.xml"),
        @ContextConfiguration("file:src/main/webapp/WEB-INF/webContext.xml")
})
public class TweeterControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void tweetFromRequestBuildingTest(){
        try {
            mockMvc.perform(get("/tweet/modify?id=1"))
            .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}