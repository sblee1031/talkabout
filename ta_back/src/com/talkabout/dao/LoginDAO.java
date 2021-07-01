package com.talkabout.dao;

import com.talkabout.dto.Login;
import com.talkabout.exception.FindException;

public interface LoginDAO {
	/**
	 * 
	 * @param Login 
	 * @throws AddException
	 */
	void signUp(Login l) ;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws FindException
	 */
	Login selectById(int no) throws FindException;
	/**
	 * 
	 * @param c
	 * @throws ModifyException
	 */
	void update(Login c) ;
}
