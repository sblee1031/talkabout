package com.talkabout.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;

@Repository
public class MemberDAOOracle implements MemberDAO{
	@Autowired
	//@Qualifier("Underscore")
	private SqlSessionFactory sqlSessionFactory;
	@Override
	public void createMember(Member m) throws AddException {
		SqlSession session = null;
		try {
			
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			session.insert("com.talkabout.dto.LoginMapper.signUp", m);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AddException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			if(session !=null) {
				session.close();
			}
		}
		
	}

	@Override
	public Member selectByNo(String socialNo) throws FindException {
		SqlSession session = null;
		try {
			
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			return session.selectOne("com.talkabout.dto.LoginMapper.selectByNo", socialNo);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			if(session !=null) {
				session.close();
			}
		}
	}

	@Override //필요없을듯
	public Member selectByNo(int member_no) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMember(Member m) throws FindException {
		SqlSession session = null;
		try {
			
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			session.update("com.talkabout.dto.LoginMapper.updateNick", m);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			if(session !=null) {
				session.close();
			}
		}
		
	}

	@Override
	public int selectNick(Member m) throws FindException {
		SqlSession session = null;
		try {
			
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			return session.selectOne("com.talkabout.dto.LoginMapper.checkNick", m);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			if(session !=null) {
				session.close();
			}
		}
	}

	@Override
	public void deleteMember(Member m) throws FindException {
		SqlSession session = null;
		try {
			
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			session.update("com.talkabout.dto.LoginMapper.signUp", m);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			if(session !=null) {
				session.close();
			}
		}
		
	}

	@Override //필요 없음
	public Member searchNick(Member m) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void main(String[] args) throws FindException {
		
		Member mem = new Member();
		MemberDAOOracle dao = new MemberDAOOracle();
		mem = dao.selectByNo("1775421132");
		System.out.println(mem.toString());
		
	}



}
