package com.talkabout.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import com.talkabout.dao.NoticeDAO;
import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public class NoticeService {
	private NoticeDAO dao;
	private static NoticeService service;
	public static String envProp;//
	private NoticeService() {
		Properties env = new Properties();
		//env.load(new FileInputStream("classes.prop"));
		try {
		env.load(new FileInputStream(envProp));
		String className = env.getProperty("noticeDAO");
		Class c = Class.forName(className);
		dao = (NoticeDAO)c.newInstance();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	public static NoticeService getInstance() {
		if(service == null) {
			service = new NoticeService();
		}
		return service;
	}
	public List<Notice> NoticeTypeSearch(String type, String content) throws FindException{
		return dao.noticeSearch(type, content);
	}
	public List<Notice> NoticeList() throws FindException{
		return dao.selectAll();
	}
	public void WriteNotice(Notice n) throws AddException{
		//dao.insert(n);
	}
	public void EditNotice(Notice einfo) throws ModifyException{
		//dao.update(einfo);
	}
	public void DeleteNotice(int Notice_no) throws DeleteException{
		dao.deleteByNoticeNo(Notice_no);
	}
	public void NoticeViews(int Notice_no) throws ModifyException{
		dao.updateCount(Notice_no);
	}
}