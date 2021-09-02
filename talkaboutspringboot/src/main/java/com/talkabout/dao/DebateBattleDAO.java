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
	
	/*
	 * Audience
	 */
	
	/**
	 * @return 전체 관중 리스트 출력
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
	 * @param 토론번호(deb_no), 회원번호(mem_no)
	 */
	void insertVote(int deb_no, int mem_no) throws AddException;
	
	/**
	 * @param audience
	 */
	void updateVote(Audience audience)  throws ModifyException;
	
	
	/*
	 * Debate
	 */
	
	/*
	 * DebateDetail
	 */
}
