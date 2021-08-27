package com.talkabout.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talkabout.dto.BoardLike;
import com.talkabout.exception.AddException;


@SpringBootTest
public class BoardLikeTest {

	@Autowired
	BoardLikeDAO dao;
	
	@Test
	void test() throws AddException{
		
	
	}
}
