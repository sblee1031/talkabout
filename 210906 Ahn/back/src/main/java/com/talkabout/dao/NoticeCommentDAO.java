package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.NoticeComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public interface NoticeCommentDAO {
	
	List<NoticeComment> selectAll(int com_notice) throws FindException;
	
	NoticeComment selectOne(int com_no) throws FindException;
    
    void insertComment(NoticeComment nc) throws AddException;
    
    void updateComment(NoticeComment nc)  throws ModifyException;
    
    void deleteComment(int com_no) throws DeleteException;
}
