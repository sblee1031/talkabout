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
import com.talkabout.dto.Member;
import com.talkabout.exception.FindException;
import com.talkabout.service.MemberService;

public class NickNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		
		request.setCharacterEncoding("utf-8");
		String nickname = request.getParameter("nickName"); // 닉네임 체크용 매개변수
		
		ServletContext sc = getServletContext();		
		MemberService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		MemberService service;
		service = MemberService.getInstance();
		
		Member m = new Member();
		m.setMember_nickName(nickname);
		boolean result = false;
		
		try {
			result = service.chkNick(m);
			//System.out.println("서블릿 result : " + result);
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr;
		
		map.put("chkNick", result);
		
		jsonStr = mapper.writeValueAsString(map);
		//5.응답
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		
		//닉네임 update 진행 코드
		
		String nickUpdate = request.getParameter("nickUpdate");//닉네임 변경여부 체크 값
		String changeNick = request.getParameter("changeNick");//바뀐 닉네임
//		System.out.println(changeNick);
//		System.out.println(nickUpdate);
		try {
		if(nickUpdate.equals("true")) {
			Member nick = new Member();
			nick.setMember_no(loginmem.getMember_no());
			nick.setMember_nickName(changeNick);
			System.out.println(nick.getMember_no() + " / "+ nick.getMember_nickName());
			try {
				service.updateNick(nick);
				System.out.println("변경 완료");
				session.invalidate(); //세션제거
			} catch (FindException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("변경 없음");
		}
		}catch(Exception e) {
			
		}
		
		
	}

}