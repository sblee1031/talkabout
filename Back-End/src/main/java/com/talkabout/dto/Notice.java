package com.talkabout.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Notice {
	/*
	 * Table : 공지사항 테이블
	 * 
	 * notice_no : 공지사항 번호(PK)
	 * notice_type : 공지사항 분류
	 * notice_title : 공지사항 제목
	 * notice_contents : 공지사항 내용
	 * notice_date : 공지사항 작성일
	 * notice_views : 공지사항 조회수
	 * notice_admin : 관리자 아이디(FK, Admin 테이블 참조)
	 * comment_list : NoticeComment has-a
	 * like_list : NoticeLike has-a
	 */
	
	private int notice_no;
	private String notice_type;
	private String notice_title;
	private String notice_contents;
	private String notice_admin;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date notice_date;
	private int notice_views;
	
	public Notice() {
		super();
	}

	public Notice(int notice_no, String notice_type, String notice_title, String notice_contents, String notice_admin,
			Date notice_date, int notice_views) {
		super();
		this.notice_no = notice_no;
		this.notice_type = notice_type;
		this.notice_title = notice_title;
		this.notice_contents = notice_contents;
		this.notice_admin = notice_admin;
		this.notice_date = notice_date;
		this.notice_views = notice_views;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_type() {
		return notice_type;
	}

	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_contents() {
		return notice_contents;
	}

	public void setNotice_contents(String notice_contents) {
		this.notice_contents = notice_contents;
	}

	public String getNotice_admin() {
		return notice_admin;
	}

	public void setNotice_admin(String notice_admin) {
		this.notice_admin = notice_admin;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}

	public int getNotice_views() {
		return notice_views;
	}

	public void setNotice_views(int notice_views) {
		this.notice_views = notice_views;
	}
}
