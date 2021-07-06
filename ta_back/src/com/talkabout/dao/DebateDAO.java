package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.Debate;

public interface DebateDAO {
	List<Debate> selectAll();
	
	Debate selectByNo(int debate_no);
}
