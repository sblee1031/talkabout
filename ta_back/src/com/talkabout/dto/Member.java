package com.talkabout.dto;

<<<<<<< HEAD
public class Member {
=======
public class Member{
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
	/*
	 * Table : 회원 테이블
	 * 
	 * member_no : 회원 번호(PK)
	 * member_social_type : 회원 로그인 타입(구글 / 카카오)
	 * member_social_no : 회원 로그인 고유번호
	 * member_nickName : 회원 닉네임
	 * member_gender : 회원 성별
	 * member_email : 회원 이메일
	 * member_thumb : 회원 썸네일
	 * member_birth : 회원 출생연도
<<<<<<< HEAD
=======
	 * member_able : 1-활동회원, 2-비활동회원(탈퇴시)
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
	 */
	private int member_no;
	private String member_social_type;
	private String member_social_no;
	private String member_nickName;
	private String member_gender;
	private String member_email;
	private String member_thumb;
	private String member_birth;
	private int member_able;
	
<<<<<<< HEAD
	public Member(int member_no, String member_social_type, String member_social_no, String member_nickName,
			String member_gender, String member_email, String member_thumb, String member_birth) {
		super();
=======

	



	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}



	//signUp 생성자
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



	//전체 생성자
	public Member(int member_no, String member_social_type, String member_social_no, String member_nickName,
			String member_gender, String member_email, String member_thumb, String member_birth, int member_able) {
		super();
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
		this.member_no = member_no;
		this.member_social_type = member_social_type;
		this.member_social_no = member_social_no;
		this.member_nickName = member_nickName;
		this.member_gender = member_gender;
		this.member_email = member_email;
		this.member_thumb = member_thumb;
		this.member_birth = member_birth;
<<<<<<< HEAD
	}

=======
		this.member_able = member_able;
	}




>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
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
<<<<<<< HEAD
=======

	public int getMember_able() {
		return member_able;
	}

	public void setMember_able(int member_able) {
		this.member_able = member_able;
	}
	
	
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
	
}
