package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;

public class MemberService {
	private MemberDAO dao;
	private static MemberService service;
	public static String envProp; //
	private MemberService() {
		Properties env = new Properties();
		try {
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("memberDAO");
			Class c = Class.forName(className);
			dao = (MemberDAO)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MemberService getInstance() {
		if(service == null) {
			service = new MemberService();
		}
		return service;
	}
	
	public void signUp(Member m) throws AddException {
		dao.createMember(m);
	}
	public Member memberCheck(String social_no) throws FindException {
		return dao.selectByNo(social_no);
		
	}
	
	public void updateNick(Member m) throws FindException{
		dao.updateMember(m);
	}
	
	public Boolean chkNick(Member m) throws FindException{
		return dao.selectNick(m);
	}
	
	public void leaveMember(Member m) throws FindException{
		dao.deleteMember(m);
	}
	


}
