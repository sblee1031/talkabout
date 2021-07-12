package com.talkabout.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import com.talkabout.dao.BoardDAO;
import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.Board;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public class BoardService {
	private BoardDAO dao;
	private static BoardService service;
	public static String envProp;//
	private BoardService() {
		Properties env = new Properties();
		//env.load(new FileInputStream("classes.prop"));
		try {
		env.load(new FileInputStream(envProp));
		String className = env.getProperty("boardDAO");
		Class c = Class.forName(className);
		dao = (BoardDAO)c.newInstance();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
		}
		return service;
	}
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
	public void EditBoard(Board binfo) throws ModifyException{
		dao.update(binfo);
	}
	public void	DeleteBoard(int Board_no) throws DeleteException{
		dao.deleteByBoardNo(Board_no);
	}
	public void CountBoardView(int Board_no) throws ModifyException{
		dao.updateCount(Board_no);
	}
}