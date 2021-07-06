package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.talkabout.dto.NoticeComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.sql.MyConnection;

public class NoticeCommentDAOOracle implements NoticeCommentDAO{
	public NoticeCommentDAOOracle() throws Exception {
		//JDBC드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
	}

	
	
	//자신 댓글 보기
	public List<NoticeComment> myNoticeComSearch(int com_notice, int com_mem) throws FindException {
		Connection con = null;
		try {
		con = MyConnection.getConnection();
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
		}
		String selectMyBCSQL = "SELECT*FROM NOTICECOMMENT WHERE com_notice = " +"? and"+" com_mem = " + "?" + "ORDER BY com_no ASC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeComment> list = new ArrayList<> ();
		try {
			pstmt = con.prepareStatement(selectMyBCSQL);
			pstmt.setInt(1, com_notice);
			pstmt.setInt(2, com_mem);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int com_no = rs.getInt("com_no");
				int com_notice_no = rs.getInt("com_notice");
				Date com_date = rs.getDate("com_date");
				String com_contents = rs.getString("com_contents");
				int com_member_no = rs.getInt("com_mem");

				NoticeComment NC = new NoticeComment(com_no, com_notice_no, com_date,
						com_contents, com_member_no);
				list.add(NC);
			}
			if(list.size() == 0) {
				throw new FindException("댓글이 없습니다");
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
	}

	
	
	//댓글 보기
	public List<NoticeComment> selectAll() throws FindException {
				Connection con = null;
				try {
				con = MyConnection.getConnection();
				}catch(SQLException e) {
					throw new FindException(e.getMessage());
				}
				String selectALLSQL = "SELECT*FROM NoticeComment where com_notice_no = ? ORDER BY com_no ASC";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<NoticeComment> list = new ArrayList<> ();
				try {
					pstmt = con.prepareStatement(selectALLSQL);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						int com_no = rs.getInt("com_no");
						int com_notice_no = rs.getInt("com_notice");
						Date com_date = rs.getDate("com_date");
						String com_contents = rs.getString("com_contents");
						int com_member_no = rs.getInt("com_mem");
						NoticeComment NC = new NoticeComment(com_no, com_notice_no, com_date,
								com_contents, com_member_no);
						list.add(NC);
					}
					if(list.size() == 0) {
						throw new FindException("댓글이 없습니다");
					}
					return list; 
				} catch (SQLException e) {
					e.printStackTrace();
					throw new FindException(e.getMessage());
				} finally {
					MyConnection.close(con, pstmt, rs);
				}
	}

	
	
