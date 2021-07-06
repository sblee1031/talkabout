package com.talkabout.dao;

import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;

public interface LoginDAO {
	/**
	 * 
	 * @param Login 
	 * @throws AddException
	 */
	void signUp(Member l) ;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws FindException
	 */
	Member selectById(int no) throws FindException;
	/**
	 * 
	 * @param c
	 * @throws ModifyException
	 */
	void update(Member c) ;
}
