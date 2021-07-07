package com.talkabout.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.talkabout.dto.DebateDetail;
import com.talkabout.exception.FindException;
import com.talkabout.sql.MyConnection;

public class DebateDetailDAOOracle implements DebateDetailDAO {
	
	public DebateDetailDAOOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("JDBC 드라이버 로드 성공");
	}
	
	public List<DebateDetail> selectAll(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT * FROM DEBATEDETAIL";
		List<DebateDetail> list = new ArrayList();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int detail_no = rs.getInt("DETAIL_NO");
				int detail_deb = rs.getInt("DETAIL_DEB");
				String discuss = rs.getString("DISCUSS");
				String evi_one = rs.getString("EVI_ONE");
				String evi_two = rs.getString("EVI_TWO");
				String evi_three = rs.getString("EVI_THREE");
				int discussor = rs.getInt("DISCUSSOR");
				Date in_time = rs.getDate("IN_TIME");
				
				DebateDetail dd = new DebateDetail(detail_no, detail_deb, discuss, evi_one, evi_two, evi_three, discussor, in_time);
				list.add(dd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return list;
	}
	
	// SelectOne By 토론번호(detail_deb), 토론자(discussor)
	public DebateDetail selectOne(int detail_deb, int discussor) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT * FROM DEBATEDETAIL WHERE DETAIL_DEB = ? AND DISCUSSOR = ?";
		DebateDetail dd = null;
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			pstmt.setInt(1, detail_deb);
			pstmt.setInt(2, discussor);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int detail_n = rs.getInt("DETAIL_NO");
				int deb_no = rs.getInt("DETAIL_DEB");
				String discuss = rs.getString("DISCUSS");
				String evi_one = rs.getString("EVI_ONE");
				String evi_two = rs.getString("EVI_TWO");
				String evi_three = rs.getString("EVI_THREE");
				int discussor_no = rs.getInt("DISCUSSOR");
				Date in_time = rs.getDate("IN_TIME");
				
				dd = new DebateDetail(detail_n, deb_no, discuss, evi_one, evi_two, evi_three, discussor_no, in_time);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}	
		return dd;
	}

	public void insert(DebateDetail dd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String insert_SQL = "INSERT INTO DEBATEDETAIL (DETAIL_NO, DETAIL_DEB, DISCUSS) VALUES (DD_SEQ.NEXTVAL, ?, ?)";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insert_SQL);
			pstmt.setInt(1, dd.getDetail_deb());
			pstmt.setString(2, dd.getDiscuss());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(con, pstmt, null);
		}
	}
	
	public void updateDiscussor(DebateDetail dd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String update_SQL = "UPDATE DEBATEDETAIL SET DISCUSSOR = ? WHERE DETAIL_NO = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(update_SQL);
			// setDate : 
			pstmt.setInt(1, dd.getDiscussor());
			pstmt.setInt(2, dd.getDetail_no());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(con, pstmt, null);
		}
		
	}

	public void updateIntime(DebateDetail dd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String update_SQL = "UPDATE DEBATEDETAIL SET IN_TIME = ? WHERE DETAIL_NO = ?";
		java.sql.Date utilToSQL = new java.sql.Date(dd.getIn_time().getTime());
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(update_SQL);
			// setDate : 
			pstmt.setDate(1, utilToSQL);
			pstmt.setInt(2, dd.getDetail_no());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(con, pstmt, null);
		}
		
	}

	public void updateEvidence(DebateDetail dd, int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql_Word = null;
		String bind_Value = null;
		if (num == 1) {
			sql_Word = "ONE";
			bind_Value = dd.getEvi_one();
		} else if (num == 2) {
			sql_Word = "TWO";
			bind_Value = dd.getEvi_two();
		} else if (num == 3) {
			sql_Word = "THREE";
			bind_Value = dd.getEvi_three();
		}
		
		String update_SQL = "UPDATE DEBATEDETAIL SET EVI_"+ sql_Word + " = ? WHERE DETAIL_NO = ?";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(update_SQL);
			pstmt.setString(1, bind_Value);
			pstmt.setInt(2, dd.getDetail_no());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(con, pstmt, null);
		}
	}
	
	public static void main(String[] args) {
		DebateDetailDAOOracle dao = new DebateDetailDAOOracle();
		
		// selectAll 테스트
		List<DebateDetail> test = dao.selectAll();
		for (DebateDetail i : test) {
			System.out.println(i.toString());
		}
		System.out.println("-----------------------------------------");
		
		// selectOne 테스트
		DebateDetail detail = dao.selectOne(1,2);
		System.out.println(detail.toString());
		System.out.println("-----------------------------------------");

		// Insert 테스트
		/*
			detail.setDetail_no(12);
			detail.setDetail_deb(3);
			detail.setDiscuss("된장찌개");
			
			dao.Insert(detail);
		*/
		
		// updateEvidence 테스트
		DebateDetail update_Test = new DebateDetail();
		update_Test.setDetail_no(7);
		update_Test.setEvi_one("근거 1 테스트");
		update_Test.setEvi_two("근거 2 테스트");
		update_Test.setEvi_three("근거 3 테스트");
		
		dao.updateEvidence(update_Test, 1);
		dao.updateEvidence(update_Test, 2);
		dao.updateEvidence(update_Test, 3);
		
		// updateIntime 테스트
		Date today = new Date();
		update_Test.setIn_time(today);
		System.out.println(update_Test.toString());
		dao.updateIntime(update_Test);
		System.out.println("-----------------------------------------");
		
		// updateDiscussor 테스트
		update_Test.setDiscussor(3);
		System.out.println(update_Test.toString());
		dao.updateDiscussor(update_Test);
	}	
}