package com.talkabout.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.DebateResultDAO;
import com.talkabout.dto.DebateSungho;
import com.talkabout.dto.DebateCommentSungho;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.RemoveException;

@Service
public class DebateResultService {
	
	@Autowired
	private DebateResultDAO dao;
	
	public List<DebateSungho> debresultlistall() throws FindException {
		return dao.selectAll();
	}	
	//대략적인 게시판정보들
//	public DebateSungho debresultone(int BoardNo) throws FindException{
//		return dao.selectByNum(BoardNo);
//	}
//	//게시판 상세정보들 근거 이런거
//	public List<DebateDetail> DetailResultOne(int boardNo) throws FindException{
//		return dao.detailselectByNo(boardNo);
//	}
//	//멤버번호 이용해서 맴버정보 가져오기
//	public Member memberinfo(int member_num) throws FindException{
//		return dao.Memberselect(member_num);
//	}
	public DebateSungho selectOne(int board_num)throws FindException{
		return dao.sunghoDebateSelectByNo(board_num);
	}
	
	public List<DebateSungho> listallWithPaging(DebateSungho cri) throws FindException{
			return dao.getlistWithPaging(cri);
	}
	
	public void CreateComment (DebateCommentSungho cm) throws AddException{
		dao.insertComment(cm);
	}
	//댓글삭제
	public void RemoveComment (int com_no)throws RemoveException{
		dao.deleteComment(com_no);
	}
	public List<DebateCommentSungho> GetListComment(int deb_no)throws FindException{
		return dao.GetListComment(deb_no);
	}
	public int GetVoteResult_left(int deb_no)throws FindException{
		return dao.GetVoteResult_left(deb_no);
	}
	public int GetVoteResult_right(int deb_no)throws FindException{
		return dao.GetVoteResult_right(deb_no);
	}
	public int GetVoteResult_middle(int deb_no)throws FindException{
		return dao.GetVoteResult_middle(deb_no);
	}
	public List<DebateSungho> Getlistbyword(String word)throws FindException{
		return dao.Getlistbyword(word);
	}
}
