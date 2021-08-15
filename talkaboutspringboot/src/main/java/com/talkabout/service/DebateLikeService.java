package com.talkabout.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.talkabout.dao.DebateLikeDAO;
import com.talkabout.dto.DebateLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;

public class DebateLikeService {
	private DebateLikeDAO dao;
	private static DebateLikeService service;
	public static String envProp; //
	private DebateLikeService() {
		Properties env = new Properties();
		try {
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("debatelikeDAO");
			Class c = Class.forName(className);
			dao = (DebateLikeDAO)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DebateLikeService getInstance() {
		if(service == null) {
			service = new DebateLikeService();
		}
		return service;
	}
	//좋아요 추천여부 검증
	public Integer debLikeChk(DebateLike dl) throws FindException{
		return dao.debLikeChk(dl);
	}
	//게시글 좋아요 추천수
	public Integer debLikeCnt(DebateLike deb_no) {
		return dao.debLikeCnt(deb_no);
	}
	//좋아요 클릭
	public void AddBoardLike(DebateLike dl) throws AddException{
		dao.insert(dl);
	}
	//좋아요 취소
	public void DeleteBoardLike(DebateLike dl_no) throws DeleteException{
		dao.deleteByDebatelikeNo(dl_no);
	}
}
