package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.ModifyException;
import com.talkabout.sql.MyConnection;

public class DebateDAOOracle implements DebateDAO {
	
	public DebateDAOOracle() {
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
				String debate_date = rs.getString("DEBATE_DATE");
				int debate_time = rs.getInt("DEBATE_TIME");
				String debate_status = rs.getString("DEBATE_STATUS");
				String debate_startdate = rs.getString("DEBATE_STARTDATE");
				String debate_enddate = rs.getString("DEBATE_ENDDATE");
				
				
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
				String debate_date = rs.getString("debate_date");
				
				//System.out.println("시간 : "+debate_date);
				int debate_time = rs.getInt("DEBATE_TIME");
				String debate_status = rs.getString("DEBATE_STATUS");
				String debate_startdate = rs.getString("DEBATE_STARTDATE");
				String debate_enddate = rs.getString("DEBATE_ENDDATE");
				
				
//				d = new Debate(debate_date);
//			}
				d = new Debate(debate_no, debate_writer, debate_topic, debate_date, debate_time, debate_status, debate_startdate, debate_enddate, null, null, null, null);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return d;
	}
	
	@Override
	public void insertDebate(Debate deb, DebateDetail dd, String discuss1, String discuss2) {
		//DB연결
				Connection con = null;
				try {
					con = MyConnection.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String InsertSQL = "INSERT INTO debate(debate_no, debate_writer, debate_topic, debate_date, debate_time) VALUES (DEB_SEQ.nextval,?,?,"
						+ "To_Date('"+deb.getDebate_date()+"','yyyy-mm-dd hh24:mi') ,?)";
				String seqSQL = "SELECT DEB_SEQ.CURRVAL AS debate FROM DUAL";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = con.prepareStatement(InsertSQL);
					pstmt.setInt(1, deb.getDebate_writer());
					pstmt.setString(2, deb.getDebate_topic());
					pstmt.setInt(3, deb.getDebate_time());
					int result = pstmt.executeUpdate();
//					System.out.println("result : "+ result);
					
					pstmt = con.prepareStatement(seqSQL);
					rs = pstmt.executeQuery();
					rs.next();
					int seq = rs.getInt(1); // debate_no pk값
					
					
					DebateDetailDAOOracle detail = new DebateDetailDAOOracle();
					dd.setDetail_deb(seq);
					dd.setDiscuss(discuss1); //주장1 
					detail.insert(dd);
					//System.out.println("주장1 입력");
					
					dd.setDetail_deb(seq);
					dd.setDiscuss(discuss2); //주장2
					detail.insert(dd);
					//System.out.println("주장2 입력");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					//DB연결 해제
					MyConnection.close(con, pstmt, rs);
				}
		
	}

	@Override
	public void updateDebateAll(Debate deb) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String updateAllSQL = "UPDATE debate SET ";
		String updateAllSQL2 = "WHERE debate_no = ? ";
		
