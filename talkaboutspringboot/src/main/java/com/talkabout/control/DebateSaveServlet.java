package com.talkabout.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.DebateDetailService;
import com.talkabout.service.DebateService;

public class DebateSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		//로그인 정보를 담은 멤버 객체
		
		request.setCharacterEncoding("utf-8");
		ServletContext sc = getServletContext();		
		DebateService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		DebateDetailService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		DebateService service;
		service = DebateService.getInstance();
		
		DebateDetailService ddservice;
		ddservice = DebateDetailService.getInstance();
		
		String method = request.getParameter("method");
		//System.out.println(method);
		String strdebate_no = request.getParameter("debate_no");
		String debate_topic = request.getParameter("debate_topic");
		String discuss1 = request.getParameter("discuss1");
		String discuss2 = request.getParameter("discuss2");
	//	Date debate_date = request.getParameter("debate_date");
		
		String strtime = request.getParameter("debate_time");
		
		
		if(method.equals("debatesave")) {
			//debate_date = debate_date.replace('T', ' ');
			int debate_time = Integer.parseInt(strtime);
			Debate deb = new Debate();
			deb.setDebate_writer(m.getMember_no());
			deb.setDebate_topic(debate_topic);
			//deb.setDebate_date(debate_date);
			deb.setDebate_time(debate_time);
			DebateDetail dd = new DebateDetail();
			service.addDebate(deb, dd, discuss1, discuss2);
			//System.out.println("주장 등록 완료"+deb.toString());
		}
		if(method.equals("btnDiscuss1")) {
			//System.out.println("토론자1 서블릿");
			int debate_no = Integer.parseInt(strdebate_no);
			Member mem = new Member();
			mem.setMember_no(m.getMember_no());
			Debate deb = new Debate();
			deb.setDebate_no(debate_no);
			deb.setDebate_topic(debate_topic);
			DebateDetail dd = new DebateDetail();
			dd.setDiscuss(discuss1);
			service.addDiscussor(deb, dd, m);
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}
		if(method.equals("btnDiscuss2")) {
			int debate_no = Integer.parseInt(strdebate_no);
			Member mem = new Member();
			mem.setMember_no(m.getMember_no());
			Debate deb = new Debate();
			deb.setDebate_no(debate_no);
			deb.setDebate_topic(debate_topic);
			DebateDetail dd = new DebateDetail();
			dd.setDiscuss(discuss2);
			service.addDiscussor(deb, dd, m);
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}
		if(method.equals("btnCancleDiscuss1")) {
			//System.out.println("토론자1 서블릿");
			int debate_no = Integer.parseInt(strdebate_no);
			Member mem = new Member();
			mem.setMember_no(0);//토론자 취소 상태 0
			Debate deb = new Debate();
			deb.setDebate_no(debate_no);
			deb.setDebate_topic(debate_topic);
			DebateDetail dd = new DebateDetail();
			dd.setDiscuss(discuss1);
			deb.setDebate_status("모집중");
			service.updateStatus(deb);
			service.addDiscussor(deb, dd, mem);
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}
		if(method.equals("btnCancleDiscuss2")) {
			int debate_no = Integer.parseInt(strdebate_no);
			Member mem = new Member();
			mem.setMember_no(0);//토론자 취소 상태 0
			Debate deb = new Debate();
			deb.setDebate_no(debate_no);
			deb.setDebate_topic(debate_topic);
			DebateDetail dd = new DebateDetail();
			dd.setDiscuss(discuss2);
			deb.setDebate_status("모집중");
			service.updateStatus(deb);
			service.addDiscussor(deb, dd, mem);
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}
		if(method.equals("status")) {
			//System.out.println("status변경");
			int debate_no = Integer.parseInt(strdebate_no);
			Debate deb = new Debate();
			deb.setDebate_no(debate_no);
			deb.setDebate_status("대기중");
			service.updateStatus(deb);
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}
		if(method.equals("statusCancle")) {
			//System.out.println("status취소");
			int debate_no = Integer.parseInt(strdebate_no);
			Debate deb = new Debate();
			deb.setDebate_no(debate_no);
			deb.setDebate_status("모집중");
			service.updateStatus(deb);
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}if(method.equals("debateDelete")) {
			//System.out.println("주제삭제");
			int debate_no = Integer.parseInt(strdebate_no);
			Debate deb = new Debate();
			deb.setDebate_no(debate_no);
			service.deleteDebate(deb);
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}if(method.equals("debateModify")) {
			int debate_no = Integer.parseInt(strdebate_no);
			//System.out.println("주제삭제");
			List<DebateDetail> ddlist = null;
			ddlist= new ArrayList<>();
			ddlist = ddservice.findByDebNo(debate_no);
			//debate_date = debate_date.replace('T', ' ');
			int debate_time = Integer.parseInt(strtime);
			Debate deb = new Debate();
			//deb.setDebate_writer(m.getMember_no());
			deb.setDebate_no(debate_no);
			deb.setDebate_topic(debate_topic);
			//deb.setDebate_date(debate_date);
			deb.setDebate_time(debate_time);
			try {
				service.updateDebateAll(deb, ddlist, discuss1, discuss2);
			} catch (ModifyException e) {
				e.printStackTrace();
			}
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}
		
		
	}

}
