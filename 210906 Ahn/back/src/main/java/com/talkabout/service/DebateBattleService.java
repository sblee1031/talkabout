package com.talkabout.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.DebateBattleDAO;
import com.talkabout.dto.Audience;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.exception.RemoveException;

@Service
public class DebateBattleService {
	
	@Autowired
	private DebateBattleDAO dao;
	
	/*
	 * Audience
	 */
	public List<Audience> findAllAud() throws FindException{
		return dao.selectAllAud();
	}
	
	public Audience findByAudNo(int audi_no) throws FindException{
		return dao.selectByAudNo(audi_no);
	}
	
	public Audience findByDeb(int deb_no, int mem_no) throws FindException{
		return dao.selectByDeb(deb_no, mem_no);
	}
	
	public Map<String, String> voteCnt(int deb_no, int vote_no) throws FindException{
		return dao.selectCnt(deb_no, vote_no);
	}
	
	public void addVote(int deb_no, int mem_no) throws AddException{
		dao.insertVote(deb_no, mem_no);
	}
	

	public void setVote(int audi_no, int vote_no) throws ModifyException{
		dao.updateVote(audi_no, vote_no);
	}
	
	/*
	 * Debate
	 */
	
	public List<Debate> findAllDeb() throws FindException{
		return dao.selectAllDeb();
	}
	
	public Debate findDebateOne(int deb_no) throws FindException {
		return dao.selectOneByDebNo(deb_no);
	}
	
	public void setStatus(Debate deb) throws ModifyException{
		dao.updateStatus(deb);
	}
	
	public void setEndDate(Debate deb) throws ModifyException{
		dao.updateEndDate(deb);
	}
	
	/*
	 * DebateDetail
	 */
	public List<DebateDetail> findTwoByDebNo(int deb_no) throws FindException{
		return dao.selectTwoByDeb(deb_no);
	}
	
	public DebateDetail findOneByPK(int detail_no) throws FindException{
		return dao.selectByDetailNo(detail_no);
	}
	
	public DebateDetail findOneByTwo(int deb_no, int discussor) throws FindException{
		return dao.selectOneByTwo(deb_no, discussor);
	}
	
	public void setIntime(DebateDetail dd) throws ModifyException{
		dao.updateIntime(dd);
	}
	
	public void setEvidence(DebateDetail dd, int evi_no) throws ModifyException{
		dao.updateEvidence(dd, evi_no);
	}
}
