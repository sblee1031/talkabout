<<<<<<< HEAD
package com.day.dto;
=======
package com.talkabout.dto;
>>>>>>> 5500205aa2729dc897e6f159d29008d88730535e

import java.util.Date;

public class BoardComment {
	private int com_no; //댓글번호
	private Date freeboard_comment_date; //댓글 작성 날짜
	private String freeboard_comment_dtl; //댓글내용
	private Member comment_member_no; //댓글 작성 회원번호
	private Board comment_freeboard_no; //댓글 작성 한 댓글번호
	
	public BoardComment() {
		super();
	}

	public BoardComment(int com_no, Date freeboard_comment_date, String freeboard_comment_dtl, Member comment_member_no,
			Board comment_freeboard_no) {
		super();
		this.com_no = com_no;
		this.freeboard_comment_date = freeboard_comment_date;
		this.freeboard_comment_dtl = freeboard_comment_dtl;
		this.comment_member_no = comment_member_no;
		this.comment_freeboard_no = comment_freeboard_no;
	}

	public int getCom_no() {
		return com_no;
	}

	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}

	public Date getFreeboard_comment_date() {
		return freeboard_comment_date;
	}

	public void setFreeboard_comment_date(Date freeboard_comment_date) {
		this.freeboard_comment_date = freeboard_comment_date;
	}

	public String getFreeboard_comment_dtl() {
		return freeboard_comment_dtl;
	}

	public void setFreeboard_comment_dtl(String freeboard_comment_dtl) {
		this.freeboard_comment_dtl = freeboard_comment_dtl;
	}

	public Member getComment_member_no() {
		return comment_member_no;
	}

	public void setComment_member_no(Member comment_member_no) {
		this.comment_member_no = comment_member_no;
	}

	public Board getComment_freeboard_no() {
		return comment_freeboard_no;
	}

	public void setComment_freeboard_no(Board comment_freeboard_no) {
		this.comment_freeboard_no = comment_freeboard_no;
	}
	
	
}
