package com.talkabout.dto;

import java.util.Date;

public class BoardComment {
	/*
	 * Table : 자유게시판 댓글 테이블
	 * 
	 * com_no : 댓글번호(PK)
	 * com_date : 댓글 작성일
	 * com_contents : 댓글 내용
	 * com_mem : 회원번호(FK, Member 테이블 참조)
	 * com_board : 게시글 번호(FK, Board 테이블 참조)
	 */
	private int com_no;
	private Date com_date;
	private String com_contents;
<<<<<<< HEAD
<<<<<<< HEAD
	private Member com_mem; // com_mem.member_no
	private Board com_board; // com_board.board_no
	
	public BoardComment(int com_no, Date com_date, String com_contents, Member com_mem, Board com_board) {
		super();
		this.com_no = com_no;
		this.com_date = com_date;
		this.com_contents = com_contents;
		this.com_mem = com_mem;
		this.com_board = com_board;
=======
	private int com_mem; // com_mem.member_no
	private int com_board; // com_board.board_no
	
	public BoardComment() {
		super();
	}	
	
	public BoardComment(int com_no, int com_board, Date com_date, String com_contents, int com_mem) {
		super();
		this.com_no = com_no;
		this.com_board = com_board;
		this.com_date = com_date;
		this.com_contents = com_contents;
		this.com_mem = com_mem;
		
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
=======
	private Member com_mem; // com_mem.member_no
	private Board com_board; // com_board.board_no
	
	public BoardComment(int com_no, Date com_date, String com_contents, Member com_mem, Board com_board) {
		super();
		this.com_no = com_no;
		this.com_date = com_date;
		this.com_contents = com_contents;
		this.com_mem = com_mem;
		this.com_board = com_board;
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
	}

	public int getCom_no() {
		return com_no;
	}

	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}

	public Date getCom_date() {
		return com_date;
	}

	public void setCom_date(Date com_date) {
		this.com_date = com_date;
	}

	public String getCom_contents() {
		return com_contents;
	}

	public void setCom_contents(String com_contents) {
		this.com_contents = com_contents;
	}

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
	public Member getCom_mem() {
		return com_mem;
	}

	public void setCom_mem(Member com_mem) {
		this.com_mem = com_mem;
	}

	public Board getCom_board() {
		return com_board;
	}

	public void setCom_board(Board com_board) {
<<<<<<< HEAD
=======
	public int getCom_mem() {
		return com_mem;
	}

	public void setCom_mem(int com_mem) {
		this.com_mem = com_mem;
	}

	public int getCom_board() {
		return com_board;
	}

	public void setCom_board(int com_board) {
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
=======
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
		this.com_board = com_board;
	}
	
}
