package com.talkabout.dto;

import java.sql.Date;

public class DebateDetailSungho {
	/*
	 * Table : 토론 상세 테이블
	 * 
	 * detail_no : 토론상세번호(PK)
	 * detail_deb : 토론번호(FK, Debate 테이블 참조)
	 * discuss : 토론 주장
	 * evi_one : 근거 1
	 * evi_two : 근거 2
	 * evi_three : 근거 3
	 * discussor : 토론자(FK, Member 테이블 참조)
	 * in_time : 입장시간
	 */
	private int detail_no;
	private int detail_deb; // detail_deb.debate_no
	private String discuss;
	private String evi_one;
	private String evi_two;
	private String evi_three;
	private Member discussor_mem; // discussor.member_no
	private String in_time;
	
	private String debate_nickname;
	private String debate_thambnail;
	private int like_count;
	
	
	public DebateDetailSungho() {
		super();
	}

	public DebateDetailSungho(int detail_no, int detail_deb, String discuss, String evi_one, String evi_two,
			String evi_three, Member discussor_mem, String in_time) {
		super();
		this.detail_no = detail_no;
		this.detail_deb = detail_deb;
		this.discuss = discuss;
		this.evi_one = evi_one;
		this.evi_two = evi_two;
		this.evi_three = evi_three;
		this.discussor_mem = discussor_mem;
		this.in_time = in_time;
	}

	public int getDetail_no() {
		return detail_no;
	}

	public void setDetail_no(int detail_no) {
		this.detail_no = detail_no;
	}

	public int getDetail_deb() {
		return detail_deb;
	}

	public void setDetail_deb(int detail_deb) {
		this.detail_deb = detail_deb;
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
		return discussor_mem;
	}

	public void setDiscussor(Member discussor) {
		this.discussor_mem = discussor;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}

	public String getDebate_nickname() {
		return debate_nickname;
	}

	public void setDebate_nickname(String debate_nickname) {
		this.debate_nickname = debate_nickname;
	}

	public String getDebate_thambnail() {
		return debate_thambnail;
	}

	public void setDebate_thambnail(String debate_thambnail) {
		this.debate_thambnail = debate_thambnail;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	@Override
	public String toString() {
		return "DebateDetail [detail_no=" + detail_no + ", detail_deb=" + detail_deb + ", discuss=" + discuss
				+ ", evi_one=" + evi_one + ", evi_two=" + evi_two + ", evi_three=" + evi_three + ", discussor="
				+ discussor_mem + ", in_time=" + in_time + "]";
	}

	
	
}
