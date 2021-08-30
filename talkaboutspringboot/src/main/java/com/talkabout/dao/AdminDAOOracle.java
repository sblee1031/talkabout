package com.talkabout.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.Admin;
import com.talkabout.dto.Debate;
import com.talkabout.dto.Notice;
import com.talkabout.exception.FindException;
@Repository
public class AdminDAOOracle implements AdminDAO{
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Override
	public Admin selectByNo(int admin_no) throws FindException {
		SqlSession session = null;
		Admin ad = new Admin();
		try {
			session = sqlSessionFactory.openSession();
			HashMap<Integer, Integer> map = new HashMap<>();
//			ad = session.select
		}catch(Exception e) {
			System.out.println(e.getMessage());
//			throw new FindException(e.getMessage());
		}finally {
			if(session !=null) {
				session.close();
			}
		}
		return null;
	}

	@Override
	public Admin AdLogin(String id, String pwd) throws FindException {
		SqlSession session = null;
		Admin loginAdmin = new Admin();
		try {
			Admin ad = new Admin();
			ad.setAdmin_id(id);
			ad.setAdmin_pwd(pwd);
			session = sqlSessionFactory.openSession();
			HashMap<Integer, Integer> map = new HashMap<>();
			loginAdmin = session.selectOne("com.talkabout.dto.AdminMapper.login", ad);
		}catch(Exception e) {
			System.out.println(e.getMessage());
//			throw new FindException(e.getMessage());
		}finally {
			if(session !=null) {
				session.close();
			}
		}
		return loginAdmin;
	}
	@Override
	public int noticeLastRow() {
		int lastrow =0; 
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			lastrow = session.selectOne("com.talkabout.dto.AdminMapper.noticeLastRow");
//			System.out.println("게시물 총"+lastrow);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			//throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			//DB연결 해제
			if(session !=null) {
				session.close();
			}
		}
		return lastrow;
	}
	
	@Override
	public List<Notice> noticeFindAll(int startRow, int endRow) throws FindException {
		List<Notice> list = new ArrayList<>();
		SqlSession session = null;
		
		 HashMap<String, Integer> map = new HashMap<>();
		 map.put("num_start_row", startRow);
		 map.put("num_end_row", endRow);
		try {
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			list = session.selectList("com.talkabout.dto.AdminMapper.noticeList",map);
		//	System.out.println(list);
		}catch (Exception e) {
			//System.out.println(e.getMessage());
			throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			//DB연결 해제
			if(session !=null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Notice> noticeFindAll(String word, int startRow, int endRow) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int noticeSearchLastRow(String word) {
		// TODO Auto-generated method stub
		return 0;
	}
}
