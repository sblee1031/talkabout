<<<<<<< HEAD
package com.day.dto;

public class Audience {
	/*
	 * battle_no : Åä·Ð¹øÈ£(PK&FK, Battle_main.battle_no)
	 * member_no : È¸¿ø¹øÈ£(PK&FK, Member.member_no)
	 * vote : ÅõÇ¥(ÁÖÀå A(1), Áß¸³(0, default), ÁÖÀå B(-1)) 
	 */
	private Battle_Main battle_no;
=======
package com.talkabout.dto;

public class Audience {
	/*
	 * battle_no : ï¿½ï¿½Ð¹ï¿½È£(PK&FK, Battle_main.battle_no)
	 * member_no : È¸ï¿½ï¿½ï¿½ï¿½È£(PK&FK, Member.member_no)
	 * vote : ï¿½ï¿½Ç¥(ï¿½ï¿½ï¿½ï¿½ A(1), ï¿½ß¸ï¿½(0, default), ï¿½ï¿½ï¿½ï¿½ B(-1)) 
	 */
	private BattleMain battle_no;
>>>>>>> 5500205aa2729dc897e6f159d29008d88730535e
	private Member member_no;
	private int vote;
	
	public Audience() {
		super();
	}

<<<<<<< HEAD
	public Audience(Battle_Main battle_no, Member member_no, int vote) {
=======
	public Audience(BattleMain battle_no, Member member_no, int vote) {
>>>>>>> 5500205aa2729dc897e6f159d29008d88730535e
		super();
		this.battle_no = battle_no;
		this.member_no = member_no;
		this.vote = vote;
	}

<<<<<<< HEAD
	public Battle_Main getBattle_no() {
		return battle_no;
	}

	public void setBattle_no(Battle_Main battle_no) {
=======
	public BattleMain getBattle_no() {
		return battle_no;
	}

	public void setBattle_no(BattleMain battle_no) {
>>>>>>> 5500205aa2729dc897e6f159d29008d88730535e
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
