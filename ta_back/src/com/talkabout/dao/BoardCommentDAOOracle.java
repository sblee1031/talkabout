package com.talkabout.dao;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import com.talkabout.dto.Board;
import com.talkabout.dto.BoardComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.sql.MyConnection;

public class BoardCommentDAOOracle implements BoardCommentDAO{
	public BoardCommentDAOOracle() throws Exception {
		//JDBC드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
	}

	@Override
	public List<BoardComment> myBoardComSearch(int com_board, int com_mem) throws FindException {
		//DB연결
		Connection con = null;
		try {
		con = MyConnection.getConnection();
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		String selectMyBCSQL = "SELECT*FROM BOARDCOMMENT WHERE com_board = " +"? and"+" com_mem = " + "?" + "ORDER BY com_no ASC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardComment> list = new ArrayList<> ();
		try {
			pstmt = con.prepareStatement(selectMyBCSQL);
			pstmt.setInt(1, com_board);
			pstmt.setInt(2, com_mem);
			rs = pstmt.executeQuery();
			//커서 이동한 위치에 행이 존재할 경우 true
			//커서 이동한 위치에 행이 존재하지 않을 경우 false
			while(rs.next()) {
				//행의 컬럼값 얻기
				int com_no = rs.getInt("com_no");
				int com_board_no = rs.getInt("com_board");
				Date com_date = rs.getDate("com_date");
				String com_contents = rs.getString("com_contents");
				int com_member_no = rs.getInt("com_mem");
				
				
				BoardComment BC = new BoardComment(com_no, com_board_no, com_date,
						com_contents, com_member_no);
				list.add(BC);
			}
			if(list.size() == 0) {//게시글이 없는 경우
				throw new FindException("게시글이 없습니다");
			}
			return list; //게시글이 있는 상태 (0보다 클때)
		} catch (SQLException e) {
			e.printStackTrace(); //콘솔에 예외종류, 내용, 줄번호 출력
			throw new FindException(e.getMessage());
		} finally {
			//DB연결해제 (안하면 메모리 누수 발생 가능성있음)
			MyConnection.close(con, pstmt, rs);
		}
	}

	@Override
	public List<BoardComment> selectAll() throws FindException {
		//DB연결
				Connection con = null;
				try {
				con = MyConnection.getConnection();
				}catch(SQLException e) {
					throw new FindException(e.getMessage());
					//DB연결에 문제발생시 예외처리
				}
				String selectALLSQL = "SELECT*FROM BoardComment where com_board_no = ? ORDER BY com_no ASC";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<BoardComment> list = new ArrayList<> ();
				try {
					pstmt = con.prepareStatement(selectALLSQL);
					rs = pstmt.executeQuery();
					//커서 이동한 위치에 행이 존재할 경우 true
					//커서 이동한 위치에 행이 존재하지 않을 경우 false
					while(rs.next()) {
						//행의 컬럼값 얻기
						int com_no = rs.getInt("com_no");
						int com_board_no = rs.getInt("com_board");
						Date com_date = rs.getDate("com_date");
						String com_contents = rs.getString("com_contents");
						int com_member_no = rs.getInt("com_mem");
						
						BoardComment BC = new BoardComment(com_no, com_board_no, com_date,
								com_contents, com_member_no);
						list.add(BC);
					}
					if(list.size() == 0) {//게시글이 없는 경우
						throw new FindException("게시글이 없습니다");
					}
					return list; //게시글이 있는 상태 (0보다 클때)
				} catch (SQLException e) {
					e.printStackTrace(); //콘솔에 예외종류, 내용, 줄번호 출력
					throw new FindException(e.getMessage());
				} finally {
					//DB연결해제 (안하면 메모리 누수 발생 가능성있음)
					MyConnection.close(con, pstmt, rs);
				}
	}

	@Override
	public BoardComment selectByComNo(int com_no) throws FindException {
		//DB연결
				Connection con = null;
				try {
					con = MyConnection.getConnection();
				}catch(SQLException e) {
					throw new FindException(e.getMessage());
					//DB연결에 문제발생시 예외처리
				}
				String selectByComNo = "SELECT*FROM BOARDCOMMENT WHERE com_no = ?";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<BoardComment> listboardcomment = new ArrayList<>();
				BoardComment bc = null;
				try {
					pstmt = con.prepareStatement(selectByComNo);
					pstmt.setInt(1, com_no);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						//행의 칼럼값 얻기
						int com_number = rs.getInt("com_no");
						int com_board = rs.getInt("com_board");
						Date com_date = rs.getDate("com_date");
						String com_contents = rs.getString("com_contents");
						int com_mem = rs.getInt("com_mem");
						
						bc = new BoardComment(com_number, com_board, com_date,com_contents, com_mem);
						listboardcomment.add(bc);
					}
					if(listboardcomment.size() == 0) { //게시글이 없는경우
						throw new FindException("댓글이 존재하지 않습니다.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//DB연결해제
					MyConnection.close(con, pstmt, rs);
				}
				return bc;
	}

	@Override
	public void insert(BoardComment bcinfo) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false); //자동커밋
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		try {
			insertInfo(con, bcinfo);
			con.commit(); //커밋
		} catch (Exception e) {
			try {
				con.rollback(); //롤백
			} catch (SQLException e1) {
			} 
			throw new AddException(e.getMessage());
		}finally {
			//DB연결 해제
			MyConnection.close(con, null, null);
		}
	}
	
