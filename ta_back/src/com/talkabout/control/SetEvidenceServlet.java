package com.talkabout.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.service.DebateDetailService;
import com.talkabout.service.DebateService;

public class SetEvidenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");

		String discussor = request.getParameter("discussor"); // A, B
		String text = request.getParameter("text"); // 변경할 텍스트 내용
		String str_discussor_no = request.getParameter("discussor_no"); // 토론자 회원번호
		int discussor_no = Integer.parseInt(str_discussor_no);
		String str_deb_no = request.getParameter("deb_no"); // 토론번호
		int deb_no = Integer.parseInt(str_deb_no); 

		String evi_num = request.getParameter("evi_num"); // 1, 2, 3(근거번호)

		DebateService debate_service = DebateService.getInstance();
		DebateDetailService detail_service = DebateDetailService.getInstance();
		
		if (discussor_no != loginmem.getMember_no()) {
			
			Map<String,Object> map = new HashMap<>();
			map.put("status", 0);
			
			ObjectMapper mapper;
			mapper = new ObjectMapper();
			String jsonStr;
			jsonStr = mapper.writeValueAsString(map);
			
			response.setContentType("application/json;charset=utf-8"); //응답형식지정
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
		} else {
			Debate d = debate_service.findByNo(deb_no);
			DebateDetail dd = detail_service.findByDeb(d, discussor_no);
			
			System.out.println("토론 테이블 : " + d.toString());
			System.out.println("토론상세 테이블 전 : " + dd.toString());
			if (evi_num.equals("1")) {
				dd.setEvi_one(text);
				detail_service.setEviOne(dd);
			} else if(evi_num.equals("2")) {
				dd.setEvi_two(text);
				detail_service.setEviTwo(dd);
			} else if(evi_num.equals("3")) {
				dd.setEvi_three(text);
				detail_service.setEviThree(dd);
			}
			System.out.println("토론상세 테이블 후 : " + dd.toString());
			
			Map<String,Object> map = new HashMap<>();
			map.put("status", 1);
			
			ObjectMapper mapper;
			mapper = new ObjectMapper();
			String jsonStr;
			jsonStr = mapper.writeValueAsString(map);
			
			response.setContentType("application/json;charset=utf-8"); //응답형식지정
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
		}
		
		
	}

}
