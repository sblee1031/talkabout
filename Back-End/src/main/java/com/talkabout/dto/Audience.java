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
	private int audi_deb; // audi_deb.debate_no
	private int audi_mem; // audi_mem.member_no
	private int vote;
	
	public Audience() {
		super();
	}

	public Audience(int audi_no, int audi_deb, int audi_mem, int vote) {
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

	public int getAudi_deb() {
		return audi_deb;
	}

	public void setAudi_deb(int audi_deb) {
		this.audi_deb = audi_deb;
	}

	public int getAudi_mem() {
		return audi_mem;
	}

	public void setAudi_mem(int audi_mem) {
		this.audi_mem = audi_mem;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "Audience [audi_no=" + audi_no + ", audi_deb=" + audi_deb + ", audi_mem=" + audi_mem + ", vote=" + vote
				+ "]";
	}
	
	
	
}
