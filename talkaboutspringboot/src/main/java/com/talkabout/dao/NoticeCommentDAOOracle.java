package com.talkabout.dao;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.Notice;
import com.talkabout.dto.NoticeComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

@Repository
public class NoticeCommentDAOOracle implements NoticeCommentDAO {
	
	@Autowired
	private SqlSessionFactory sessionFactory;

	public List<NoticeComment> selectAll(int com_notice) throws FindException {
		List<NoticeComment> list = new ArrayList<>();
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			list = session.selectList("com.talkabout.dto.NoticeCommentMapper.selectAll", com_notice);
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	public NoticeComment selectOne(int com_no) throws FindException {
		SqlSession session = null;
		NoticeComment notice_comment = new NoticeComment();
		try {
			session = sessionFactory.openSession();
			notice_comment = session.selectOne("com.talkabout.dto.NoticeCommentMapper.selectOne", com_no);
			return notice_comment;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	public void insertComment(NoticeComment nc) throws AddException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.talkabout.dto.NoticeCommentMapper.insertNC", nc);
			session.commit();
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
	}

	public void updateComment(NoticeComment nc) throws ModifyException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.update("com.talkabout.dto.NoticeCommentMapper.updateNC", nc);
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}

	public void deleteComment(int com_no) throws DeleteException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.delete("com.talkabout.dto.NoticeCommentMapper.deleteNC", com_no);
		}catch(Exception e){
			throw new DeleteException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}
	
	
}
