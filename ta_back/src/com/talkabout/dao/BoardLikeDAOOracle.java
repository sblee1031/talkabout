package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.talkabout.dto.BoardLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.sql.MyConnection;

public class BoardLikeDAOOracle implements BoardLikeDAO{
	public BoardLikeDAOOracle() throws Exception {
		//JDBC드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
	}

	@Override
	public void insert (BoardLike BL) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		}catch(SQLException e) {
			throw new AddException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		String insertSQL = "INSERT INTO BOARDLIKE VALUES (BL_SEQ.NEXTVAL,?,?)";
		
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, BL.getboardLike_board().getBoard_no());
			pstmt.setInt(2, BL.getboardLike_mem().getMember_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				MyConnection.close(con, pstmt, null);
		}
		
	}

	@Override
	public void deleteByBoardLikeNo(int boardlike_no) throws DeleteException {
		//DB연결
		Connection con = null;
		try {
			con=MyConnection.getConnection();
		} catch (SQLException e) {
			throw new DeleteException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		PreparedStatement pstmt = null;
		String deleteSQL = "DELETE BOARDLIKE WHERE boardlike_no = ?";
		try {
			pstmt= con.prepareStatement(deleteSQL);
			pstmt.setInt(1, boardlike_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}
}
