package com.talkabout.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

import com.talkabout.dao.AudienceDAO;
import com.talkabout.dto.Audience;
import com.talkabout.exception.FindException;

public class AudienceService {
	private AudienceDAO dao;
	private static AudienceService service;
	public static String envProp;
	
	public AudienceService() {
		Properties env = new Properties();
		try {
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("audienceDAO");
			Class c = Class.forName(className);
			dao = (AudienceDAO)c.newInstance(); //jvm에 로드, 객체생성
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	public static AudienceService getInstance() {
		if (service == null) {
			service = new AudienceService();
		}
		return service;
	}
	
	public List<Audience> findAll() throws FindException{
		return dao.selectAll();
	}
	
	public Audience findByNo(int audi_no) throws FindException {
		return dao.selectByNo(audi_no);
	}
	
	public Audience findByDeb(int deb_no, int mem_no) {
		return dao.selectByDeb(deb_no, mem_no);
	}
	
	public void addVote(int deb_no, int mem_no) {
		dao.insertVote(deb_no, mem_no);
	}
	
	public void setVote(Audience a) {
		dao.updateVote(a);
	}
	
	public ArrayList<Integer> countVote(int deb_no) {
		return dao.selectByCnt(deb_no);
	}
}