package com.talkabout.dto;

public class Audience {
	/*
	 * battle_no : ��й�ȣ(PK&FK, Battle_main.battle_no)
	 * member_no : ȸ����ȣ(PK&FK, Member.member_no)
	 * vote : ��ǥ(���� A(1), �߸�(0, default), ���� B(-1)) 
	 */
	private BattleMain battle_no;
	private Member member_no;
	private int vote;
	
	public Audience() {
		super();
	}

	public Audience(BattleMain battle_no, Member member_no, int vote) {
		super();
		this.battle_no = battle_no;
		this.member_no = member_no;
		this.vote = vote;
	}

	public BattleMain getBattle_no() {
		return battle_no;
	}

	public void setBattle_no(BattleMain battle_no) {
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
