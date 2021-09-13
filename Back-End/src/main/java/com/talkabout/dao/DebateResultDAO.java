package com.talkabout.dao;

import java.util.List;
import java.util.Map;

import com.talkabout.dto.DebateSungho;
import com.talkabout.dto.DebateComment;
import com.talkabout.dto.DebateCommentSungho;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.RemoveException;

public interface DebateResultDAO {
	/**
	 * 토론된 토론의 리스트를 반환합니다
	 * @return 
	 * @throws FindException
	 */
	public List<DebateSungho> selectAll() throws FindException;
	
	/**
	 * 한개의 게시판을 반환합니다
	 * @param Debatenum
	 * @return
	 * @throws FindException
	 */
	public DebateSungho selectByNum(int DebateNum) throws FindException;
	
	/**
	 * 
	 * @param 게시판 번호 가져와
	 * @return 보드 상세페이지 반환해줌
	 * @throws FindException
	 */
	public List<DebateDetail> detailselectByNo(int detail_DebateNum)throws FindException;
	/**
	 * 
	 * @param Member_num (debate_wirter가 멤버 번호인데 그거 여기다 넣으면됨)
	 * @return	 그럼 멤버정보 가져올수 있음
	 * @throws FindException
	 */
	public Member Memberselect(int Member_num)throws FindException;
	
	/**
	 * 토론번호에 해당하는 토론정보를 반환한다 토론정보에는 debate( 토론제안자, 토론기본정보)와 debatedetail(근거, 주장,토론자..),
	 * @param debate_no
	 * @return
	 * @throws FindException
	 */
	public DebateSungho sunghoDebateSelectByNo(int debate_no)throws FindException;
	/**
	 * 페이징처리
	 * @param cri
	 * @return
	 * @throws FindException
	 */
	public List<DebateSungho> getlistWithPaging(DebateSungho cri)throws FindException;
	/**
	 * 게시물 번호로 댓글 리스트 가져오기
	 * @param 해당게시물번호
	 * @return
	 * @throws FindException
	 */
	public List<DebateCommentSungho> GetListComment(int deb_no)throws FindException;
	/**
	 * 댓글입력
	 * @param cri
	 * @throws FindException
	 */
	public void insertComment(DebateCommentSungho cm)throws AddException;

	/**
	 * 댓글삭제
	 * @param com_no 댓글번호로 삭제
	 * @throws RemoveException
	 */
	public void deleteComment(int com_no)throws RemoveException;
	
	/**
	 * 투표결과 가져오는거
	 * @param deb_no 
	 * @return
	 * @throws FindException
	 */
	public int GetVoteResult_left(int deb_no)throws FindException;
	public int GetVoteResult_right(int deb_no)throws FindException;
	public int GetVoteResult_middle(int deb_no)throws FindException;
	/**
	 * 단어검색
	 * @param word 글자로 검색
	 * @return
	 * @throws FindException
	 */
	public List<DebateSungho> Getlistbyword(String word)throws FindException;
}