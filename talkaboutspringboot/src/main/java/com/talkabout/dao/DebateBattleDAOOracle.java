package com.talkabout.dao;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.Audience;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

@Repository
public class DebateBattleDAOOracle implements DebateBattleDAO {
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	/**
	 * Audience
	 */
	@Override
	public List<Audience> selectAllAud() throws FindException {
		List<Audience> list = new ArrayList<>();
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			list = session.selectList("com.talkabout.dto.DebateBattleMapper.selectAllAud");
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
	public Audience selectByAudNo(int audi_no) throws FindException {
		SqlSession session = null;
		Audience audience = new Audience();
		try {
			session = sessionFactory.openSession();
			audience = session.selectOne("com.talkabout.dto.DebateBattleMapper.selectByAudNo", audi_no);
			return audience;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public Audience selectByDeb(int deb_no, int mem_no) throws FindException {
		SqlSession session = null;
		Audience audience = new Audience();
		HashMap<String, Integer> map = new HashMap<>();
		map.put("deb_no", deb_no);
		map.put("mem_no", mem_no);
		try {
			session = sessionFactory.openSession();
			audience = session.selectOne("com.talkabout.dto.DebateBattleMapper.selectByDeb", map);
			return audience;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void insertVote(int deb_no, int mem_no) throws AddException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVote(Audience audience) throws ModifyException {
		// TODO Auto-generated method stub
		
	}

}
