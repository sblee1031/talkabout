package com.talkabout.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.exception.FindException;
@Repository
public class DebateResultDAOOracle implements DebateResultDAO {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public List<Debate> selectAll() throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.talkabout.dto.DebateMapper.selectAll");
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
		
	}

	@Override
	public Debate selectByNum(int DebateNum) throws FindException {
		
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectOne("com.talkabout.dto.DebateMapper.selectByNum",DebateNum);
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}
	@Override
	public List<DebateDetail> detailselectByNo(int detail_DebateNum)throws FindException{ //보드넘버 이용해서 가져오자 상세정보들
		SqlSession session =  null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.talkabout.dto.DebateMapper.detailselectByNo",detail_DebateNum);
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	} 
	
}
