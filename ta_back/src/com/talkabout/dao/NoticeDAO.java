package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.Notice;
import com.talkabout.exception.FindException;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.ModifyException;

public interface NoticeDAO {

	//public List<Notice> noticeSearch() throws FindException;
	//검색 기능(공통)
	public List<Notice> noticeSearch(String type, String contents) throws FindException;
	//공지/업데이트/이벤트 타입별 검색(공통)
	public List<Notice> selectAll() throws FindException;
	//게시글 리스트보기(공통)
	Notice selectByNoticeNo(int Notice_no) throws FindException;
	//게시글 상세보기(공통)
	void insert(int einfo) throws AddException;
	//게시글 작성(어드민)
	void update(int einfo) throws ModifyException;
	//게시글 수정(어드민)
	void deleteByNoticeNo(int Notice_no) throws DeleteException;
	//게시글 삭제(어드민)
}