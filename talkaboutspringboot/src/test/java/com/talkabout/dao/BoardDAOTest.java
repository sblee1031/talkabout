package com.talkabout.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talkabout.dto.Board;
import com.talkabout.dto.BoardComment;
import com.talkabout.dto.BoardLike;
import com.talkabout.exception.FindException;

@SpringBootTest
public class BoardDAOTest {

	@Autowired
	BoardDAO dao;
	
	@Test
	void test()throws FindException{
		
		Board b = dao.selectByBoardNo(1);
		int expectedNo = 1;
		assertEquals(expectedNo, b.getBoard_no());
		
		List<BoardComment> comments = b.getComment_list();
		int expectedCommentsSize = 2;
		assertEquals(expectedCommentsSize, comments.size());
		
		List<BoardLike> likes = b.getLike_list();
		int expectedLikesSize = 2;
		assertEquals(expectedLikesSize, likes.size());
		
		for(BoardLike lk: likes) {
			System.out.println(lk.getBoardLike_member());
		}
		
		
	}
	


}
