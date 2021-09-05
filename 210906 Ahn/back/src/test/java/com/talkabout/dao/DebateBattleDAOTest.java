package com.talkabout.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talkabout.dto.Audience;
import com.talkabout.exception.FindException;

@SpringBootTest
public class DebateBattleDAOTest {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DebateBattleDAO debbattledao;
	
	@Test
	public void testSelectAllAud() throws FindException {
		List<Audience> list = debbattledao.selectAllAud();
		log.error(list.get(0).toString());
	}

	@Test
	public void testSelectByAudNo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByDeb() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertVote() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateVote() {
		fail("Not yet implemented");
	}

}
