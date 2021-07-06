package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.talkabout.dao.DebateCommentDAO;
import com.talkabout.dao.DebateLikeDAO;
import com.talkabout.dto.DebateLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;

public class DebateLikeService {
	private DebateLikeDAO dao;
	public static String envProp;
	private static DebateLikeService service;
	public static DebateLikeService getInstance() {
		if(service == null) {
			service = new DebateLikeService();
		}
		return service;
		
	}

	private DebateLikeService() {
		Properties env = new Properties();
		try {
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("DebateLikeDAO");
			Class c = Class.forName(className);
			dao=(DebateLikeDAO)c.newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void DLinsert(DebateLike DL) throws AddException{
		dao.insert(DL);
	}
	public void DLdeleteByDebatelikeNo(int deblike_no) throws DeleteException{
		dao.deleteByDebatelikeNo(deblike_no);
	}
	
}
