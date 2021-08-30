package com.talkabout.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talkabout.dto.DebateCommentSungho;
import com.talkabout.dto.DebateSungho;
import com.talkabout.dto.PageDTOsungho;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.RemoveException;
@SpringBootTest
class DebateResultDAOTest {

	@Autowired
	DebateResultDAO dao;
	

	@Test
	void sunghotest() throws FindException {
		
		 DebateSungho dto = dao.sunghoDebateSelectByNo(3);
		 org.junit.jupiter.api.Assertions.assertNotNull(dto);
		 String nickname = dto.getDebate_mem().getMember_nickName();
		 String expected = "자두";
		 assertEquals(expected, nickname);
		 
		 int detailSize = dto.getDetail_list().size(); //토론1, 토론2
		 int expectedDetailSize = 2;
		 assertTrue(expectedDetailSize == detailSize); 
		 
		 dto.getComment_list().get(0);
		 
		 
	}
	@Test
	public void testpage()throws FindException{
		//1 10
		DebateSungho cri = new DebateSungho();
		
		List<DebateSungho> list = dao.getlistWithPaging(cri);
		
		for (DebateSungho debateSungho : list) {
			System.out.println(list);
			
		}
	}
	@Test
	public void testPageDTO() {
		DebateSungho cri = new DebateSungho();
	
		cri.setPage_num(11);
		
		 PageDTOsungho pagedto = new PageDTOsungho(cri,250);
		 
		System.out.println(pagedto);
		
	}
	@Test
	public void TestInsertCommnet() throws AddException, FindException{
		int beginSize = dao.GetListComment(1).size();
		DebateCommentSungho cm = new DebateCommentSungho(1,"InsertComment Test",3);
		for (int i = 0; i < 1; i++) {
			dao.insertComment(cm);
		}
		int afterSize = dao.GetListComment(1).size();
		assertEquals(afterSize-beginSize, 1000);
	}
	
	@Test
	public void TestDeleteComment() throws RemoveException{
		dao.deleteComment(35);
		
	}
//	@Test
//	public void TestGetlistByWord()throws FindException{
//		List<DebateSungho>list= dao.Getlistbyword("호");
//		System.out.println(list);
//	}
}