		DebateDAOOracle dao;
		Debate dbDebate = null;
		try {
			
			dao = new DebateDAOOracle();
			dbDebate = dao.selectByNo(deb.getDebate_no());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean flag = false; //변경할 값이 있는 경우 true
		
		String debate_topic = deb.getDebate_topic();		
		if( debate_topic != null && !debate_topic.equals("") && !debate_topic.equals(dbDebate.getDebate_topic())) {
			updateAllSQL += "debate_topic = '" + debate_topic + "'";
			flag = true;
		}		
		
		int debate_time = deb.getDebate_time();		
		if( debate_time != 0 &&  debate_time!=dbDebate.getDebate_time()) {
			if(flag) {
				updateAllSQL += ",";
			}		
			updateAllSQL += "debate_time = '" + debate_time + "'";
			flag = true;
		}		
			
		String debate_date = dbDebate.getDebate_date();		
		if( debate_date != null && !debate_date.equals("")&& !debate_date.equals(dbDebate.getDebate_date())) {
			if(flag) {
				updateAllSQL += ",";
			}	
			updateAllSQL += "debate_date = '" + debate_date + "'";
			flag = true;
		}

		
		if(!flag) {
			throw new ModifyException("수정할 내용이 없습니다");
		}
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateAllSQL+updateAllSQL2);
			pstmt.setInt(1, deb.getDebate_no());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(con, pstmt, null);
		}
		
	}

	@Override
	public void updateStatus(Debate status) {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String updateStatus = "UPDATE debate SET debate_status = ? WHERE debate_no = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//Member l = null;
		try {
			pstmt = con.prepareStatement(updateStatus);
			pstmt.setString(1, status.getDebate_status());
			pstmt.setInt(2, status.getDebate_no());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally{
			//DB연결 해제
			MyConnection.close(con, pstmt, rs);
		}
		
	}

	@Override
	public void updateSartdate(Debate start_date) {
		//detail쪽 작성
	}

	@Override
	public void updateEnddate(Debate end_date) {
		// detail쪽 작성
		
	}
	@Override
	public void updateDiscussor(Debate deb_no, DebateDetail dd, Member m) {// 토론자 등록
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String updateStatus = "UPDATE debatedetail SET discussor = ? WHERE detail_deb = ? and discuss = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//Member l = null;
		try {
			pstmt = con.prepareStatement(updateStatus);
			pstmt.setInt(1, m.getMember_no());
			pstmt.setInt(2, deb_no.getDebate_no());
			pstmt.setString(3, dd.getDiscuss());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally{
			//DB연결 해제
			MyConnection.close(con, pstmt, rs);
		}
		
	}

	@Override
	public void deleteDebate(Debate deb_no) {
		//DB연결
				Connection con = null;
				try {
					con = MyConnection.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String deleteSQL = "DELETE debate WHERE debate_no = ?";
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				//Member l = null;
				try {
					pstmt = con.prepareStatement(deleteSQL);
					pstmt.setInt(1, deb_no.getDebate_no());
					
					pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
					
				}finally{
					//DB연결 해제
					MyConnection.close(con, pstmt, rs);
				}
	}
	
	public static void main(String[] args) {
		
		DebateDAOOracle dao = new DebateDAOOracle();
		
//		Debate deb_no = new Debate();
//		deb_no.setDebate_no(2);
//		DebateDetail dd = new DebateDetail();
//		dd.setDiscuss("삼성");
//		Member m = new Member();
//		m.setMember_no(2);
//		dao.updateDiscussor(deb_no, dd, m);
		
		
//		Debate d = new Debate();
//		d.setDebate_no(6);
//		dao.deleteDebate(d);
		
//		Debate db = dao.selectByNo(1);
//		System.out.println(db.getDebate_date());
//		System.out.println(db.getDebate_no());
//		System.out.println(db.getDebate_topic());
//		System.out.println(db.getDebate_time());
//		System.out.println(time);
		
		//전체 수정
//		Debate deb = new Debate();
//		deb.setDebate_topic("수정_주제");
//		deb.setDebate_time(30);
//		deb.setDebate_no(1);
//		
//		Date debate_date = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("2021-07-07 12:31:00");
//		debate_date.setYear(2023);;
//		System.out.println(df);
//		deb.setDebate_date(debate_date);
		
//		
//		try {
//			dao.updateDebateAll(deb);
//		} catch (ModifyException e) {
//			e.printStackTrace();
//		}
		
		

		
//		//Debate insert
//		Debate deb = new Debate();
//		Member mem = new Member();
//		mem.setMember_no(1);
//		DebateDetail dd = new DebateDetail();
//		deb.setDebate_writer(1);
//		deb.setDebate_topic("시험인가");
//		
//		Date date = new Date();
//		long ldate = date.getTime();
//		java.sql.Date sqldate = new java.sql.Date(ldate);
//		String from = "2018-09-06 11:11";
//		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		java.sql.Date to=null;
//		to = sqldate;
//		System.out.println(to.toString());
//		deb.setDebate_date(sqldate);
//		deb.setDebate_time(120);
//		dao.insertDebate(deb, dd, "주장1이다", "주장2이다");
		
		
		
		//		List<Debate> list = dao.selectAll();
//		
//		for (Debate debate : list) {
//			System.out.println(debate.getDebate_topic());
//		}
//		System.out.println(dao.selectByNo(3).toString());
	}


}