package com.talkabout.control;

import java.io.IOException;
import java.io.PrintWriter;

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
		ServletContext sc = getServletContext();
		DebateCommentService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		request.setCharacterEncoding("utf-8");
		//요청전달데이터
		
	    String strdc= request.getParameter("com_contents");
	    System.out.println(strdc);
	    	
	    
		ObjectMapper mapper = new ObjectMapper();
		DebateCommentService service;
		service = DebateCommentService.getInstance();
		String jsonStr ="";
		DebateComment DC = new DebateComment();
		DC.setCom_contents(strdc);
		MemberDAOOracle mDAO  = null;
		Member member = null;
		
		try {
			DC.setCom_mem(member.getMember_no());
			DC.setCom_deb(member.getMember_no());
			service.DCinsert(DC);
		     
			
		} catch (AddException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=utf-8;");
		PrintWriter out = response.getWriter();
		response.getWriter().print(jsonStr);	
	}

}
