package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.Admin;
import com.talkabout.dto.Notice;
import com.talkabout.exception.FindException;

public interface AdminDAO {
	Admin selectByNo(int admin_no) throws FindException;
	
	Admin AdLogin(String id, String pwd) throws FindException;
	
	//notice
	List<Notice> noticeFindAll(int startRow, int endRow) throws FindException;
	List<Notice> noticeFindAll(String word,int startRow, int endRow) throws FindException;
    int noticeLastRow();
    int noticeSearchLastRow(String word);
}
