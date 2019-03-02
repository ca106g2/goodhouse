package com.goodhouse.notice.model;

import java.util.*;

public interface NoticeDAO_interface {
	public void insert(NoticeVO noticeVO);

	public void update(NoticeVO noticeVO);

	public void delete(String notice_id);

	public NoticeVO findByPrimaryKey(String notice_id);

	public List<NoticeVO> getAll();

}
//git上傳註解用無意義