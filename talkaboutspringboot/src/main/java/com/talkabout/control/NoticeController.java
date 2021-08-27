package com.talkabout.control;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.NoticeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	NoticeService noticeService;
	
	/*
	 	https://dsc-sookmyung.tistory.com/36
		https://victorydntmd.tistory.com/333
		https://dsc-sookmyung.tistory.com/33
		https://doublesprogramming.tistory.com/202
	*/	
	
	// http://localhost:9999/back/notice/list
	// http://localhost:9999/back/notice/list/word
	@GetMapping(value = {"/list", "/list/{word}"})
	public Map<String, Object> list(@PathVariable(name="word") Optional<String> optWord){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Notice> list = new ArrayList<Notice>();
		try {
			if(optWord.isPresent()) {
				list = noticeService.findByWord(optWord.get());
			}else {
				list = noticeService.findAll();
			}
			result.put("status", 1);
			result.put("notices", list);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	// http://localhost:9999/back/notice/번호
	@GetMapping("/{notice_no}")
    public Map<String, Object> noticeDetail(@PathVariable int notice_no) {
		Map<String, Object> result = new HashMap<>();
        Notice notice = new Notice();
		try {
			notice = noticeService.findByNo(notice_no);
			noticeService.updateviews(notice);
			result.put("status", 1);
			result.put("notice", notice);
		} catch (FindException | ModifyException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
        return result;
    }
	
	// http://localhost:9999/back/notice
    @PostMapping
    public Map<String, Object> insertNotice(@RequestBody Notice notice) {
    	Map<String, Object> result = new HashMap<>();
    	
    	try {
			noticeService.insertNotice(notice);
			result.put("status", 1);
	    	result.put("msg", "Insert Completed");
		} catch (AddException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }
       
    // http://localhost:9999/back/notice/3
    // 
    @PutMapping("/{notice_no}")
    public Map<String, Object> updateNotice(@PathVariable int notice_no, @RequestBody Notice notice) {
    	
    	Map<String, Object> result = new HashMap<>();
    	try {
    		Notice updateNotice = noticeService.findByNo(notice_no);
    		updateNotice.setNotice_type(notice.getNotice_type());
    		updateNotice.setNotice_title(notice.getNotice_title());
    		updateNotice.setNotice_contents(notice.getNotice_contents());
			noticeService.updateNotice(notice);
			result.put("status", 1);
	    	result.put("msg", "Update Completed");
		} catch (ModifyException | FindException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }

	// http://localhost:9999/back/notice/4
    @DeleteMapping("/{notice_no}")
    public Map<String, Object> deleteNotice(@PathVariable int notice_no) {
    	Map<String, Object> result = new HashMap<>();
    	try {
			noticeService.deleteNotice(notice_no);
			result.put("status", 1);
	    	result.put("msg", "Delete Completed");
		} catch (DeleteException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }
}
