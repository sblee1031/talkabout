package com.talkabout.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;

@Repository
public class DebateDAOOracle implements DebateDAO {
	
	@Autowired
	//@Qualifier("Underscore")
	private SqlSessionFactory sqlSessionFactory;

public int num_page_size; //1페이지당 사이즈
public int num_page_no = 1; //페이지번호
public int lastrow; // row개수

public void pageSize(int size) {
	this.num_page_size = size;
	//System.out.println("페이지 사이즈 : "+num_page_size);
}

	public void pageNum(int page) {
		this.num_page_no = page;
		//System.out.println("페이지번호 : "+num_page_no);
	}
	//마지막 row 가져오기
	public int lastRow() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			lastrow = session.selectOne("com.talkabout.dto.DebateRecruitMapper.lastRow");
//			System.out.println("게시물 총"+lastrow);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			//throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			//DB연결 해제
			if(session !=null) {
				session.close();
			}
		}
		return lastrow;
	}
	public int searchLastRow(String word) { //검색 후 결과 개수
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			lastrow = session.selectOne("com.talkabout.dto.DebateRecruitMapper.searchLastRow",word);
//			System.out.println("게시물 총"+lastrow);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			//throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			//DB연결 해제
			if(session !=null) {
				session.close();
			}
		}
		return lastrow;
	}
	
	@Override
	public List<Debate> selectAll(int startRow, int endRow) throws FindException {
		List<Debate> list = new ArrayList<>();
		SqlSession session = null;
		
		 HashMap<String, Integer> map = new HashMap<>();
		 map.put("num_start_row", startRow);
		 map.put("num_end_row", endRow);
		try {
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			list = session.selectList("com.talkabout.dto.DebateRecruitMapper.selectAll",map);
		//	System.out.println(list);
		}catch (Exception e) {
			//System.out.println(e.getMessage());
			throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			//DB연결 해제
			if(session !=null) {
				session.close();
			}
		}
		return list;
	}
	@Override
	public List<Debate> selectAll(String word,int startRow, int endRow) {
		List<Debate> list = new ArrayList<>();
		SqlSession session = null;

		 HashMap<String, Object> map = new HashMap<>();
		 map.put("word", word);
		 map.put("num_start_row", startRow);
		 map.put("num_end_row", endRow);
		try {
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			list = session.selectList("com.talkabout.dto.DebateRecruitMapper.selectWord",map);
		//	System.out.println(list);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			//throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			//DB연결 해제
			if(session !=null) {
				session.close();
			}
		}
		return list;
	}
	@Override
	public Map<String, Object> selectByNo(int debate_no) throws FindException {
		Map<String, Object> map = new HashMap<>();
		Debate deb = new Debate();
		DebateDetail dd = new DebateDetail();
		List<DebateDetail> ddList = new ArrayList<>();
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			deb = session.selectOne("com.talkabout.dto.DebateRecruitMapper.selectByNo",debate_no);
			ddList = session.selectList("com.talkabout.dto.DebateRecruitMapper.ddselectByNo",debate_no);
			map.put("debate", deb);
			map.put("detail", ddList);
		//	System.out.println(list);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			//DB연결 해제
			if(session !=null) {
				session.close();
			}
		}
		return map;
	}

	@Override
	public List<Debate> selectSearch(String column, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertDebate(Debate deb, String discuss1, String discuss2) {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession(); //jdbc MyConnetion 역할.
			session.insert("com.talkabout.dto.DebateRecruitMapper.debInsert",deb);
			int debNo = session.selectOne("com.talkabout.dto.DebateRecruitMapper.debSeq");
			Map<String,Object> map = new HashMap<>();
			map.put("debate_no", debNo);
			map.put("discuss", discuss1);
			session.insert("com.talkabout.dto.DebateRecruitMapper.ddInsert",map);
			map.put("discuss", discuss2);
			session.insert("com.talkabout.dto.DebateRecruitMapper.ddInsert",map);
			//System.out.println("==="+debNo);
			//	System.out.println(list);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			//throw new FindException(e.getMessage()); //콘솔에 예외 종류, 내용, 줄번호 출력 (가공예외)
		}finally{
			//DB연결 해제
			if(session !=null) {
				session.close();
			}
		}
	}

	@Override
	public void updateDebateAll(Debate deb) throws ModifyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDebateAll(Debate deb, List<DebateDetail> dd, String discuss1, String discuss2)
			throws ModifyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStatus(Debate status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStartdate(Debate deb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnddate(Debate end_date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDiscussor(Debate deb_no, DebateDetail dd, Member m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDebate(Debate deb_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancleDiscussor(Debate deb_no, DebateDetail dd, Member m) {
		// TODO Auto-generated method stub
		
	}



	
	public static void main(String[] args) {
		
		DebateDAOOracle dao = new DebateDAOOracle();
		dao.lastRow();
		
//		Debate deb_no = new Debate();
//		deb_no.setDebate_no(2);
//		DebateDetail dd = new DebateDetail();
//		dd.setDiscuss("삼성");
//		Member m = new Member();
//		m.setMember_no(2);
//		dao.updateDiscussor(deb_no, dd, m);
		
		
//		Debate d = new Debate();
//		d.setDebate_no(6);
//		dao.deleteDebate(d);
		
//		Debate db = dao.selectByNo(1);
//		System.out.println(db.getDebate_date());
//		System.out.println(db.getDebate_no());
//		System.out.println(db.getDebate_topic());
//		System.out.println(db.getDebate_time());
//		System.out.println(time);
		
		//전체 수정
//		Debate deb = new Debate();
//		deb.setDebate_topic("수정_주제");
//		deb.setDebate_time(30);
//		deb.setDebate_no(1);
//		
//		Date debate_date = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("2021-07-07 12:31:00");
//		debate_date.setYear(2023);;
//		System.out.println(df);
//		deb.setDebate_date(debate_date);
		
//		
//		try {
//			dao.updateDebateAll(deb);
//		} catch (ModifyException e) {
//			e.printStackTrace();
//		}
		
		

		
//		//Debate insert
//		Debate deb = new Debate();
//		Member mem = new Member();
//		mem.setMember_no(1);
//		DebateDetail dd = new DebateDetail();
//		deb.setDebate_writer(1);
//		deb.setDebate_topic("시험인가");
//		
//		Date date = new Date();
//		long ldate = date.getTime();
//		java.sql.Date sqldate = new java.sql.Date(ldate);
//		String from = "2018-09-06 11:11";
//		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		java.sql.Date to=null;
//		to = sqldate;
//		System.out.println(to.toString());
//		deb.setDebate_date(sqldate);
//		deb.setDebate_time(120);
//		dao.insertDebate(deb, dd, "주장1이다", "주장2이다");
		
		
		
		//		List<Debate> list = dao.selectAll();
//		
//		for (Debate debate : list) {
//			System.out.println(debate.getDebate_topic());
//		}
//		System.out.println(dao.selectByNo(3).toString());
	}









}
