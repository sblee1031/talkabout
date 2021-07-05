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
	public List<Board> boardSearch(String type, String content) throws FindException{
		return dao.boardSearch(type, content);
	}
	public List<Board> selectAll() throws FindException{
		return dao.selectAll();
	}
	public void insert(Board b) throws AddException{
		dao.insert(b);
	}
	public void update(Board binfo) throws ModifyException{
		dao.update(binfo);
	}
	public void deleteByBoardNo(int Board_no) throws DeleteException{
		dao.deleteByBoardNo(Board_no);
	}
}