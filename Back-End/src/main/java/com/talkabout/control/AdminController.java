package com.talkabout.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.Admin;
import com.talkabout.dto.Board;
import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.dto.Notice;
import com.talkabout.dto.Pagination;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.AdminService;
import com.talkabout.service.BoardService;
import com.talkabout.service.DebateService;
import com.talkabout.service.MailService;

@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
//@CrossOrigin("*")
@RequestMapping("/admin/**")
@RestController
public class AdminController {
	@Autowired
	private MailService mailService;
	@Autowired
	private DebateService debService;
	@Autowired
	private AdminService service;
	@Autowired
	private BoardService boardService;
	
	@PostMapping(value = "/login")
	public Map<String, Object> adminLogin(@RequestBody Admin ad, HttpSession session) throws FindException{
		Admin logininfo = (Admin)session.getAttribute("admininfo");
//		System.out.println(ad.getAdmin_id()+ ad.getAdmin_pwd());
		Map<String, Object> result =new HashMap<String, Object>();
		Admin loginAdmin = new Admin();
		try {
			loginAdmin=service.adLogin(ad.getAdmin_id(), ad.getAdmin_pwd());
			session.setAttribute("admininfo", loginAdmin);
			result.put("status", 1);
			result.put("loginInfo", loginAdmin.getAdmin_id());
		} catch (Exception e) {
			
			result.put("status", 0);
			result.put("error", e.getMessage());
//			throw new FindException(e.getLocalizedMessage());
		}
		return result;
	}
	
	@GetMapping(value = {"/notice/list","/notice/list/{word}"})
	public Map<String, Object> list(@PathVariable(name = "word") Optional<String> optWord , String pageNo, String pageSize, HttpSession session){
		Admin loginmem = (Admin) session.getAttribute("admininfo");
		//		System.out.println("======");
//		System.out.println(pageNo + pageSize);
		
		Map<String, Object> result =new HashMap<String, Object>();
		List<Notice> list = new ArrayList<Notice>();
		Pagination page = new Pagination();
		int lastRow;
		try {
			int startRow =page.startRow(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			//System.out.println(startRow);
			int endRow = page.endRow(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
//			System.out.println("=>"+startRow+endRow);
		if(optWord.isPresent()) {
//			list = service.findAll();
//			System.out.println("??????list=>"+list.toString());
			list = service.noticeFindAll(optWord.get(),startRow, endRow);
			lastRow = service.noticeSearchLastRow(optWord.get());
		}else {
			list = service.noticeFindAll(startRow, endRow);
			lastRow = service.noticeLastRow();
			//System.out.println("list=>"+list.toString());
		}
			result.put("noticelist", list);
			result.put("status", 1);
			result.put("lastRow", lastRow);
			result.put("logininfo", loginmem);
//		result.put("logininfo", member)
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	@GetMapping(value = {"/board/list","/board/list/{word}"})
	public Map<String, Object> boardList(@PathVariable(name = "word") Optional<String> optWord , String pageNo, String pageSize, HttpSession session){
		Admin loginmem = (Admin) session.getAttribute("logininfo");
		
		Map<String, Object> result =new HashMap<String, Object>();
		List<Board> list = new ArrayList<>();
		Pagination page = new Pagination();
		int lastRow;
		try {
			int startRow =page.startRow(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			int endRow = page.endRow(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		if(optWord.isPresent()) {
			list = service.boardFindAll(optWord.get(),startRow, endRow);
			lastRow = service.boardSearchLastRow(optWord.get());
		}else {
			list = service.boardFindAll(startRow, endRow);
			lastRow = service.boardLastRow();
		}
			result.put("boardlist", list);
			result.put("status", 1);
			result.put("lastRow", lastRow);
			result.put("logininfo", loginmem);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@DeleteMapping("/board/{board_no}")
	public Map<String, Object> remove (@PathVariable int board_no,  HttpSession session){
		System.out.println(board_no);
		Map<String, Object> result = new HashMap<String, Object>();
//		board.setBoard_no(board_no);
			try {
				boardService.DeleteBoard(board_no);
				result.put("status", 1);
				
			}catch(DeleteException e){
				e.printStackTrace();
				result.put("status", 0);
				result.put("msg", e.getMessage());
				
			}
		return result;
	}
	
	@PutMapping("/approve/{board_no}")
	public Map<String, Object> approve (@PathVariable int board_no,  HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			service.approve(board_no);
			result.put("status", 1);
			try {
				List<DebateDetail> list = new ArrayList<>();
				list = debService.checkDeb(board_no);
				Map<String, Object> debate =new HashMap<String, Object>();
				debate = debService.findByNo(board_no);
				if(list.get(0)!=null &list.get(1)!=null) {
					Debate deb = (Debate)debate.get("debate");
					DebateDetail dd1 = (DebateDetail)((List<DebateDetail>)debate.get("detail")).get(0);
					DebateDetail dd2 = (DebateDetail)((List<DebateDetail>)debate.get("detail")).get(1);
					if(dd1.getDiscussor()==null | dd2.getDiscussor()==null) {
						result.put("status", 1);
					}else {
						//System.out.println("????????????");
//						mailService.sendMail(deb, dd1, dd2);
						mailService.approveMail(deb, dd1, dd2);
					}
				}
			} catch (FindException e) {
				//throw new FindException(e.getMessage());
			}
		}catch(ModifyException e){
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	@PutMapping("/disapprove/{board_no}")
	public Map<String, Object> disapprove (@PathVariable int board_no,  HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			service.disapprove(board_no);
			result.put("status", 1);
			
		}catch(ModifyException e){
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
}
