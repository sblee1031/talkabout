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
	private String com_date;
	private String com_contents;
	private int com_mem; // com_mem.member_no
	private int com_board; // com_board.board_no
	
	public BoardComment() {
		super();
	}	
	
	public BoardComment(int com_no, int com_board, String com_date, String com_contents, int com_mem) {
		super();
		this.com_no = com_no;
		this.com_board = com_board;
		this.com_date = com_date;
		this.com_contents = com_contents;
		this.com_mem = com_mem;
		
	}

	public int getCom_no() {
		return com_no;
	}

	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}

	public String getCom_date() {
		return com_date;
	}

	public void setCom_date(String com_date) {
		this.com_date = com_date;
	}

	public String getCom_contents() {
		return com_contents;
	}

	public void setCom_contents(String com_contents) {
		this.com_contents = com_contents;
	}

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
		this.com_board = com_board;
	}
	
}
