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
import com.talkabout.exception.ModifyException;
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
		String board_title = request.getParameter("board_title");
		String board_contents = request.getParameter("board_contents");
		int board_no = Integer.parseInt(strboard_no);
		System.out.println(board_no);
		System.out.println(board_title);
		System.out.println(board_contents);
		BoardService service;
		
		//2.비즈니스로직 호출
		ServletContext sc = getServletContext();
		BoardService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		service = BoardService.getInstance();
		String path;
		//Map<String,Object> map = new HashMap<>();
		try {
			Board b = new Board();
			b.setBoard_no(board_no);
			b.setBoard_title(board_title);
			b.setBoard_contents(board_contents);
			service.EditBoard(b);
			//map.put("boardDetail", b);
		
		} catch(ModifyException e) {
			e.printStackTrace();
		}
		jsonStr = mapper.writeValueAsString("성공");
		System.out.println(jsonStr);
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}

}
