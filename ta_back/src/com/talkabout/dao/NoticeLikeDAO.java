package com.talkabout.dao;

//import java.util.List;

//import com.talkabout.dto.Notice;
import com.talkabout.dto.NoticeLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
//import com.talkabout.exception.ModifyException;

public interface NoticeLikeDAO {
	void insert(NoticeLike NL) throws AddException;
	void deleteByNlNo(int NoticeLike_no) throws DeleteException;
}
