package com.talkabout.dao;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talkabout.dto.Notice;
import com.talkabout.exception.FindException;

@SpringBootTest
public class NoticeDAOTest {
	
	@Autowired 
	NoticeDAO dao;
	
	@Test
	public void testSelectAll() throws FindException {
		List<Notice> list = dao.selectAll();
		
		int ExpectedName = 1;
		
		assertEquals(ExpectedName, list.get(0).getNotice_no());
	}

	@Test
	public void testSelectOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertNotice() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateNotice() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteNotice() {
		fail("Not yet implemented");
	}

}
