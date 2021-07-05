package com.talkabout.dto;

import java.util.Date;

public class DebateComment {
	/*
	 * Table : 토론 댓글 테이블
	 * 
	 * com_no : 토론결과 댓글번호(PK)
	 * com_deb : 토론번호 (FK, Debate 테이블 참조)
	 * com_contents : 토론결과 댓글 내용
	 * com_date : 토론결과 댓글 작성일
	 * com_mem : 회원번호 (FK, Member 테이블 참조) 
	 */
	private int com_no;
	private int com_deb; // com_deb.debate_no
	private String com_contents;
	private Date com_date;
	private int com_mem; // com_mem.member_no
	
	public DebateComment(int com_no, int com_deb, String com_contents, Date com_date, int com_mem) {
		super();
		this.com_no = com_no;
		this.com_deb = com_deb;
		this.com_contents = com_contents;
		this.com_date = com_date;
		this.com_mem = com_mem;
	}

	public int getCom_no() {
		return com_no;
	}

	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}

	public int getCom_deb() {
		return com_deb;
	}

	public void setCom_deb(int com_deb) {
		this.com_deb = com_deb;
	}

	public String getCom_contents() {
		return com_contents;
	}

	public void setCom_contents(String com_contents) {
		this.com_contents = com_contents;
	}

	public Date getCom_date() {
		return com_date;
	}

	public void setCom_date(Date com_date) {
		this.com_date = com_date;
	}

	public int getCom_mem() {
		return com_mem;
	}

	public void setCom_mem(int com_mem) {
		this.com_mem = com_mem;
	}
	
}
