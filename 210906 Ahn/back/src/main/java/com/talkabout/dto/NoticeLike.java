package com.talkabout.dto;

public class NoticeLike {
	/*
	 * Table : 공지사항 좋아요 테이블
	 * 
	 * noticeLike_no : 공지사항 좋아요 번호(PK)
	 * noticeLike_notice : 공지사항 번호(FK, Notice 테이블 참조)
	 * noticeLike_mem : 회원번호(FK, Member 테이블 참조)
	 */
	private int noticeLike_no; 
	private Notice noticeLike_notice; // noticeLike_notice.notice_no
	private Member noticeLike_mem; // noticeLike_mem.member_no
	
	public NoticeLike(int noticeLike_no, Notice noticeLike_notice, Member noticeLike_mem) {
		super();
		this.noticeLike_no = noticeLike_no;
		this.noticeLike_notice = noticeLike_notice;
		this.noticeLike_mem = noticeLike_mem;
	}

	public int getNoticeLike_no() {
		return noticeLike_no;
	}

	public void setNoticeLike_no(int noticeLike_no) {
		this.noticeLike_no = noticeLike_no;
	}

	public Notice getNoticeLike_notice() {
		return noticeLike_notice;
	}

	public void setNoticeLike_notice(Notice noticeLike_notice) {
		this.noticeLike_notice = noticeLike_notice;
	}

	public Member getNoticeLike_mem() {
		return noticeLike_mem;
	}

	public void setNoticeLike_mem(Member noticeLike_mem) {
		this.noticeLike_mem = noticeLike_mem;
	}
}
