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

import com.talkabout.dto.Audience;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.dto.Notice;
import com.talkabout.dto.Pagination;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.DebateBattleService;

@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
@RequestMapping("/debbattle/**")
@RestController
public class DebateBattleController {
	
	@Autowired
	private DebateBattleService service;
	
	// http://localhost:9999/ta_back/debbattle/list
	@GetMapping(value = {"/list"})
	public Map<String, Object> audListAll(){
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Audience> list = new ArrayList<Audience>();
		
		try {
			list = service.selectAllAud();
			result.put("status", 1);
			result.put("list", list);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	// http://localhost:9999/ta_back/debbattle/번호
	@GetMapping("/{audi_no}")
    public Map<String, Object> audOneByPK(@PathVariable int audi_no) {
		Map<String, Object> result = new HashMap<>();
		Audience audience = new Audience();
		
		try {
			audience = service.selectByAudNo(audi_no);
			result.put("status", 1);
			result.put("audience", audience);
		} catch (FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
        return result;
    }
	
	
}
