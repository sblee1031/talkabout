package com.talkabout.dto;

import java.util.Date;
import java.util.List;

public class Debate {
	/*
	 * Table : 토론 메인 테이블
	 * 
	 * debate_no : 토론번호(PK)
	 * debate_writer : 토론주제 제안자(FK, Member 테이블 참조)
	 * debate_topic : 토론주제
	 * debate_date : 토론예정일
	 * debate_time : 토론시간
	 * debate_status : 토론상태
	 * debate_startDate : 토론 시작시간
	 * debate_endDate : 토론 종료시간
	 * detail_list : DebateDetail has-a
	 * comment_list : DebateComment has-a
	 * like_list : DebateLike has-a
	 * audience_list : Audience has-a
	 */
	
	private int debate_no;
	private int debate_writer; // debate_writer.member_no
	private String debate_topic;
	private String debate_date;
	private int debate_time;
	private String debate_status;
	private String debate_startDate;
	private String debate_endDate;
	List<DebateDetail> detail_list;
	List<DebateComment> comment_list;
	List<DebateLike> like_list; 
	List<Audience> audience_list;
	/*
		또는 Debate 테이블에 좋아요 수를 나타내는 컬럼 추가하고 
		DebateLike 테이블에 행이 추가될 때마다 Debate 테이블의 좋아요 수도 1씩 증가 
	 */
	
	
	
	public Debate(int debate_no, int debate_writer, String debate_topic, String debate_date, int debate_time,
			String debate_status, String debate_startDate, String debate_endDate, List<DebateDetail> detail_list,
			List<DebateComment> comment_list, List<DebateLike> like_list, List<Audience> audience_list) {
		super();
		this.debate_no = debate_no;
		this.debate_writer = debate_writer;
		this.debate_topic = debate_topic;
		this.debate_date = debate_date;
		this.debate_time = debate_time;
		this.debate_status = debate_status;
		this.debate_startDate = debate_startDate;
		this.debate_endDate = debate_endDate;
		this.detail_list = detail_list;
		this.comment_list = comment_list;
		this.like_list = like_list;
		this.audience_list = audience_list;
	}

	public Debate(String debate_date) {
		super();
		this.debate_date = debate_date;
	}

	public Debate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getDebate_no() {
		return debate_no;
	}

	public void setDebate_no(int debate_no) {
		this.debate_no = debate_no;
	}

	public int getDebate_writer() {
		return debate_writer;
	}

	public void setDebate_writer(int debate_writer) {
		this.debate_writer = debate_writer;
	}

	public String getDebate_topic() {
		return debate_topic;
	}

	public void setDebate_topic(String debate_topic) {
		this.debate_topic = debate_topic;
	}

	public String getDebate_date() {
		return debate_date;
	}

	public void setDebate_date(String debate_date) {
		this.debate_date = debate_date;
	}

	public int getDebate_time() {
		return debate_time;
	}

	public void setDebate_time(int debate_time) {
		this.debate_time = debate_time;
	}

	public String getDebate_status() {
		return debate_status;
	}

	public void setDebate_status(String debate_status) {
		this.debate_status = debate_status;
	}

	public String getDebate_startDate() {
		return debate_startDate;
	}

	public void setDebate_startDate(String debate_startDate) {
		this.debate_startDate = debate_startDate;
	}

	public String getDebate_endDate() {
		return debate_endDate;
	}

	public void setDebate_endDate(String debate_endDate) {
		this.debate_endDate = debate_endDate;
	}

	public List<DebateDetail> getDetail_list() {
		return detail_list;
	}

	public void setDetail_list(List<DebateDetail> detail_list) {
		this.detail_list = detail_list;
	}

	public List<DebateComment> getComment_list() {
		return comment_list;
	}

	public void setComment_list(List<DebateComment> comment_list) {
		this.comment_list = comment_list;
	}

	public List<DebateLike> getLike_list() {
		return like_list;
	}

	public void setLike_list(List<DebateLike> like_list) {
		this.like_list = like_list;
	}

	public List<Audience> getAudience_list() {
		return audience_list;
	}

	public void setAudience_list(List<Audience> audience_list) {
		this.audience_list = audience_list;
	}
	
	
}
