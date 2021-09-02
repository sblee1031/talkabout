package com.talkabout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.DebateBattleDAO;
import com.talkabout.dao.NoticeDAO;
import com.talkabout.dto.Audience;
import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.exception.RemoveException;

@Service
public class DebateBattleService {
	
	@Autowired
	private DebateBattleDAO dao;
	
	public List<Audience> selectAllAud() throws FindException{
		return dao.selectAllAud();
	}
	
	public Audience selectByAudNo(int audi_no) throws FindException{
		return dao.selectByAudNo(audi_no);
	}
	
	public Audience selectByDeb(int deb_no, int mem_no) throws FindException{
		return dao.selectByDeb(deb_no, mem_no);
	}
	
}
