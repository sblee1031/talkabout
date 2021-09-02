package com.talkabout.control;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.talkabout.dto.NoticeComment;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.service.NoticeCommentService;

@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/noticecomment")
public class NoticeCommentController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	NoticeCommentService service;
	
	// http://localhost:9999/back/noticecomment/list/공지번호
	// 매개변수 - 공지사항 번호
	// 공지사항 n번에 대한 댓글 목록 출력
	@GetMapping("/list/{com_notice}")
	public Map<String, Object> selectAllNC(@PathVariable int com_notice){
		Map<String, Object>	result = new HashMap<>();
		List<NoticeComment> list = new ArrayList<>();
		try {
			list = service.findAll(com_notice);
			result.put("status", 1);
			result.put("list", list);
		}catch(FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	// http://localhost:9999/back/noticecomment/one/댓글번호
	// 댓글 번호에 의한 리스트 1개 출력
	@GetMapping("/one/{com_no}")
    public Map<String, Object> selectOneNC(@PathVariable int com_no) {
		Map<String, Object> result = new HashMap<>();
        NoticeComment noticeComment = new NoticeComment();
		try {
			noticeComment = service.findByNo(com_no);
			result.put("status", 1);
			result.put("noticeComment", noticeComment);
		} catch (FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
        return result;
    }
	
	// http://localhost:9999/back/noticecomment
    @PostMapping
    public Map<String, Object> insertNC(@RequestBody NoticeComment noticeComment) throws FindException {
    	System.out.println(noticeComment.getCom_mem().getMember_no());
    	Map<String, Object> result = new HashMap<>();
    	try {
    		service.insertNC(noticeComment);
			result.put("status", 1);
	    	result.put("msg", "Insert Completed");
		} catch (AddException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }
       
    // http://localhost:9999/back/noticecomment/com_no
    @PutMapping("/{com_no}")
    public Map<String, Object> updateNC(@PathVariable int com_no, @RequestBody NoticeComment comment) {
    	
    	Map<String, Object> result = new HashMap<>();
    	try {
    		NoticeComment updateComment = service.findByNo(com_no);
    		updateComment.setCom_contents(comment.getCom_contents()); 
			service.updateNC(updateComment);
			result.put("status", 1);
	    	result.put("msg", "Update Completed");
		} catch (ModifyException | FindException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }

	// http://localhost:9999/back/noticecomment/com_no
    @DeleteMapping("/{com_no}")
    public Map<String, Object> deleteNC(@PathVariable int com_no) {
    	Map<String, Object> result = new HashMap<>();
    	try {
			service.deleteNC(com_no);
			result.put("status", 1);
	    	result.put("msg", "Delete Completed");
		} catch (DeleteException e) {
			result.put("status", 0);
	    	result.put("msg", e.getMessage());
		}
    	return result;
    }
}
