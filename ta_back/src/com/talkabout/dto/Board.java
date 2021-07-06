package com.talkabout.dto;


import java.util.Date;
import java.util.List;

public class Board {
	/*
	 * Table : 자유게시판 테이블
	 * 
	 * board_no : 자유게시판 번호(PK)
	 * board_type : 자유게시판 분류
	 * board_title : 자유게시판 제목
	 * board_contents : 자유게시판 내용
	 * board_date : 자유게시판 작성일
	 * board_views : 자유게시판 조회수
	 * board_mem : 회원번호(FK, Member 테이블 참조)
	 * comment_list : BoardComment has-a
	 * like_list : BoardLike has-a
	 */
	private int board_no;
	private String board_type;
	private String board_title;
	private String board_contents;
	private Date board_date;
	private int board_views;
<<<<<<< HEAD
	private Member board_mem; // board_mem.member_no
=======
	private int board_mem; // board_mem.member_no
//	private Member board_mem;
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
	List<BoardComment> comment_list;
	List<BoardLike> like_list; 
	// 게시글번호,회원번호 체크?
	
<<<<<<< HEAD
	public Board(int board_no, String board_type, String board_title, String board_contents, Date board_date,
			int board_views, Member board_mem, List<BoardComment> comment_list, List<BoardLike> like_list) {
		super();
=======
	public Board() {
		super();
	}	
	
	public Board(int board_no, String board_type, String board_title, String board_contents, Date board_date,
			int board_views, int board_mem, List<BoardComment> comment_list, List<BoardLike> like_list) {
		super();
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
		this.board_no = board_no;
		this.board_type = board_type;
		this.board_title = board_title;
		this.board_contents = board_contents;
		this.board_date = board_date;
		this.board_views = board_views;
		this.board_mem = board_mem;
		this.comment_list = comment_list;
		this.like_list = like_list;
	}
<<<<<<< HEAD
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_contents() {
		return board_contents;
	}
	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public int getBoard_views() {
		return board_views;
	}
	public void setBoard_views(int board_views) {
		this.board_views = board_views;
	}
	public Member getBoard_mem() {
		return board_mem;
	}
	public void setBoard_mem(Member board_mem) {
		this.board_mem = board_mem;
	}
	public List<BoardComment> getComment_list() {
		return comment_list;
	}
=======
	public Board(int board_no, String board_type, String board_title, String board_contents, Date board_date,
			int board_views, int board_mem) {
		super();
		this.board_no = board_no;
		this.board_type = board_type;
		this.board_title = board_title;
		this.board_contents = board_contents;
		this.board_date = board_date;
		this.board_views = board_views;
		this.board_mem = board_mem;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_contents() {
		return board_contents;
	}
	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public int getBoard_views() {
		return board_views;
	}
	public void setBoard_views(int board_views) {
		this.board_views = board_views;
	}
	public int getBoard_mem() {
		return board_mem;
	}
	public void setBoard_mem(int board_mem) {
		this.board_mem = board_mem;
	}
	public List<BoardComment> getComment_list() {
		return comment_list;
	}
>>>>>>> b2119f65f28489bfc6bb0cfeef710cd6044263fc
	public void setComment_list(List<BoardComment> comment_list) {
		this.comment_list = comment_list;
	}
	public List<BoardLike> getLike_list() {
		return like_list;
	}
	public void setLike_list(List<BoardLike> like_list) {
		this.like_list = like_list;
	}	
}
