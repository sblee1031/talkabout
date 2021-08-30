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
import com.talkabout.dto.BoardComment;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.service.BoardCommentService;
import com.talkabout.service.BoardService;

/**
 * Servlet implementation class BoardComWriteServlet
 */
public class BoardComWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Map<String,Object> map = new HashMap<>();
		Member loginmem = (Member) session.getAttribute("logininfo");
		
		if (loginmem == null) {
			   //로그인이 안되어있을 때
			   map.put("status", 0);
			} else {
				//로그인이 되어있을 때
			    request.setCharacterEncoding("utf-8");
				String strcom_board = request.getParameter("com_board");
				int com_board = Integer.parseInt(strcom_board);
				String com_contents = request.getParameter("com_contents");
				int com_mem = loginmem.getMember_no();
				
				
				BoardComment bc = new BoardComment();
				bc.setCom_board(com_board);
				bc.setCom_contents(com_contents);
				bc.setCom_mem(com_mem);
				
				ServletContext sc = getServletContext();
				BoardCommentService.envProp = sc.getRealPath(sc.getInitParameter("env"));
				BoardCommentService service;
				service = BoardCommentService.getInstance();
				
				try {
					service.AddBoardCom(bc);
					map.put("status", 1);
				} catch (AddException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
