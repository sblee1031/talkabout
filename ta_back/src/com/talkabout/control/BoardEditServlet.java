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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkabout.dto.Board;
import com.talkabout.exception.FindException;
import com.talkabout.service.BoardService;

/**
 * Servlet implementation class BoardEditServlet
 */
public class BoardEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr;
		
		//1. 요청전달데이터 얻기
		request.setCharacterEncoding("utf-8");
		String strboard_no = request.getParameter("board_no");
		int board_no = Integer.parseInt(strboard_no);
		System.out.println(board_no);
		BoardService service;
		
		//2.비즈니스로직 호출
		ServletContext sc = getServletContext();
		BoardService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		service = BoardService.getInstance();
		String path;
		Map<String,Object> map = new HashMap<>();
		try {
			Board b = new Board();
			b = service.BoardDetail(board_no);
			map.put("boardDetail", b);
		
		} catch(FindException e) {
			e.printStackTrace();
		}
		jsonStr = mapper.writeValueAsString(map);
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}

}
