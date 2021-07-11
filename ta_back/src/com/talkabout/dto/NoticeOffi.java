package com.talkabout.dto;

import java.util.Date;

public class NoticeOffi {
	private int offi_no;
	private String offi_type;
	private String offi_title;
	private String offi_contents;
	private Date offi_date;
	private int offi_cnt;

	public NoticeOffi() {
		super();
	}

	public int getOffi_no() {
		return offi_no;
	}

	public void setOffi_no(int offi_no) {
		this.offi_no = offi_no;
	}

	public String getOffi_type() {
		return offi_type;
	}

	public void setOffi_type(String offi_type) {
		this.offi_type = offi_type;
	}

	public String getOffi_title() {
		return offi_title;
	}

	public void setOffi_title(String offi_title) {
		this.offi_title = offi_title;
	}

	public String getOffi_contents() {
		return offi_contents;
	}

	public void setOffi_contents(String offi_contents) {
		this.offi_contents = offi_contents;
	}

	public Date getOffi_date() {
		return offi_date;
	}

	public void setOffi_date(Date offi_date) {
		this.offi_date = offi_date;
	}

	public int getOffi_cnt() {
		return offi_cnt;
	}

	public void setOffi_cnt(int offi_cnt) {
		this.offi_cnt = offi_cnt;
	}
	public NoticeOffi(
			int offi_no,
			String offi_type,
			String offi_title,
			String offi_contents,
			Date offi_date,
			int offi_cnt) {
		super();
		this.offi_no = offi_no;
		this.offi_type = offi_type;
		this.offi_title = offi_title;
		this.offi_contents = offi_contents;
		this.offi_date = offi_date;
		this.offi_cnt = offi_cnt;
	}
}