	public NoticeComment selectByComNo(int com_no) throws FindException {
		//DB연결
				Connection con = null;
				try {
					con = MyConnection.getConnection();
				}catch(SQLException e) {
					throw new FindException(e.getMessage());
					//DB연결에 문제발생시 예외처리
				}
				String selectByComNo = "SELECT*FROM NOTICECOMMENT WHERE com_no = ?";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<NoticeComment> listnoticecomment = new ArrayList<>();
				NoticeComment nc = null;
				try {
					pstmt = con.prepareStatement(selectByComNo);
					pstmt.setInt(1, com_no);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						//행의 칼럼값 얻기
						int com_number = rs.getInt("com_no");
						int com_notice = rs.getInt("com_notice");
						Date com_date = rs.getDate("com_date");
						String com_contents = rs.getString("com_contents");
						int com_mem = rs.getInt("com_mem");
						
						nc = new NoticeComment(com_number, com_notice, com_date,com_contents, com_mem);
						listnoticecomment.add(nc);
					}
					if(listnoticecomment.size() == 0) { //게시글이 없는경우
						throw new FindException("댓글이 존재하지 않습니다.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//DB연결해제
					MyConnection.close(con, pstmt, rs);
				}
				return nc;
	}
	
	
	
	//댓글작성
	public void insert(NoticeComment ecinfo) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false); //자동커밋
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
		try {
			insertNoticeComment(con, ecinfo);
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			} 
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(con, null, null);
		}
	}
	private void insertNoticeComment(Connection con, NoticeComment ecinfo) throws AddException {	
		PreparedStatement pstmt = null;
		String insertNoticeCommentSQL = "INSERT INTO NOTICECOMMENT VALUES (NC_SEQ.NEXTVAL, ?, sysdate, ?, ?)";
		try {
			pstmt = con.prepareStatement(insertNoticeCommentSQL);
			pstmt.setInt(1, ecinfo.getCom_notice());
			pstmt.setString(2, ecinfo.getCom_contents());
			pstmt.setInt(3, ecinfo.getCom_mem());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException("댓글 추가 실패" + e.getMessage());
		} finally {
			MyConnection.close(null, pstmt, null);
		}
	}		
	


	//댓글수정
	public void update(NoticeComment nc) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String updateSQL = "UPDATE NOTICECOMMENT SET "; 
		String updateSQL1 = "WHERE com_no = ?";
		NoticeCommentDAOOracle dao;
		NoticeComment dbNoticeComment = null;
		try {
			dao = new NoticeCommentDAOOracle();
			dbNoticeComment = dao.selectByComNo(nc.getCom_no());
		}catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = false;
		String Com_contents = nc.getCom_contents();
		System.out.println(Com_contents + " / " + dbNoticeComment.getCom_contents());
		if(Com_contents != null && !Com_contents.equals("") && !Com_contents.contentEquals(dbNoticeComment.getCom_contents())) {
			updateSQL += "com_contents = '" + Com_contents + "'";
			flag = true;
		}
		if(!flag) {
			throw new ModifyException("수정할 내용이 없습니다");
		}
			try {
				pstmt = con.prepareStatement(updateSQL + updateSQL1);
				String testsql = updateSQL + updateSQL1;
				System.out.println(testsql);
				pstmt.setInt(1, nc.getCom_no());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				MyConnection.close(con, pstmt, null);
		}
	}
		
	
	
	//자신댓글선택삭제
	public void deleteByNo(int com_notice, int com_mem) throws DeleteException {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			}catch(SQLException e) {
				throw new DeleteException(e.getMessage());
			}
		PreparedStatement pstmt = null;
		String deleteSQL = "DELETE FROM NOTICECOMMENT WHERE com_notice = ? AND com_mem = ? ";
		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, com_notice);
			pstmt.setInt(2, com_mem);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}
	
	
	
	//댓글 어드민 강제삭제
	public void deleteByNoAdmin(int com_notice) throws DeleteException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			}catch(SQLException e) {
				throw new DeleteException(e.getMessage());
			}
		PreparedStatement pstmt = null;
		String deleteSQL = "DELETE NOTICECOMMENT WHERE com_notice = ?";
		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, com_notice);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}
	
	
	
	@Override
	public void insertNoticeComment(int NoticeCommnet_NC) throws AddException {
		// TODO Auto-generated method stub	
	}
	@Override
	public void updateNoticeComment(int NoticeCommnet_NC) throws ModifyException {
		// TODO Auto-generated method stub	
	}
	@Override
	public void deleteNoticeComment(int NoticeCommnet_no) throws DeleteException {
		// TODO Auto-generated method stub	
	}
		
	
	
	public static void main(String[] args) throws Exception {
		NoticeCommentDAOOracle dao = new NoticeCommentDAOOracle();
		
		//댓글검색 TEST
		//NoticeComment noticecomment = dao.selectByComNo(3);
		//System.out.println(noticecomment.getCom_no());
		//System.out.println(noticecomment.getCom_notice());
		//System.out.println(noticecomment.getCom_date());
		//System.out.println(noticecomment.getCom_contents());
		//System.out.println(noticecomment.getCom_mem());
		
		//댓글작성 TEST
		NoticeComment nc = new NoticeComment();
		nc.setCom_notice(3);
		nc.setCom_contents("hi");
		nc.setCom_mem(1);
		dao.insert(nc);
		
		//댓글수정 TEST
		//NoticeComment nc = new NoticeComment();
		//nc.setCom_notice(3);
		//nc.setCom_no(3);
		//nc.setCom_contents("바뀐타이틀11");
		//nc.setCom_mem(2);
		//dao.update(nc);
		
		//자신댓글선택삭제 TEST
		//NoticeComment noticecomment = new NoticeComment();
		//noticecomment.setCom_no(2);
		//dao.deleteByNo(3, 2);
		
		//Admin댓글강제삭제 TEST
		//NoticeComment noticecomment = new NoticeComment();
		//noticecomment.setCom_no(4);
		//dao.deleteByNoAdmin(1);
	
	}
}
