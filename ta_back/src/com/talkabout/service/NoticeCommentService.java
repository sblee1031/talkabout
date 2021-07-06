package com.talkabout.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import com.talkabout.dao.NoticeCommentDAO;
import com.talkabout.dto.NoticeComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public class NoticeCommentService {
	private NoticeCommentDAO dao;
	private static NoticeCommentService service;
	public static String envProp; //
	private NoticeCommentService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("NoticeCommentDAO");
			Class c = Class.forName(className);
			dao = (NoticeCommentDAO)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static NoticeCommentService getInstance() {
		if(service == null) {
			service = new NoticeCommentService();
		}
		return service;
	}
	//작성된 댓글보기
	public List<NoticeComment> writedNcSearch(int com_notice, int com_mem) throws FindException{
		return dao.myNoticeComSearch(com_notice, com_mem);
	}
	//댓글보기
	public List<NoticeComment> selectAll() throws FindException{
		return dao.selectAll();
	}
	//댓글작성
	public void NcWrite(NoticeComment nc) throws AddException{
//		dao.insert(nc);
	}
	//댓글수정
	public void NcEdit(NoticeComment nc) throws ModifyException{
//		dao.update(nc);
	}
	//댓글삭제(사용자)
	public void NcDeleteUser(int com_notice, int com_mem) throws DeleteException{
		dao.deleteNoticeComment(com_notice);
	}
	//댓글삭제(어드민)
	public void NcDeleteAdmin(int NoticeCommnet_no) throws DeleteException{
		dao.deleteNoticeComment(NoticeCommnet_no);
	}
}