	private void insertInfo(Connection con, BoardComment bcinfo) throws AddException {
		//SQL송신
		PreparedStatement pstmt = null;
		String insertInfoSQL = "INSERT INTO BOARDCOMMENT VALUES (BC_SEQ.NEXTVAL, ?, sysdate, ?, ?)";
		try {
			pstmt = con.prepareStatement(insertInfoSQL);
			pstmt.setInt(1, bcinfo.getCom_board());
			pstmt.setString(2, bcinfo.getCom_contents());
			pstmt.setInt(3, bcinfo.getCom_mem());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException("댓글 추가 실패" + e.getMessage());
		} finally {
			MyConnection.close(null, pstmt, null);
		}
	}
		
	



	@Override
	public void update(BoardComment bc) throws ModifyException {
		//DB연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
			//DB연결에 문제발생시 예외처리
		}
		String updateSQL = "UPDATE BOARDCOMMENT SET "; // com_contetns;
		String updateSQL1 = "WHERE com_no = ?";
		
		BoardCommentDAOOracle dao;
		BoardComment dbBoardComment = null;
		try {
			dao = new BoardCommentDAOOracle();
			dbBoardComment = dao.selectByComNo(bc.getCom_no());
		}catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = false; //변경할 값이 있는 경우 true
		
		String Com_contents = bc.getCom_contents();
		if(Com_contents != null && !Com_contents.equals("") && !Com_contents.contentEquals(dbBoardComment.getCom_contents())) {
			updateSQL += "com_contents = '" + Com_contents + "'";
			flag = true;
		}
		if(!flag) {
			throw new ModifyException("수정할 내용이 없습니다");
		}
			try {
				pstmt = con.prepareStatement(updateSQL + updateSQL1);
				pstmt.setInt(1, bc.getCom_no());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				MyConnection.close(con, pstmt, null);
		}
		
	}
		
	

	@Override
	public void deleteByNo(int com_no) throws DeleteException {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			}catch(SQLException e) {
				throw new DeleteException(e.getMessage());
				//DB연결에 문제발생시 예외처리
			}
		PreparedStatement pstmt = null;
		String deleteSQL = "DELETE BOARDCOMMENT WHERE com_no = ?";
		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, com_no);
			pstmt.executeUpdate();
//			if() {
//				System.out.println("댓글을 성공적으로 삭제했습니다.");
//			}else {
//				System.out.println("댓글 삭제에 실패했습니다.");
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, null);
		}
		
	}
	public static void main(String[] args) throws Exception {
		BoardCommentDAOOracle dao = new BoardCommentDAOOracle();
		
//		BoardComment boardcomment = dao.selectByComNo(6);
//		System.out.println(boardcomment.getCom_no());
//		System.out.println(boardcomment.getCom_board());
//		System.out.println(boardcomment.getCom_date());
//		System.out.println(boardcomment.getCom_contents());
//		System.out.println(boardcomment.getCom_mem());
		
		
		
//		BoardComment boardcomment = new BoardComment();
//		boardcomment.setCom_no(1);
//		boardcomment.setCom_contents("바껴라 좀");
//		dao.update(boardcomment);
//		dao.deleteByNo(6);
		
		BoardComment bc = new BoardComment();
		bc.setCom_board(2);
		bc.setCom_contents("왜 안되요?");
		bc.setCom_mem(2);
		dao.insert(bc);
		
	}
}







=======
public class BoardCommentDAOOracle {

}
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
