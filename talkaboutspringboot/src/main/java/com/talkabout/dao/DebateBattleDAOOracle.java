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
	public Map<String, String> selectCnt(int deb_no, int vote_no) throws FindException {
		SqlSession session = null;
		Map<String, String> result = new HashMap<String, String>();
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("deb_no", deb_no);
		map.put("vote_no", vote_no);
		try {
			session = sessionFactory.openSession();
			result = session.selectOne("com.talkabout.dto.DebateBattleMapper.selectCnt", map);
			return result;
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
		SqlSession session = null;
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("deb_no", deb_no);
		map.put("mem_no", mem_no);
		try {
			session = sessionFactory.openSession();
			session.insert("com.talkabout.dto.DebateBattleMapper.insertVote", map);
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
	public void updateVote(int audi_no, int vote_no) throws ModifyException {
		SqlSession session = null;
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("audi_no", audi_no);
		map.put("vote_no", vote_no);
		try {
			session = sessionFactory.openSession();
			session.update("com.talkabout.dto.DebateBattleMapper.updateVote", map);
			session.commit();
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * Debate
	 */

	@Override
	public List<Debate> selectAllDeb() throws FindException {
		List<Debate> list = new ArrayList<>();
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			list = session.selectList("com.talkabout.dto.DebateBattleMapper.selectAllDeb");
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
	public Debate selectOneByDebNo(int deb_no) throws FindException {
		
		SqlSession session = null;
		Debate debate = new Debate();
		try {
			session = sessionFactory.openSession();
			debate = session.selectOne("com.talkabout.dto.DebateBattleMapper.selectByDebNo", deb_no);
			return debate;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateStatus(Debate deb) throws ModifyException {
		SqlSession session = null;
		
		try {
			session = sessionFactory.openSession();
			session.update("com.talkabout.dto.DebateBattleMapper.updateStatus", deb);
			session.commit();
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateEndDate(Debate deb) throws ModifyException {
		SqlSession session = null;
		
		try {
			session = sessionFactory.openSession();
			session.update("com.talkabout.dto.DebateBattleMapper.updateEndDate", deb);
			session.commit();
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * DebateDetail
	 */

	@Override
	public List<DebateDetail> selectTwoByDeb(int deb_no) throws FindException {
		SqlSession session = null;
		List<DebateDetail> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			list = session.selectList("com.talkabout.dto.DebateBattleMapper.selectTwoByDeb", deb_no);
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
	public DebateDetail selectByDetailNo(int detail_no) throws FindException{
		SqlSession session = null;
		DebateDetail dd = new DebateDetail();
		try {
			session = sessionFactory.openSession();
			dd = session.selectOne("com.talkabout.dto.DebateBattleMapper.selectByDetailNo", detail_no);
			return dd;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public DebateDetail selectOneByTwo(int deb_no, int discussor) throws FindException {
		SqlSession session = null;
		DebateDetail dd = new DebateDetail();
		HashMap<String, Integer> map = new HashMap<>();
		map.put("deb_no", deb_no);
		map.put("discussor", discussor);
		try {
			session = sessionFactory.openSession();
			dd = session.selectOne("com.talkabout.dto.DebateBattleMapper.selectDetailByDeb", map);
			return dd;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateIntime(DebateDetail dd) throws ModifyException {
		SqlSession session = null;
		
		try {
			session = sessionFactory.openSession();
			session.update("com.talkabout.dto.DebateBattleMapper.updateIntime", dd);
			session.commit();
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateEvidence(DebateDetail dd, int evi_no) throws ModifyException {
		SqlSession session = null;
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("dd", dd);
		map.put("evi_no", evi_no);
		try {
			session = sessionFactory.openSession();
			session.update("com.talkabout.dto.DebateBattleMapper.updateEvidence", map);
			session.commit();
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	

}
