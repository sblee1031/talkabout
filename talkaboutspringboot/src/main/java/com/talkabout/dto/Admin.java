package com.talkabout.dto;

public class Admin {
	/*
	 * Table : 관리자 테이블
	 * 
	 * admin_id : 관리자 아이디(PK)
	 * admin_pwd : 관리자 비밀번호
	 */
	private String admin_id;
	private String admin_pwd;

	public Admin(String admin_id, String admin_pwd) {
		super();
		this.admin_id = admin_id;
		this.admin_pwd = admin_pwd;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_pwd() {
		return admin_pwd;
	}

	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}
}
