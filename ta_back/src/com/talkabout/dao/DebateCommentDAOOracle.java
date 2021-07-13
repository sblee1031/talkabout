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
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//String select_SQL = "SELECT * FROM DEBATE";
		String select_SQL = "SELECT * FROM debate WHERE debate_status = '종료'";
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
	
	public void insert(DebateComment dc) throws AddException {
		Connection con = null;
	      try {
	      con = MyConnection.getConnection();
	      }catch(SQLException e) {
	         throw new AddException(e.getMessage());
	         //DB연결에 문제발생시 예외처리
	      }
	      String InsertSQL = "INSERT INTO debatecomment VALUES (DC_SEQ.NEXTVAL, ?, ?, sysdate, ?)";
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt = con.prepareStatement(InsertSQL);
	         
	         pstmt.setInt(1, dc.getCom_deb());
	         pstmt.setString(2, dc.getCom_contents());
	         pstmt.setInt(3, dc.getCom_mem());
//	         pstmt.setInt(2, bc.getCom_mem());
	         pstmt.executeQuery();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         //DB연결 해제
	         MyConnection.close(con, pstmt, rs);
	      }
	}
	
	public void delete(int com_no) throws DeleteException {
		Connection con = null;
	      try {
	      con = MyConnection.getConnection();
	      }catch(SQLException e) {
	         throw new  DeleteException(e.getMessage());
	         //DB연결에 문제발생시 예외처리
	      }
	      String DeleteSQL = "Delete FROM DebateComment where com_no=?  ";
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt = con.prepareStatement( DeleteSQL);
	         
	         pstmt.setInt(1, com_no);
//	         pstmt.setInt(2, bc.getCom_mem());
	         pstmt.executeQuery();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         //DB연결 해제
	         MyConnection.close(con, pstmt, rs);
	      }

		
	}

	public void update(DebateComment dc) throws ModifyException {
		//DB연결
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con = MyConnection.getConnection();
				}catch(SQLException e) {
					e.printStackTrace();
					//DB연결에 문제발생시 예외처리
				}
				String updateSQL = "UPDATE DEBATECOMMENT SET com_contents = '"+ dc.getCom_contents()+"' "; // com_contetns;
				String updateSQL1 = "WHERE com_no = ?";
				
				DebateCommentDAOOracle dao;
				DebateComment dbDebateComment = null;
				try {
					dao = new DebateCommentDAOOracle();
					dbDebateComment= dao.selectByNo(dc.getCom_no());
				}catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("업데이트"+dc.getCom_contents());
					try {
						pstmt = con.prepareStatement(updateSQL + updateSQL1);
						pstmt.setInt(1, dc.getCom_no());
						pstmt.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						MyConnection.close(con, pstmt, null);
				}

		
	}
	//댓글 한개만 가져온느거
	public DebateComment selectByNo(int com_no) throws FindException  {
		// TODO Auto-generated method stub
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		String selectByComNo = "SELECT * FROM DebateComment WHERE com_no = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DebateComment> listDebateComment = new ArrayList<>();
		DebateComment dc = null;
		try {
			pstmt = con.prepareStatement(selectByComNo);
			pstmt.setInt(1, com_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//행의 칼럼값 얻기
				int com_no1 = rs.getInt("com_no");
				int com_deb = rs.getInt("com_deb");
				String com_contents = rs.getString("com_contents");
				String com_date = rs.getString("com_date");
				int com_mem = rs.getInt("com_mem");
				
				dc = new DebateComment(com_no1, com_deb, com_contents, com_date, com_mem);
				listDebateComment.add(dc);
			}
			if(listDebateComment.size() == 0) { //게시글이 없는경우
				throw new FindException("댓글이 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}
		return dc;
	}
	public List<DebateComment> selectByComNo(int com_no) throws FindException  {
		// TODO Auto-generated method stub
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		String selectByComNo = "SELECT * FROM DebateComment WHERE com_deb = ?";//위에 int com_no무시 sql엔 com_deb으로 들어간다
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DebateComment> listDebateComment = new ArrayList<>();
		DebateComment dc = null;
		try {
			pstmt = con.prepareStatement(selectByComNo);
			pstmt.setInt(1, com_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//행의 칼럼값 얻기
				int com_no1 = rs.getInt("com_no");
				int com_deb = rs.getInt("com_deb");
				String com_contents = rs.getString("com_contents");
				String com_date = rs.getString("com_date");
				int com_mem = rs.getInt("com_mem");
				
				dc = new DebateComment(com_no1, com_deb, com_contents, com_date, com_mem);
				listDebateComment.add(dc);
			}
			if(listDebateComment.size() == 0) { //게시글이 없는경우
				throw new FindException("댓글이 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}
		return listDebateComment;
	}
	@Override
	public void deleteByAdmin(int com_no) throws DeleteException {
		// TODO Auto-generated method stub
		Connection con = null;
	      try {
	      con = MyConnection.getConnection();
	      }catch(SQLException e) {
	         throw new  DeleteException(e.getMessage());
	         //DB연결에 문제발생시 예외처리
	      }
	      String DeleteSQL = "Delete FROM DebateComment where com_no=?  ";
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt = con.prepareStatement( DeleteSQL);
	         
	         pstmt.setInt(1, com_no);
//	         pstmt.setInt(2, bc.getCom_mem());
	         pstmt.executeQuery();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         //DB연결 해제
	         MyConnection.close(con, pstmt, rs);
	      }
		
	}
	
	
	public static void main(String []args) throws Exception {
		DebateCommentDAOOracle dao = new DebateCommentDAOOracle();
		List<Debate> list = new ArrayList<>();
		list = dao.selectAll();
		for (Debate debate : list) {
			System.out.println(debate.getDebate_topic());
		}
		
//		DebateComment dc = new DebateComment();
//		MemberDAOOracle mDAO = null;
//		Member member = null;
//		try {
//			mDAO = new MemberDAOOracle();
//			member= mDAO.selectByNo(1);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		dc.setCom_deb(1);
//		dc.setCom_mem(member.getMember_no());
//		dc.setCom_contents("2번댓글 ");
//		dao.insert(dc);
//		DebateComment debatecomment = new DebateComment();
//		debatecomment.setCom_no(3);
//		debatecomment.setCom_contents("바꼇슈");
//		dao.update(debatecomment);
		
//		DebateComment debatecomment = dao.selectByComNo(3);
//		System.out.println(debatecomment.toString());
	}
//	public static void main(String []args) throws DeleteException  {
//		DebateCommentDAOOracle dao = null;
//		try {
//			dao = new DebateCommentDAOOracle();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		DebateComment dc = new DebateComment();
//		
//		
//		dao.delete(2);
//	}

	
}
