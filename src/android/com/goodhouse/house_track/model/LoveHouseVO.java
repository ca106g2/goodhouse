package android.com.goodhouse.house_track.model;

import android.com.goodhouse.house.model.HouseVO;

public class LoveHouseVO extends HouseVO {
	
	private int quantity;
	
	public LoveHouseVO(String hou_id,String hou_name,String hou_type,Integer hou_rent,int quantity) {
		super(hou_id,hou_name, hou_type, hou_rent);
		this.quantity = quantity;
		
	}
	public LoveHouseVO(HouseVO houseVO, int quantity) {
		this(houseVO.getHou_id(),houseVO.getHou_name(),houseVO.getHou_type(),houseVO.getHou_rent(),quantity);
		this.quantity = quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	
	

}
