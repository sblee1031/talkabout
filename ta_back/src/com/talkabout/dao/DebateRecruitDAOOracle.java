package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.exception.FindException;
import com.talkabout.sql.MyConnection;

public class DebateRecruitDAOOracle implements DebateDAO {
	
	public DebateRecruitDAOOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("JDBC 드라이버 로드 성공");
	}

	public List<Debate> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT * FROM DEBATE";
		List<Debate> list = new ArrayList();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int debate_no = rs.getInt("DEBATE_NO");
				int debate_writer = rs.getInt("DEBATE_WRITER");
				String debate_topic = rs.getString("DEBATE_TOPIC");
				Date debate_date = rs.getDate("DEBATE_DATE");
				int debate_time = rs.getInt("DEBATE_TIME");
				String debate_status = rs.getString("DEBATE_STATUS");
				Date debate_startdate = rs.getDate("DEBATE_STARTDATE");
				Date debate_enddate = rs.getDate("DEBATE_ENDDATE");
				
				Debate d = new Debate(debate_no, debate_writer, debate_topic, debate_date, debate_time, debate_status, debate_startdate, debate_enddate, null, null, null, null);
				list.add(d);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return list;
	}

	public Debate selectByNo(int debate_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT * FROM DEBATE WHERE DEBATE_NO = ?";
		Debate d = null;
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			pstmt.setInt(1, debate_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int deb_no = rs.getInt("DEBATE_NO");
				int debate_writer = rs.getInt("DEBATE_WRITER");
				String debate_topic = rs.getString("DEBATE_TOPIC");
				Date debate_date = rs.getDate("DEBATE_DATE");
				int debate_time = rs.getInt("DEBATE_TIME");
				String debate_status = rs.getString("DEBATE_STATUS");
				Date debate_startdate = rs.getDate("DEBATE_STARTDATE");
				Date debate_enddate = rs.getDate("DEBATE_ENDDATE");
				
				d = new Debate(deb_no, debate_writer, debate_topic, debate_date, debate_time, debate_status, debate_startdate, debate_enddate, null, null, null, null);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return d;
	}
	
	public static void main(String[] args) {
		DebateRecruitDAOOracle dao = new DebateRecruitDAOOracle();
		List<Debate> list = dao.selectAll();
		
		for (Debate debate : list) {
			System.out.println(debate.getDebate_topic());
		}
		System.out.println(dao.selectByNo(3).toString());
	}
}
