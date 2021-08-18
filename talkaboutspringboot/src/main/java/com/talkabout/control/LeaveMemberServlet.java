package com.talkabout.control;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.service.MemberService;

public class LeaveMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		
		request.setCharacterEncoding("utf-8");
		String chkLeave = request.getParameter("chkLeave"); // 닉네임 체크용 매개변수
		String leaveNo = request.getParameter("leaveNo"); // 닉네임 체크용 매개변수
		int intleaveNo = Integer.parseInt(leaveNo);
//		System.out.println(chkLeave+ " / " + intleaveNo);
		
		ServletContext sc = getServletContext();		
		MemberService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		MemberService service;
		service = MemberService.getInstance();
		
		if(loginmem.getMember_no() == intleaveNo) {
			Member m = new Member();
			m.setMember_no(intleaveNo);
			try {
				service.leaveMember(m);
			} catch (FindException e) {
				e.printStackTrace();
			}
//			System.out.println(loginmem.getMember_nickName()+" 탈퇴완료");
			session.invalidate(); //세션제거
		}
		
	}

}
