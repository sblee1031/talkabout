package com.talkabout.dto;

public class Pagination {
//	 int num_start_row = ((num_page_no-1) * num_page_size) + 1 ;
//	 int num_end_row   = (num_page_no * num_page_size) ;
	private int num_page_no;
	private int num_page_size;

	
	public int startRow(int num_page_no, int num_page_size) {
		return ((num_page_no-1) * num_page_size) + 1 ;
	}
	public int endRow(int num_page_no, int num_page_size) {
		return (num_page_no * num_page_size) ;
	}
	
	public int getNum_page_no() {
		return num_page_no;
	}
	public void setNum_page_no(int num_page_no) {
		this.num_page_no = num_page_no;
	}
	public int getNum_page_size() {
		return num_page_size;
	}
	public void setNum_page_size(int num_page_size) {
		this.num_page_size = num_page_size;
	}

}
