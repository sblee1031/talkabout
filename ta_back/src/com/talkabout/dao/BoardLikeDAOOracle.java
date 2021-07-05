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
	public int insertLike(int boardLike_no, int boardLike_board, int boardLike_mem) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false);//자동커밋 해제
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		String insertSQL = "INSERT INTO BOARDLIKE VALUES (?,?,?)";
		
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, boardLike_no);
			pstmt.setInt(2, boardLike_board);
			pstmt.setInt(3, boardLike_mem);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				MyConnection.close(con, pstmt, rs);
		}
		return -1;
		
	}

	@Override
	public void deleteByBoardLikeNo(int boardlike_no) throws DeleteException {
		// TODO Auto-generated method stub
		
	}
}
