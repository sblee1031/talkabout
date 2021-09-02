package com.talkabout.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class DebateDAOTest {
	@Autowired
	DebateDAO dao;
	@Test
	void test() {
		int a = dao.lastRow();
		assertNotNull(a);
	}

}
