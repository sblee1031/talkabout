package com.talkabout.dao;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.Board;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

@Repository
public class BoardDAOOracle implements BoardDAO{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	//DB커넥트
	
	public int num_page_size;
	public int num_page_no;
	public int lastrow;
	
	public void pageSize(int size) {
		this.num_page_size = size;
	}
	//마지막 row 가져오기
	public int lastRow() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			lastrow = session.selectOne("com.talkbabout.dto.BoardMapper.lastRow");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return lastrow;
	}
	//자유게시판 검색
	@Override
	public List<Board> boardSearch(String type, String contents) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			HashMap<String, String> map = new HashMap<>();
			map.put("type", type);
			map.put("contents", contents);
			return session.selectList("com.talkabout.dto.BoardMapper.selectByBoardType", map);
		}catch(Exception e) {
			throw new FindException(e.getMessage());
			
		}finally {
			if(session !=null) {
				session.close();
			}
		}
	}
	//자유게시판 리스트
	@Override
	public List<Board> selectAll() throws FindException {
		SqlSession session = null;
		try {
		session = sqlSessionFactory.openSession();
		List<Board> list = session.selectList("com.talkabout.dto.BoardMapper.selectAll");
		return list;
		}
		catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	//자유게시판 상세보기
	@Override
	public Board selectByBoardNo(int board_no) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			//Board b = 
				return session.selectOne("com.talkabout.dto.BoardMapper.selectByBoardNo", board_no);
//			if(b==null) {
//				throw new FindException("게시글이 없습니다." + board_no);
//			}
//			return b;
		} catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage() + board_no + "왜안돼냐?");
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	//자유게시판 작성 DB연결 및 커밋
	@Override
	public void insert(Board binfo) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.talkabout.dto.BoardMapper.insert", binfo);
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
	
	//자유게시판 수정
	@Override
	public void update(int board_no) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.talkabout.dto.BoardMapper.update",board_no);
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}
	
	//게시물 조회수
	@Override
	public void updateCount(int Board_no) throws ModifyException {
		
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.talkabout.dto.BoardMapper.updateCount",Board_no);
		} catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	//자유게시판 삭제
	@Override
	public void deleteByBoardNo(int Board_no) throws DeleteException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.talkabout.dto.BoardMapper.deleteByBoardNo", Board_no);
			
		}catch(Exception e){
			throw new DeleteException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	
}
