package com.talkabout.dao;

import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;

public interface MemberDAO {
	/**
	 * 
	 * @param Member 
	 * @throws AddException
	 */
	void createMember(Member m) throws AddException ;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws FindException
	 */
	Member selectByNo(String socialNo) throws FindException;
	
	Member selectByNo(int member_no) throws FindException;
	/**
	 * 
	 * @param c
	 * @throws ModifyException
	 */
	void updateMember(Member m) throws FindException;
	
	int selectNick(Member m) throws FindException;
	
	void deleteMember(Member m)throws FindException;
	
	Member searchNick(Member m)throws FindException;
	
}
