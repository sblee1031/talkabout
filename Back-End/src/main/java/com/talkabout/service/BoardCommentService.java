package com.talkabout.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.BoardCommentDAO;
import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.BoardComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

@Service
public class BoardCommentService {
	@Autowired
	private BoardCommentDAO dao;

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
