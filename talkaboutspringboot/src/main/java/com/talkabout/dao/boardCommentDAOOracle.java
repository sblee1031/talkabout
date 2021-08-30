package com.talkabout.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.BoardComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;


@Repository
public class boardCommentDAOOracle implements BoardCommentDAO{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<BoardComment> myBoardComSearch(int com_board, int com_mem) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			HashMap<Integer, Integer> map = new HashMap<>();
			map.put(com_board, com_board);
			map.put(com_mem, com_mem);
			return session.selectList("com.talkabout.dto.BoardCommentMapper.myBoardComSearch", map);
			
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session !=null) {
				session.close();
			}
		}
	}

	@Override
	public List<BoardComment> selectAll(int com_board) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			return session.selectList("com.talkabout.dto.BoardCommentMapper.selectAll", com_board);
			
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session !=null)
				session.close();
		}
	}

	@Override
	public BoardComment selectByComNo(int com_no) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			return session.selectOne("com.talkabout.dto.BoardCommentMapper.selectByComNo",com_no);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void insert(BoardComment bcinfo) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.talkabout.dto.BoardCommentMapper.insert", bcinfo);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	
		
	@Override
	public void update(BoardComment bc) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.talkabout.dto.BoardComment.update", bc);
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
		
	@Override
	public void deleteByNo(int com_no) throws DeleteException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.talkabout.dto.BoardCommentMapper.deleteByNo",com_no);
		}catch(Exception e) {
			throw new DeleteException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		boardCommentDAOOracle dao = new boardCommentDAOOracle();
		
//		BoardComment boardcomment = dao.selectByComNo(6);
//		System.out.println(boardcomment.getCom_no());
//		System.out.println(boardcomment.getCom_board());
//		System.out.println(boardcomment.getCom_date());
//		System.out.println(boardcomment.getCom_contents());
//		System.out.println(boardcomment.getCom_mem());
		
		List<BoardComment> list =  new ArrayList<>();
		try {
			list = dao.selectAll(1);
			for(BoardComment bc : list) {
				System.out.print(bc.getCom_no() + " ");
				System.out.print(bc.getCom_board()+ " ");
				System.out.print(bc.getCom_date()+ " ");
				System.out.print(bc.getCom_contents()+ " ");
				System.out.println(bc.getCom_member()+ "//  ");
			}
		} catch(FindException e) {
			e.printStackTrace();
		}
		
//		int com_board = 2;
//		int com_mem = 1;
//		List<BoardComment> list = new ArrayList<>();
//			list = dao.myBoardComSearch(com_board, com_mem);
//			for(BoardComment bc : list) {
//				System.out.print(bc.getCom_no() + " ");
//				System.out.print(bc.getCom_board()+ " ");
//				System.out.print(bc.getCom_date()+ " ");
//				System.out.print(bc.getCom_contents()+ " ");
//				System.out.println(bc.getCom_mem()+ "//  ");
//			}
		
		
		
//		BoardComment boardcomment = new BoardComment();
//		boardcomment.setCom_no(1);
//		boardcomment.setCom_contents("바껴라 좀");
//		dao.update(boardcomment);
//		dao.deleteByNo(6);
		
//		BoardComment bc = new BoardComment();
//		bc.setCom_board(2);
//		bc.setCom_contents("댓글 안받아와짐 왜 그럼?");
//		bc.setCom_mem(2);
//		dao.insert(bc);
		
	}
}







