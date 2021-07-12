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
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.service.MemberService;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		request.setCharacterEncoding("utf-8");
		String social_type = request.getParameter("social_type");
		String social_no = request.getParameter("social_no");
		String nickname =  request.getParameter("nickname");
		String email =  request.getParameter("email");
		String birthday =  request.getParameter("birthday");
		String thumb = request.getParameter("thumb");
		String gender = request.getParameter("gender");

		
		Member newM = new Member(social_type, social_no, nickname,
				gender, email, thumb, birthday);
		
		ServletContext sc = getServletContext();		
		MemberService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		MemberService service;
		service = MemberService.getInstance();
		try {
			service.signUp(newM);
		} catch (AddException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr;
		try {
			Member m = service.memberCheck(social_no);
//			if(m == null) {
//				map.put("usercheck", "non_member");
//			}else {
				session.setAttribute("logininfo", m);
				map.put("usercheck", "member");
				map.put("member", m);
//			}
			
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		jsonStr = mapper.writeValueAsString(map);
		//5.응답
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		
//		System.out.println("회원 가입 성공");
//		System.out.println("정보 : "+social_type +" / "+social_no+" / "+email + " / "+ nickname+" / " +birthday+ " / "+ gender+" / " + thumb);
		
	}

}
