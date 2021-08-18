package com.talkabout.control;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateResultService;

@RequestMapping(value = "/resultlist/**")
@CrossOrigin(origins="*")
@RestController
public class DebateResultController {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());	
	@Autowired
	DebateResultService service;
	
	@GetMapping(value = "/resultlist")
	public Map<String,List<Debate>> getlist()throws FindException{
		Map<String, List<Debate>> map = new HashMap<>();
		List<Debate> debatelist = service.debresultlistall();
		
		map.put("debatelist",debatelist);
		
		return map;
	}
	
	@GetMapping(value = "/resultlist/{boardno}")
	public Map<String,Object> getone(@PathVariable int boardno)throws FindException{
		Map<String,Object> map = new HashMap<>();
		Debate debatedetail = service.debresultone(boardno);
		List<DebateDetail> debatedetaill2 = service.DetailResultOne(boardno);
		
		
		map.put("dabateone", debatedetail);
		map.put("dabatetwo", debatedetaill2);
		log.error("/resultlist/boardno debateone=" + debatedetail);
		return map;
	}
}
