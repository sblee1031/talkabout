package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateComment;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.sql.MyConnection;

public class DebateCommentDAOOracle implements DebateCommentDAO{
	public DebateCommentDAOOracle() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("JDBC 드라이버 로드 성공");
}
	public List<Debate> selectAll() {
		return null;
	}
	
	public void insert(DebateComment dc) throws AddException {
		
	}
	
	public void delete(int com_no) throws DeleteException {
		

		
	}

	public void update(DebateComment dc) throws ModifyException {
		

		
	}
	//댓글 한개만 가져온느거
	public DebateComment selectByNo(int com_no) throws FindException  {
		return null;
	}
	public List<DebateComment> selectByComNo(int com_no) throws FindException  {
		return null;
	}
	@Override
	public void deleteByAdmin(int com_no) throws DeleteException {
		
		
	}
	
	
	public static void main(String []args) throws Exception {
	}
}