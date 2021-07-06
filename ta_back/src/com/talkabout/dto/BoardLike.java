
package com.talkabout.dto;


<<<<<<< HEAD
public class boardLike {
=======
public class BoardLike {
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
	/*
	 * Table : 자유게시판 좋아요 테이블
	 * 
	 * boardLike_no : 게시판좋아요 번호(PK)
	 * boardLike_board : 게시판번호(FK, Board 테이블 참조)
	 * boardLike_mem : 회원번호(FK, Member 테이블 참조)
	 */
	private int boardLike_no;
<<<<<<< HEAD
	private Board boardLike_board; // boardLike_board.board_no
	private Member boardLike_mem; // boardLike_mem.member_no 
=======
	private int boardLike_board; // boardLike_board.board_no
	private int boardLike_mem; // boardLike_mem.member_no 
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
	
	public boardLike(int boardLike_no, Board boardLike_board, Member boardLike_mem) {
		super();
		this.boardLike_no = boardLike_no;
		this.boardLike_board = boardLike_board;
		this.boardLike_mem = boardLike_mem;
	}
<<<<<<< HEAD

	public int getboardLike_no() {
		return boardLike_no;
	}

	public void setboardLike_no(int boardLike_no) {
		this.boardLike_no = boardLike_no;
	}

	public Board getboardLike_board() {
		return boardLike_board;
	}

	public void setboardLike_board(Board boardLike_board) {
		this.boardLike_board = boardLike_board;
	}

	public Member getboardLike_mem() {
		return boardLike_mem;
	}

	public void setboardLike_mem(Member boardLike_mem) {
=======
	public BoardLike( int boardLike_board, int boardLike_mem) {
		super();
		
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
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
		this.boardLike_mem = boardLike_mem;
	}
}
