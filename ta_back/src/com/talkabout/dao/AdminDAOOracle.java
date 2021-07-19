package com.talkabout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.talkabout.dto.Admin;
import com.talkabout.dto.Debate;
import com.talkabout.exception.FindException;
import com.talkabout.sql.MyConnection;

public class AdminDAOOracle implements AdminDAO {

	@Override
	public Admin selectByNo(int admin_no) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin AdLogin(String id, String pwd) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM admin WHERE admin_id = ? and admin_pwd = ?";
		Admin a = null;
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String admin_id = rs.getString("admin_id");
				String admin_pwd = rs.getString("admin_pwd");
				a = new Admin(admin_id, admin_pwd);
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}		
		return a;
	}

}
