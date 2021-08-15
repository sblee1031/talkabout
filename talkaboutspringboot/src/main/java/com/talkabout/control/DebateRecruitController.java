package com.talkabout.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.Debate;
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateService;
@CrossOrigin("*")
@RestController
public class DebateRecruitController {
	@Autowired
	private DebateService service;
	
	@GetMapping(value = {"/debrecruit","/debrecruit/{word}"})
	public Map<String, Object> list(@PathVariable(name = "word") Optional<String> optWord){
		Map<String, Object> result =new HashMap<String, Object>();
		List<Debate> list = new ArrayList<Debate>();
		try {
		if(optWord.isPresent()) {
			list = service.findAll();
//			list = service.findAll(optWord.get());
		}else {
			list = service.findAll();
		//	System.out.println(list.toString());
		}
		result.put("status", 1);
		result.put("debatelist", list);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}

}
