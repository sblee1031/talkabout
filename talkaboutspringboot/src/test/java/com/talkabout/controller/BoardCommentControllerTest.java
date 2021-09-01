package com.talkabout.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.talkabout.exception.DeleteException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BoardCommentControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@DisplayName("댓글 리스트")
	void test() throws Exception{
		MockHttpServletRequestBuilder requestBuilder;
		String uri = "/boardcomment/list/1";
		requestBuilder = MockMvcRequestBuilders.get(uri);
		try {
			ResultActions resultActions = mockMvc.perform(requestBuilder);
			ResultMatcher ok = MockMvcResultMatchers.status().isOk();
			resultActions.andExpect(ok);
			ResultHandler resultHandler = MockMvcResultHandlers.print(); 
			resultActions.andDo(resultHandler);
		}catch (Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	@DisplayName("댓글 삭제")
	void test1() throws DeleteException{
		MockHttpServletRequestBuilder requestBuilder;
		String uri = "/boardcomment/102";
		requestBuilder = MockMvcRequestBuilders.delete(uri);
		try {
			ResultActions resultActions = mockMvc.perform(requestBuilder);
			ResultMatcher ok = MockMvcResultMatchers.status().isOk();
			resultActions.andExpect(ok);
			ResultHandler resultHandler = MockMvcResultHandlers.print();
			resultActions.andDo(resultHandler);
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
