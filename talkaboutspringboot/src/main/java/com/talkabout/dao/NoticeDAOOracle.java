package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.talkabout.dto.Admin;
import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.sql.MyConnection;

public class NoticeDAOOracle implements NoticeDAO {
	
	public NoticeDAOOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//lastRow();
		System.out.println("JDBC 드라이버 로드 성공");
	}
	
	
	public List<Notice> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT * FROM NOTICE";
		//String select_SQL = "SELECT * FROM ( SELECT ROWNUM AS RNUM, notice.* FROM notice) WHERE RNUM BETWEEN ? AND ?";
		List<Notice> list = new ArrayList();
		 
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
//			System.out.println("넘버 : "+num_page_no);
//			System.out.println("start_row"+num_start_row+" end_row : "+  num_end_row);
//			pstmt.setInt(1, num_start_row);
//			pstmt.setInt(2, num_end_row);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int notice_no = rs.getInt("NOTICE_NO");
				String notice_type = rs.getString("NOTICE_TYPE");
				String notice_title = rs.getString("NOTICE_TITLE");
				String notice_contents = rs.getString("NOTICE_CONTENTS");
				String notice_admin = rs.getString("NOTICE_ADMIN");
				String notice_date = rs.getString("NOTICE_DATE");
				//String notice_date = rs.getString("NOTICE_DATE");//시간값이....
				int notice_views = rs.getInt("NOTICE_VIEWS");
				
				
				Notice n = new Notice(notice_no, notice_type, notice_title, notice_contents, notice_admin, notice_date, notice_views, null, null);
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return list;
	}
	
	
	public Notice selectByNo(int notice_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select_SQL = "SELECT * FROM NOTICE WHERE NOTICE_NO = ?";
		Notice n = null;
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			pstmt.setInt(1, notice_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int not_no = rs.getInt("NOTICE_NO");
				String notice_type = rs.getString("NOTICE_TYPE");
				String notice_title = rs.getString("NOTICE_TITLE");
				String notice_contents = rs.getString("NOTICE_CONTENTS");
				String notice_admin = rs.getString("NOTICE_ADMIN");
				String notice_date = rs.getString("NOTICE_DATE");
				//String notice_date = rs.getString("NOTICE_DATE");//시간값이....
				int notice_views = rs.getInt("NOTICE_VIEWS");
				
				n = new Notice(notice_no, notice_type, notice_title, notice_contents, notice_admin, notice_date, notice_views, null, null);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return n;
	}
	
	
	public List<Notice> selectSearch(String type, String keyword){//컬럼 분류
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String select_SQL = "";
	      if (type.equals("공지")) {
	    	 select_SQL = "select * from notice where NOTICE_TYPE = ? ORDER BY notice_no ASC";
	      } else if (type.equals("업데이트")) {
	    	 select_SQL = "select * from notice where NOTICE_TYPE = ? ORDER BY notice_no ASC";
	      } else if (type.equals("이벤트")) {
		     select_SQL = "select * from notice where NOTICE_TYPE = ? ORDER BY notice_no ASC";
		      } 
	      List<Notice> list = new ArrayList();
	      try {
	         con = MyConnection.getConnection();
	         pstmt = con.prepareStatement(select_SQL);
	         pstmt.setString(1, keyword);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
					int notice_no = rs.getInt("NOTICE_NO");
					String notice_type = rs.getString("NOTICE_TYPE");
					String notice_title = rs.getString("NOTICE_TITLE");
					String notice_contents = rs.getString("NOTICE_CONTENTS");
					String notice_admin = rs.getString("NOTICE_ADMIN");
					String notice_date = rs.getString("NOTICE_DATE");
					//String notice_date = rs.getString("NOTICE_DATE");//시간값이....
					int notice_views = rs.getInt("NOTICE_VIEWS");

	            Notice n = new Notice(notice_no, notice_type, notice_title, notice_contents, notice_admin, notice_date, notice_views, null, null);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();   
	      } finally {
	         //DB연결해제
	         MyConnection.close(con, pstmt, rs);
	      }
	      return list;
	   }


	public void insertNotice(Notice einfo) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
		try {
			insertInfo(con, einfo);
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
	public void insertInfo(Connection con, Notice einfo) throws AddException{
		PreparedStatement pstmt = null;
		String insertInfoSQL = "INSERT INTO NOTICE (notice_no, notice_type, notice_title, notice_contents, notice_date, notice_admin) VALUES(NOTICE_SEQ.nextval, ?, ?, ?, sysdate, 'ad1')";
		
		try {
			pstmt = con.prepareStatement(insertInfoSQL);
			pstmt.setString(1, einfo.getNotice_type());
			pstmt.setString(2, einfo.getNotice_title());
			pstmt.setString(3, einfo.getNotice_contents());
			int rs = pstmt.executeUpdate();
			if(rs==1) {
				System.out.println("공지 작성");
			}else {
				System.out.println("공지 작성 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(null, pstmt, null);
		}
	}
	
	
	
	//update
	public void updateNotice(Notice noti) throws ModifyException {
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
			dbNotice = dao.selectByNo(noti.getNotice_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean flag = false; //변경할 값이 있는 경우 true
		
		String Notice_type = noti.getNotice_type();		
		if( Notice_type != null && !Notice_type.equals("") && !Notice_type.equals(dbNotice.getNotice_type())) {
			updateSQL += "Notice_type = '" + Notice_type + "'";
			flag = true;
		}		
		
		String Notice_title = noti.getNotice_title();		
		if( Notice_title != null && !Notice_title.equals("")&& !Notice_title.equals(dbNotice.getNotice_title())) {
			if(flag) {
				updateSQL += ",";
			}		
			updateSQL += "Notice_title = '" + Notice_title + "'";
			flag = true;
		}		
			
		String Notice_contents = noti.getNotice_contents();		
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
				pstmt.setInt(1, noti.getNotice_no());
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
	public void deleteNotice(int noti_no) throws DeleteException {
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
			pstmt.setInt(1, noti_no);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, null);
		}
	}
	
	
	
	//게시글 조회수
	public void updateCount(int Notice_no) throws ModifyException {
	      //DB연결
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int count = 0;
	      try {
	         con = MyConnection.getConnection();
	         }catch(SQLException e) {
	            throw new ModifyException(e.getMessage());
	            //DB연결에 문제발생시 예외처리
	         }
	      
	      
	      try {
	         String getReadCountSql = "select notice_views from notice where notice_no = ?";
	         pstmt = con.prepareStatement(getReadCountSql);
	         pstmt.setInt(1, Notice_no);
	         rs = pstmt.executeQuery();
	         if(rs.next() ) {
	            count = rs.getInt(1);
	            count++;
	         }
	         String sql = "UPDATE NOTICE SET notice_views = ? WHERE notice_no = ?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, count);
	         pstmt.setInt(2, Notice_no);
	         pstmt.executeUpdate();
	         

	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         MyConnection.close(con, pstmt, rs);
	      }
	   }
	public int num_page_size; //1페이지당 사이즈
	public int num_page_no = 1; //페이지번호
	public int lastrow; // row개수

	public void pageSize(int size) {
		this.num_page_size = size;
		System.out.println("페이지 사이즈 : "+num_page_size);
	}

		public void pageNum(int page) {
			this.num_page_no = page;
			System.out.println("페이지번호 : "+num_page_no);
		}
		//마지막 row 가져오기
		public int lastRow() {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String select_SQL = "SELECT RNUM FROM (SELECT ROWNUM AS RNUM FROM debate ORDER BY ROWNUM DESC) WHERE ROWNUM = 1";
			try {
				con = MyConnection.getConnection();
				pstmt = con.prepareStatement(select_SQL);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					lastrow = rs.getInt("rnum");
					System.out.println("총 게시글게수 : "+lastrow);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();	
			} finally {
				//DB연결해제
				MyConnection.close(con, pstmt, rs);
			}
			return lastrow;
		}
	
	
	
	public static void main(String []arg) throws Exception {
		NoticeDAOOracle dao = new NoticeDAOOracle();
		
		
		//검색
//		List<Notice> list = new ArrayList<>();
//		list = dao.selectAll();
//		for (Notice notice : list) {
//			System.out.println(notice.getNotice_no());
//			System.out.println(notice.getNotice_contents());
//			System.out.println(notice.getNotice_title());
//		}
		
		
		
		//작성
		Notice notice = new Notice();
		notice.setNotice_type("공지용2");
		notice.setNotice_title("제목 공지2 ");
		notice.setNotice_contents("세번공지내용2 ");
		dao.insertNotice(notice);
		System.out.println("1번");
	}

		
		//삭제 
//		dao.deleteNotice(21);
		
//		selectByNo();
//		Notice notice = new Notice();
//		notice = dao.selectByNo(3);
//		System.out.println(notice.getNotice_no());
//		System.out.println(notice.getNotice_type());
//		System.out.println(notice.getNotice_title());
//		System.out.println(notice.getNotice_contents());
//		System.out.println(notice.getNotice_date());
//		System.out.println(notice.getNotice_views());
//		System.out.println(notice.getNotice_admin());
		
		
		
		//업데이트
//		Notice noti = new Notice();
//		noti.setNotice_no(2);
//		noti.setNotice_type("바뀐타입11 ");
//		noti.setNotice_title("바뀐타이틀11");
//		noti.setNotice_contents("바뀐내용11");
//		dao.updateNotice(noti);
//	}


//	@Override
//	public void insertNotice(Notice noti) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void updateNotice(int einfo) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void deleteNotice(Notice notice_no) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public List<Notice> noticeSearch() throws FindException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Notice> selectSearch(String type, String contents) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void insertNotice(int einfo) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateNotice(int einfo) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteNotice(Notice notice_no) {
//		// TODO Auto-generated method stub
//		
//	}
	
}

