package com.talkabout.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.talkabout.dto.DebateSungho;
import com.talkabout.dto.DebateComment;
import com.talkabout.dto.DebateCommentSungho;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.RemoveException;

@Repository
public class DebateResultDAOOracle implements DebateResultDAO {

	@Autowired
	private SqlSessionFactory sessionFactory;

	@Override
	public List<DebateSungho> selectAll() throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.talkabout.dto.DebateResultMapper.selectAll");
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public DebateSungho selectByNum(int DebateNum) throws FindException {

		SqlSession session = null;
		DebateSungho d = new DebateSungho();
//		try {
//			session = sessionFactory.openSession();
//			return (DebateSungho)session.selectOne("com.talkabout.dto.DebateResultMapper.selectByNum",DebateNum);
//		}catch(Exception e) {
//			throw new FindException(e.getMessage());
//		}finally {
//			if(session != null) session.close();
//		}
		return d;
	}

	@Override
	public List<DebateDetail> detailselectByNo(int detail_DebateNum) throws FindException { // 보드넘버 이용해서 가져오자 상세정보들
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.talkabout.dto.DebateResultMapper.detailselectByNo", detail_DebateNum);
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public Member Memberselect(int Member_num) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectOne("com.talkabout.dto.DebateResultMapper.Memberselect", Member_num);
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			session.close();
		}
	}

	// 이거하나면 나머지 안해도됨
	@Override
	public DebateSungho sunghoDebateSelectByNo(int debate_no) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectOne("com.talkabout.dto.DebateResultMapper.sunghoDebateSelectByNo", debate_no);
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public List<DebateSungho> getlistWithPaging(DebateSungho cri) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.talkabout.dto.DebateResultMapper.getlistWithPaging", cri);
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void insertComment(DebateCommentSungho cm) throws AddException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.talkabout.dto.DebateResultMapper.insertComment", cm);
		} catch (Exception e) {
			throw new AddException(e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void deleteComment(int com_no) throws RemoveException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.delete("com.talkabout.dto.DebateResultMapper.deleteComment", com_no);
		} catch (Exception e) {
			throw new RemoveException(e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public List<DebateCommentSungho> GetListComment(int deb_no) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.talkabout.dto.DebateResultMapper.GetListComment", deb_no);
		} catch (Exception e) {
			throw new FindException("댓글을 찾을 수 없습니다");
		} finally {
			session.close();
		}
	}
	@Override
	public int GetVoteResult_left(int deb_no) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectOne("com.talkabout.dto.DebateResultMapper.GetVoteResult_left", deb_no);
			
		} catch (Exception e) {
			throw new FindException("투표결과가 없습니다");
			
		}finally {
			session.close();
		}
	}
	@Override
		public int GetVoteResult_right(int deb_no) throws FindException {
			SqlSession session = null;
			try {
				session = sessionFactory.openSession();
				return session.selectOne("com.talkabout.dto.DebateResultMapper.GetVoteResult_right", deb_no);
				
			} catch (Exception e) {
				throw new FindException("투표결과가 없습니다");
				
			}finally {
				session.close();
			}
	}
	@Override
	public int GetVoteResult_middle(int deb_no) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			return session.selectOne("com.talkabout.dto.DebateResultMapper.GetVoteResult_middle", deb_no);

		} catch (Exception e) {
			throw new FindException("투표결과가 없습니다");

		} finally {
			session.close();
		}
	}
}
