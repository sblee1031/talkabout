package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.BoardComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public interface BoardCommentDAO {
	
	/**
	 * 내 댓글 검색
	 * @param Board_no
	 * @param com_mem
	 * @return List<BoardComment> 
	 * @throws FindException
	 */
	public List<BoardComment> myBoardComSearch(int com_board, int com_mem) throws FindException;
	
	/**
	 * 댓글 리스트 보기
	 * @return List<BaordComment>
	 * @throws FindException
	 */
	public List<BoardComment> selectAll() throws FindException;
	
	/**
	 * 내 댓글 수정시 사용
	 * @param com_no
	 * @return
	 * @throws FindException
	 */
	public BoardComment selectByComNo (int com_no) throws FindException;
	/**
	 * 댓글 작성
	 * @param BC
	 * @throws AddException
	 */
	void insert(BoardComment bc) throws AddException;
	
	/**
	 * 댓글 수정
	 * @param BC
	 * @throws ModifyException
	 */
	void update(BoardComment bc) throws ModifyException;
	
	/**
	 * 댓글 삭제
	 * @param com_no
	 * @throws DeleteException
	 */
	void deleteByNo(int com_no) throws DeleteException;
}
