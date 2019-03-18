package com.goodhouse.house_track.model;

//對應資料庫：房屋追蹤
public class House_TrackVO implements java.io.Serializable{
	
	private String hou_tra_id;//房屋追蹤編號
	private String mem_id;//會員編號
	private String hou_id;//房屋編號
//	private String hou_tra_status;//房屋追蹤狀態
	public String getHou_tra_id() {
		return hou_tra_id;
	}
	public void setHou_tra_id(String hou_tra_id) {
		this.hou_tra_id = hou_tra_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	public String getHou_id() {
		return hou_id;
	}
	public void setHou_id(String hou_id) {
		this.hou_id = hou_id;
	}
//	public String getHou_tra_status() {
//		return hou_tra_status;
//	}
//	public void setHou_tra_status(String hou_tra_status) {
//		this.hou_tra_status = hou_tra_status;
//	}
	
	
	
}