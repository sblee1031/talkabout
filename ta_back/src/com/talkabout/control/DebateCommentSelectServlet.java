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
import com.talkabout.dto.DebateComment;
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateCommentService;
import com.talkabout.service.MemberService;

public class DebateCommentSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();
		DebateCommentService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		request.setCharacterEncoding("utf-8");
	
		String strcom_no = request.getParameter("com_deb");
		String method = request.getParameter("method");
		//System.out.println(strcom_no);
		
		MemberService memservice;
		memservice = MemberService.getInstance();
		
		ObjectMapper mapper = new ObjectMapper();
		DebateCommentService service;
		service = DebateCommentService.getInstance();
		String jsonStr ="";
		Map<String, Object> map = new HashMap<>();
		List<DebateComment> DC = new ArrayList<>();
		List<Debate> debateList = new ArrayList<>();
		
		if(method.equals("comment")) {
		int com_no = Integer.parseInt(strcom_no);
		//System.out.println("게시글 : "+com_no);
		try {
			 DC = service.DCselectByComNo(com_no);//com_no는 그냥 변수고 com_deb 으로  select
			System.out.println("size : "+DC.size());
			 List<Member> memList = new ArrayList<>();
			for (DebateComment debate : DC) {
				
				Member mem = new Member();
				try {
					mem = memservice.memberInfo(debate.getCom_mem());
					memList.add(mem);
					//System.out.println("mlist : "+ memList.size());
				} catch (FindException e) {
					e.printStackTrace();
				}
				
			}
				if(DC.size()==0) {
					System.out.println("게시글 없음");
				}else {
					map.put("comment", DC);
					map.put("memberinfo", memList);
				}
		} catch (FindException e) {
			e.printStackTrace();
			// TODO: handle exception
		}//selectbycomno Servlet
		}
		if(method.equals("result")) {
			//System.out.println("result");
			try {
				debateList=service.selectAll();
				List<Member> memList = new ArrayList<>();
				for (Debate debate : debateList) {
					Member mem = new Member();
					try {
						mem = memservice.memberInfo(debate.getDebate_writer());
						memList.add(mem);
					} catch (FindException e) {
						e.printStackTrace();
					}
					
				}
					if(debateList.size()==0) {
						System.out.println("게시글 없음");
					}else {
						map.put("debatelist", debateList);
						map.put("memberinfo", memList);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			
		}
		
		
		 jsonStr = mapper.writeValueAsString(map);
		 //System.out.println(jsonStr);
		response.setContentType("application/json;charset=utf-8;");
		PrintWriter out = response.getWriter();
		response.getWriter().print(jsonStr);
	}

}
