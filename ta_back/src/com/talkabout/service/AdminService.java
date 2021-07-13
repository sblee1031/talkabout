package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.talkabout.dao.AdminDAO;
import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.Admin;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;

public class AdminService {
	private AdminDAO dao;
	private static AdminService service;
	public static String envProp; //
	private AdminService() {
		Properties env = new Properties();
		try {
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("adminDAO");
			Class c = Class.forName(className);
			dao = (AdminDAO)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static AdminService getInstance() {
		if(service == null) {
			service = new AdminService();
		}
		return service;
	}
	
//	public void signUp(Member m) throws AddException {
//		dao.createMember(m);
//	}
//	public Member memberCheck(String social_no) throws FindException {
//		return dao.selectByNo(social_no);
//	}
	public Admin adminInfo(int admin_no) throws FindException {
		return dao.selectByNo(admin_no);
	}
	///////
	public Admin adminInfo(Admin notice_admin) {
		// TODO Auto-generated method stub
		return null;
	}
	////////
	
//	public void updateNick(Member m) throws FindException{
//		dao.updateMember(m);
//	}
//	
//	public Boolean chkNick(Member m) throws FindException{
//		return dao.selectNick(m);
//	}
//	
//	public void leaveMember(Member m) throws FindException{
//		dao.deleteMember(m);
//	}
//	
//	public Member searchNick(Member m) throws FindException{//닉네임 검색시, 멤버 번호 가져옴
//		return dao.searchNick(m);
//	}

}