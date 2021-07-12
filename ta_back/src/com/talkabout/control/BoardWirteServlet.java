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
import com.talkabout.dao.BoardDAO;
import com.talkabout.dto.Board;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.service.BoardService;

/**
 * Servlet implementation class BoardWirteServlet
 */
public class BoardWirteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		Map<String,Object> map = new HashMap<>();
		Member loginmem = (Member) session.getAttribute("logininfo");
		if (loginmem == null) {
			   //로그인이 안되어있을 때
			   map.put("status", 0);
		} 
		else {
		   //로그인이 되어있을 때
		    request.setCharacterEncoding("utf-8");
			String board_type = request.getParameter("board_type");
			String board_title = request.getParameter("board_title");
			String board_contents = request.getParameter("board_contents");
			// String strboard_mem = request.getParameter("board_mem");
			System.out.println(board_contents);
			// int board_mem = Integer.parseInt(strboard_mem);
			int board_mem = loginmem.getMember_no();
			
			Board b = new Board();
			b.setBoard_type(board_type);
			b.setBoard_title(board_title);
			b.setBoard_contents(board_contents);
			b.setBoard_mem(board_mem);
			
			
			ServletContext sc = getServletContext();
			BoardService.envProp = sc.getRealPath(sc.getInitParameter("env"));
			BoardService service;
			service = BoardService.getInstance();
			
			try {
				service.AddBoard(b);
				 map.put("status", 1);
				 System.out.println("add");
			} catch(AddException e1) {
				e1.printStackTrace();
			}
		}

        ObjectMapper mapper;
        mapper = new ObjectMapper();
        String jsonStr;
        jsonStr = mapper.writeValueAsString(map);
        
        response.setContentType("application/json;charset=utf-8"); //응답형식지정
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        System.out.println(jsonStr);
	}
	
}
