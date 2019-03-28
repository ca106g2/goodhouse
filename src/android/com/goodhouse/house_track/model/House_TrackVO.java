package android.com.goodhouse.house_track.model;

import java.util.List;

import android.com.goodhouse.house.model.HouseVO;
import android.com.goodhouse.member.model.MemVO;

//對應資料庫：房屋追蹤
public class House_TrackVO implements java.io.Serializable{
	
	private String hou_tra_id;//房屋追蹤編號
	private String mem_id;//會員編號
	private String hou_id;//房屋編號
	private List<HouseVO> loveHouseList;
	private List<MemVO> memberList;
	
	
	
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
	
	public List<HouseVO> getLoveHouseList() {
		return loveHouseList;
	}
	public void setLoveHouseList(List<HouseVO> loveHouseList) {
		this.loveHouseList = loveHouseList;
	}
	public List<MemVO> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemVO> memberList) {
		this.memberList = memberList;
	}
	
	
	
}