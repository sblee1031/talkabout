package com.talkabout.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.DebateBattleService;

//@CrossOrigin("*")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
@RequestMapping("/debbattle")
@RestController
public class DebateBattleController {
	
	@Autowired
	private DebateBattleService service;
	
	/*
	 * Audience
	 */
	
	// http://localhost:9999/ta_back/debbattle/audience
	@GetMapping(value = {"/audience"})
	public Map<String, Object> audienceList(){
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Audience> list = new ArrayList<Audience>();
		
		try {
			list = service.findAllAud();
			result.put("status", 1);
			result.put("list", list);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	// http://localhost:9999/ta_back/debbattle/audience/one?audi_no=숫자
	@GetMapping("audience/one")
    public Map<String, Object> audienceOne(@RequestParam("audi_no") int audi_no) {
		Map<String, Object> result = new HashMap<>();
		Audience audience = new Audience();
		
		try {
			audience = service.findByAudNo(audi_no);
			result.put("status", 1);
			result.put("audience", audience);
		} catch (FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
        return result;
    }
	
	// http://localhost:9999/ta_back/debbattle/audience/two?deb_no=숫자&mem_no=숫자
	@GetMapping("/audience/two")
    public Map<String, Object> audienceByTwo(@RequestParam("deb_no") int deb_no, @RequestParam("mem_no") int mem_no) {
		Map<String, Object> result = new HashMap<>();
		Audience audience = new Audience();
		
		try {
			audience = service.findByDeb(deb_no, mem_no);
			result.put("status", 1);
			result.put("audience", audience);
		} catch (FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
        return result;
    }
	
	// http://localhost:9999/ta_back/debbattle/audience/vote?deb_no=숫자
	@GetMapping("/audience/vote")
    public Map<String, Object> voteCount(@RequestParam("deb_no") int deb_no) {
		Map<String, Object> result = new HashMap<>();
		Map<String, String> voteOne = new HashMap<String, String>();
		Map<String, String> voteTwo = new HashMap<String, String>();
		Map<String, String> voteThree = new HashMap<String, String>();
		
		try {
			voteOne = service.voteCnt(deb_no, 1);
			voteTwo = service.voteCnt(deb_no, 2);
			voteThree = service.voteCnt(deb_no, 3);
			result.put("status", 1);
			if(voteOne == null) {
				result.put("num1", 0);
			} else {
				result.put("num1", voteOne.get("cnt"));
			}
			if(voteTwo == null) {
				result.put("num2", 0);
			} else {
				result.put("num2", voteTwo.get("cnt"));
			}
			if(voteThree == null) {
				result.put("num3", 0);
			} else {
				result.put("num3", voteThree.get("cnt"));
			}
		} catch (FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
        return result;
    }
	
	// http://localhost:9999/ta_back/debbattle/audience
    @PostMapping(value = {"/audience"})
    public Map<String, Object> insertVote(@RequestBody Map<String, Object> map) {
    	Map<String, Object> result = new HashMap<>();
    	
    	int deb_no = (int)map.get("deb_no");
    	int mem_no = (int)map.get("mem_no");
    	
    	try {
			service.addVote(deb_no, mem_no);
			result.put("status", 1);
	    	result.put("msg", "Insert Completed");
		} catch (AddException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }
       
    // http://localhost:9999/ta_back/debbattle/audience/관중번호
    @PutMapping("/audience/{audi_no}")
    public Map<String, Object> updateVote(@PathVariable int audi_no, @RequestBody Map<String, Object> map) {
    	Map<String, Object> result = new HashMap<>();
    	
    	try {
    		service.setVote(audi_no, Integer.parseInt(String.valueOf(map.get("vote_no"))));
    		// service.setVote(audi_no, (int) map.get("vote_no"));
			result.put("status", 1);
	    	result.put("msg", "Update Completed");
		} catch (ModifyException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }
	
    /*
     * Debate
     */
    
    // http://localhost:9999/ta_back/debbattle/debate
 	@GetMapping(value = {"/debate"})
 	public Map<String, Object> debateList(HttpSession session){
 		Member logininfo = (Member)session.getAttribute("logininfo");
 		
 		Map<String, Object> result = new HashMap<String, Object>();
 		List<Debate> list = new ArrayList<Debate>();
 		
 		try {
 			if(logininfo !=null) {
 	 			result.put("logininfo", logininfo);
 	 		}
 			list = service.findAllDeb();
 			result.put("status", 1);
 			result.put("list", list);
 		}catch(FindException e) {
 			result.put("status", 0);
 			result.put("msg", e.getMessage());
 		}
 		return result;
 	}
 	
 	// http://localhost:9999/ta_back/debbattle/debate/토론번호
  	@GetMapping(value = {"/debate/{deb_no}"})
  	public Map<String, Object> debateOne(@PathVariable int deb_no){
  		Map<String, Object> result = new HashMap<String, Object>();
  		
  		Debate debObj = new Debate();
  		
  		try {
  			debObj = service.findDebateOne(deb_no);
  			result.put("status", 1);
  			result.put("debate", debObj);
  		}catch(FindException e) {
  			result.put("status", 0);
  			result.put("msg", e.getMessage());
  		}
  		return result;
  	}
 	
 	// http://localhost:9999/ta_back/debbattle/debate/토론번호
    @PutMapping("/debate/{deb_no}")
    public Map<String, Object> updateDebate(@PathVariable int deb_no, @RequestBody Map<String, Object> map) {
    	Map<String, Object> result = new HashMap<>();
    	
    	try {
    		Debate debObj = service.findDebateOne(deb_no);
    		String word = (String) map.get("word");
    		if (word.equals("status")) {
    			debObj.setDebate_status((String) map.get("setdata"));
    			service.setStatus(debObj);
    		} else if (word.equals("enddate")) {
    			service.setEndDate(debObj);
			}
    		result.put("status", 1);
	    	result.put("msg", "Update Completed");
		} catch (FindException | ModifyException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }
    
    /*
     * DebateDetail
     */
    
    // http://localhost:9999/ta_back/debbattle/debatedetail/토론번호
   	@GetMapping(value = {"/debatedetail/{deb_no}"})
   	public Map<String, Object> discussorList(@PathVariable int deb_no){
   		Map<String, Object> result = new HashMap<String, Object>();
   		
   		List<DebateDetail> list = new ArrayList<DebateDetail>();
   		
   		try {
   			list = service.findTwoByDebNo(deb_no);
   			result.put("status", 1);
   			result.put("list", list);
   		}catch(FindException e) {
   			result.put("status", 0);
   			result.put("msg", e.getMessage());
   		}
   		return result;
   	}
   	
	// http://localhost:9999/ta_back/debbattle/debatedetail/one?deb_no=숫자&discussor=숫자
	@GetMapping("/debatedetail/one")
	public Map<String, Object> detailOne(@RequestParam("deb_no") int deb_no, @RequestParam("discussor") int discussor) {
		Map<String, Object> result = new HashMap<>();
		DebateDetail detailObj = new DebateDetail();
		
		try {
			detailObj = service.findOneByTwo(deb_no, discussor);
			result.put("status", 1);
			result.put("detail", detailObj);
		} catch (FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
	    return result;
	}
	
	// http://localhost:9999/ta_back/debbattle/debatedetail/상세번호
	// map {"word", "setdata", "evi_no"}
    @PutMapping("/debatedetail/{detail_no}")
    public Map<String, Object> updateDetail(@PathVariable int detail_no, @RequestBody Map<String, Object> map) {
    	Map<String, Object> result = new HashMap<>();
    	System.out.println("근거 업데이트");
    	try {
    		DebateDetail detailObj = service.findOneByPK(detail_no);
    		String word = (String) map.get("word"); // intime or evidence
    		if (word.equals("intime")) {
    			service.setIntime(detailObj);
    		} else if (word.equals("evidence")) {
    			int evi_no = (int) map.get("evi_no");
    			if(evi_no == 1) {
    				detailObj.setEvi_one((String) map.get("setdata"));
    				service.setEvidence(detailObj, evi_no);
    			} else if (evi_no == 2) {
    				detailObj.setEvi_two((String) map.get("setdata"));
    				service.setEvidence(detailObj, evi_no);
    			} else if (evi_no == 3) {
    				detailObj.setEvi_three((String) map.get("setdata"));
    				service.setEvidence(detailObj, evi_no);
    			}
			}
    		result.put("status", 1);
	    	result.put("msg", "Update Completed");
	    	System.out.println("근거 업데이트끝");
		} catch ( FindException |ModifyException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }
}
