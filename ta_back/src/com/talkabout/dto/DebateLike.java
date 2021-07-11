package com.talkabout.dto;

public class DebateLike {
	/*
	 * Table : 토론결과 좋아요 테이블
	 * 
	 * deblike_no : 토론결과 좋아요 번호(PK)
	 * deblike_deb : 토론번호(FK, Debate 테이블 참조)
	 * deblike_mem : 회원번호(FK, Member 테이블 참조)
	 */
	private int deblike_no;
	private Debate deblike_deb; // deblike_deb.debate_no
	private Member deblike_mem; // deblike_mem.member_no 
	
	public DebateLike(int deblike_no, Debate deblike_deb, Member deblike_mem) {
		super();
		this.deblike_no = deblike_no;
		this.deblike_deb = deblike_deb;
		this.deblike_mem = deblike_mem;
	}

	public int getDeblike_no() {
		return deblike_no;
	}

	public void setDeblike_no(int deblike_no) {
		this.deblike_no = deblike_no;
	}

	public Debate getDeblike_deb() {
		return deblike_deb;
	}

	public void setDeblike_deb(Debate deblike_deb) {
		this.deblike_deb = deblike_deb;
	}

	public Member getDeblike_mem() {
		return deblike_mem;
	}

	public void setDeblike_mem(Member deblike_mem) {
		this.deblike_mem = deblike_mem;
	}
}
