package com.talkabout.dao;

import java.util.List;
import java.util.Map;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public interface DebateDAO {
	/**
	 * 
	 * @param endRow 
	 * @param startRow 
	 * @return 전체 토론 리스 가져오기
	 */
	List<Debate> selectAll(int startRow, int endRow) throws FindException;
	List<Debate> selectAll(String word,int startRow, int endRow) throws FindException;
	/**
	 * 
	 * @param debate_no 토론 번호
	 * @return 선택한 debate 가져오기
	 */
	Map<String, Object> selectByNo(int debate_no) throws FindException;
	/**
	 * 
	 * @param deb Debate테이블
	 * @param dd Detail 테이블
	 */
	
	List<Debate> selectSearch(String column, String keyword);
	
	void insertDebate(Debate deb, String discuss1, String Discuss2) throws AddException;
	/**
	 * 
	 * @param deb 토론 수정하기
	 */
	void updateDebateAll(Debate deb) throws ModifyException;
	
	/**
	 * 
	 * @param deb 토론 수정하기
	 */
	void updateDebateAll(Debate deb, List<DebateDetail> dd, String discuss1,String discuss2) throws ModifyException;
	//스프링 프로젝트용 업데이트
	void updateDebate(Debate deb, DebateDetail dd1,DebateDetail dd2) throws ModifyException;
	/**
	 * 
	 * @param status 토론 상태 업데이트
	 */
	void updateStatus(Debate status);
	
	/**
	 * 
	 * @param start_date 시작시간 입력
	 */
	void updateStartdate(Debate deb);
	/**
	 * 
	 * @param end_date 종료시간 입력
	 */
	void updateEnddate(Debate end_date);
	/**
	 * 
	 * @param 토론자 등록.
	 */
	void updateDiscussor(Debate deb_no,DebateDetail dd, Member m) throws ModifyException;
	/**
	 * 
	 * @param deb_no 토론 삭제
	 */
	void deleteDebate(String deb_no) throws DeleteException;
	
	/*
	 * 토론자 취소
	 */
	void cancleDiscussor(Debate deb_no, DebateDetail dd, Member m) throws ModifyException;
	/*
	 * 검색기능
	 */
	
     int lastRow();
     
     int searchLastRow(String word);

     /**
      * 토론자 1,2 선정시 토론 승인을 위한 체크 메서드
      * @param deb_no
      * @return
      * @throws FindException
      */
     List<DebateDetail> checkDeb(int deb_no) throws FindException;

}
