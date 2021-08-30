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
import com.talkabout.dto.Debate;
import com.talkabout.service.AudienceService;
import com.talkabout.service.DebateDetailService;
import com.talkabout.service.DebateService;

public class DebateBattleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		DebateService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		DebateDetailService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		AudienceService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		DebateService deb_service = DebateService.getInstance();
		DebateDetailService detail_service = DebateDetailService.getInstance();
		AudienceService aud_service = AudienceService.getInstance();
		
		List<Debate> debate_info = deb_service.findAll();
		ArrayList<Integer> vote_info = new ArrayList<Integer>();
		for (Debate deb : debate_info) {
			ArrayList<Integer> vote_cnt = aud_service.countVote(deb.getDebate_no());
			// 인덱스 0 : 찬성, 1 : 중립, 2 : 반대
			int vote_sum = vote_cnt.get(0) + vote_cnt.get(1) + vote_cnt.get(2);
			vote_info.add(vote_sum);
		}
		
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json;charset=utf-8");
		
		map.put("debateinfo", debate_info);
		map.put("voteinfo", vote_info);
		String jsonStr = mapper.writeValueAsString(map);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}
}
