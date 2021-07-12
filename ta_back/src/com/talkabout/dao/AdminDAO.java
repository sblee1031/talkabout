package com.talkabout.dao;

import com.talkabout.dto.Admin;
import com.talkabout.exception.FindException;

public interface AdminDAO {
	Admin selectByNo(int admin_no) throws FindException;
}
