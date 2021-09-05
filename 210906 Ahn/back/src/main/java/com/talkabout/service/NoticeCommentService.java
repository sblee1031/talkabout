package com.talkabout.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.NoticeCommentDAO;
import com.talkabout.dto.NoticeComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

@Service
public class NoticeCommentService {
	
	@Autowired
	private NoticeCommentDAO ncDAO;
	
	/**	전체 리스트 출력 */
	public List<NoticeComment> findAll(int com_notice) throws FindException{
		return ncDAO.selectAll(com_notice);
	}
	
	/**	리스트 1개 출력 by com_no */
	public NoticeComment findByNo(int com_no) throws FindException{
		return ncDAO.selectOne(com_no);
	}
	
	/**	Notice 댓글 생성 */
	public void insertNC(NoticeComment nc) throws AddException{
		ncDAO.insertComment(nc);
	}
	
	/**	Notice 댓글 수정 */
	public void updateNC(NoticeComment nc) throws ModifyException{
		ncDAO.updateComment(nc);
	}
	
	/**	Notice 댓글 삭제  */
	public void deleteNC(int com_no) throws DeleteException{
		ncDAO.deleteComment(com_no);
	}
}
