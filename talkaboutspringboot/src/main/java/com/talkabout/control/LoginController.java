package com.talkabout.control;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.service.MemberService;

@CrossOrigin("*")
@RequestMapping("/member/**")
@RestController
public class LoginController {
	@Autowired
	private MemberService service;
	
	@PostMapping("/login")
	public Map<String, Object> login(HttpSession session, String socialNo){
		System.out.println("세션생성시간 : " + session.getCreationTime());
		System.out.println("세션 접근시간 : " + session.getLastAccessedTime());
//		HttpSession session = request.getSession();
//		System.out.println(session);
//		session.removeAttribute("logininfo");
		Member loginmem = (Member) session.getAttribute("logininfo");
		System.out.println("=>"+session.getMaxInactiveInterval());
		Map<String, Object> result = new HashMap<>();
		Member m = new Member();
		if(loginmem == null) {
			System.out.println("==============세션널");
			try {
				m =service.memberCheck(socialNo);
				if(m != null) {
//					System.out.println("=====>"+loginmem.getMember_email());
					System.out.println("회원임==================");
					session.setAttribute("logininfo", m);
//					System.out.println(m.toString());
//					result.put("status", loginmem);
					result.put("usercheck", "member");
					result.put("logined", "logined");
					result.put("member", m);
					
				}else {
					result.put("status", 0);
					result.put("usercheck", "non_member");

				}
			} catch (FindException e) {
				e.printStackTrace();
			}
		
		}else {
//			System.out.println(loginmem.getMember_email());
			result.put("status", 1);
			result.put("usercheck", "member");
			result.put("logined", "logined");
			result.put("member", loginmem);
		}
		
		return result;
	}
	@PostMapping("/logout")
	public Map<String, Object> signUp(HttpSession session){
//		HttpSession session = request.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		//session.invalidate();
		Map<String, Object> result = new HashMap<>();
		result.put("loginmem", loginmem);
			result.put("status", 1);
		return result;
	}
	@PostMapping("/signup")
	public Map<String, Object> signUp(HttpSession session, @RequestBody Member mem){
		System.out.println("signup");
		Map<String, Object> result = new HashMap<>();
		try {
			service.signUp(mem);
			result.put("status", 1);
			session.setAttribute("logininfo", mem);
		} catch (AddException e) {
			e.printStackTrace();
		}
		return result;
	}
	@PostMapping("/nickcheck")
	public Map<String, Object> nickCheck(HttpSession session, String member_nickName){
		System.out.println("nickcheck");
//		HttpSession session = session.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		Map<String, Object> result = new HashMap<>();
		Member m = new Member();
		m.setMember_nickName(member_nickName);
		int chkNick = 0;
		try {
			chkNick = service.chkNick(m);
			System.out.println("중복"+chkNick);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.put("chkNick", chkNick);
		return result;
	}
	@PutMapping("/nickupdate")
	public Map<String, Object> nickUpdate(HttpSession session, String change_nickName){
		System.out.println("nicknameupdate");
//		HttpSession session = request.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		Map<String, Object> result = new HashMap<>();
		Member m = new Member();
		m.setMember_no(loginmem.getMember_no());
		m.setMember_nickName(change_nickName);
		try {
			service.updateNick(m);
			result.put("status", 1);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("status", 0);
		}
		return result;
	}
	@PostMapping("/leavemember")
	public Map<String, Object> nickUpdate(HttpSession session){
		System.out.println("/leavemember");
//		HttpSession session = request.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		Map<String, Object> result = new HashMap<>();
		try {
			service.leaveMember(loginmem);
			result.put("status", 1);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("status", 0);
		}
		return result;
	}
}
