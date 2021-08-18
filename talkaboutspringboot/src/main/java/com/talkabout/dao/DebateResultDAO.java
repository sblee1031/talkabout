package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.exception.FindException;

public interface DebateResultDAO {
	/**
	 * 토론된 토론의 리스트를 반환합니다
	 * @return 
	 * @throws FindException
	 */
	public List<Debate> selectAll() throws FindException;
	
	/**
	 * 한개의 게시판을 반환합니다
	 * @param Debatenum
	 * @return
	 * @throws FindException
	 */
	public Debate selectByNum(int DebateNum) throws FindException;
	
	/**
	 * 
	 * @param 게시판 번호 가져와
	 * @return 보드 상세페이지 반환해줌
	 * @throws FindException
	 */
	public List<DebateDetail> detailselectByNo(int detail_DebateNum)throws FindException;
}
