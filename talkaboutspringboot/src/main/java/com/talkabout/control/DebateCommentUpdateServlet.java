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
import com.talkabout.dao.MemberDAOOracle;
import com.talkabout.dto.DebateComment;
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.DebateCommentService;

public class DebateCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
		
		ServletContext sc = getServletContext();
		DebateCommentService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		request.setCharacterEncoding("utf-8");
	
		String strcom_no = request.getParameter("com_no");
		String com_contents = request.getParameter("com_contents");
		String method = request.getParameter("method");
		
		ObjectMapper mapper = new ObjectMapper();
		DebateCommentService service;
		service = DebateCommentService.getInstance();
		String jsonStr ="";
		DebateComment DC = new DebateComment();
		DC.setCom_contents(strcom_no);
		MemberDAOOracle mDAO  = null;
		Member member = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int com_no = Integer.parseInt(strcom_no);
			DC.setCom_no(com_no);//로그인한 정보로 조회
			DC.setCom_contents(com_contents);//로그인한 정보로 조회
			service.DCupdate(DC);//내용을 수정
			
		} catch (ModifyException e) {
			e.printStackTrace();
			// TODO: handle exception
		}//selectbycomno Servlet
		jsonStr = mapper.writeValueAsString(map);
		response.setContentType("application/json;charset=utf-8;");
		PrintWriter out = response.getWriter();
		response.getWriter().print(jsonStr);
		System.out.println("제이슨"+jsonStr);
		
	}

}
