package com.goodhouse.notice.model;

import java.util.List;

public class NoticeService {

	private NoticeDAO_interface dao;

	public NoticeService() {
		dao = new NoticeDAO();
	}

	public NoticeVO addNotice(String mem_id, String notice_message, String notice_status) {

		NoticeVO noticeVO = new NoticeVO();

		noticeVO.setMem_id(mem_id);
		noticeVO.setNotice_message(notice_message);
		noticeVO.setNotice_status(notice_status);
		dao.insert(noticeVO);

		return noticeVO;
	}

	public NoticeVO updateNotice(String notice_id, String mem_id, String notice_message, String notice_status) {

		NoticeVO noticeVO = new NoticeVO();

		noticeVO.setNotice_id(notice_id);
		noticeVO.setMem_id(mem_id);
		noticeVO.setNotice_message(notice_message);
		noticeVO.setNotice_status(notice_status);
		dao.update(noticeVO);
		
		return noticeVO;
	}

	public void deleteNotice(String notice_id) {
		dao.delete(notice_id);
	}

	public NoticeVO getOneNotice(String notice_id) {
		return dao.findByPrimaryKey(notice_id);
	}

	public List<NoticeVO> getAll() {
		return dao.getAll();
	}

}
//git上傳註解用無意義