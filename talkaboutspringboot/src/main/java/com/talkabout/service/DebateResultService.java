package com.talkabout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.DebateResultDAO;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.exception.FindException;

@Service
public class DebateResultService {
	
	@Autowired
	private DebateResultDAO dao;
	
	public List<Debate> debresultlistall() throws FindException {
		return dao.selectAll();
	}	
	//대략적인 게시판정보들
	public Debate debresultone(int BoardNo) throws FindException{
		return dao.selectByNum(BoardNo);
	}
	//게시판 상세정보들 근거 이런거
	public List<DebateDetail> DetailResultOne(int boardNo) throws FindException{
		return dao.detailselectByNo(boardNo);
	}
}
