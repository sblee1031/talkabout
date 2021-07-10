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
<<<<<<< HEAD
	private int com_deb; // com_deb.debate_no
	private String com_contents;
	private Date com_date;
	private int com_mem; // com_mem.member_no
<<<<<<< HEAD
	String com_admin;
	
	
	public String getCom_admin() {
		return com_admin;
	}

	public void setCom_admin(String com_admin) {
		this.com_admin = com_admin;
	}

	public DebateComment() {
		super();
		// TODO Auto-generated constructor stub
	}

=======
	
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
	public DebateComment(int com_no, int com_deb, String com_contents, Date com_date, int com_mem) {
=======
	private Debate com_deb; // com_deb.debate_no
	private String com_contents;
	private Date com_date;
	private Member com_mem; // com_mem.member_no
	
	public DebateComment(int com_no, Debate com_deb, String com_contents, Date com_date, Member com_mem) {
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
		super();
		this.com_no = com_no;
		this.com_deb = com_deb;
		this.com_contents = com_contents;
		this.com_date = com_date;
		this.com_mem = com_mem;
	}
<<<<<<< HEAD
<<<<<<< HEAD
	public DebateComment(int com_no, int com_deb, String com_contents, Date com_date, String com_admin) {
		super();
		this.com_no = com_no;
		this.com_deb = com_deb;
		this.com_contents = com_contents;
		this.com_date = com_date;
		this.com_admin = com_admin;
	}
=======
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
=======
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8

	public int getCom_no() {
		return com_no;
	}

	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}

<<<<<<< HEAD
	public int getCom_deb() {
		return com_deb;
	}

	public void setCom_deb(int com_deb) {
=======
	public Debate getCom_deb() {
		return com_deb;
	}

	public void setCom_deb(Debate com_deb) {
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
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

<<<<<<< HEAD
	public int getCom_mem() {
		return com_mem;
	}

	public void setCom_mem(int com_mem) {
		this.com_mem = com_mem;
	}
<<<<<<< HEAD

	@Override
	public String toString() {
		return "DebateComment [com_no=" + com_no + ", com_deb=" + com_deb + ", com_contents=" + com_contents
				+ ", com_date=" + com_date + ", com_mem=" + com_mem + "]";
	}
	
=======
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
=======
	public Member getCom_mem() {
		return com_mem;
	}

	public void setCom_mem(Member com_mem) {
		this.com_mem = com_mem;
	}
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
	
}
