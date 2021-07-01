package com.talkabout.dto;

public class BoardLike {
	private Board boardlike_no; //자유게시판 좋아요(게시글 번호 참조)
	private Member boardlike_member; //좋아요 회원번호(
	
	public BoardLike() {
		super();
	}

	public BoardLike(Board boardlike_no, Member boardlike_member) {
		super();
		this.boardlike_no = boardlike_no;
		this.boardlike_member = boardlike_member;
	}

	public Board getBoardlike_no() {
		return boardlike_no;
	}

	public void setBoardlike_no(Board boardlike_no) {
		this.boardlike_no = boardlike_no;
	}

	public Member getBoardlike_member() {
		return boardlike_member;
	}

	public void setBoardlike_member(Member boardlike_member) {
		this.boardlike_member = boardlike_member;
	}

}
