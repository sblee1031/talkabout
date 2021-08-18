package com.talkabout.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkabout.dto.Audience;
import com.talkabout.dto.Member;
import com.talkabout.service.AudienceService;
import com.talkabout.service.MemberService;

public class SetVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		
		// 현재 접속중인 클라이언트의 회원번호
		int loginmem_no = loginmem.getMember_no();
		
		String strvote_type = request.getParameter("vote_type");
		int vote_type = Integer.parseInt(strvote_type);
		String strdeb_no = request.getParameter("deb_no");
		int deb_no = Integer.parseInt(strdeb_no);
		String strA_no = request.getParameter("discussorA_no");
		int discussorA_no = Integer.parseInt(strA_no);
		String strB_no = request.getParameter("discussorB_no");
		int discussorB_no = Integer.parseInt(strB_no);
		
		ServletContext sc = getServletContext();		
		AudienceService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		request.setCharacterEncoding("utf-8");
		
		AudienceService aud_service;
		aud_service = AudienceService.getInstance();
		
		Map<String, Object> map = new HashMap<>();
		
		if (loginmem_no == discussorA_no || loginmem_no == discussorB_no) {
			// 현재 로그인된 회원이 토론자 A 또는 토론자 B일 경우
			map.put("status", 0);
		} else {
			Audience a = aud_service.findByDeb(deb_no, loginmem_no);
			a.setVote(vote_type);
			aud_service.setVote(a);
			map.put("status", 1);
		}

		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr;
		jsonStr = mapper.writeValueAsString(map);
		
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}

}
