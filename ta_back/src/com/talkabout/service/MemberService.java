package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.Member;

public class MemberService {
	private MemberDAO dao;
	private static MemberService service;
	public static String envProp; //
	private MemberService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("loginDAO");
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
	public void signUp(Member l) {
		dao.signUp(l);
	}

}
