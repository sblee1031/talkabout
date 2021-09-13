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
	private Member com_member; // com_mem.member_no
	private int com_board; // com_board.board_no
	public BoardComment(int com_no, String com_date, String com_contents, Member com_member, int com_board) {
		super();
		this.com_no = com_no;
		this.com_date = com_date;
		this.com_contents = com_contents;
		this.com_member = com_member;
		this.com_board = com_board;
	}
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
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
	public Member getCom_member() {
		return com_member;
	}
	public void setCom_member(Member com_member) {
		this.com_member = com_member;
	}
	public int getCom_board() {
		return com_board;
	}
	public void setCom_board(int com_board) {
		this.com_board = com_board;
	}
	@Override
	public String toString() {
		return "BoardComment [com_no=" + com_no + ", com_date=" + com_date + ", com_contents=" + com_contents
				+ ", com_member=" + com_member + ", com_board=" + com_board + "]";
	}
	
	
}
