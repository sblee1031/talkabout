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

import com.talkabout.dto.Admin;
import com.talkabout.dto.Debate;
import com.talkabout.dto.Notice;
import com.talkabout.dto.Pagination;
import com.talkabout.exception.FindException;
import com.talkabout.service.AdminService;
import com.talkabout.service.NoticeService;

@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
//@CrossOrigin("*")
@RequestMapping("/admin/**")
@RestController
public class AdminController {
	@Autowired
	private AdminService service;

	private NoticeService noticeService;
	
	@PostMapping(value = "/login")
	public Map<String, Object> adminLogin(@RequestBody Admin ad, HttpSession session) throws FindException{
		Admin logininfo = (Admin)session.getAttribute("logininfo");
//		System.out.println(ad.getAdmin_id()+ ad.getAdmin_pwd());
		Map<String, Object> result =new HashMap<String, Object>();
		Admin loginAdmin = new Admin();
		try {
			loginAdmin=service.adLogin(ad.getAdmin_id(), ad.getAdmin_pwd());
			session.setAttribute("logininfo", loginAdmin);
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
		Admin loginmem = (Admin) session.getAttribute("logininfo");
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
//			System.out.println("검색list=>"+list.toString());
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
	
}
