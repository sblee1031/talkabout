package com.talkabout.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

@Repository
public class NoticeDAOOracle implements NoticeDAO {
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public List<Notice> selectAll() throws FindException {
		List<Notice> list = new ArrayList<>();
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			list = session.selectList("com.talkabout.dto.NoticeMapper.selectList");
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	public Notice selectOne(int notice_no) throws FindException {
		SqlSession session = null;
		Notice notice = new Notice();
		try {
			session = sessionFactory.openSession();
			notice = session.selectOne("com.talkabout.dto.NoticeMapper.selectOne", notice_no);
			return notice;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Notice> selectSearch(String word) throws FindException {
		List<Notice> list = new ArrayList<>();
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			list = session.selectList("com.talkabout.dto.NoticeMapper.selectSearch", word);
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void insertNotice(Notice notice) throws AddException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.talkabout.dto.NoticeMapper.insertNotice", notice);
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

	@Override
	public void updateNotice(Notice notice) throws ModifyException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.update("com.talkabout.dto.NoticeMapper.updateNotice",notice);
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	public void updateCount(Notice notice) throws ModifyException {
		
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.update("com.talkabout.dto.NoticeMapper.updateViews",notice);
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	public void deleteNotice(int notice_no) throws DeleteException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.delete("com.talkabout.dto.NoticeMapper.deleteNotice", notice_no);
		}catch(Exception e){
			throw new DeleteException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
}
