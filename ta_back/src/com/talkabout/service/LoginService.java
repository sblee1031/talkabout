package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.talkabout.dao.LoginDAO;
import com.talkabout.dto.Login;

public class LoginService {
	private LoginDAO dao;
	private static LoginService service;
	public static String envProp; //
	private LoginService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("loginDAO");
			Class c = Class.forName(className);
			dao = (LoginDAO)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static LoginService getInstance() {
		if(service == null) {
			service = new LoginService();
		}
		return service;
	}
	public void signUp(Login l) {
		dao.signUp(l);
	}

}
