package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.talkabout.dto.Board;
import com.talkabout.dto.BoardLike;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.sql.MyConnection;

public class BoardDAOOracle implements BoardDAO{
	
	public BoardDAOOracle() throws Exception {
		//JDBC드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
	}

	
	public List<Board> boardSearch(String type, String contents) throws FindException {
		//DB연결
		Connection con = null;
		try {
		con = MyConnection.getConnection();
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		String selectByTypeSQL = "SELECT*FROM BOARD WHERE +" +type +" = ? ORDER BY board_no ASC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list_search = new ArrayList<> ();
		try {
			pstmt = con.prepareStatement(selectByTypeSQL);
			pstmt.setString(1, contents);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//행의 컬럼값 얻기
				int board_no = rs.getInt("board_no");
				String board_type = rs.getString("board_type");
				String board_title = rs.getString("board_title");
				String board_contents = rs.getString("board_contents");
				Date board_date = rs.getDate("board_date");
				int board_views = rs.getInt("board_views");
				int board_mem = rs.getInt("board_mem"); //작성자 가져와야함 
				
				Board b = new Board (board_no, board_type, board_title,
							board_contents, board_date, board_views, board_mem);
				list_search.add(b);
			}
			if(list_search.size() == 0) { //게시글이 없는 경우
				throw new FindException("게시글이 존재하지 않습니다");
			}
			return list_search;
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
		}finally {
			//DB연결해제 (안하면 메모리 누수 발생 가능성있음)
			MyConnection.close(con, pstmt, rs);
		}
	}

	
	public List<Board> selectAll() throws FindException {
		//DB연결
		Connection con = null;
		try {
		con = MyConnection.getConnection();
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		String selectALLSQL = "SELECT*FROM BOARD ORDER BY board_no ASC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<> ();
		try {
			pstmt = con.prepareStatement(selectALLSQL);
			rs = pstmt.executeQuery();
			//커서 이동한 위치에 행이 존재할 경우 true
			//커서 이동한 위치에 행이 존재하지 않을 경우 false
			while(rs.next()) {
				//행의 컬럼값 얻기
				int board_no = rs.getInt("board_no");
				String board_type = rs.getString("board_type");
				String board_title = rs.getString("board_title");
				String board_contents = rs.getString("board_contents");
				Date board_date = rs.getDate("board_date");
				int board_views = rs.getInt("board_views");
				int board_mem = rs.getInt("board_mem");
				
				Board b = new Board(board_no, board_type, board_title,
							board_contents, board_date, board_views, board_mem);
				list.add(b);
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
	public Board selectByBoardNo(int board_no) throws FindException {
		//DB연결
				Connection con = null;
				try {
				con = MyConnection.getConnection();
				}catch(SQLException e) {
					throw new FindException(e.getMessage());
					//DB연결에 문제발생시 예외처리
				}
				String selectByTypeSQL = "SELECT*FROM BOARD WHERE board_no = ? ";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<Board> listboard = new ArrayList<> ();
				Board b = null;
				try {
					pstmt = con.prepareStatement(selectByTypeSQL);
					pstmt.setInt(1, board_no);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						//행의 컬럼값 얻기
						int board_number = rs.getInt("board_no");
						String board_type = rs.getString("board_type");
						String board_title = rs.getString("board_title");
						String board_contents = rs.getString("board_contents");
						Date board_date = rs.getDate("board_date");
						int board_views = rs.getInt("board_views");
						int board_mem = rs.getInt("board_mem"); //작성자 가져와야함 
						
						b = new Board (board_number, board_type, board_title,
									board_contents, board_date, board_views, board_mem);
						listboard.add(b);
					}
					if(listboard.size() == 0) { //게시글이 없는 경우
						throw new FindException("게시글이 존재하지 않습니다");
					}
					return b;
				}catch(SQLException e) {
					throw new FindException(e.getMessage());
				}finally {
					//DB연결해제 (안하면 메모리 누수 발생 가능성있음)
					MyConnection.close(con, pstmt, rs);
				}
	}

	@Override
	public void insert(Board binfo) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false);//자동커밋 해제
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
			//DB연결에 문제발생시 예외처리
		}
		try {
			insertInfo(con, binfo);
			con.commit(); //커밋
		} catch (Exception e) {
			try {
				con.rollback(); //롤백
			} catch (SQLException e1) {
			}
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(con, null, null);
		}
	} 
	/**
	 * 게시글 작성
	 * @param con DB연결객체
	 * @param binfo 게시글 정보
	 * @throws AddException
	 */
	private void insertInfo(Connection con, Board binfo) throws AddException{
		//SQL송신
		PreparedStatement pstmt = null;
		String insertInfoSQL = "INSERT INTO BOARD (board_no, board_type, board_title, board_contents, board_date, board_mem) VALUES(5, ?, ?, ?, sysdate, 1)";
		try {
			pstmt = con.prepareStatement(insertInfoSQL);
//			pstmt.setInt(1, binfo.getBoard_no());
			pstmt.setString(1, binfo.getBoard_type());
			pstmt.setString(2, binfo.getBoard_title());
			pstmt.setString(3, binfo.getBoard_contents());
//			pstmt.setInt(4, x);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException("주문기본추가실패:" + e.getMessage());
		}finally {
			MyConnection.close(null, pstmt, null);
		}	
	}

	
	//질문
	@Override
	public void update(Board binfo) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String updateSQL = "UPDATE BOARD SET "; // board_type, board_title, board_contents
		String updateSQL1 = " WHERE board_no = ?";
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		StringBuilder sql = new StringBuilder();
//		sql.append("UPDATE BOARD SET ")
//			.append("	board_type = ?")
//			.append("	board_title = ?")
//			.append("	board_contents = ?");
		
