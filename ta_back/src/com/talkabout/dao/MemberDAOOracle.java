package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.sql.MyConnection;

public class MemberDAOOracle implements MemberDAO{

	
	public MemberDAOOracle() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
		return;
	}

	public void createMember(Member l) throws AddException{
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String InsertSQL = "INSERT INTO member VALUES (MEM_SEQ.nextval,?,?,?,?,?,?,?,1)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(InsertSQL);
			//pstmt.setString(1, "MEMBER_NO_SEQ.nextval");
			pstmt.setString(1, l.getMember_social_type());
			pstmt.setString(2, l.getMember_social_no());
			pstmt.setString(3, l.getMember_nickName());
			pstmt.setString(4, l.getMember_gender());
			pstmt.setString(5, l.getMember_email());
			pstmt.setString(6, l.getMember_thumb());
			pstmt.setString(7, l.getMember_birth());
			int result = pstmt.executeUpdate();
			System.out.println("가입회원 추가 : "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//DB연결 해제
			MyConnection.close(con, pstmt, rs);
		}
		
	}

	@Override
	public Member selectByNo(int mem_no) throws FindException {//member_no로 회원정보가져오기
		//DB연결
				Connection con = null;
				try {
					con = MyConnection.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String selectByNo = "SELECT * FROM member WHERE member_no = ? AND member_able = 1";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				Member l = null;
				try {
					pstmt = con.prepareStatement(selectByNo);
					pstmt.setInt(1, mem_no);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						//행의 컬럼값 얻기
						int member_no = rs.getInt("member_no");
						String member_nickName = rs.getString("member_nickName");
						String member_social_no = rs.getNString("member_social_no");
						String member_social_type = rs.getString("member_social_type");
						String member_gender = rs.getString("member_gender");
						String member_email = rs.getString("member_email");
						String member_thumb = rs.getString("member_thumb");
						String member_birth = rs.getString("member_birth");
						int member_able = rs.getInt("member_able");
						l = new  Member(member_no, member_social_type, member_social_no, member_nickName,
								member_gender, member_email, member_thumb, member_birth, member_able); //DB값 읽어와 DTO에 담기
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					//DB연결 해제
					MyConnection.close(con, pstmt, rs);
				}return l;
	}
	//member_social_no로 회원정보가져오기
	public Member selectByNo(String social_no) throws FindException {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String selectById = "SELECT * FROM member WHERE member_social_no = ? AND member_able = 1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member l = null;
		try {
			pstmt = con.prepareStatement(selectById);
			pstmt.setString(1, social_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//행의 컬럼값 얻기
				int member_no = rs.getInt("member_no");
				String member_nickName = rs.getString("member_nickName");
				String member_social_no = rs.getNString("member_social_no");
				String member_social_type = rs.getString("member_social_type");
				String member_gender = rs.getString("member_gender");
				String member_email = rs.getString("member_email");
				String member_thumb = rs.getString("member_thumb");
				String member_birth = rs.getString("member_birth");
				int member_able = rs.getInt("member_able");
				l = new  Member(member_no, member_social_type, member_social_no, member_nickName,
						member_gender, member_email, member_thumb, member_birth,member_able); //DB값 읽어와 DTO에 담기
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//DB연결 해제
			MyConnection.close(con, pstmt, rs);
		}return l;
		
	}

	//닉네임 변경 메서드
	public void updateMember(Member m) throws FindException  {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String updateNick = "UPDATE MEMBER SET MEMBER_NICKNAME = '"+m.getMember_nickName()+"' WHERE MEMBER_NO = "+m.getMember_no();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//Member l = null;
		try {
			pstmt = con.prepareStatement(updateNick);
			int result = pstmt.executeUpdate();
			if( result==0) {
				throw new FindException("해당 변경 값 찾기 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally{
			//DB연결 해제
			MyConnection.close(con, pstmt, rs);
		}
	}
	@Override
	public Boolean selectNick(Member m) throws FindException {
		//닉네임 중복체크 : false (사용가능) , true(닉네임중복)
		//DB연결
		Connection con = null;
		boolean result = false;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String checkNick = "SELECT member_nickname FROM member WHERE member_nickname = ? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(checkNick);
			pstmt.setString(1, m.getMember_nickName());
			rs = pstmt.executeQuery();
			result = rs.next();
//			System.out.println("rs : "+ rs.getString(1));
//			System.out.println("rs : "+ result);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally{
			//DB연결 해제
			MyConnection.close(con, pstmt, rs);
		}return result;
	}
		
	//회원 탈퇴 메서드
	public void deleteMember(Member m) {
		//DB연결
				Connection con = null;
				try {
					con = MyConnection.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String leaveMember = "UPDATE MEMBER SET member_able = 2 WHERE MEMBER_NO = "+m.getMember_no();
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = con.prepareStatement(leaveMember);
					pstmt.executeUpdate();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				}finally{
					//DB연결 해제
					MyConnection.close(con, pstmt, rs);
				}
	}
		
	
	
	public static void main(String[] args)  {
		
		try {
		MemberDAOOracle lo= new MemberDAOOracle();
		Member m = new Member();
		m.setMember_social_type("구글");
		m.setMember_social_no("24844572");
		m.setMember_nickName("바부");
		m.setMember_gender("남자");
		m.setMember_email("sdf@nsdf.com");
		m.setMember_thumb("http://");
		m.setMember_birth("2020-07");
		
		lo.createMember(m);
	} catch (Exception e) {
		e.printStackTrace();
	}
		
//		try {
//			MemberDAOOracle lo= new MemberDAOOracle();
//			Member m = new Member();
//			m.setMember_no(1);
//			lo.deleteMember(m);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		try {
//			MemberDAOOracle lo= new MemberDAOOracle();
//			Member m = new Member();
//			m.setMember_nickName("1또바뀜");
//			boolean boll = lo.selectNick(m);
//			System.out.println(boll);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
//		try {
//			MemberDAOOracle lo= new MemberDAOOracle();
//			Member m = new Member();
//			m.setMember_no(4);
//			m.setMember_nickName("또바뀜");
//			lo.updateMember(m);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		

		try {
			MemberDAOOracle lo1 = new MemberDAOOracle();
			Member l =lo1.selectByNo(3);
			System.out.println(l.getMember_no());
			System.out.println(l.getMember_nickName());
			System.out.println(l.getMember_email());
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}




}
