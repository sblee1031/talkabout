package com.day.dto;

import java.util.Date;

public class Board {
	private int freeboard_no; //게시글 번호
	private String freeboard_type; //게시글 종류
	private String freeboard_title; //게시글 제목
	private String freeboard_contents; //게시글 내용
	private Date freeboard_date; //작성시간(날짜)
	private int freeboard_cnt; //조회수(count) 
	private Member freeboard_member; //회원(member table)번호 참조
	//게시글번호,회원번호 체크?
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int freeboard_no, String freeboard_type, String freeboard_title, String freeboard_contents,
			Date freeboard_date, int freeboard_cnt, Member freeboard_member) {
		super();
		this.freeboard_no = freeboard_no;
		this.freeboard_type = freeboard_type;
		this.freeboard_title = freeboard_title;
		this.freeboard_contents = freeboard_contents;
		this.freeboard_date = freeboard_date;
		this.freeboard_cnt = freeboard_cnt;
		this.freeboard_member = freeboard_member;
	}
	public int getFreeboard_no() {
		return freeboard_no;
	}
	public void setFreeboard_no(int freeboard_no) {
		this.freeboard_no = freeboard_no;
	}
	public String getFreeboard_type() {
		return freeboard_type;
	}
	public void setFreeboard_type(String freeboard_type) {
		this.freeboard_type = freeboard_type;
	}
	public String getFreeboard_title() {
		return freeboard_title;
	}
	public void setFreeboard_title(String freeboard_title) {
		this.freeboard_title = freeboard_title;
	}
	public String getFreeboard_contents() {
		return freeboard_contents;
	}
	public void setFreeboard_contents(String freeboard_contents) {
		this.freeboard_contents = freeboard_contents;
	}
	public Date getFreeboard_date() {
		return freeboard_date;
	}
	public void setFreeboard_date(Date freeboard_date) {
		this.freeboard_date = freeboard_date;
	}
	public int getFreeboard_cnt() {
		return freeboard_cnt;
	}
	public void setFreeboard_cnt(int freeboard_cnt) {
		this.freeboard_cnt = freeboard_cnt;
	}
	public Member getFreeboard_member() {
		return freeboard_member;
	}
	public void setFreeboard_member(Member freeboard_member) {
		this.freeboard_member = freeboard_member;
	}
	

	
}
