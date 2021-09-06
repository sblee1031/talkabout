package com.talkabout.dao;

import java.util.ArrayList;
import java.util.List;

import com.talkabout.dto.Audience;

public interface AudienceDAO {
	/**
	 * 
	 * @param debate_no 토론 번호
	 * @return 선택한 debate 가져오기
	 */
	List<Audience> selectAll();
	
	// SELECT ONE BY PK
	Audience selectByNo(int audi_no);
	
	// SELECT ONE BY 토론번호, 회원번호
	Audience selectByDeb(int deb_no, int mem_no);
	
	// INSERT
	void insertVote(int deb_no, int mem_no);
	
	void updateVote(Audience a);
	
	ArrayList<Integer> selectByCnt(int deb_no);
}
