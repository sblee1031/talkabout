package com.talkabout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkabout.dao.NoticeDAO;
import com.talkabout.dto.Notice;
import com.talkabout.exception.AddException;
import com.talkabout.exception.DeleteException;
import com.talkabout.exception.FindException;
import com.talkabout.exception.ModifyException;
import com.talkabout.exception.RemoveException;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	/**	전체 리스트 출력 */
	public List<Notice> findAll() throws FindException{
		return noticeDAO.selectAll();
	}
	
	/**	리스트 1개 출력 by deb_no, mem_no */
	public Notice findByNo(int notice_no) throws FindException{
		return noticeDAO.selectOne(notice_no);
	}
	
	/**	검색 리스트 출력 */
	public List<Notice> findByWord(String word) throws FindException{
		return noticeDAO.selectSearch(word);
	}
	
	/**	Notice 생성 */
	public void insertNotice(Notice notice) throws AddException{
		noticeDAO.insertNotice(notice);
	}
	
	/**	Notice 수정 */
	public void updateNotice(Notice notice) throws ModifyException{
		noticeDAO.updateNotice(notice);
	}
	
	/**	Notice 삭제  */
	public void deleteNotice(int notice_no) throws DeleteException{
		noticeDAO.deleteNotice(notice_no);
	}
	
	/**	조회수 수정 */
	public void updateviews(Notice notice) throws ModifyException{
		noticeDAO.updateCount(notice);
	}
}
