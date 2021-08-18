package com.talkabout.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.Admin;
import com.talkabout.exception.FindException;

@Repository
public class AdminDAOOracle implements AdminDAO {
	
	@Autowired
	private SqlSessionFactory sessionFactory;

	@Override
	public Admin selectByNo(int admin_no) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin AdLogin(String id, String pwd) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			HashMap<String, String> map = new HashMap<>();
			map.put("id", id);
			map.put("pwd", pwd);
			return session.selectOne("com.talkabout.dto.AdminMapper.AdLogin",map); //이부분 잘 모르겠음
		}catch(Exception e) {
			throw new FindException(e.getMessage() + "올바르지 않은 ID입니다." + id + pwd);
		}finally {
			if(session != null) session.close();
		}
	}

}
