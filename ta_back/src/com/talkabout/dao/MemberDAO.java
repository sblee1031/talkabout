package com.talkabout.dao;

import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;

public interface MemberDAO {
	/**
	 * 
	 * @param Member 
	 * @throws AddException
	 */
	void createMember(Member m) ;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws FindException
	 */
	Member selectByNo(String social_no) throws FindException;
	/**
	 * 
	 * @param c
	 * @throws ModifyException
	 */
	void updateMember(Member m) ;
	
	void deleteMember(Member no);
	
}
