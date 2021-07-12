package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.talkabout.dto.Audience;
import com.talkabout.dto.DebateDetail;
import com.talkabout.sql.MyConnection;

public class AudienceDAOOracle implements AudienceDAO {
	
	public AudienceDAOOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("JDBC 드라이버 로드 성공");
	}
	
	public List<Audience> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT * FROM AUDIENCE";
		List<Audience> list = new ArrayList();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int audi_no = rs.getInt("AUDI_NO");
				int audi_deb = rs.getInt("AUDI_DEB");
				int audi_mem = rs.getInt("AUDI_MEM");
				int vote = rs.getInt("VOTE");
				
				Audience a = new Audience(audi_no, audi_deb, audi_mem, vote);
				
				list.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return list;
	}

	public Audience selectByNo(int audi_no) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT * FROM AUDIENCE WHERE AUDI_NO = ?";
		Audience a = null;
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			pstmt.setInt(1, audi_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int audi_n = rs.getInt("AUDI_NO");
				int audi_deb = rs.getInt("AUDI_DEB");
				int audi_mem = rs.getInt("AUDI_MEM");
				int vote = rs.getInt("VOTE");
				
				a = new Audience(audi_n, audi_deb, audi_mem, vote);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return a;
	}
	
	public Audience selectByDeb(int deb_no, int mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT * FROM AUDIENCE WHERE AUDI_DEB = ? AND AUDI_MEM = ?";
		Audience a = null;
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			pstmt.setInt(1, deb_no);
			pstmt.setInt(2, mem_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int audi_n = rs.getInt("AUDI_NO");
				int audi_deb = rs.getInt("AUDI_DEB");
				int audi_mem = rs.getInt("AUDI_MEM");
				int vote = rs.getInt("VOTE");
				
				a = new Audience(audi_n, audi_deb, audi_mem, vote);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return a;
	}
	
	public void insertVote(int deb_no, int mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String insert_SQL = "INSERT INTO AUDIENCE VALUES (AUDI_SEQ.NEXTVAL, ?,?,2)";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insert_SQL);
			pstmt.setInt(1, deb_no);
			pstmt.setInt(2, mem_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
	}
	
	public void updateVote(Audience a) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/*
		 * UPDATE AUDIENCE SET VOTE = 2 WHERE AUDI_NO = 3;
		 * UPDATE AUDIENCE SET VOTE = 1 WHERE AUDI_DEB = 1 AND AUDI_MEM = 1;
		 */
		String update_SQL = "UPDATE AUDIENCE SET VOTE = ? WHERE AUDI_NO = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(update_SQL);
			pstmt.setInt(1, a.getVote());
			pstmt.setInt(2, a.getAudi_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
	}
	
	// {1 갯수, 2 갯수, 3 갯수}
	public ArrayList<Integer> selectByCnt(int deb_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT vote, count(vote) as cnt from audience where AUDI_DEB = ? group by vote";
		ArrayList list = new ArrayList();
		int[] arr = new int[3];
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			pstmt.setInt(1, deb_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int vote = rs.getInt("VOTE");
				int cnt = rs.getInt("CNT");

				arr[vote-1] = cnt;
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		
		return list;
	};
	
	public static void main(String[] args) throws SQLException {
		AudienceDAOOracle dao = new AudienceDAOOracle();
		List<Audience> list = dao.selectAll();
		
		// SelectAll
		for (Audience audience : list) {
			System.out.println(audience.toString());
		}
		
		// SelectOne By audi_no 
		System.out.println(dao.selectByNo(2).toString());
		
		// UpdateVote By AudienceDTO(audi_no)
		Audience a = dao.selectByNo(3);
		a.setVote(2);
		dao.updateVote(a);
		
		// 
		ArrayList<Integer> vote_List = dao.selectByCnt(1);
		for (int i : vote_List) {
			System.out.println(i);
		}
		
		Audience a3 = dao.selectByDeb(1, 2);
		System.out.println("a3");
		System.out.println(a3.toString());
	}


	
}
