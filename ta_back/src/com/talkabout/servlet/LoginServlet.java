package com.talkabout.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talkabout.dto.Member;
import com.talkabout.service.MemberService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		String social_type = request.getParameter("social_type");
		String social_no = request.getParameter("social_no");
		String nickname =  request.getParameter("nickname");
		String email =  request.getParameter("email");
		String birthday =  request.getParameter("birthday");
		String thumb = request.getParameter("thumb");
		String gender = request.getParameter("gender");
		System.out.println(social_type +" / "+email + " / "+ nickname+" / " +birthday+ " / "+ gender+" / " + thumb);
		
		Member login = new Member(social_type, social_no,nickname,
				gender,email,thumb,birthday);
		
		ServletContext sc = getServletContext();		
		MemberService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		MemberService service;
		service = MemberService.getInstance();
		service.signUp(login);
		
		System.out.println("회원 가입 성공");
		
	
	}

}
