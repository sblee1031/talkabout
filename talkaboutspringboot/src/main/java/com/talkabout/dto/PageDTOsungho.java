package com.talkabout.dto;

public class PageDTOsungho {

	private int startPage, endPage;
	private boolean prev,next;
	
	private int total;
	private DebateSungho cri;
	
	public PageDTOsungho(DebateSungho cri, int total) {
		this.cri = cri;
		this.total =total;
		
		this.endPage = (int)Math.ceil(cri.getPage_num()/10.0)*10;
		
		this.startPage = endPage-9;
		
		this.prev =this.startPage>1;
		
		int realEnd = (int)( Math.ceil(( total * 1.0)/cri.getAmount()));
		this.endPage = realEnd <=endPage ? realEnd : endPage;
		
		this.next = this.endPage < realEnd;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public DebateSungho getCri() {
		return cri;
	}

	public void setCri(DebateSungho cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "PageDTOsungho [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}
}
