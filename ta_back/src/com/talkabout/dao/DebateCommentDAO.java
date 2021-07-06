package com.talkabout.dao;

import com.talkabout.dto.DebateComment;
import com.talkabout.dto.DebateLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public interface DebateCommentDAO {
	void insert(DebateComment DC) throws AddException;

	void delete(int com_no) throws DeleteException;

	void update(DebateComment DC) throws ModifyException ;

	DebateComment selectByComNo(int com_no) throws FindException;
	
void deleteByAdmin(int com_no) throws DeleteException;
}
