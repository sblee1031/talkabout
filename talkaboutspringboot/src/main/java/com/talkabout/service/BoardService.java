package com.talkabout.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.BoardDAO;
import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.Board;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;

	public List<Board> BoardSearch(String type, String contents) throws FindException{
		return dao.boardSearch(type, contents);
	}
	public List<Board> BoardList() throws FindException{
		return dao.selectAll();
	}
	public Board BoardDetail(int board_no) throws FindException{
		return dao.selectByBoardNo(board_no);
	}
	public void AddBoard(Board b) throws AddException{
		dao.insert(b);
	}
	public void EditBoard(int board_no) throws ModifyException{
		dao.update(board_no);
	}
	public void	DeleteBoard(int Board_no) throws DeleteException{
		dao.deleteByBoardNo(Board_no);
	}
	public void CountBoardView(int Board_no) throws ModifyException{
		dao.updateCount(Board_no);
	}
}