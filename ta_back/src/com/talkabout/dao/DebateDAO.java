package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.ModifyException;

public interface DebateDAO {
	/**
	 * 
	 * @return 전체 토론 리스 가져오기
	 */
	List<Debate> selectAll();
	/**
	 * 
	 * @param debate_no 토론 번호
	 * @return 선택한 debate 가져오기
	 */
	Debate selectByNo(int debate_no);
	/**
	 * 
	 * @param deb Debate테이블
	 * @param dd Detail 테이블
	 */
	void insertDebate(Debate deb, DebateDetail dd, String discuss1, String Discuss2);
	/**
	 * 
	 * @param deb 토론 수정하기
	 */
	void updateDebateAll(Debate deb) throws ModifyException;
	/**
	 * 
	 * @param status 토론 상태 업데이트
	 */
	void updateStatus(Debate status);
	
	/**
	 * 
	 * @param start_date 시작시간 입력
	 */
	void updateSartdate(Debate start_date);
	/**
	 * 
	 * @param end_date 종료시간 입력
	 */
	void updateEnddate(Debate end_date);
	/**
	 * 
	 * @param 토론자 등록.
	 */
	void updateDiscussor(Debate deb_no,DebateDetail dd, Member m);
	/**
	 * 
	 * @param deb_no 토론 삭제
	 */
	void deleteDebate(Debate deb_no);
<<<<<<< HEAD
	


=======
	/*
	 * 토론자 취소
	 */
	void cancleDiscussor(Debate deb_no, DebateDetail dd, Member m);
	/*
	 * 검색기능
	 */
	List<Debate> selectSearch(String column, String keyword);
	
     int lastRow();
     
     void pageNum(int page);
     
     void pageSize(int size);
>>>>>>> 5ac37c42a178fda003efc468c67fd7af6320ff58
}
