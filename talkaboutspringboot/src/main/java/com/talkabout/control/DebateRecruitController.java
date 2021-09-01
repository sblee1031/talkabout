package com.talkabout.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.dto.Pagination;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.DebateService;
import com.talkabout.service.MailService;
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
//@CrossOrigin("*")
@RequestMapping("/debrecruit/**")
@RestController
public class DebateRecruitController {
	@Autowired
	private DebateService service;
	@Autowired
	private MailService mailService;
	
	@GetMapping(value = {"/list","/list/{word}"})
	public Map<String, Object> list(@PathVariable(name = "word") Optional<String> optWord , String pageNo, String pageSize, HttpSession session){
		Member loginmem = (Member) session.getAttribute("logininfo");
		//		System.out.println("======");
		System.out.println(pageNo + pageSize);
		
		Map<String, Object> result =new HashMap<String, Object>();
		List<Debate> list = new ArrayList<Debate>();
		Pagination page = new Pagination();
		int lastRow;
		try {
			int startRow =page.startRow(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			//System.out.println(startRow);
			int endRow = page.endRow(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		if(optWord.isPresent()) {
//			list = service.findAll();
			list = service.findAll(optWord.get(),startRow, endRow);
			lastRow = service.searchLastRow(optWord.get());
		}else {
			list = service.findAll(startRow, endRow);
			lastRow = service.lastRow();
		//	System.out.println(list.toString());
		}
		if(loginmem==null) {
		System.out.print("세션널");
		result.put("debatelist", list);
		result.put("status", 2);
		result.put("lastRow", lastRow);
		result.put("logininfo", "non-member");
		}else {
			result.put("debatelist", list);
			result.put("status", 1);
			result.put("lastRow", lastRow);
			result.put("logininfo", loginmem);
		}
//		result.put("logininfo", member)
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	@GetMapping(value = {"/{no}"})//
	public Map<String, Object> list(@PathVariable(name = "no") int debNo, HttpSession session){
//		System.out.println("======");
//		System.out.println(start + end);
		Member loginmem = (Member) session.getAttribute("logininfo");
		Map<String, Object> result =new HashMap<String, Object>();
		try {
			Map<String, Object> map =new HashMap<String, Object>();
			map = service.findByNo(debNo);
		//	System.out.println(list.toString());
			result.put("debate", map);
			if(loginmem==null) {
				System.out.print("세션널");
				result.put("status", 2);
				result.put("logininfo", "non-member");
				}else {
					result.put("status", 1);
					result.put("logininfo", loginmem);
				}
			
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	@PostMapping(value="/write")//
	public Map<String, Object> debWrite(@RequestBody Map<String, Object> map, HttpSession session){
		Map<String, Object> result =new HashMap<String, Object>();
		Member loginmem = (Member) session.getAttribute("logininfo");
		//System.out.println(map.get("discuss"));
		Debate deb = new Debate();
		//System.out.println(map.get("debateTime"));
		deb.setDebate_writer(loginmem);
		deb.setDebate_time(Integer.parseInt((String)map.get("debateTime")));
		deb.setDebate_topic((String)map.get("debate_topic"));
		deb.setDebate_content((String)map.get("debate_content"));
		deb.setDebate_startDate((String)map.get("debateDate"));
		try {
			String discuss1 = (String)map.get("discuss1");
			String discuss2 = (String)map.get("discuss2");
			System.out.println(discuss1 + discuss2);
			service.addDebate(deb, discuss1, discuss2);
			result.put("deb", deb);
			result.put("status", 1);
		} catch (AddException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("status", 0);
		}
		
		return result;
	}
	@PutMapping(value="/discussor")
	public Map<String, Object> discussorUpdate( HttpSession session , @RequestBody Map<String, Object> map) throws ModifyException, FindException{
		Map<String, Object> result =new HashMap<String, Object>();
		Member loginmem = (Member) session.getAttribute("logininfo");
		
		DebateDetail dd = new DebateDetail();
		dd.setDetail_no((int)map.get("dd_no"));
		dd.setDiscussor(loginmem);
//		String str = (String)map.get("dd_no");
		int loginNum = (int)map.get("member_no");
		int deb_no = (int)map.get("deb_no");
		
		if(loginmem.getMember_no() != loginNum) {
			throw new ModifyException("잘못된 접근 입니다.");
		}
		try {
			service.addDiscussor(null, dd, null);
			result.put("status", 1);
			List<DebateDetail> list = new ArrayList<>();
			try {
				list = service.checkDeb(deb_no);
				Map<String, Object> debate =new HashMap<String, Object>();
				debate = service.findByNo(deb_no);
				if(list.get(0)!=null &list.get(1)!=null) {
//					System.out.println("메일발송"+((Debate)debate.get("debate")).getDebate_topic());
//					System.out.println("메일발송"+((List<DebateDetail>)debate.get("detail")).get(0));
//					System.out.println("메일발송"+((List<DebateDetail>)debate.get("detail")).get(1));
					Debate deb = (Debate)debate.get("debate");
					DebateDetail dd1 = (DebateDetail)((List<DebateDetail>)debate.get("detail")).get(0);
					DebateDetail dd2 = (DebateDetail)((List<DebateDetail>)debate.get("detail")).get(1);
					if(dd1.getDiscussor()==null | dd2.getDiscussor()==null) {
						result.put("status", 1);
					}else {
						//System.out.println("메일발송");
						mailService.sendMail("psyy2244@gmail.com", "talkabout1234",
								deb, dd1, dd2);
					}
				}
			} catch (FindException e) {
				// TODO Auto-generated catch block
				//throw new FindException(e.getMessage());
				
			}
		} catch (ModifyException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("error", e.getMessage());
		}
		return result;
	}
	@PutMapping(value="/canclediscussor")
	public Map<String, Object> discussorCancle( HttpSession session , @RequestBody Map<String, Object> map) throws ModifyException{
		Map<String, Object> result =new HashMap<String, Object>();
		Member loginmem = (Member) session.getAttribute("logininfo");
		
		DebateDetail dd = new DebateDetail();
		dd.setDetail_no((int)map.get("dd_no"));
		dd.setDiscussor(loginmem);
//		String str = (String)map.get("dd_no");
		int loginNum = (int)map.get("member_no");
		if(loginmem.getMember_no() != loginNum) {
			throw new ModifyException("잘못된 접근 입니다.");
		}
		try {
			service.cancleDiscussor(null, dd, null);
			result.put("status", 1);
		} catch (ModifyException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("error", e.getMessage());
		}
		return result;
	}
	
	@PutMapping(value="/update")
	public Map<String, Object> debUpdate( HttpSession session , @RequestBody Map<String, Object> map) throws ModifyException{
		System.out.println("->"+(int)map.get("discuss1_no"));
		System.out.println("discuss1->"+(String)map.get("discuss1"));
		System.out.println("discuss2->"+(String)map.get("discuss2"));
		Map<String, Object> result =new HashMap<String, Object>();
		Member loginmem = (Member) session.getAttribute("logininfo");
		if(loginmem==null) {
			throw new ModifyException("잘못된 접근 입니다.");
		}
		Debate deb = new Debate();
		DebateDetail dd1 = new DebateDetail();
//		int dis1 = (int)map.get("discuss1_no");
		dd1.setDetail_no((int)map.get("discuss1_no"));
		dd1.setDiscuss((String)map.get("discuss1"));
		DebateDetail dd2 = new DebateDetail();
		dd2.setDetail_no((int)map.get("discuss2_no"));
		dd2.setDiscuss((String)map.get("discuss2"));
		
		//System.out.println(map.get("debateTime"));
		deb.setDebate_no((int)map.get("debate_no"));
		deb.setDebate_writer(loginmem);
		deb.setDebate_time((int)map.get("debateTime"));
		deb.setDebate_topic((String)map.get("debate_topic"));
		deb.setDebate_content((String)map.get("debate_content"));
		deb.setDebate_startDate((String)map.get("debateDate"));
		
		try {
			service.updateDebate(deb,dd1,dd2);
			result.put("status", 1);
		} catch (ModifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("status", 0);
			result.put("error", e.getMessage());
		}
		return result;
	}
	
	@DeleteMapping(value="/delete")//
	public Map<String, Object> debDelete( HttpSession session , @RequestBody Map<String, Object> map){
		System.out.println("->"+map.get("debNo"));
		Map<String, Object> result =new HashMap<String, Object>();
		Member loginmem = (Member) session.getAttribute("logininfo");
		//System.out.println(map.get("discuss"));
		//System.out.println(map.get("debateTime"));
		
		try {
			service.deleteDebate((String)map.get("debNo"));
			result.put("status", 1);
		} catch (DeleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("status", 0);
			result.put("error", e.getMessage());
		}
		return result;
	}
}
