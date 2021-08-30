package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.Board;
import com.talkabout.dto.BoardLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public interface BoardDAO {
	
	/**
	 * 게시글 검색
	 * @return List<Board>
	 * @throws FindException
	 */
	public List<Board> boardSearch(String type, String content) throws FindException;
	
	/**
	 * 게시글 리스트 보기
	 * @return List<Board>
	 * @throws FindException
	 */
	public List<Board> selectAll() throws FindException;
	
	/**
	 * 게시글 상세보기
	 * @param board_no
	 * @return
	 * @throws FindException
	 */
	public Board selectByBoardNo(int board_no) throws FindException;
	
	/**
	 * 게시글 작성
	 * @param b
	 * @throws AddException
	 */
	void insert(Board b) throws AddException;
	
	/**
	 * 게시글 수정
	 * @param b
	 * @throws ModifyException
	 */
	void update(int board_no) throws ModifyException;
	
	/**
	 * 게시글 조회수 카운트
	 * @param Board_no
	 * @throws ModifyException
	 */
	void updateCount(int Board_no) throws ModifyException;
	
	/**
	 * 게시글 삭제
	 * @param Board_no
	 * @throws DeleteException
	 */
	void deleteByBoardNo(int Board_no) throws DeleteException;
	
}
