package com.talkabout.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.BoardLike;
import com.talkabout.exception.DeleteException;
import com.talkabout.service.BoardLikeService;
@RestController
//@CrossOrigin(origins="*")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
@RequestMapping("/boardlike")
public class BoardLikeController {
	@Autowired
	private BoardLikeService service;
	
	@PostMapping("/{boardlike_no}")
	public Map<String, Object> insert (@RequestBody Map<String, Object> map){
		Map<String,Object> result = new HashMap<>();
		result.put("status", 1);
		result.put("msg", "좋아요 추가 성공");
		return result;
	}
	
	@DeleteMapping("/{boardlike_no}")
	public Map<String, Object> remove(@PathVariable int boardlike_no){
//		ResponseEntity<String> responseEntity =
//				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		return responseEntity;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			service.DeleteBoardLike(boardlike_no);
			result.put("status", 1);
		}catch(DeleteException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
}
