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
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateCommentService;

public class DebateCommentSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();
		DebateCommentService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		System.out.println(DebateCommentService.envProp);
		request.setCharacterEncoding("utf-8");
	
		String strcom_no = request.getParameter("com_deb");
		System.out.println(strcom_no);
		
		int com_no = Integer.parseInt(strcom_no);
		ObjectMapper mapper = new ObjectMapper();
		DebateCommentService service;
		service = DebateCommentService.getInstance();
		String jsonStr ="";
		
		Map<String, Object> map = new HashMap<>();
		List<DebateComment> DC = new ArrayList<>();
		try {
			 DC = service.DCselectByComNo(com_no);
			map.put("comment", DC);
		 jsonStr = mapper.writeValueAsString(map);
			
		} catch (FindException e) {
			e.printStackTrace();
			// TODO: handle exception
		}//selectbycomno Servlet
		response.setContentType("application/ json;charset=utf-8;");
		PrintWriter out = response.getWriter();
		response.getWriter().print(jsonStr);
	}

}
