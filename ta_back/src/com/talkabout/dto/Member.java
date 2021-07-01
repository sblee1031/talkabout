package com.talkabout.dto;

public class Member {
	

	private int member_no;
	private String member_social_type;
	private String member_social_no;
	private String member_nickName;
	private String member_gender;
	private String member_email;
	private String member_thumb;
	private String member_birth;
	
	
	public Member() {
		super();
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getMember_social_type() {
		return member_social_type;
	}
	public void setMember_social_type(String member_social_type) {
		this.member_social_type = member_social_type;
	}
	
	public String getMember_social_no() {
		return member_social_no;
	}
	public void setMember_social_no(String member_social_no) {
		this.member_social_no = member_social_no;
	}
	public String getMember_nickName() {
		return member_nickName;
	}
	public void setMember_nickName(String member_nickName) {
		this.member_nickName = member_nickName;
	}
	public String getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_thumb() {
		return member_thumb;
	}
	public void setMember_thumb(String member_thumb) {
		this.member_thumb = member_thumb;
	}
	public String getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
	}
	
	
	
	public Member(int member_no, String member_email, String member_thumb) {
		super();
		this.member_no = member_no;
		this.member_email = member_email;
		this.member_thumb = member_thumb;
	}
	
	
	
	public Member(String member_social_type, String member_social_no, String member_nickName, String member_gender,
			String member_email, String member_thumb, String member_birth) {
		super();
		this.member_social_type = member_social_type;
		this.member_social_no = member_social_no;
		this.member_nickName = member_nickName;
		this.member_gender = member_gender;
		this.member_email = member_email;
		this.member_thumb = member_thumb;
		this.member_birth = member_birth;
	}
	public Member(int member_no, String member_social_type, String member_social_no, String member_nickName,
			String member_gender, String member_email, String member_thumb, String member_birth) {
		super();
		this.member_no = member_no;
		this.member_social_type = member_social_type;
		this.member_social_no = member_social_no;
		this.member_nickName = member_nickName;
		this.member_gender = member_gender;
		this.member_email = member_email;
		this.member_thumb = member_thumb;
		this.member_birth = member_birth;
	}
	
	



	
	
}
