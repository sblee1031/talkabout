package com.talkabout.dao;

import com.talkabout.dto.DebateLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;

public interface DebateLikeDAO {
	void insert(DebateLike DL) throws AddException;

	void deleteByDebatelikeNo(DebateLike dl) throws DeleteException;
	
	Integer debLikeChk(DebateLike dl) throws FindException;
	
	Integer debLikeCnt(DebateLike deb_no);
}
