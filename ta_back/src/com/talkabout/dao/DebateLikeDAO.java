package com.talkabout.dao;

import com.talkabout.dto.DebateLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;

public interface DebateLikeDAO {
void insert (DebateLike DL) throws AddException;
	
	void deleteByDebatelikeNo (int deblike_no) throws DeleteException;
}
