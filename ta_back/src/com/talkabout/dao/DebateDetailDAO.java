package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.DebateDetail;
import com.talkabout.exception.FindException;

public interface DebateDetailDAO {
	
	List<DebateDetail> selectAll();
	
	DebateDetail selectOne(int detail_no, int discussor);
	
	void insert(DebateDetail dd);
	
	void updateDiscussor(DebateDetail dd);
	
	void updateIntime(DebateDetail dd);
	
	void updateEvidence(DebateDetail dd, int num);
	
	List<DebateDetail> selectByNo(int deb_no); // 주장A, 주장B 가져오기
}