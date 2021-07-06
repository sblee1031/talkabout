package com.talkabout.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talkabout.dto.Login;
import com.talkabout.service.LoginService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String strno = request.getParameter("id");
		String email =  request.getParameter("email");
		String thumb = request.getParameter("thumb");
		System.out.println(strno +" / "+email + " / "+ thumb);
		
		Login login = new Login(strno, email, thumb);
		
		ServletContext sc = getServletContext();		
		LoginService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		LoginService service;
		service = LoginService.getInstance();
		service.signUp(login);
		
		System.out.println("회원 가입 성공");
		
	
	}

}
