package com.talkabout.dao;

import java.util.List;

import java.util.Map;

import com.talkabout.dto.Audience;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public interface DebateBattleDAO {
	
	// Audience
	
	/**
	 * @return Audience 전체 리스트 출력
	 */
	List<Audience> selectAllAud() throws FindException;
	
	/**
	 * @param audi_no(PK)
	 * @return Audience 리스트 1개 출력
	 */
	Audience selectByAudNo(int audi_no) throws FindException;
	
	/**
	 * @param 토론번호(deb_no), 회원번호(mem_no)
	 * @return Audience 리스트 1개 출력
	 */
	Audience selectByDeb(int deb_no, int mem_no) throws FindException;
	
	/**
	 * @param 토론번호(deb_no), 투표번호(vote_no)
	 * @return vote, cnt
	 */
	Map<String, String> selectCnt(int deb_no, int vote_no) throws FindException;
	
	/**
	 * @param 토론번호(deb_no), 회원번호(mem_no)
	 */
	void insertVote(int deb_no, int mem_no) throws AddException;
	
	/**
	 * @param audi_no(PK), 투표번호(vote_no)
	 */
	void updateVote(int audi_no, int vote_no) throws ModifyException;
	
	
	// Debate
	
	/**
	 * @return Debate 전체 리스트 출력
	 */
	List<Debate> selectAllDeb() throws FindException;
	
	/**
	 * 
	 * @param 토론번호(deb_no)
	 * @return Debate 1개 출력
	 */
	Debate selectOneByDebNo(int deb_no) throws FindException;
	
	/**
	 * @param Debate 객체
	 */
	void updateStatus(Debate deb) throws ModifyException;
	
	/**
	 * @param Debate 객체
	 */
	void updateEndDate(Debate deb) throws ModifyException;
	
	// DebateDetail
	
	/**
	 * 
	 * @param 토론번호(deb_no)
	 * @return DebateDetail 객체 2개(주장 A, 주장 B)
	 */
	List<DebateDetail> selectTwoByDeb(int deb_no) throws FindException;
	
	/**
	 * 
	 * @param 상세번호(detail_no)
	 * @return DebateDetail 객체 1개
	 */
	DebateDetail selectByDetailNo(int detail_no) throws FindException;
	
	/**
	 * 
	 * @param 토론번호(deb_no), 회원번호(mem_no)
	 * @return DebateDetail 객체 1개(주장 1개)
	 */
	DebateDetail selectOneByTwo(int deb_no, int discussor) throws FindException;
	
	/**
	 * @param DebateDetail 객체
	 */
	void updateIntime(DebateDetail dd) throws ModifyException;
	
	/**
	 * @param DebateDetail 객체, 근거 번호(1,2,3)
	 */
	void updateEvidence(DebateDetail dd, int evi_no) throws ModifyException;
}
