package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.DebateComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public interface DebateCommentDAO {
	void insert(DebateComment DC) throws AddException;

	void delete(int com_no) throws DeleteException;

	void update(DebateComment DC) throws ModifyException ;
	
	DebateComment selectByNo(int com_no) throws FindException ;//한개만 가져오는거

	List<DebateComment> selectByComNo(int com_com) throws FindException; //댓글들 가져오느느 com_deb으로 가져온다 oracle과 여기엔 변수만 com_no임 나중에 수정해서 변수는 못바꿈
	
void deleteByAdmin(int com_no) throws DeleteException;
}
