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
	

	public Admin adminInfo(int admin_no) throws FindException {
		return dao.selectByNo(admin_no);
	}
	public Admin adLogin(String id, String pwd) throws FindException {
		return dao.AdLogin(id, pwd);
	}


}