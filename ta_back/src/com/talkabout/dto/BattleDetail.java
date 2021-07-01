package com.talkabout.dto;

public class BattleDetail {
	/*
	 * battle_no : ��й�ȣ(PK&FK, Battle_Main.battle_no)
	 * discuss : �������
	 * evi_one : �ٰ� 1
	 * evi_two : �ٰ� 2
	 * evi_three : �ٰ� 3
	 * discussor : ����� ȸ����ȣ(FK, Member.member_no)
	 */
	private BattleMain battle_no;
	private String discuss;
	private String evi_one;
	private String evi_two;
	private String evi_three;
	private Member discussor;
	
	public BattleDetail() {
		super();
	}

	public BattleDetail(BattleMain battle_no, String discuss, String evi_one, String evi_two, String evi_three,
			Member discussor) {
		super();
		this.battle_no = battle_no;
		this.discuss = discuss;
		this.evi_one = evi_one;
		this.evi_two = evi_two;
		this.evi_three = evi_three;
		this.discussor = discussor;
	}

	public BattleMain getBattle_no() {
		return battle_no;
	}

	public void setBattle_no(BattleMain battle_no) {
		this.battle_no = battle_no;
	}

	public String getDiscuss() {
		return discuss;
	}

	public void setDiscuss(String discuss) {
		this.discuss = discuss;
	}

	public String getEvi_one() {
		return evi_one;
	}

	public void setEvi_one(String evi_one) {
		this.evi_one = evi_one;
	}

	public String getEvi_two() {
		return evi_two;
	}

	public void setEvi_two(String evi_two) {
		this.evi_two = evi_two;
	}

	public String getEvi_three() {
		return evi_three;
	}

	public void setEvi_three(String evi_three) {
		this.evi_three = evi_three;
	}

	public Member getDiscussor() {
		return discussor;
	}

	public void setDiscussor(Member discussor) {
		this.discussor = discussor;
	}

	@Override
	public String toString() {
		return "Battle_detail [battle_no=" + battle_no + ", discuss=" + discuss + ", evi_one=" + evi_one + ", evi_two="
				+ evi_two + ", evi_three=" + evi_three + ", discussor=" + discussor + "]";
	}
}
