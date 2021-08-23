package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	
	public void signUp(Member m) throws AddException {
		dao.createMember(m);
	}
	public Member memberCheck(String social_no) throws FindException {
		return dao.selectByNo(social_no);
	}
	public Member memberInfo(int mem_no) throws FindException {
		return dao.selectByNo(mem_no);
	}
	
	public void updateNick(Member m) throws FindException{
		dao.updateMember(m);
	}
	
	public int chkNick(Member m) throws FindException{
		return dao.selectNick(m);
	}
	
	public void leaveMember(Member m) throws FindException{
		dao.deleteMember(m);
	}
	
	public Member searchNick(Member m) throws FindException{//닉네임 검색시, 멤버 번호 가져옴
		return dao.searchNick(m);
	}

}
