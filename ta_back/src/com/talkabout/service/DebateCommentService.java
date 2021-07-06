package com.talkabout.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.talkabout.dao.DebateCommentDAO;
import com.talkabout.dto.DebateComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

public class DebateCommentService {
private DebateCommentDAO dao;
public static String envProp;
private static DebateCommentService service;
public static DebateCommentService getInstance() {
	if(service == null) {
		service = new DebateCommentService();
	}
	return service;
	
}

private DebateCommentService() {
	Properties env = new Properties();
	try {
		env.load(new FileInputStream(envProp));
		String className = env.getProperty("DebateCommentDAO");
		Class c = Class.forName(className);
		dao=(DebateCommentDAO)c.newInstance();
	} catch(Exception e) {
		e.printStackTrace();
	}
}
public void DCinsert(DebateComment DC)throws AddException{
	dao.insert(DC);
}
public void DCdelete(int com_no)throws DeleteException{
	dao.delete(com_no);
}
public void DCupdate(DebateComment DC)throws ModifyException{
	dao.update(DC);
}
public DebateComment DCselectByComNo(int com_no)throws FindException{
	return dao.selectByComNo(com_no);
}

}
