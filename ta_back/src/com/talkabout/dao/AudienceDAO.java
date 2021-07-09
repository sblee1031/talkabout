package com.talkabout.dao;

import java.util.ArrayList;
import java.util.List;

import com.talkabout.dto.Audience;

public interface AudienceDAO {
	List<Audience> selectAll();
	
	Audience selectByNo(int audi_no);
	
	void updateVote(Audience a);
	
	ArrayList<Integer> selectByCnt(int deb_no);
}
