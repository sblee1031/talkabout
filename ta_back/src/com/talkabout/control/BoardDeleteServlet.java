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
import com.talkabout.exception.DeleteException;
import com.talkabout.service.BoardService;

/**
 * Servlet implementation class BoardDeleteServlet
 */
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.데이터 얻기
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String strboard_no = request.getParameter("board_no");
		int board_no = Integer.parseInt(strboard_no);
		
		
		//2.비즈니스 로직
		ServletContext sc = getServletContext();
		BoardService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		BoardService service;
		service = BoardService.getInstance();
		Map<String,Object> map = new HashMap<>();
		try {
			service.DeleteBoard(board_no);
			map.put("status", 1);
		} catch (DeleteException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper;
        mapper = new ObjectMapper();
        String jsonStr;
        jsonStr = mapper.writeValueAsString(map);
        
        response.setContentType("application/json;charset=utf-8"); //응답형식지정
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        
	}

}
