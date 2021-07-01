package com.day.dto;

public class Audience {
	/*
	 * battle_no : 토론번호(PK&FK, Battle_main.battle_no)
	 * member_no : 회원번호(PK&FK, Member.member_no)
	 * vote : 투표(주장 A(1), 중립(0, default), 주장 B(-1)) 
	 */
	private Battle_Main battle_no;
	private Member member_no;
	private int vote;
	
	public Audience() {
		super();
	}

	public Audience(Battle_Main battle_no, Member member_no, int vote) {
		super();
		this.battle_no = battle_no;
		this.member_no = member_no;
		this.vote = vote;
	}

	public Battle_Main getBattle_no() {
		return battle_no;
	}

	public void setBattle_no(Battle_Main battle_no) {
		this.battle_no = battle_no;
	}

	public Member getMember_no() {
		return member_no;
	}

	public void setMember_no(Member member_no) {
		this.member_no = member_no;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "Audience [battle_no=" + battle_no + ", member_no=" + member_no + ", vote=" + vote + "]";
	}
}
