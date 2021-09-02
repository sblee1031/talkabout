package com.talkabout.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
@SpringBootTest
class MemberDAOTest {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	MemberDAO dao;
	@Test
	void selectNyNo() {
		
	}
	@Test
	void selectByNoTest() throws FindException {
		String socialNo = "1775421132";
		Member mem = dao.selectByNo(socialNo);
		log.error(mem.toString());
		assertNotNull(dao);
	}

}
