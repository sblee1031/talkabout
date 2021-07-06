package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.talkabout.dao.NoticeLikeDAO;
import com.talkabout.dto.NoticeLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;

public class NoticeLikeService {
	private NoticeLikeDAO dao;
	private static NoticeLikeService service;
	public static String envProp; //
	private NoticeLikeService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("noticelikeDAO");
			Class c = Class.forName(className);
			dao = (NoticeLikeDAO)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static NoticeLikeService getInstance() {
		if(service == null) {
			service = new NoticeLikeService();
		}
		return service;
	}
	//좋아요
	public void NoticeLike(NoticeLike NL) throws AddException{
		dao.insert(NL);
	}
	//좋아요 취소
	public void NoticeCancleLike(int noticelike_no) throws DeleteException{
		dao.deleteByNlNo(noticelike_no);
	}
}