package com.talkabout.control;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.service.MemberService;

@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000"})
//@CrossOrigin("*")
@RequestMapping("/member/**")
@RestController
public class LoginController {
	@Autowired
	private MemberService service;
	
	@PostMapping("/test")
	public Map<String, Object> test(HttpSession session, @RequestParam String map){
		Map<String, Object> result = new HashMap<>();
		System.out.println(map.toString()+"/");
		
		result.put("return", map);
		return result;
	}
	
	@PostMapping("/login")
	public Map<String, Object> login(HttpServletRequest request, @RequestParam String socialNo){
//		System.out.println("socialNo==>"+socialNo);
	
		HttpSession session = request.getSession();

//		System.out.println("생성여부:"+session.isNew());
		Member loginmem = (Member) session.getAttribute("logininfo");
//		System.out.println("session=>"+loginmem);
//		System.out.println("=>"+session.getMaxInactiveInterval());
		Map<String, Object> result = new HashMap<>();
//		result.put("session", session.getId()+" / "+session.getAttribute("logininfo"));
		Member m = new Member();
		if(loginmem == null) {
//			System.out.println("==============세션널");
			
				if(socialNo.equals("null")) {
//					System.out.println("======NotLogined");
					result.put("status", 0); //비회원
					result.put("usercheck", "Not_logined");
				}else {
					try {
					m =service.memberCheck(socialNo);
					if(m == null) {
//						System.out.println("신규임==================");
						result.put("status", 2);//신규회원
						result.put("usercheck", "non_member");
//						result.put("logined", "logined");
//						result.put("member", m);
						
					}else {
//						System.out.println("기존회원임==================");
//						session.invalidate();
						session.setAttribute("logininfo", m);
						result.put("status", 1);
						result.put("usercheck", "member");
						result.put("logined", "logined");
						result.put("member", m);
	
					}
				} catch (FindException e) {
					e.printStackTrace();
				}
			}
		
		}else {
//			System.out.println(loginmem.getMember_email());
//			System.out.println("로그인한 회원임==================");
			result.put("status", 1);//기존 로그인 한 회원
			result.put("usercheck", "member");
			result.put("logined", "logined");
			result.put("member", loginmem);
			
		}
		
		return result;
	}
	@PostMapping("/logout")
	public Map<String, Object> logout(HttpSession session){
		Map<String, Object> result = new HashMap<>();
		
		Member loginmem = (Member) session.getAttribute("logininfo");
		System.out.println("로그아웃");
//		HttpSession session = reqn.getAttributeNames());
		session.invalidate();
		
//		result.put("loginmem", loginmem);
			result.put("status", 1);
			result.put("usercheck", "Not_logined");
//			result.put("session", session.getId()+" / "+session.getAttribute("logininfo"));
			
			return result;
	}
	@PostMapping("/signup")
	public Map<String, Object> signUp(HttpSession session, @RequestBody Member mem){
		System.out.println("signup");
		System.out.println(mem);
		Map<String, Object> result = new HashMap<>();
		try {
			service.signUp(mem);
			result.put("status", 1);
			session.setAttribute("logininfo", mem);
			result.put("usercheck", "member");
			result.put("member", mem);
			
		} catch (AddException e) {
			e.printStackTrace();
		}
		return result;
	}
	@PostMapping("/nickname")
	public Map<String, Object> nickCheck(HttpSession session,@RequestParam String nickName){
		System.out.println("nickcheck");
//		HttpSession session = session.getSession();
		Member loginmem = (Member) session.getAttribute("logininfo");
		Map<String, Object> result = new HashMap<>();
		Member m = new Member();
		m.setMember_nickName(nickName);
		Boolean chkNick = false;
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
	public Map<String, Object> nickUpdate(HttpSession session, @RequestParam String change_nickName){
		System.out.println("nicknameupdate" + change_nickName);
		
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
		session.invalidate();
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
		session.invalidate();
		return result;
	}
}
