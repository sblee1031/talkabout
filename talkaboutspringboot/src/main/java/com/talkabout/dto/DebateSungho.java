package com.talkabout.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DebateSungho {
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
	private Member debate_writer; //제안자 debate_writer.member_no
	private String debate_topic;
	@JsonFormat(pattern = "yy/MM/dd HH:mm", timezone = "Asia/Seoul")
	private Date debate_date;
	private int debate_time;
	private String debate_status;
	private String debate_startDate;
	private String debate_endDate;
	private String debate_nickname;
	
	
	private List<DebateDetailSungho> detail_list;
	//List<DebateComment> comment_list;
	private List<DebateLike> like_list; 
	private List<Audience> audience_list;
	
	/*------*/
	private Member debate_mem; //토론자
	private List<DebateCommentSungho> comment_list;
	private int like_count;
	/*
		또는 Debate 테이블에 좋아요 수를 나타내는 컬럼 추가하고 
		DebateLike 테이블에 행이 추가될 때마다 Debate 테이블의 좋아요 수도 1씩 증가 
	 */
	//페이징처리 구문
	private int page_num=1;
	private int amount=5;
	

	public DebateSungho(int page_num ,int amount) {
		super();
		this.page_num = page_num;
		this.amount = amount;
		
	}
	
	public DebateSungho(int debate_no, Member debate_writer, String debate_topic, Date debate_date, int debate_time,
			String debate_status, String debate_startDate, String debate_endDate, List<DebateDetailSungho> detail_list,
			List<DebateCommentSungho> comment_list, List<DebateLike> like_list, List<Audience> audience_list) {
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

	public DebateSungho() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*------*/

	public Member getDebate_mem() {
		return debate_mem;
	}

	public void setDebate_mem(Member debate_mem) {
		System.out.println("setDebate_mem:" + debate_mem);
		this.debate_mem = debate_mem;
	}

	/*------*/

	public int getDebate_no() {
		return debate_no;
	}

	public void setDebate_no(int debate_no) {
		this.debate_no = debate_no;
	}

	public Member getDebate_writer() {
		return debate_writer;
	}

	public void setDebate_writer(Member debate_writer) {
		this.debate_writer = debate_writer;
	}

	public String getDebate_topic() {
		return debate_topic;
	}

	public void setDebate_topic(String debate_topic) {
		this.debate_topic = debate_topic;
	}

	public Date getDebate_date() {
		return debate_date;
	}
	
	public void setDebate_date(Date debate_date) {
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

	public List<DebateDetailSungho> getDetail_list() {
		return detail_list;
	}

	public void setDetail_list(List<DebateDetailSungho> detail_list) {
		this.detail_list = detail_list;
	}

	public List<DebateCommentSungho> getComment_list() {
		return comment_list;
	}

	public void setComment_list(List<DebateCommentSungho> comment_list) {
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

	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDebate_nickname() {
		return debate_nickname;
	}

	public void setDebate_nickname(String debate_nickname) {
		this.debate_nickname = debate_nickname;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}



	@Override
	public String toString() {
		return "Debate [debate_no=" + debate_no + ", debate_writer=" + debate_writer + ", debate_topic=" + debate_topic
				+ ", debate_date=" + debate_date + ", debate_time=" + debate_time + ", debate_status=" + debate_status
				+ ", debate_startDate=" + debate_startDate + ", debate_endDate=" + debate_endDate + "]";
	}
	
	
	
}
