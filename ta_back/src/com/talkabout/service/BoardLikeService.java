package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.talkabout.dao.BoardLikeDAO;
import com.talkabout.dao.MemberDAO;
import com.talkabout.dto.BoardLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;

public class BoardLikeService {
	private BoardLikeDAO dao;
	private static BoardLikeService service;
	public static String envProp; //
	private BoardLikeService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("boardlikeDAO");
			Class c = Class.forName(className);
			dao = (BoardLikeDAO)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static BoardLikeService getInstance() {
		if(service == null) {
			service = new BoardLikeService();
		}
		return service;
	}
	public void insert(BoardLike BL) throws AddException{
		dao.insert(BL);
	}
	public void deleteByBoardLikeNo(int boardlike_no) throws DeleteException{
		dao.deleteByBoardLikeNo(boardlike_no);
	}
}
