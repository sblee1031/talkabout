package com.talkabout.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talkabout.dto.Admin;
import com.talkabout.dto.DebateSungho;
import com.talkabout.exception.FindException;

@SpringBootTest
public class AdminDAOTest {

	@Autowired
	AdminDAO dao;
	DebateResultDAO dao2;
	
	@Test
	void test()throws FindException{
		
		Admin dto = dao.AdLogin("ad1", "p1");
		String ExpectedName = "ad1";
		Assertions.assertThat(ExpectedName).isEqualTo(dto.getAdmin_id());
		Assertions.assertThat("p1").isEqualTo(dto.getAdmin_pwd());
		System.out.println(dto.getAdmin_id());
		System.out.println(dto.getAdmin_pwd());
	}

}
