package com.talkabout.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.talkabout.dto.Board;
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.service.BoardService;
import com.talkabout.service.MemberService;

/**
 * Servlet implementation class BoardListServlet
 */
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1. 세션에서 데이터 얻기
		ObjectMapper mapper = new ObjectMapper(); //jack-bind라이브러리API
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//2.비즈니스 로직 호출
		ServletContext sc = getServletContext();
		BoardService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		BoardService service;
		MemberService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		MemberService memservice;
		service = BoardService.getInstance();
		memservice= MemberService.getInstance();
		List<Board> boardinfo= null;
		ArrayList<String> nick_list = new ArrayList<String>();
		Map<String,Object> map = new HashMap<>();
		try {
			 boardinfo = service.BoardList();
			 for (Board b : boardinfo) {
				Member m = memservice.memberInfo(b.getBoard_mem());
				nick_list.add(m.getMember_nickName());
			}
			map.put("boardinfo", boardinfo);
			map.put("nicklist", nick_list);
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		}
	}

}
