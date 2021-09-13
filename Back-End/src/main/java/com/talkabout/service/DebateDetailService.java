package com.talkabout.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.talkabout.dao.DebateDetailDAO;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.exception.FindException;

public class DebateDetailService {
	private DebateDetailDAO dao;
	private static DebateDetailService service;
	public static String envProp;
	
	public DebateDetailService() {
		Properties env = new Properties();
		try {
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("debateDetailDAO");
			Class c = Class.forName(className);
			dao = (DebateDetailDAO)c.newInstance(); //jvm에 로드, 객체생성
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

	public static DebateDetailService getInstance() {
		if (service == null) {
			service = new DebateDetailService();
		}
		return service;
	}
	
	public List<DebateDetail> findAll() throws FindException {

		return dao.selectAll();
	}
	
	public DebateDetail findByDeb(Debate d, int discussor) {
		return dao.selectOne(d.getDebate_no(), discussor);
	}

	
	// 주장A,B 가져오기
	public List<DebateDetail> findByDebNo(int deb_no) {
		return dao.selectByNo(deb_no);
	}
	
	public void addDetail(DebateDetail dd) {
		dao.insert(dd);
	}
	
	public void setDiscussor(DebateDetail dd) {
		dao.updateDiscussor(dd);
	}
	
	public void setIntime(DebateDetail dd) {
		dao.updateIntime(dd);
	}
	
	public void setEviOne(DebateDetail dd) {
		dao.updateEvidence(dd, 1);
	}
	
	public void setEviTwo(DebateDetail dd) {
		dao.updateEvidence(dd, 2);
	}
	
	public void setEviThree(DebateDetail dd) {
		dao.updateEvidence(dd, 3);
	}
}