		try {
			pstmt = con.prepareStatement(updateSQL + updateSQL1);
			
			pstmt.setString(1, binfo.getBoard_type());
			pstmt.setString(2, binfo.getBoard_title());
			pstmt.setString(3, binfo.getBoard_contents());
			if (pstmt.executeUpdate() ==1 ) {
				System.out.println("게시글 수정에 성공하였습니다.");
			}else {
				System.out.println("게시글 수정에 실패하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(con, pstmt, null);
		} return;
//		String updateSQL = "UPDATE BOARD SET "; // board_type, board_title, board_contents
//		String updateSQL1 = " WHERE board_no = ?";
//		
//		boolean flag = false; //변경할 값이 있는 경우 true
//		
//		String Board_type = binfo.getBoard_type();		
//		if( Board_type != null && !Board_type.equals("")) {
//			updateSQL += "Board_type = '" + Board_type + "'";
//			flag = true;
//		}		
//		
//		String Board_title = binfo.getBoard_title();		
//		if( Board_title != null && !Board_title.equals("")) {
//			if(flag) {
//				updateSQL += ",";
//			}		
//			updateSQL += "Board_title = '" + Board_title + "'";
//			flag = true;
//		}		
//			
//		String Board_contents = binfo.getBoard_contents();		
//		if( Board_contents != null && !Board_contents.equals("")) {
//			if(flag) {
//				updateSQL += ",";
//			}	
//			updateSQL += "Board_contents = '" + Board_contents + "'";
//			flag = true;
//		}
//
//		if(!flag) {
//			throw new ModifyException("수정할 내용이 없습니다");
//		}
		
		
	}

	//자식 객체 때문에 board_no로 지우면 안지워짐;; 찾아봤지만... 이해가 안됨...
	@Override
	public void deleteByBoardNo(int Board_no) throws DeleteException {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			}catch(SQLException e) {
				throw new DeleteException(e.getMessage());
				//DB연결에 문제발생시 예외처리
			}
		
		PreparedStatement pstmt = null;
		String deleteSQL = "DELETE BOARD WHERE board_no = ?";
		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, Board_no);
			if(pstmt.executeUpdate()==1) {
				System.out.println("게시글을 성공적으로 삭제했습니다.");
			}else {
				System.out.println("게시글 삭제에 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(con, pstmt, null);
		}
		return;
	}

	@Override
	public void insert(BoardLike BL) throws AddException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByBlNo(int BoardLike_no) throws DeleteException {
		// TODO Auto-generated method stub
		
	}
	

	public static void main(String[] args) throws Exception {
		BoardDAOOracle dao = new BoardDAOOracle();
//		List<Board> list = new ArrayList<>();
//		try {
//			list = dao.selectAll();
//			for (Board board : list) {
//				System.out.print(board.getBoard_no() + " ");
//				System.out.print(board.getBoard_type()+ " ");
//				System.out.print(board.getBoard_title()+ " ");
//				System.out.print(board.getBoard_date()+ " ");
//				System.out.print(board.getBoard_contents() + "//	");
//				
//			}
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String type = "board_title";
//		String contents = "궁시렁";
//		List<Board> list = new ArrayList<>();
//		list = dao.boardSearch(type, contents);
//		
//		for (Board board : list) {
//			System.out.print(board.getBoard_no() + " ");
//			System.out.print(board.getBoard_type()+ " ");
//			System.out.print(board.getBoard_title()+ " ");
//			System.out.print(board.getBoard_date()+ " ");
//			System.out.print(board.getBoard_contents() + "//	");
//			
//		}
	
//		Board b = new Board();
//		b.setBoard_no(2);
//		b.setBoard_type("해외축구");
//		try {
//		dao.update(b);
//		}catch (ModifyException e) {
//			e.printStackTrace();
//		}
//		System.out.println(b.getBoard_type());
//		dao.deleteByBoardNo(3);
		
		//-------
		Board board =  dao.selectByBoardNo(1);
		System.out.println(board.getBoard_mem());
		System.out.println(board.getBoard_title());
		System.out.println(board.getBoard_contents());
		System.out.println("board_mem : "+board.getBoard_mem());
		System.out.println("===================");
		MemberDAOOracle memberdao = new MemberDAOOracle();
		Member member = memberdao.selectById(board.getBoard_mem());
		System.out.println("작성자 닉 : " +member.getMember_nickName());
		System.out.println("작성자 메일 : " +member.getMember_email());
	}
	
}
