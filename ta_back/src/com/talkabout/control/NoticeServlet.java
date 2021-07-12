package com.talkabout.control;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.talkabout.dao.NoticeDAOOracle;
import com.talkabout.dto.NoticeDetail;
import com.talkabout.dto.Admin;
import com.talkabout.dto.Notice;
import com.talkabout.exception.FindException;
import com.talkabout.service.AdminService;
import com.talkabout.service.NoticeDetailService;
import com.talkabout.service.NoticeService;

/**
 * Servlet implementation class DebateServelt
 */
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//세션 정보 가져오기 필요.
		
		request.setCharacterEncoding("utf-8");
		ServletContext sc = getServletContext();		
		NoticeService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		//AdminService.envProp = sc.getRealPath(sc.getInitParameter("env"));//admin으로 변경
		String method = request.getParameter("method");
//		String page = request.getParameter("page");
//		String pageSize = request.getParameter("pagesize");
//		int intpage = Integer.parseInt(page);
//		int intpagesize = Integer.parseInt(pageSize);
		//System.out.println("서블릿 페이지 사이즈:"+intpagesize);
		
		NoticeService service;
		service = NoticeService.getInstance();
//		service.pageNum(intpage);
//		service.pageSize(intpagesize);
		
//		AdminService adminservice;//admin으로 변경
//		adminservice = AdminService.getInstance();//admin으로 변경
		
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr ="";
		
		
		Map<String, Object> map = new HashMap<>();
		List<Notice> list = new ArrayList<>();
		int last = service.lastRow();
		//num_page_no = intpage;
		
		
		if(method.equals("listall")) {
			try {
			list = service.findAll();
			System.out.println("리스트사이즈"+list.size());
					map.put("noticelist", list);
//					map.put("admininfo", adminList);//admin으로 변경
//					map.put("row", last);
			}catch (Exception e){
				e.printStackTrace();
			}
		}

		
		jsonStr = mapper.writeValueAsString(map);
		//5.응답
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//세션 정보 가져오기 필요.
		
		request.setCharacterEncoding("utf-8");
		ServletContext sc = getServletContext();		
		NoticeService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		AdminService.envProp = sc.getRealPath(sc.getInitParameter("env"));//admin으로 변경
//		NoticeDetailService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		
		String method = request.getParameter("method");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		//System.out.println(method);
		
		NoticeService service;
		service = NoticeService.getInstance();
		
		
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr ="";
		
		
		Map<String, Object> map = new HashMap<>();
//		List<NoticeDetail> list = new ArrayList<>();
		List<Notice> nlist = new ArrayList<>();
		
		String strnotice_no = request.getParameter("notice_no");
		System.out.println("노티스 넘버"+ strnotice_no);
		if(method.equals("noticedetail")) {
			//System.out.println("detail");
			int notice_no = Integer.parseInt(strnotice_no);
			Notice n = new Notice();
			try {
				n = service.selectByNo(notice_no);
			} catch (FindException e) {
				e.printStackTrace();
			}
			map.put("notice", n);
//			map.put("detail", list);
	}
		
		jsonStr = mapper.writeValueAsString(map);
		//5.응답
		response.setContentType("application/json;charset=utf-8"); //응답형식지정
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}
}