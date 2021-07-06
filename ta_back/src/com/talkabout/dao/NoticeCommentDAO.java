package com.talkabout.dao;

import java.util.List;

import com.talkabout.dto.NoticeComment;
import com.talkabout.exception.FindException;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.ModifyException;

public interface NoticeCommentDAO {

	public List<NoticeComment>myNoticeComSearch(int Notice_no, int com_mem) throws FindException;
	//내 댓글 검색
	public List<NoticeComment> selectAll() throws FindException;
	//댓글 리스트 보기
	void insertNoticeComment(int NoticeCommnet_NC) throws AddException;
	//댓글 작성(공통)
	void updateNoticeComment(int NoticeCommnet_NC) throws ModifyException;
	//댓글 수정(공통)
	void deleteNoticeComment(int NoticeCommnet_no) throws DeleteException;
	//댓글 삭제(유저)
	void deleteByNoAdmin(int NoticeCommnet_no) throws DeleteException;
	//댓글 삭제(어드민)
}
