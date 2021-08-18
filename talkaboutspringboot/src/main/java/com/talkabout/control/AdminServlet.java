//package com.talkabout.control;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.talkabout.dto.Admin;
//import com.talkabout.exception.FindException;
//import com.talkabout.service.AdminService;
//
///**
// * Servlet implementation class AdminServlet
// */
//public class AdminServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		Admin adminInfo = (Admin)session.getAttribute("adminInfo");
//		
//		//세션 정보 가져오기 필요.
//		
//		request.setCharacterEncoding("utf-8");
//		ServletContext sc = getServletContext();		
//		AdminService.envProp = sc.getRealPath(sc.getInitParameter("env"));
//		
//		String admin_id = request.getParameter("id");
//		String admin_pw = request.getParameter("pwd");
//		String method = request.getParameter("method");
//		
//		AdminService service;
//		service = AdminService.getInstance();
//		
//		ObjectMapper mapper;
//		mapper = new ObjectMapper();
//		String jsonStr ="";
//		try {
//			if(method.equals("logout")) {
//				session.invalidate();
//			}
//		}catch (Exception e) {
//			
//		}
//		
//		Map<String, Object> map = new HashMap<>();
//		if(adminInfo != null) {
//			map.put("adminInfo", adminInfo.getAdmin_id());
////			System.out.println(adminInfo.getAdmin_id()+" id");
//		}else {
//			Admin a = null;
//			try {
//				a = service.adLogin(admin_id, admin_pw);
//				if(a == null) {
//					map.put("status", "fail");
////					System.out.println("fail");
//				}else if(a.getAdmin_id().equals(admin_id)) {
//					map.put("adminInfo", a.getAdmin_id());
//					session.setAttribute("adminInfo", a);
////					System.out.println(a.getAdmin_id());
//				}
//			} catch (FindException e) {
//				e.printStackTrace();
//			}
//		}
//		jsonStr = mapper.writeValueAsString(map);
//		//5.응답
//		response.setContentType("application/json;charset=utf-8"); //응답형식지정
//		PrintWriter out = response.getWriter();
//		out.print(jsonStr);
//	}
//
//}
