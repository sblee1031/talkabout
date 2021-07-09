package com.talkabout.servlet;

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
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateService;
import com.talkabout.service.MemberService;

/**
 * Servlet implementation class DebateServelt
 */
public class DebateServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//세션 정보 가져오기 필요.
		
		request.setCharacterEncoding("utf-8");
		ServletContext sc = getServletContext();		
		DebateService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		MemberService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		DebateService service;
		service = DebateService.getInstance();
		
		MemberService memservice;
		memservice = MemberService.getInstance();
		
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr ="";
		
		Map<String, Object> map = new HashMap<>();
		
		List<Debate> list = new ArrayList<>();

		try {
			
		list = service.findAll();
		//System.out.println(list.size());
		
		List<Member> memList = new ArrayList<>();
		for (Debate debate : list) {
			Member mem = new Member();
			try {
				//System.out.println(debate.getDebate_writer());
				mem = memservice.memberInfo(debate.getDebate_writer());
				memList.add(mem);
			} catch (FindException e) {
				e.printStackTrace();
			}
			
		}
			if(list.size()==0) {
				System.out.println("게시글 없음");
			}else {
				map.put("debatelist", list);
				map.put("memberinfo", memList);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		jsonStr = mapper.writeValueAsString(map);
		//5.응답
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
