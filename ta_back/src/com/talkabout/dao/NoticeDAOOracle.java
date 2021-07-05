package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.sql.MyConnection;

public class NoticeDAOOracle {
	
	public NoticeDAOOracle() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
	}
	
	
	
	//공지게시글 보기
		public List<Notice> selectAll() throws FindException {
			Connection con = null;
			try {
			con = MyConnection.getConnection();
			}catch(SQLException e) {
				throw new FindException(e.getMessage());
			}
			String selectALLSQL = "SELECT*FROM NOTICE ORDER BY notice_no ASC";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Notice> list = new ArrayList<> ();
			try {
				pstmt = con.prepareStatement(selectALLSQL);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int notice_no = rs.getInt("notice_no");
					String notice_type = rs.getString("notice_type");
					String notice_title = rs.getString("notice_title");
					String notice_contents = rs.getString("notice_contents");
					Date notice_date = rs.getDate("notice_date");
					int notice_views = rs.getInt("notice_views");
		            String notice_admin = rs.getString("notice_admin");
		            Notice n = new Notice(notice_no, notice_type, notice_title,
		                     notice_contents, notice_date, notice_views, notice_admin);
		            list.add(n);
				}
				if(list.size() == 0) {
					throw new FindException("공지 없음");
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new FindException(e.getMessage());
			} finally {
				MyConnection.close(con, pstmt, rs);
			}	
		}
		
		
		
		
		
		
		public Notice selectByNoticeNo(int notice_no) {
			//DB연결
			Connection con = null;
			try {
			con = MyConnection.getConnection();
			}catch(Exception e) {
				e.printStackTrace();
				//throw new FindException(e.getMessage());
				//DB연결에 문제발생시 예외처리
			}
			String selectByTypeSQL = "SELECT * FROM NOTICE WHERE notice_no = ? ";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			//List<Notice> listnotice = new ArrayList<> ();
			Notice n = null;
			try {
				pstmt = con.prepareStatement(selectByTypeSQL);
				pstmt.setInt(1, notice_no);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					//행의 컬럼값 얻기
					int notice_number = rs.getInt("notice_no");
					String notice_type = rs.getString("notice_type");
					String notice_title = rs.getString("notice_title");
					String notice_contents = rs.getString("notice_contents");
					Date notice_date = rs.getDate("notice_date");
					int notice_views = rs.getInt("notice_views");
					String notice_admin = rs.getString("notice_admin"); //작성자 가져와야함 
					
					n = new Notice (notice_number, notice_type, notice_title,
							notice_contents, notice_date, notice_views, notice_admin);
					//listnotice.add(n);
				}
				//if(listnotice.size() == 0) { //게시글이 없는 경우
					//throw new FindException("게시글이 존재하지 않습니다");
				//}
				
			}
			catch(Exception e) {
				e.printStackTrace();
				//throw new FindException(e.getMessage());
			}finally {
				//DB연결해제 (안하면 메모리 누수 발생 가능성있음)
				MyConnection.close(con, pstmt, rs);
			}
			return n;
		}
	
		
		
	//공지 타입별 검색(전체/공지/업데이트/이벤트)
	public List<Notice> noticeSearch(String type, String contents) throws FindException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			throw new FindException(e.getMessage());
		}
		String selectByTypeSQL = "SELECT*FROM NOTICE WHERE notice_type = ? ORDER BY notice_no ASC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list_search = new ArrayList<> ();
		try {
			pstmt = con.prepareStatement(selectByTypeSQL);
			pstmt.setString(1, contents);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
				int notice_no = rs.getInt("notice_no");
				String notice_type = rs.getString("notice_type");
				String notice_title = rs.getString("notice_title");
				String notice_contents = rs.getString("notice_contents");
				Date notice_date = rs.getDate("notice_date");
				int notice_views = rs.getInt("notice_views");
	            int notice_admin = rs.getInt("notice_admin");
	            //Notice n = new Notice(notice_no, notice_type, notice_title,
	            //         notice_contents, notice_date, notice_views, notice_admin);
	            //list_search.add(n);
	         }
	         if(list_search.size() == 0) {
	            throw new FindException("공지 없음");
	         }
	         return list_search;
	      } catch (SQLException e) {
	         e.printStackTrace();
	         throw new FindException(e.getMessage());
	      } finally {
	         MyConnection.close(con, pstmt, rs);
		}
	}
		
	

	//공지작성
	public void insert(Notice einfo) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
		try {
			//insertInfo(con, einfo);
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
	public void insertInfo(Notice einfo) throws AddException{
		Connection con = null;
		try {
		con = MyConnection.getConnection();
		}catch(SQLException e) {
			//throw new FindException(e.getMessage());
		}
		PreparedStatement pstmt = null;
		String insertInfoSQL = "INSERT INTO NOTICE (notice_no, notice_type, notice_title, notice_contents, notice_date, notice_admin) VALUES(NOTICE_SEQ.nextval, ?, ?, ?, sysdate, 'ad1')";
		try {
			pstmt = con.prepareStatement(insertInfoSQL);
			pstmt.setString(1, einfo.getNotice_type());
			pstmt.setString(2, einfo.getNotice_title());
			pstmt.setString(3, einfo.getNotice_contents());
			pstmt.executeUpdate();
			if(pstmt.executeUpdate()==1) {
				System.out.println("공지 작성");
			}else {
				System.out.println("공지 작성 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(con, pstmt, null);
		} return;
	}
	
	
	
	//update
	public void update(Notice einfo) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String updateSQL = "UPDATE NOTICE SET ";
		String updateSQL1 = " WHERE notice_no = ?";

		NoticeDAOOracle dao;
		Notice dbNotice = null;
		try {
			
			dao = new NoticeDAOOracle();
			dbNotice = dao.selectByNoticeNo(einfo.getNotice_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean flag = false; //변경할 값이 있는 경우 true
		
		String Notice_type = einfo.getNotice_type();		
		if( Notice_type != null && !Notice_type.equals("") && !Notice_type.equals(dbNotice.getNotice_type())) {
			updateSQL += "Notice_type = '" + Notice_type + "'";
			flag = true;
		}		
		
		String Notice_title = einfo.getNotice_title();		
		if( Notice_title != null && !Notice_title.equals("")&& !Notice_title.equals(dbNotice.getNotice_title())) {
			if(flag) {
				updateSQL += ",";
			}		
			updateSQL += "Notice_title = '" + Notice_title + "'";
			flag = true;
		}		
			
		String Notice_contents = einfo.getNotice_contents();		
		if( Notice_contents != null && !Notice_contents.equals("")&& !Notice_contents.equals(dbNotice.getNotice_contents())) {
			if(flag) {
				updateSQL += ",";
			}	
			updateSQL += "Notice_contents = '" + Notice_contents + "'";
			flag = true;
		}

		
		if(!flag) {
			throw new ModifyException("수정할 내용이 없습니다");
		}
			try {
				pstmt = con.prepareStatement(updateSQL + updateSQL1);
				pstmt.setInt(1, einfo.getNotice_no());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			// TODO: handle finally clause
				MyConnection.close(con, pstmt, null);
		}
		
	}

	



	//공지삭제
	public void deleteByNoticeNo(int Notice_no) throws DeleteException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			}catch(SQLException e) {
				throw new DeleteException(e.getMessage());
			}
		PreparedStatement pstmt = null;
		String deleteSQL = "DELETE FROM NOTICE WHERE notice_no = ?";
		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, Notice_no);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}
	
	
	
	
	
	
	public static void main(String []arg) throws Exception {
		NoticeDAOOracle dao = new NoticeDAOOracle();
		
		
		//검색
		//List<Notice> list = new ArrayList<>();
		//list = dao.selectAll();
		//for (Notice notice : list) {
			//System.out.println(notice.getNotice_no());
			//System.out.println(notice.getNotice_contents());
			//System.out.println(notice.getNotice_title());
		//}
		
		
		//insert
		//Notice notice = new Notice();
		//notice.setNotice_type("공지용");
		//notice.setNotice_title("제목 공지 ");
		//notice.setNotice_contents("세번공지내용 ");
		//dao.insertInfo(notice);
		
		//삭제 
		//dao.deleteByNoticeNo(10);
		
		//selectByNo()
		//Notice notice = new Notice();
		//notice = dao.selectByNoticeNo(3);
		//System.out.println(notice.getNotice_no());
		//System.out.println(notice.getNotice_type());
		//System.out.println(notice.getNotice_title());
		//System.out.println(notice.getNotice_contents());
		//System.out.println(notice.getNotice_date());
		//System.out.println(notice.getNotice_views());
		//System.out.println(notice.getNotice_admin());
		
		
		
		//업데이트
		Notice notice = new Notice();
		notice.setNotice_no(3);
		notice.setNotice_type("바뀐타입11 ");
		notice.setNotice_title("바뀐타이틀11");
		notice.setNotice_contents("바뀐내용11");
		dao.update(notice);
	}
	
	
}
