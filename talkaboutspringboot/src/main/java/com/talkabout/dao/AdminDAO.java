package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.Admin;
import com.talkabout.dto.Board;
import com.talkabout.dto.Notice;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public interface AdminDAO {
	Admin selectByNo(int admin_no) throws FindException;
	
	Admin AdLogin(String id, String pwd) throws FindException;
	
	//notice
	List<Notice> noticeFindAll(int startRow, int endRow) throws FindException;
	List<Notice> noticeFindAll(String word,int startRow, int endRow) throws FindException;
    int noticeLastRow();
    int noticeSearchLastRow(String word);
    
    
    //board
	List<Board> boardFindAll(int startRow, int endRow) throws FindException;
	List<Board> boardFindAll(String word,int startRow, int endRow) throws FindException;
    int boardLastRow();
    int boardSearchLastRow(String word);
    
    //debate approve
    void approve(int deb_no) throws ModifyException;
}
