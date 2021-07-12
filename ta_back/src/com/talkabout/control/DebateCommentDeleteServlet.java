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
import com.talkabout.dto.DebateComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateCommentService;


public class DebateCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ServletContext sc = getServletContext();
		DebateCommentService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		request.setCharacterEncoding("utf-8");
	
		String strcom_deb = request.getParameter("com_no");
		System.out.println(strcom_deb);
		
		
		ObjectMapper mapper = new ObjectMapper();
		DebateCommentService service;
		service = DebateCommentService.getInstance();
		String jsonStr ="";
		DebateComment DC = new DebateComment();
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			int com_deb = Integer.parseInt(strcom_deb);
			 service.DCdelete(com_deb);//원하는 토론번호의 댓글 삭제
			
		} catch (DeleteException e) {
			e.printStackTrace();
			// TODO: handle exception
		}//selectbycomno Servlet
		 jsonStr = mapper.writeValueAsString(map);
		response.setContentType("application/json;charset=utf-8;");
		PrintWriter out = response.getWriter();
		response.getWriter().print(jsonStr);
	}

}
