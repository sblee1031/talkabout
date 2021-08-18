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
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateLike;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateLikeService;

/**
 * Servlet implementation class DebateLikeServlet
 */
public class DebateLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		//로그인 정보를 담은 멤버 객체
		request.setCharacterEncoding("utf-8");
		ServletContext sc = getServletContext();		
		DebateLikeService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		DebateLikeService service;
		service = DebateLikeService.getInstance();
		
		String method = request.getParameter("method");
		String deb_no = request.getParameter("deb_no");
		System.out.println(method);
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr ="";
		
		Map<String, Object> map = new HashMap<>();
		DebateLike dl = new DebateLike();
		
		if(method.equals("likechk")) {
			try {
				DebateLike dll = new DebateLike();
				Debate deb = new Debate();
				deb.setDebate_no(Integer.parseInt(deb_no));
				dll.setDeblike_deb(deb);
				dll.setDeblike_mem(m);
				int chk = 0;
				chk = service.debLikeChk(dll);
				map.put("likechk", chk);
				//1 : like , 0: dislike
			} catch (FindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}if(method.equals("likecnt")) {
			DebateLike dll = new DebateLike();
			Debate deb = new Debate();
			deb.setDebate_no(Integer.parseInt(deb_no));
			dll.setDeblike_deb(deb);
			int chk = 0;
			chk = service.debLikeCnt(dll);
			map.put("likecnt", chk);
			
		}if(method.equals("like")) {
			try {
				Debate deb = new Debate();
				deb.setDebate_no(Integer.parseInt(deb_no));
				dl.setDeblike_deb(deb);
				dl.setDeblike_mem(m);
				service.AddBoardLike(dl);
			} catch (AddException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}if(method.equals("dislike")) {
			try {
				Debate deb = new Debate();
				deb.setDebate_no(Integer.parseInt(deb_no));
				dl.setDeblike_deb(deb);
				dl.setDeblike_mem(m);
				service.DeleteBoardLike(dl);
			} catch (DeleteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		jsonStr = mapper.writeValueAsString(map);
		//5.응답
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		
		
	}

}
