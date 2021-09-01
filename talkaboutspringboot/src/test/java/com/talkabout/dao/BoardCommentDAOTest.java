package com.talkabout.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talkabout.dto.BoardComment;
import com.talkabout.exception.FindException;

@SpringBootTest
public class BoardCommentDAOTest {
	
	@Autowired
	BoardCommentDAO dao;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Test
	void test()throws FindException{
		BoardComment bc = dao.selectByComNo(3);
		int ExpectedName = 3;
		
		assertEquals(ExpectedName, bc.getCom_no());
	}
	
	@Test
	void test1() throws FindException{
		java.util.List<BoardComment> list = dao.selectAll(1);
		
	}
}
