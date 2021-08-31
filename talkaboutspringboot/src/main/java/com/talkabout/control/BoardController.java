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

import com.talkabout.dto.Board;
import com.talkabout.dto.Member;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.exception.RemoveException;
import com.talkabout.service.BoardService;

@RequestMapping("/board/**")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
//@CrossOrigin(origins="*")
@RestController
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping(value = {"/list", "/list/{word}"})
	public Map<String, Object> list(@PathVariable(name="word") Optional<String> optWord){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Board> list = new ArrayList<Board>();
		try {
			if(optWord.isPresent()) {
				list = service.BoardSearch(optWord.get(),optWord.get());
				
			}else {
				list = service.BoardList();
			}
			result.put("status", 1);
			result.put("boardlist", list);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@GetMapping("/{board_no}")
	public Map<String, Object> info(@PathVariable int board_no){
		Map<String, Object> result = new HashMap<>();
		Board board = new Board();
		try {
			board = service.BoardDetail(board_no);
			result.put("status", 1);
			//result.put("board_no", board_no);
			result.put("board", board);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	
	@PostMapping("/write")
	public Map<String, Object> insert(Board board, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		
		Member logininfo = (Member)session.getAttribute("logininfo");
		try {
			board.setBoard_member(logininfo);
			service.AddBoard(board);
			result.put("status", 1);
			result.put("msg", "게시글 추가 성공");
		} catch (AddException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
//		System.out.println(board.getBoard_member()+"------");
		return result;
	}
	
//	@PutMapping("/{board_no}")
//	public ResponseEntity<String> modify (@PathVariable int board_no){
//		ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
//		return responseEntity;
//	}
	@PutMapping("/{board_no}")
	public Map<String, Object> modify (@PathVariable int board_no, @RequestBody Board board){
		Map<String, Object> result = new HashMap<>();
		try {
			board.setBoard_no(board_no);
			service.EditBoard(board_no);
			result.put("status", 1);
		}catch (ModifyException e) {
			e.printStackTrace();
			result.put("status",0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@PutMapping("/boardviewcount/{board_no}")
	public Map<String, Object> viewcount (@PathVariable int board_no){
		Map<String, Object> result = new HashMap<>();
		try {
			
			service.CountBoardView(board_no);
			result.put("status", 1);
		}catch (ModifyException e) {
			e.printStackTrace();
			result.put("status",0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
//	@DeleteMapping("/{board_no}")
//	public ResponseEntity<String> remove(@PathVariable int board_no){
//		ResponseEntity<String> responseEntity = 
//				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		return responseEntity;
//	}
	@DeleteMapping("/{board_no}")
	public Map<String, Object> remove (@PathVariable int board_no,  HttpSession session){
		System.out.println(board_no);
		Map<String, Object> result = new HashMap<String, Object>();
//		board.setBoard_no(board_no);
		Member logininfo = (Member)session.getAttribute("logininfo");
		System.out.println(logininfo);
		if(logininfo != null) {
			try {
				service.DeleteBoard(board_no);
				result.put("status", 1);
				
			}catch(DeleteException e){
				e.printStackTrace();
				result.put("status", 0);
				result.put("msg", e.getMessage());
				
			}
		}else {
			result.put("status", 3);
		}
		return result;
	}
}
