
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
	private int boardLike_board; // boardLike_board.board_no
	private int boardLike_mem; // boardLike_mem.member_no 
	
	public BoardLike(int boardLike_no, int boardLike_board, int boardLike_mem) {
		super();
		this.boardLike_no = boardLike_no;
		this.boardLike_board = boardLike_board;
		this.boardLike_mem = boardLike_mem;
	}

	public int getboardLike_no() {
		return boardLike_no;
	}

	public void setboardLike_no(int boardLike_no) {
		this.boardLike_no = boardLike_no;
	}

	public int getboardLike_board() {
		return boardLike_board;
	}

	public void setboardLike_board(int boardLike_board) {
		this.boardLike_board = boardLike_board;
	}

	public int getboardLike_mem() {
		return boardLike_mem;
	}

	public void setboardLike_mem(int boardLike_mem) {
		this.boardLike_mem = boardLike_mem;
	}
}
