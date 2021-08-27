package com.talkabout.dto;

import java.util.Date;

public class BattleMain {
	
	private int battle_no;
	private Member battle_writer;
	private String discuss_topic;
	private Date discuss_start_date;
	private int discuss_time;
	private String topic_current_progress;
	
	public int getBattle_no() {
		return battle_no;
	}
	public void setBattle_no(int battle_no) {
		this.battle_no = battle_no;
	}
	public Member getBattle_writer() {
		return battle_writer;
	}
	public void setBattle_writer(Member battle_writer) {
		this.battle_writer = battle_writer;
	}
	public String getDiscuss_topic() {
		return discuss_topic;
	}
	public void setDiscuss_topic(String discuss_topic) {
		this.discuss_topic = discuss_topic;
	}
	public Date getDiscuss_start_date() {
		return discuss_start_date;
	}
	public void setDiscuss_start_date(Date discuss_start_date) {
		this.discuss_start_date = discuss_start_date;
	}
	public int getDiscuss_time() {
		return discuss_time;
	}
	public void setDiscuss_time(int discuss_time) {
		this.discuss_time = discuss_time;
	}
	public String getTopic_current_progress() {
		return topic_current_progress;
	}
	public void setTopic_current_progress(String topic_current_progress) {
		this.topic_current_progress = topic_current_progress;
	}
	
	public BattleMain(int battle_no, Member battle_writer, String discuss_topic, Date discuss_start_date,
			int discuss_time, String topic_current_progress) {
		super();
		this.battle_no = battle_no;
		this.battle_writer = battle_writer;
		this.discuss_topic = discuss_topic;
		this.discuss_start_date = discuss_start_date;
		this.discuss_time = discuss_time;
		this.topic_current_progress = topic_current_progress;
	}
	
	

}
