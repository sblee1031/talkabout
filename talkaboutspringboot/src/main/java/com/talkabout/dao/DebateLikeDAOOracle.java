package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.talkabout.dto.DebateLike;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.sql.MyConnection;

public class DebateLikeDAOOracle implements DebateLikeDAO{
	public DebateLikeDAOOracle() throws Exception {
		//JDBC드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
	}
	@Override
	public void insert(DebateLike DL) throws AddException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		}catch(SQLException e) {
			throw new AddException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		String insertSQL = "INSERT INTO DEBATELIKE VALUES (DL_SEQ.NEXTVAL,?,?)";
		
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, DL.getDeblike_deb().getDebate_no());
			pstmt.setInt(2, DL.getDeblike_mem().getMember_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				MyConnection.close(con, pstmt, null);
		}
	}

	@Override	
	public void deleteByDebatelikeNo(DebateLike dl) throws DeleteException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con=MyConnection.getConnection();
		} catch (SQLException e) {
			throw new DeleteException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		PreparedStatement pstmt = null;
		String deleteSQL = "DELETE DEBATELIKE WHERE DEBLIKE_DEB = ? and DEBLIKE_MEM = ?";
		try {
			pstmt= con.prepareStatement(deleteSQL);
			pstmt.setInt(1, dl.getDeblike_deb().getDebate_no());
			pstmt.setInt(2, dl.getDeblike_mem().getMember_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}
	
	@Override
	public Integer debLikeChk(DebateLike dl) throws FindException {
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=MyConnection.getConnection();
		} catch (SQLException e) {
			throw new FindException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		String sql = "select count(*) from debatelike where DEBLIKE_DEB = ? and DEBLIKE_MEM = ?";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, dl.getDeblike_deb().getDebate_no());
			pstmt.setInt(2, dl.getDeblike_mem().getMember_no());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int likeCnt = rs.getInt("count(*)");
				cnt = likeCnt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, rs);
		}return cnt;
	}
	@Override
	public Integer debLikeCnt(DebateLike deb_no)  {
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=MyConnection.getConnection();
		} catch (SQLException e) {
			cnt = 0;
			//DB연결에 문제발생시 예외처리
		}
		DebateLike dll = new DebateLike();
	
		String sql = "select count(*) from debatelike where DEBLIKE_DEB = ?";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, deb_no.getDeblike_deb().getDebate_no());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int likeCnt = rs.getInt("count(*)");
				cnt = likeCnt;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		DebateLikeDAOOracle dao = new DebateLikeDAOOracle();
//		dao.deleteByDebatelikeNo(1);
		
	}


}
