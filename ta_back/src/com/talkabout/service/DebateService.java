package com.talkabout.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import com.talkabout.dao.DebateDAO;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;

public class DebateService {
	private DebateDAO dao;
	private static DebateService service;
	public static String envProp; //
	private DebateService() {
		Properties env = new Properties();
		try {
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("debateDAO");
			Class c = Class.forName(className);
			dao = (DebateDAO)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DebateService getInstance() {
		if(service == null) {
			service = new DebateService();
		}
		return service;
	}
	
	public List<Debate> findAll() {
		return dao.selectAll();
	}
	public Debate findByNo(int deb_no) {
		return dao.selectByNo(deb_no);
	}
	
	public void addDebate(Debate deb, DebateDetail dd, String discuss1, String discuss2) {
		dao.insertDebate(deb, dd,discuss1,discuss2);
	}
	
	public void setStartDate(Debate deb) {
		dao.updateStartdate(deb);
	}
	
	public void setEndDate(Debate deb) {
		dao.updateEnddate(deb);
	}
	
	public List<Debate> selectSearch(String column, String keyword){
		return dao.selectSearch(column, keyword);
	}
}
