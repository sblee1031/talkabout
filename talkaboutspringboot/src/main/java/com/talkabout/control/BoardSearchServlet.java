package com.talkabout.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkabout.dto.Board;
import com.talkabout.exception.FindException;
import com.talkabout.service.BoardService;

/**
 * Servlet implementation class BoardSearchServlet
 */
public class BoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		
//		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
//		String board_type = request.getParameter("board_type");
//		String board_title = request.getParameter("board_title");
//		String board_contents = request.getParameter("board_contents");
		
		String type = request.getParameter("type");
		String contents = request.getParameter("contents");
		System.out.println(type);
		System.out.println(contents);
		 
		ServletContext sc = getServletContext();
		BoardService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		BoardService service = null;
		service = BoardService.getInstance();
		List<Board> boardsearch = null;
		
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			boardsearch = service.BoardSearch(type, contents);
			System.out.println(boardsearch.size());
			String jsonStr = mapper.writeValueAsString(boardsearch);
			System.out.println(jsonStr);
			out.print(jsonStr);
		}catch(FindException e){
			e.printStackTrace();
		}
	}
}
