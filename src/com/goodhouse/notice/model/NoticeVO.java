package com.goodhouse.notice.model;

public class NoticeVO implements java.io.Serializable {

	private String notice_id;
	private String mem_id;
	private String notice_message;
	private String notice_status;

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getNotice_message() {
		return notice_message;
	}

	public void setNotice_message(String notice_message) {
		this.notice_message = notice_message;
	}

	public String getNotice_status() {
		return notice_status;
	}

	public void setNotice_status(String notice_status) {
		this.notice_status = notice_status;
	}

}
//git上傳註解用無意義