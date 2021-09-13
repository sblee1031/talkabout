package com.talkabout.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.BoardLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
@Repository
public class BoardLikeDAOOracle implements BoardLikeDAO{
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public void insert (BoardLike BL) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.talkabout.dto.BoardLikeMapper.insert", BL);
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
	public void deleteByBoardLikeNo(int boardlike_no) throws DeleteException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.talkabout.dto.BoardLikeMapper.deleteByBoardLikeNo", boardlike_no);
			
		}catch(Exception e){
			throw new DeleteException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
}
