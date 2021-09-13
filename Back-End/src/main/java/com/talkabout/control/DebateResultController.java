package com.talkabout.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.DebateSungho;
import com.talkabout.dto.DebateCommentSungho;
import com.talkabout.dto.DebateDetail;
import com.talkabout.dto.Member;
import com.talkabout.dto.PageDTOsungho;
import com.talkabout.exception.AddException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.RemoveException;
import com.talkabout.service.DebateResultService;

@RequestMapping(value = "/resultlist/**")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000"})
@RestController
public class DebateResultController {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	DebateResultService service;

//	@GetMapping(value = "/resultlist")
//	public Map<String, List<DebateSungho>> getlist() throws FindException {
//		Map<String, List<DebateSungho>> map = new HashMap<>();
//		List<DebateSungho> debatelist = service.debresultlistall();
//
//		map.put("debatelist", debatelist);
//		return map;
//	}
	@GetMapping(value = "/resultlist")
	public Map<String, Object> getlist(DebateSungho cri, HttpSession session) throws FindException {
		Member logininfo = (Member)session.getAttribute("logininfo");
		Map<String, Object> map = new HashMap<>();
		List<DebateSungho> debatelist = service.listallWithPaging(cri);
		System.out.println("debatelist"+debatelist);
		int totalListCount = service.debresultlistall().size();
		map.put("logininfo", logininfo); //로그인정보
		map.put("debatelist", debatelist);//게시글 리스트 
		map.put("pageMaker", new PageDTOsungho(cri, totalListCount));//페이징처리
		return map;
	}
	@GetMapping(value = "/resultlist/list/{pageno}")
	public Map<String, Object> getlistbypageno(DebateSungho cri, HttpSession session,@PathVariable(required = false) int pageno) throws FindException {
		Member logininfo = (Member)session.getAttribute("logininfo");
		cri.setPage_num(pageno);
		Map<String, Object> map = new HashMap<>();
		List<DebateSungho> debatelist = service.listallWithPaging(cri);
		int totalListCount = service.debresultlistall().size();
		map.put("logininfo", logininfo); //로그인정보
		map.put("debatelist", debatelist);//게시글 리스트 
		map.put("pageMaker", new PageDTOsungho(cri, totalListCount));//페이징처리
		return map;
	}
	@GetMapping(value = "/resultlist/{boardno}")
	public Map<String, Object> getone(@PathVariable int boardno,HttpSession session) throws FindException {
		Member logininfo = (Member)session.getAttribute("logininfo");
		System.out.println(boardno);
		Map<String, Object> map = new HashMap<>();
		DebateSungho debatedetail = (DebateSungho) service.selectOne(boardno);
		List<DebateCommentSungho> commentlist = service.GetListComment(boardno);
		
		map.put("logininfo", logininfo);
		map.put("dabateone", debatedetail);
		map.put("commentlist", commentlist);
		map.put("vote_left", service.GetVoteResult_left(boardno));
		map.put("vote_right", service.GetVoteResult_right(boardno));
		map.put("vote_middle", service.GetVoteResult_middle(boardno));
		return map;
	}

	@PostMapping(value = "/resultreply/{boardno}")
	public Map<String, Object> insert(@RequestParam String com_contents, @PathVariable int boardno ,HttpSession session) {
		Map<String, Object> result = new HashMap<>();

		System.out.println("댓글입력내용" + com_contents);
		
		Member logininfo = (Member)session.getAttribute("logininfo");
		int CommentMemberNo = logininfo.getMember_no();
		DebateCommentSungho cm = new DebateCommentSungho();
		cm.setMember_no(CommentMemberNo);
		int member_no = cm.getMember_no();
		DebateCommentSungho comment = new DebateCommentSungho(boardno, com_contents, member_no);

		try {
			service.CreateComment(comment);
			result.put("status", 1);
			result.put("msg", "댓글 추가 성공");
		} catch (AddException e) {
			e.printStackTrace();
			result.put("status", 2);
			result.put("msg", "댓글 추가 실패");
		}
		return result;
	}

	@DeleteMapping(value = "/resultreply/{boardno}")
	public Map<String, Object> delete(@PathVariable int boardno) {
		Map<String, Object> result = new HashMap<>();

		try {
			service.RemoveComment(boardno);
			result.put("status", 1);
			result.put("msg", "댓글 삭제 성공");
		} catch (RemoveException e) {
			e.printStackTrace();
			result.put("status", 2);
			result.put("msg", "댓글 삭제 실패");
		}
		return result;
	}
	
	@GetMapping(value = "/search/{word}")
	public Map<String,Object> getlistbyword(@PathVariable String word )throws FindException{
		Map<String, Object> result = new HashMap<>();
		List<DebateSungho> debatelistbyword = service.Getlistbyword(word);
		result.put("debatelistbyword", debatelistbyword);
		return result;
	}
}
