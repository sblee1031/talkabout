package com.talkabout.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NoticeComment {
	/*
	 * Table : 공지사항 댓글 테이블
	 * 
	 * com_no : 댓글번호(PK)
	 * com_notice : 게시글 번호(FK, Board 테이블 참조)
	 * com_date : 댓글 작성일
	 * com_contents : 댓글 내용
	 * com_mem : 회원번호(FK, Member 테이블 참조)
	 */
	private int com_no;
	private int com_notice; // com_notice.notice_no
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date com_date;
	private String com_contents;
	private Member com_mem; // com_mem.member_no
	
	public NoticeComment() {
		super();
	}
	
	public NoticeComment(int com_no, int com_notice, Date com_date, String com_contents, Member com_mem) {
		super();
		this.com_no = com_no;
		this.com_notice = com_notice;
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

	public int getCom_notice() {
		return com_notice;
	}

	public void setCom_notice(int com_notice) {
		this.com_notice = com_notice;
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

	public Member getCom_mem() {
		return com_mem;
	}

	public void setCom_mem(Member com_mem) {
		this.com_mem = com_mem;
	}

	
}
