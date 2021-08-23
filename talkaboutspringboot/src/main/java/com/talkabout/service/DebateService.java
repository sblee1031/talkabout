package com.talkabout.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.DebateDAO;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
@Service
public class DebateService {
	@Autowired
	private DebateDAO dao;

	public List<Debate> findAll(int startRow, int endRow) throws FindException {
		return dao.selectAll( startRow,  endRow);
	}
	public List<Debate> findAll(String optWord,int startRow, int endRow) throws FindException {
		return dao.selectAll(optWord, startRow,  endRow);
	}
	public Map<String, Object> findByNo(int deb_no) throws FindException {
		return dao.selectByNo(deb_no);
	}
	
	public void addDebate(Debate deb,  String discuss1, String discuss2) throws AddException{
		dao.insertDebate(deb, discuss1,discuss2);
	}
	public void addDiscussor(Debate deb_no, DebateDetail dd, Member m) {
		dao.updateDiscussor(deb_no, dd, m);
	}
	public void cancleDiscussor(Debate deb_no, DebateDetail dd, Member m) {
		dao.cancleDiscussor(deb_no, dd, m);
	}
	public void updateDebateAll(Debate deb, List<DebateDetail> dd, String discuss1,String discuss2) throws ModifyException{
		dao.updateDebateAll(deb, dd,discuss1,discuss2);
	}
	
	public void updateStatus(Debate status) {
		dao.updateStatus(status);
	}
	public void deleteDebate(Debate deb_no) {
		dao.deleteDebate(deb_no);
	}
	public List<Debate> selectSearch(String column, String keyword){
		return dao.selectSearch(column, keyword);
	}
	public int lastRow() {//총 게시물 개수 구하기
		return dao.lastRow();
	}
	public int searchLastRow(String word) {//총 게시물 개수 구하기
		return dao.searchLastRow(word);
	}
//	public void pageNum(int page) {
//		dao.pageNum(page);
//	}
//	public void pageSize(int size) {
//		dao.pageSize(size);
//	}
	
	public void setStartDate(Debate deb) {
		dao.updateStartdate(deb);
	}
	
	public void setEndDate(Debate deb) {
		dao.updateEnddate(deb);
	}
	
}
