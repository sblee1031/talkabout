package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.BoardLikeDAO;
import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.BoardLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
@Service
public class BoardLikeService {
	@Autowired
	private BoardLikeDAO dao;
	
	public void AddBoardLike(BoardLike BL) throws AddException{
		dao.insert(BL);
	}
	public void DeleteBoardLike(int boardlike_no) throws DeleteException{
		dao.deleteByBoardLikeNo(boardlike_no);
	}
}
