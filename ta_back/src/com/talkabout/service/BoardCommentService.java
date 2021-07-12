package com.talkabout.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import com.talkabout.dao.BoardCommentDAO;
import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.BoardComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public class BoardCommentService {
	private BoardCommentDAO dao;
	private static BoardCommentService service;
	public static String envProp; //
	private BoardCommentService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("boardCommentDAO");
			Class c = Class.forName(className);
			dao = (BoardCommentDAO)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static BoardCommentService getInstance() {
		if(service == null) {
			service = new BoardCommentService();
		}
		return service;
	}
	public List<BoardComment> MyBoradComseach(int com_board, int com_mem) throws FindException{
		return dao.myBoardComSearch(com_board, com_mem);
	}
	public List<BoardComment> BoardComList(int com_board) throws FindException{
		return dao.selectAll(com_board);
	}
	public void AddBoardCom(BoardComment bc) throws AddException{
		dao.insert(bc);
	}
	public void EditBoardCom(BoardComment bc) throws ModifyException{
		dao.update(bc);
	}
	public void DeleteBoardCom(int com_no) throws DeleteException{
		dao.deleteByNo(com_no);
	}
}
