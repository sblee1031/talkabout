package com.talkabout.dao;

import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;

public interface MemberDAO {
	/**
	 * 
	 * @param Member 
	 * @throws AddException
	 */
	void signUp(Member c) ;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws FindException
	 */
	Member selectById(int member_no) throws FindException;
	/**
	 * 
	 * @param c
	 * @throws ModifyException
	 */
	void update(Member c) ;
}
