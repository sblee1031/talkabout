package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.talkabout.dto.NoticeLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.sql.MyConnection;

public class NoticeLikeDAOOracle implements NoticeLikeDAO{
	public NoticeLikeDAOOracle() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
	}
	public void insert (NoticeLike NL) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		}catch(SQLException e) {
			throw new AddException(e.getMessage());
		}
		String insertSQL = "INSERT INTO NOTICELIKE VALUES (BL_SEQ.NEXTVAL,?,?)";
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, NL.getnoticeLike_notice());
			pstmt.setInt(2, NL.getnoticeLike_mem());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				MyConnection.close(con, pstmt, null);
		}
	}
	public void deleteByNLNo(int noticelike_no) throws DeleteException {
		Connection con = null;
		try {
			con=MyConnection.getConnection();
		} catch (SQLException e) {
			throw new DeleteException(e.getMessage());
		}
		PreparedStatement pstmt = null;
		String deleteSQL = "DELETE NOTICELIKE WHERE noticelike_no = ?";
		try {
			pstmt= con.prepareStatement(deleteSQL);
			pstmt.setInt(1, noticelike_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}
	
	
	
	@Override
	public void deleteByNlNo(int NoticeLike_no) throws DeleteException {
		// TODO Auto-generated method stub
		
	}
	
	
	
//	public static void main(String[] args) throws Exception {
//		NoticeLikeDAOOracle dao = new NoticeLikeDAOOracle();
//	}

}