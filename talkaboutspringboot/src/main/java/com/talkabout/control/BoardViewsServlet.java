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
import com.talkabout.exception.ModifyException;
import com.talkabout.service.BoardService;

/**
 * Servlet implementation class BoardViewsServlet
 */
public class BoardViewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String strboard_no = request.getParameter("board_no");
		//String strboard_views= request.getParameter("board_views");
		int board_no = Integer.parseInt(strboard_no);
		//int board_views = Integer.parseInt(strboard_views);
		//System.out.println(board_no);
		//System.out.println(board_views);
	
		ObjectMapper mapper = new ObjectMapper(); //jack-bind라이브러리API
		PrintWriter out = response.getWriter();
		
		//비즈니스 로직
		ServletContext sc = getServletContext();
		BoardService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		BoardService service;
		service = BoardService.getInstance();
		try {
			service.CountBoardView(board_no);
		} catch (ModifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=utf-8");
		String jsonStr = mapper.writeValueAsString("성공");
		out.print(jsonStr);
		
	}

}
