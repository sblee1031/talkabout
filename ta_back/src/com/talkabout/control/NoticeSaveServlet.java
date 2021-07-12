package com.talkabout.control;

import java.io.IOException;
import java.util.ArrayList;
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
import com.talkabout.dto.Notice;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.DebateDetailService;
import com.talkabout.service.DebateService;
import com.talkabout.service.NoticeService;

public class NoticeSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		//로그인 정보를 담은 멤버 객체
		
				request.setCharacterEncoding("utf-8");
				ServletContext sc = getServletContext();		
				NoticeService.envProp = sc.getRealPath(sc.getInitParameter("env"));
//				DebateDetailService.envProp = sc.getRealPath(sc.getInitParameter("env"));
				
				NoticeService service;
				service = NoticeService.getInstance();
				
//				DebateDetailService ddservice;
//				ddservice = DebateDetailService.getInstance();
				
				String method = request.getParameter("method");
				//System.out.println(method);
				String strnotice_no = request.getParameter("notice_no");
				String notice_type = request.getParameter("notice_type");
				String notice_title = request.getParameter("notice_title");
				String notice_contents = request.getParameter("notice_contents");
				String notice_date = request.getParameter("notice_date");
				
//				String strtime = request.getParameter("debate_time");
				if(method.equals("noticesave")) {
					notice_date = notice_date.replace('T', ' ');
//					int debate_time = Integer.parseInt(strtime);
					Notice noti = new Notice();
					noti.setNotice_admin(a.getAdmin_no());//질문 admin...
					noti.setNotice_type(notice_type);
					noti.setNotice_title(notice_title);
					noti.setNotice_contents(notice_contents);
					noti.setNotice_date(notice_date);//시간설명필요
//					DebateDetail dd = new DebateDetail();
					service.WriteNotice(noti);
					//System.out.println("주장 등록 완료"+deb.toString());
				}if(method.equals("noticeDelete")) {
					//System.out.println("주제삭제");
					int notice_no = Integer.parseInt(strnotice_no);
					Notice noti = new Notice();
					noti.setNotice_no(notice_no);
					service.DeleteNotice(int noti_no);
					//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
				}if(method.equals("debateModify")) {
					int notice_no = Integer.parseInt(strnotice_no);
					//System.out.println("주제삭제");
					List<Notice> list = null;
//					ddlist= new ArrayList<>();
//					ddlist = ddservice.findByDebNo(debate_no);
					notice_date = notice_date.replace('T', ' ');
//					int debate_time = Integer.parseInt(strtime);
					Notice noti = new Notice();
					//deb.setDebate_writer(m.getMember_no());
					noti.setNotice_no(notice_no);
					noti.setNotice_type(notice_type);
					noti.setNotice_title(notice_title);
					noti.setNotice_contents(notice_contents);
					noti.setNotice_date(notice_date);
//					deb.setDebate_time(debate_time);
					try {
						service.EditNotice(noti);
					} catch (ModifyException e) {
						e.printStackTrace();
					}
					//System.out.println(deb.getDebate_writer()+"번 회원 토론자 입력");
				}
				
				
			}

		
				
				
	}


