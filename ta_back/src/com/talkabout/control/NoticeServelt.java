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
public class NoticeServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//세션 정보 가져오기 필요.
		
		request.setCharacterEncoding("utf-8");
		ServletContext sc = getServletContext();		
		NoticeService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		AdminService.envProp = sc.getRealPath(sc.getInitParameter("env"));//admin으로 변경
		String method = request.getParameter("method");
		String page = request.getParameter("page");
		String pageSize = request.getParameter("pagesize");
		int intpage = Integer.parseInt(page);
		int intpagesize = Integer.parseInt(pageSize);
		//System.out.println("서블릿 페이지 사이즈:"+intpagesize);
		
		NoticeService service;
		service = NoticeService.getInstance();
		service.pageNum(intpage);
		service.pageSize(intpagesize);
		
		AdminService adminservice;//admin으로 변경
		adminservice = AdminService.getInstance();//admin으로 변경
		
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
			List<Admin> adminList = new ArrayList<>();//admin으로 변경
			for (Notice notice : list) {
				Admin admin = new Admin();//admin으로 변경
				try {
					admin = adminservice.adminInfo(notice.getNotice_admin());//admin으로 변경
					adminList.add(admin);//admin으로 변경
				} catch (FindException e) {
					e.printStackTrace();
				}
				
			}
				if(list.size()==0) {
					System.out.println("게시글 없음");
				}else {
					map.put("noticelist", list);
					map.put("admininfo", adminList);//admin으로 변경
					map.put("row", last);
				}
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
		String column = request.getParameter("column");
		String keyword = request.getParameter("keyword");
		//System.out.println(method);
		
		NoticeService service;
		service = NoticeService.getInstance();
		
//		NoticeDetailService ndservice;
//		ndservice = NoticeDetailService.getInstance();
		
		AdminService adminservice;
		adminservice = AdminService.getInstance();
		
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr ="";
		
		
		Map<String, Object> map = new HashMap<>();
//		List<NoticeDetail> list = new ArrayList<>();
		List<Notice> nlist = new ArrayList<>();
		
		String strnotice_no = request.getParameter("notice_no");
		
		if(method.equals("noticedetail")) {
			//System.out.println("detail");
			int notice_no = Integer.parseInt(strnotice_no);
			Notice n = new Notice();
			n = service.findByNo(notice_no);
			list= ndservice.findByNoticNo(notice_no);
			map.put("notice", n);
			map.put("detail", list);
		}if(method.equals("noticesearch")) {
			try {
				if(column.equals("WRITER")) {
					Admin a = new Admin();//admin으로 변경
					a.setAdmin_nickName(keyword);//admin으로 변경
					Admin nickAdmin = adminservice.searchNick(a);//admin으로 변경
					try {
					nlist = service.selectSearch(column, nickAdmin.getadmin_no()+"");//admin으로 변경
					}catch(Exception e) {
						if(nlist.size()==0) {
							//System.out.println("게시글 없음");
							map.put("rs", 0);}
					}
					}else {
					nlist= service.selectSearch(column, keyword);
					
				}
			List<Admin> adminList = new ArrayList<>();//admin으로 변경
			for (Notice notice : nlist) {
				Admin admin = new Admin();//admin으로 변경
				try {
					admin = adminservice.adminInfo(notice.getNotice_admin());//admin으로 변경
					adminList.add(admin);//admin으로 변경
				} catch (FindException e) {
					e.printStackTrace();
				}
				}
				if(nlist.size()==0) {
					//System.out.println("게시글 없음");
					map.put("rs", 0);
				}else {
					map.put("rs", nlist);
					map.put("admininfo", adminList);//admin으로 변경
				}
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

}