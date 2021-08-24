package com.talkabout.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.Debate;
import com.talkabout.dto.Member;
import com.talkabout.dto.Pagination;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateService;
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
//@CrossOrigin("*")
@RequestMapping("/debrecruit/**")
@RestController
public class DebateRecruitController {
	@Autowired
	private DebateService service;
	
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
		result.put("status", 1);
		result.put("debatelist", list);
		result.put("lastRow", lastRow);
		result.put("logininfo", "non-member");
		}else {
			result.put("status", 1);
			result.put("debatelist", list);
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
	@GetMapping(value = {"/{no}"})
	public Map<String, Object> list(@PathVariable(name = "no") int debNo){
//		System.out.println("======");
//		System.out.println(start + end);
	
		Map<String, Object> result =new HashMap<String, Object>();
		try {
			Map<String, Object> map =new HashMap<String, Object>();
			map = service.findByNo(debNo);
		//	System.out.println(list.toString());
		result.put("status", 1);
		result.put("debate", map);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	@PostMapping(value="/write")
	public Map<String, Object> debWrite(@RequestBody Map<String, Object> map){
		Map<String, Object> result =new HashMap<String, Object>();
		//System.out.println(map.get("discuss"));
		Debate deb = new Debate();
		Member m = new Member();
		//System.out.println(map.get("debateTime"));
		m.setMember_no(1);
		deb.setDebate_writer(m);
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
}
