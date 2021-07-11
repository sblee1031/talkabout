package com.talkabout.dto;

import java.util.Date;

public class NoticeComments {

	
	private int member_no;
	private int com_no;
	private Date com_date;
	private String com_contents;
	
	public NoticeComments() {
		super();
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public int getCom_no() {
		return com_no;
	}

	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}

	public Date getCom_date() {
		return com_date;
	}

	public void setCom_date(Date com_date) {
		this.com_date = com_date;
	}

	public String getCom_contents() {
		return com_contents;
	}

	public void setCom_contents(String com_contents) {
		this.com_contents = com_contents;
	}
	public NoticeComments(
			int member_no,      
			int com_no,         
			Date com_date,      
			String com_contents) {
		super();
		this.member_no = member_no;
		this.com_no = com_no;
		this.com_date = com_date;
		this.com_contents = com_contents;
	}
}
