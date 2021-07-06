package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.sql.MyConnection;

public class MemberDAOOracle implements MemberDAO{

	
	public MemberDAOOracle() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
		return;
	}

	public void signUp(Member l) {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String InsertSQL = "INSERT INTO member VALUES (MEMBER_NO_SEQ.nextval,?,?,?,?,?,?,?)";
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
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//DB연결 해제
			MyConnection.close(con, pstmt, rs);
		}
		
	}

	public Member selectById(int no) throws FindException {//member_no로 회원정보가져오기
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String selectById = "SELECT * FROM member WHERE member_no = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member l = null;
		try {
			pstmt = con.prepareStatement(selectById);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//행의 컬럼값 얻기
				int member_no = rs.getInt("member_no");
				String member_social_type = rs.getString("member_social_type");
				String member_social_no = rs.getString("member_social_no");
				String member_nickName = rs.getString("member_nickName");
				String member_gender = rs.getString("member_gender");
				String member_email = rs.getString("member_email");
				String member_thumb = rs.getString("member_thumb");
				String member_birth = rs.getString("member_birth");
				l = new Member(member_no,member_social_type,member_social_no, member_nickName, member_gender, member_email,member_thumb,member_birth); //DB값 읽어와 DTO에 담기
			}
			if(l == null) {
				throw new FindException("조회된 id가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//DB연결 해제
			MyConnection.close(con, pstmt, rs);
		}return l;
		
	}

	public void update(Member c) {
		// TODO Auto-generated method stub
	}
	
	
	public static void main(String[] args) {

		try {
			MemberDAOOracle lo = new MemberDAOOracle();
			Member l =lo.selectById(1);
			System.out.println(l.getMember_no());
			System.out.println(l.getMember_nickName());
			System.out.println(l.getMember_email());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
