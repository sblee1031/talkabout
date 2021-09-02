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

	public List<Board> BoardSearch(String word, int startRow, int endRow) throws FindException{
		return dao.boardSearch(word, startRow, endRow);
	}
	public List<Board> BoardList(int startRow, int endRow) throws FindException{
		return dao.selectAll(startRow, endRow);
	}
	public Board BoardDetail(int board_no) throws FindException{
		return dao.selectByBoardNo(board_no);
	}
	public void AddBoard(Board b) throws AddException{
		dao.insert(b);
	}
	public void EditBoard(Board board) throws ModifyException{
		dao.update(board);
	}
	public void	DeleteBoard(int Board_no) throws DeleteException{
		dao.deleteByBoardNo(Board_no);
	}
	public void CountBoardView(int Board_no) throws ModifyException{
		dao.updateCount(Board_no);
	}
	public int lastRow() {
		return dao.lastRow();
	}
	public int searchLastRow(String word) {
		return dao.searchLastRow(word);
	}
}