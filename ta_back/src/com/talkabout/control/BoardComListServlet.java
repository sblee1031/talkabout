package com.talkabout.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkabout.dto.BoardComment;
import com.talkabout.exception.FindException;
import com.talkabout.service.BoardCommentService;

/**
 * Servlet implementation class BoardComListServlet
 */
public class BoardComListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//1.요청전달데이터 얻기
		//request.setCharacterEncoding("utf-8");
		String board_no = request.getParameter("board_no");
		int com_board = Integer.parseInt(board_no);
		System.out.println(com_board);
		
		//2.비즈니스 로직
		ServletContext sc = getServletContext();
		BoardCommentService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		BoardCommentService service;
		service = BoardCommentService.getInstance();
		List<BoardComment> boardcomlist = null;
		Map<String,Object> map = new HashMap<>();
		try {
			boardcomlist = service.BoardComList(com_board);
			System.out.println(boardcomlist.size());
			map.put("boardcomlist",boardcomlist);
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
