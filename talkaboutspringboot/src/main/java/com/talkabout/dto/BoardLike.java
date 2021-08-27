
package com.talkabout.dto;


public class BoardLike {
	/*
	 * Table : 자유게시판 좋아요 테이블
	 * 
	 * boardLike_no : 게시판좋아요 번호(PK)
	 * boardLike_board : 게시판번호(FK, Board 테이블 참조)
	 * boardLike_mem : 회원번호(FK, Member 테이블 참조)
	 */
	private int boardLike_no;
	private Board boardLike_board; // boardLike_board.board_no
	private Member boardLike_member; // boardLike_mem.member_no 

	public int getBoardLike_no() {
		return boardLike_no;
	}

	public void setBoardLike_no(int boardLike_no) {
		this.boardLike_no = boardLike_no;
	}

	public Board getBoardLike_board() {
		return boardLike_board;
	}

	public void setBoardLike_board(Board boardLike_board) {
		this.boardLike_board = boardLike_board;
	}

	public Member getBoardLike_member() {
		return boardLike_member;
	}

	public void setBoardLike_member(Member boardLike_member) {
		this.boardLike_member = boardLike_member;
	}
	
	
}
