package com.talkabout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.AdminDAO;
import com.talkabout.dto.Admin;
import com.talkabout.dto.Notice;
import com.talkabout.exception.FindException;
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
}