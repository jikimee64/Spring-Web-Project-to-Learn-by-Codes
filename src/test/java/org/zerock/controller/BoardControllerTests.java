package org.zerock.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//Test for Controller
@ContextConfiguration(classes = {
        org.zerock.config.RootConfig.class,
        org.zerock.config.ServletConfig.class })
@Slf4j
//@Log4j
public class BoardControllerTests {
    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    public void testList() throws Exception {
        log.info(
                String.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andReturn()
                .getModelAndView()
                .getModelMap())
        );
    }

    public void testRegister()throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title", "테스트 새글 제목")
                .param("content", "테스트 새글 내용")
                .param("writer", "user00")
        ).andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }

    public void tetGet() throws Exception {
        log.info(String.valueOf(mockMvc.perform(MockMvcRequestBuilders
        .get("/board/get")
        .param("bno", "21"))
        .andReturn()
        .getModelAndView().getModelMap()));
    }

    public void testModify() throws Exception {

        String resultPage = mockMvc
                .perform(MockMvcRequestBuilders.post("/board/modify")
                .param("bno", "41")
                .param("title", "수정된 테스트 새글 제목")
                .param("content", "수정된 테스트 새글 내용")
                .param("writer", "user00"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    //@Test
    public void testRemove() throws Exception {
        //삭제전 데이터베이스에 게시물 번호 확인할 것
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
        .param("bno", "50")
        ).andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }

    @Test
    public void testListPaging() throws Exception {
        log.info(String.valueOf(mockMvc.perform(
                MockMvcRequestBuilders.get("/board/list")
                .param("pageNum", "2")
                .param("amount", "50"))
                .andReturn().getModelAndView().getModelMap()));
    }

}
