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
	public List<Notice> NoticeTypeSearch(String type, String keyword) throws FindException{
		return dao.selectSearch(type, keyword);
	}
	public List<Notice> NoticeList() throws FindException{
		return dao.selectAll();
	}
	public void WriteNotice(Notice noti) throws AddException{
		dao.insertNotice(noti);
	}
	public void EditNotice(Notice noti) throws ModifyException{
		dao.updateNotice(noti);
	}
	public void DeleteNotice(int noti_no) throws DeleteException{
		dao.deleteNotice(noti_no);
	}
	//조회수
//	public void NoticeViews(int Notice_no) throws ModifyException{
//		dao.updateCount(Notice_no);
//	}
	public List<Notice> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Notice> findAll() { //추가
		// TODO Auto-generated method stub
		return dao.selectAll();
	}
	public int lastRow() {//총 게시물 개수 구하기
		return dao.lastRow();
	}
	public void pageNum(int page) {
		dao.pageNum(page);
	}
	public void pageSize(int size) {
		dao.pageSize(size);
	}
	public Notice selectByNo(int notice_no) throws FindException {
		return dao.selectByNo(notice_no);
	}

}