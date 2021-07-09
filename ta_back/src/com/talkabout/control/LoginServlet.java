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
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.service.MemberService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인했다면 로그인 정보가 담겨있는 객체
		HttpSession session = request.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		
		
		if(loginmem==null) {
			System.out.println("null임");
			
			ServletContext sc = getServletContext();		
			MemberService.envProp = sc.getRealPath(sc.getInitParameter("env"));
			//session.removeAttribute("usercheck");
			
			request.setCharacterEncoding("utf-8");
			String social_no = request.getParameter("social_no");
			
			MemberService service;
			service = MemberService.getInstance();
			
			ObjectMapper mapper;
			mapper = new ObjectMapper();
			String jsonStr ="";
			
			Map<String, Object> map = new HashMap<>();
			String usercheck;
			
			Member m = null;
			try {
				 m = service.memberCheck(social_no);
				if(m == null) {
					map.put("usercheck", "non_member");
				}else {
					session.setAttribute("logininfo", m);//로그인 계정 정보가 담긴 세션
					map.put("usercheck", "member");
					map.put("member", m);
				}
			} catch (FindException e) {
				e.printStackTrace();
			}
			jsonStr = mapper.writeValueAsString(map);
			//5.응답
			response.setContentType("application/json;charset=utf-8"); //응답형식지정
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			
		}else {
			System.out.println(loginmem.getMember_nickName());
			Map<String, Object> map = new HashMap<>();
			ObjectMapper mapper;
			mapper = new ObjectMapper();
			String jsonStr ="";
			map.put("logined", "logined");
			map.put("member", loginmem);
			
			jsonStr = mapper.writeValueAsString(map);
			//5.응답
			response.setContentType("application/json;charset=utf-8"); //응답형식지정
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			
		}
		//session.invalidate(); //세션제거
		
		
		
	}

}
