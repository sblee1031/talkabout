package com.talkabout.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dao.boardCommentDAOOracle;
import com.talkabout.dto.Board;
import com.talkabout.dto.BoardComment;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.BoardCommentService;


@RequestMapping("/boardcomment/*")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
//@CrossOrigin(origins="*")
@RestController
public class BoardCommentController {
	@Autowired
	private BoardCommentService service;
	
//	@GetMapping(value = {"/list/{com_board}", "/list/{mem_no}"})
//	public Map<String, Object> list(@PathVariable(name="mem_no") Optional<Integer> optNo, @RequestBody int com_board){
//		Map<String, Object>	result = new HashMap<>();
//		List<BoardComment> list = new ArrayList<>();
//		try {
//			if(optNo.isPresent()) {
//				list = service.MyBoradComseach(optNo.get(), optNo.get());
//			}else {
//				list = service.BoardComList(com_board);
//			}
//			result.put("status", 1);
//			result.put("boardcommentlist", list);
//		}catch(FindException e) {
//			result.put("status", 0);
//			result.put("msg", e.getMessage());
//		}
//		return result;
//	}
	@GetMapping("/list/{com_board}")
	public Map<String, Object> list(@PathVariable int com_board){
		Map<String, Object>	result = new HashMap<>();
		List<BoardComment> list = new ArrayList<>();
		try {
			list = service.BoardComList(com_board);
			result.put("status", 1);
			result.put("boardcommentlist", list);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@PostMapping("/write")
	public Map<String,Object> insert(BoardComment bc, HttpSession session){
		
		Map<String,Object> result = new HashMap<>();
		Member logininfo = (Member)session.getAttribute("logininfo");
		System.out.println(logininfo);
		
		try {
			bc.setCom_member(logininfo);
			System.out.println(logininfo);
			service.AddBoardCom(bc);
			result.put("status", 1);
			result.put("msg", "댓글 추가 성공");
		}catch(AddException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
//	@PutMapping("/{com_no}")
//	public ResponseEntity<String> modify (@PathVariable int com_no){
//		ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
//		return responseEntity;
//	}
	
	@PutMapping("/{com_no}")
	public Map<String, Object> insert (@PathVariable int com_no,
			@RequestBody BoardComment bc){
		Map<String,Object> result = new HashMap<>();
		try {
			bc.setCom_no(com_no);
			service.EditBoardCom(bc);
			result.put("status", 1);
		} catch(ModifyException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
//	@DeleteMapping("/{com_no}")
//	public ResponseEntity <String>	remove(@PathVariable int com_no){
//		ResponseEntity<String> responseEntity = 
//				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		return responseEntity;
//	}
	@DeleteMapping("/{com_no}")
	public Map<String, Object>	remove(@PathVariable int com_no){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			service.DeleteBoardCom(com_no);
			result.put("status", 1);
		}catch(DeleteException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
}
