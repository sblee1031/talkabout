package com.talkabout.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talkabout.dto.Admin;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;

@SpringBootTest
public class DebateDAOTest {

	@Autowired
	DebateResultDAO dao;

	@Test
	void SelectAlltest()throws FindException{
		
//		Admin dto = dao.AdLogin("ad1", "p1");
//		String ExpectedName = "ad1";
//		Assertions.assertThat(ExpectedName).isEqualTo(dto.getAdmin_id());
//		Assertions.assertThat("p1").isEqualTo(dto.getAdmin_pwd());
//		System.out.println(dto.getAdmin_id());
//		System.out.println(dto.getAdmin_pwd());
		
		List<Debate> selectlist = dao.selectAll();
		//String ExpectedName = "산 VS 바다"; 
		Assertions.assertNotNull(selectlist);
		System.out.println("리스트목록 = "+selectlist);
	}
	
	@Test 
	void SelectOneTest()throws FindException{
		Debate debate = dao.selectByNum(3);
		int ExpectedName =3;
		System.out.println(debate);
		org.assertj.core.api.Assertions.assertThat(ExpectedName).isEqualTo(3);
	}
	
	@Test
	void SelectDetailOneTest()throws FindException{
		List<DebateDetail> debateDetail = dao.detailselectByNo(3);
		//int Expectednum =3;
		Assertions.assertNotNull(debateDetail);
		System.out.println("상세목록 ="+debateDetail);
		
	}
	
	@Test
	void selectMemberTest()throws FindException{
		Member member = dao.Memberselect(3);
		Assertions.assertNotNull(member);
		org.assertj.core.api.Assertions.assertThat("Luke").isEqualTo(member.getMember_nickName());
	}
}
