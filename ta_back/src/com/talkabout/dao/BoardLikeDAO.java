package com.talkabout.dao;

import com.talkabout.dto.BoardLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;

public interface BoardLikeDAO {
	
	/**
	 * 좋아요 추가
	 * @param blike
	 * @throws AddException
	 */
	int insertLike (int boardLike_no, int boardLike_board, int boardLike_mem) throws AddException;
	
	/**
	 * 좋아요 삭제
	 * @param boardlike_no
	 * @throws DeleteException
	 */
	void deleteByBoardLikeNo (int boardlike_no) throws DeleteException;
	
}
