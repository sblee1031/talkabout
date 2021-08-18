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
import com.talkabout.dao.MemberDAOOracle;
import com.talkabout.dto.DebateComment;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.service.DebateCommentService;


public class DebateCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginmem=(Member) session.getAttribute("logininfo");
		ServletContext sc = getServletContext();
		DebateCommentService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		request.setCharacterEncoding("utf-8");
		//요청전달데이터
		
	    String strdc= request.getParameter("com_contents");
	    String strcom_deb= request.getParameter("com_deb");
	    System.out.println(strdc);
	    	
	    
		ObjectMapper mapper = new ObjectMapper();
		DebateCommentService service;
		service = DebateCommentService.getInstance();
		String jsonStr ="";
		DebateComment DC = new DebateComment();
		DC.setCom_contents(strdc);
		MemberDAOOracle mDAO  = null;
		Member member = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int com_deb = Integer.parseInt(strcom_deb);
			DC.setCom_mem(loginmem.getMember_no());//예시 ,여기는 로그인정보가 들어가야함
			DC.setCom_deb(com_deb);//댓글을 달려는 토론 결과의 토로번호
			service.DCinsert(DC);//댓글작성
			
		} catch (AddException e) {
			e.printStackTrace();
		}
		 jsonStr = mapper.writeValueAsString(map);
		response.setContentType("application/json;charset=utf-8;");
		PrintWriter out = response.getWriter();
		response.getWriter().print(jsonStr);
	}

}
