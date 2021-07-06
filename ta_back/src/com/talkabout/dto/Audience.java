package com.talkabout.dto;

public class Audience {
	/*
	 * Table : 관중(중계방) 테이블
	 * 
	 * audi_no : 관중번호(PK)
	 * audi_deb : 토론번호(FK, Debate 테이블 참조)
	 * audi_mem : 회원번호(FK, Member 테이블 참조)
	 * vote : 투표(주장 A(1), 중립(2, default), 주장 B(3)) 
	 */
	private int audi_no;
	private Debate audi_deb; // audi_deb.debate_no
	private Member audi_mem; // audi_mem.member_no
	private int vote;
	
	public Audience(int audi_no, Debate audi_deb, Member audi_mem, int vote) {
		super();
		this.audi_no = audi_no;
		this.audi_deb = audi_deb;
		this.audi_mem = audi_mem;
		this.vote = vote;
	}

	public int getAudi_no() {
		return audi_no;
	}

	public void setAudi_no(int audi_no) {
		this.audi_no = audi_no;
	}

	public Debate getAudi_deb() {
		return audi_deb;
	}

	public void setAudi_deb(Debate audi_deb) {
		this.audi_deb = audi_deb;
	}

	public Member getAudi_mem() {
		return audi_mem;
	}

	public void setAudi_mem(Member audi_mem) {
		this.audi_mem = audi_mem;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}
	
}
