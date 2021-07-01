package com.talkabout.dto;

import java.util.Date;

public class battle_result {
private int member_no;
private String deb_like;
private int deb_no;
private String com_writer;
private String com_contents;
private int discuss_time;
private Date discuss_start_date;
private String discuss_topic;
public battle_result() {
	super();
	// TODO Auto-generated constructor stub
}
public int getMember_no() {
	return member_no;
}
public void setMember_no(int member_no) {
	this.member_no = member_no;
}
public String getDeb_like() {
	return deb_like;
}
public void setDeb_like(String deb_like) {
	this.deb_like = deb_like;
}
public int getDeb_no() {
	return deb_no;
}
public void setDeb_no(int deb_no) {
	this.deb_no = deb_no;
}
public String getCom_writer() {
	return com_writer;
}
public void setCom_writer(String com_writer) {
	this.com_writer = com_writer;
}
public String getCom_contents() {
	return com_contents;
}
public void setCom_contents(String com_contents) {
	this.com_contents = com_contents;
}
public int getDiscuss_time() {
	return discuss_time;
}
public void setDiscuss_time(int discuss_time) {
	this.discuss_time = discuss_time;
}
public Date getDiscuss_start_date() {
	return discuss_start_date;
}
public void setDiscuss_start_date(Date discuss_start_date) {
	this.discuss_start_date = discuss_start_date;
}
public String getDiscuss_topic() {
	return discuss_topic;
}
public void setDiscuss_topic(String discuss_topic) {
	this.discuss_topic = discuss_topic;
}
public battle_result(int member_no, String deb_like, int deb_no, String com_writer, String com_contents,
		int discuss_time, Date discuss_start_date, String discuss_topic) {
	super();
	this.member_no = member_no;
	this.deb_like = deb_like;
	this.deb_no = deb_no;
	this.com_writer = com_writer;
	this.com_contents = com_contents;
	this.discuss_time = discuss_time;
	this.discuss_start_date = discuss_start_date;
	this.discuss_topic = discuss_topic;
}
public battle_result(int member_no, int discuss_time, Date discuss_start_date, String discuss_topic) {
	super();
	this.member_no = member_no;
	this.discuss_time = discuss_time;
	this.discuss_start_date = discuss_start_date;
	this.discuss_topic = discuss_topic;
}






}
