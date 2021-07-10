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
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateDetailService;
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
		String method = request.getParameter("method");
		
		DebateService service;
		service = DebateService.getInstance();
		
		MemberService memservice;
		memservice = MemberService.getInstance();
		
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr ="";
		
		
		Map<String, Object> map = new HashMap<>();
		List<Debate> list = new ArrayList<>();
		
		if(method.equals("listall")) {
			try {
			list = service.findAll();
			List<Member> memList = new ArrayList<>();
			for (Debate debate : list) {
				Member mem = new Member();
				try {
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
		}

		
		jsonStr = mapper.writeValueAsString(map);
		//5.응답
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//세션 정보 가져오기 필요.
		
		request.setCharacterEncoding("utf-8");
		ServletContext sc = getServletContext();		
		DebateService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		MemberService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		DebateDetailService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		
		String method = request.getParameter("method");
		String column = request.getParameter("column");
		String keyword = request.getParameter("keyword");
		//System.out.println(method);
		
		DebateService service;
		service = DebateService.getInstance();
		
		DebateDetailService ddservice;
		ddservice = DebateDetailService.getInstance();
		
		MemberService memservice;
		memservice = MemberService.getInstance();
		
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr ="";
		
		
		Map<String, Object> map = new HashMap<>();
		List<DebateDetail> list = new ArrayList<>();
		List<Debate> dlist = new ArrayList<>();
		
		String strdeb_no = request.getParameter("deb_no");
		
		if(method.equals("debatedetail")) {
			//System.out.println("detail");
			int deb_no = Integer.parseInt(strdeb_no);
			Debate d = new Debate();
			d = service.findByNo(deb_no);
			list= ddservice.findByDebNo(deb_no);
			map.put("debate", d);
			map.put("detail", list);
		}if(method.equals("debatesearch")) {
			try {
				if(column.equals("WRITER")) {
					Member m = new Member();
					m.setMember_nickName(keyword);
					Member nickMem = memservice.searchNick(m);
					dlist= service.selectSearch(column, nickMem.getMember_no()+"");
				}else {
					dlist= service.selectSearch(column, keyword);
				}
			List<Member> memList = new ArrayList<>();
			for (Debate debate : dlist) {
				Member mem = new Member();
				try {
					mem = memservice.memberInfo(debate.getDebate_writer());
					memList.add(mem);
				} catch (FindException e) {
					e.printStackTrace();
				}
			}
				if(dlist.size()==0) {
					System.out.println("게시글 없음");
				}else {
					map.put("rs", dlist);
					map.put("memberinfo", memList);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
		jsonStr = mapper.writeValueAsString(map);
		//5.응답
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}

}
