package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public interface NoticeDAO {
	
	List<Notice> selectAll() throws FindException;
	
    Notice selectOne(int notice_no) throws FindException;
    
    List<Notice> selectSearch(String word) throws FindException;
    
    void insertNotice(Notice notice) throws AddException;
    
    void updateNotice(Notice notice)  throws ModifyException;
    
    void updateCount(Notice notice) throws ModifyException;
    
    void deleteNotice(int notice_no) throws DeleteException;
}
