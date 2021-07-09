package com.talkabout.control;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
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
		
		DebateService service;
		service = DebateService.getInstance();
		
		String method = request.getParameter("method");
		//System.out.println(method);
		String debate_topic = request.getParameter("debate_topic");
		String discuss1 = request.getParameter("discuss1");
		String discuss2 = request.getParameter("discuss2");
		String debate_date = request.getParameter("debate_date");
		debate_date = debate_date.replace('T', ' ');
		String strtime = request.getParameter("debate_time");
		int debate_time = Integer.parseInt(strtime);
		
		if(method.equals("debatesave")) {
			Debate deb = new Debate();
			deb.setDebate_writer(m.getMember_no());
			deb.setDebate_topic(debate_topic);
			deb.setDebate_date(debate_date);
			deb.setDebate_time(debate_time);
			DebateDetail dd = new DebateDetail();
			service.addDebate(deb, dd, discuss1, discuss2);
			//System.out.println("주장 등록 완료"+deb.toString());
		}
		if(method.equals("btnDiscuss1")) {
			//System.out.println("토론자1 서블릿");
			Debate deb = new Debate();
			deb.setDebate_writer(m.getMember_no()); // 임시 작성자
			deb.setDebate_topic(debate_topic);
			deb.setDebate_date(debate_date);
			deb.setDebate_time(debate_time);
			DebateDetail dd = new DebateDetail();
			service.addDebate(deb, dd, discuss1, discuss2);
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}
		if(method.equals("btnDiscuss2")) {
			Debate deb = new Debate();
			deb.setDebate_writer(m.getMember_no()); // 임시 작성자
			deb.setDebate_topic(debate_topic);
			deb.setDebate_date(debate_date);
			deb.setDebate_time(debate_time);
			DebateDetail dd = new DebateDetail();
			service.addDebate(deb, dd, discuss1, discuss2);
			//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
		}
		
		
	}

}
