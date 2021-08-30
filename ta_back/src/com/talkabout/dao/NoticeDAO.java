package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.Notice;
import com.talkabout.exception.FindException;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.ModifyException;

public interface NoticeDAO {

//	public List<Notice> noticeSearch() throws FindException;
	//검색 기능(공통)
	List<Notice> selectSearch(String type, String keyword);//공지/업데이트/이벤트 타입별 검색
	List<Notice> selectAll();//게시글 리스트보기
	Notice selectByNo(int notice_no) throws FindException;//게시글 상세보기
	void insertNotice(Notice noti) throws AddException;//게시글 작성
	void updateNotice(Notice noti) throws ModifyException;//게시글 수정
	void deleteNotice(int noti_no)throws DeleteException;//게시글 삭제
	//void updateCount(int Notice_no) throws ModifyException;//게시글 조회수
    int lastRow();//페이징
    void pageNum(int page);//페이징
    void pageSize(int size);//페이징
}