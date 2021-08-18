package com.talkabout.control;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkabout.dto.Audience;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.service.AudienceService;
import com.talkabout.service.DebateDetailService;
import com.talkabout.service.DebateService;
import com.talkabout.service.MemberService;

/**
 * Servlet implementation class DebateBattleDetailServlet
 */
public class DebateBattleDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		
		request.setCharacterEncoding("utf-8");
		String str_Debno = request.getParameter("deb_no");
		int deb_no = Integer.parseInt(str_Debno);
		
		ServletContext sc = getServletContext();
		
		AudienceService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		DebateService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		DebateDetailService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		MemberService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		AudienceService aud_service = AudienceService.getInstance();
		DebateService deb_service = DebateService.getInstance();
		DebateDetailService detail_service = DebateDetailService.getInstance();
		MemberService mem_service = MemberService.getInstance();
		
		Map<String,Object> map = new HashMap<>();
		if (loginmem == null) {
			map.put("status", 0);
		} else {
			// Debate
			Debate deb_list = deb_service.findByNo(deb_no);
			
			// DebateDetail
			List<DebateDetail> discussor_list = detail_service.findByDebNo(deb_no);
			DebateDetail discussor_a = discussor_list.get(0);
			DebateDetail discussor_b = discussor_list.get(1);

			// Member
			Member mem_a = null;
			Member mem_b = null;
			try {
				mem_a = mem_service.memberInfo(discussor_a.getDiscussor());
				mem_b = mem_service.memberInfo(discussor_b.getDiscussor());
			} catch (FindException e) {
				e.printStackTrace();
			}
			
			// Audience
			int login_mem_no = loginmem.getMember_no();
			Audience session_user = aud_service.findByDeb(deb_no, login_mem_no);
			// 세션 회원번호 != 토론자 A 번호 and 세션 회원번호 != 토론자 B 번호 and session_user == null
			if (login_mem_no != discussor_a.getDiscussor() && login_mem_no != discussor_b.getDiscussor() && session_user == null) {
				aud_service.addVote(deb_no, login_mem_no);
			}
			ArrayList<Integer> vote_list = aud_service.countVote(deb_no);

			map.put("detail_vote", vote_list);
			map.put("detail_debate", deb_list);
			map.put("detail_discussorA", discussor_a);
			map.put("detail_discussorB", discussor_b);
			map.put("detail_memberA", mem_a);
			map.put("detail_memberB", mem_b);
			map.put("login_member", loginmem);
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
