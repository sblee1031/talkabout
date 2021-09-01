package com.talkabout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.AdminDAO;
import com.talkabout.dto.Admin;
import com.talkabout.dto.Board;
import com.talkabout.dto.Notice;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
@Service
public class AdminService {
	
	@Autowired
	private AdminDAO dao;
	

	public Admin adminInfo(int admin_no) throws FindException {
		return dao.selectByNo(admin_no);
	}
	public Admin adLogin(String id, String pwd) throws FindException {
		return dao.AdLogin(id, pwd);
	}
	
//notice
	public List<Notice> noticeFindAll(int startRow, int endRow) throws FindException {
		return dao.noticeFindAll( startRow,  endRow);
	}
	public List<Notice> noticeFindAll(String optWord,int startRow, int endRow) throws FindException {
		return dao.noticeFindAll(optWord, startRow,  endRow);
	} 
	public int noticeLastRow() {//총 게시물 개수 구하기
		return dao.noticeLastRow();
	}
	public int noticeSearchLastRow(String word) {//총 게시물 개수 구하기
		return dao.noticeSearchLastRow(word);
	}
//board
	public List<Board> boardFindAll(int startRow, int endRow) throws FindException {
		return dao.boardFindAll( startRow,  endRow);
	}
	public List<Board> boardFindAll(String optWord,int startRow, int endRow) throws FindException {
		return dao.boardFindAll(optWord, startRow,  endRow);
	} 
	public int boardLastRow() {//총 게시물 개수 구하기
		return dao.boardLastRow();
	}
	public int boardSearchLastRow(String word) {//총 게시물 개수 구하기
		return dao.boardSearchLastRow(word);
	}
	
//debate_approve
	public void approve(int deb_no) throws ModifyException{
		dao.approve(deb_no);
	}
	public void disapprove(int deb_no) throws ModifyException{
		dao.disapprove(deb_no);
	}
}