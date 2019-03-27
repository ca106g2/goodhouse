package android.com.goodhouse.house.model;

import java.util.*;

public interface HouseDAO_interface {
		public void insert(HouseVO houseVO);
		public void update(HouseVO houseVO);
		public void delete(String hou_id);
		public HouseVO findByPrimaryKey(String hou_id);
		public List<HouseVO> getAll();
		public List<HouseVO> findByLanidLannotrant(String lan_id,String hou_property);
		public List<HouseVO> getAllok(String hou_property,String hou_parkspace);
	
		
		byte[] getImage (String hou_id);
		byte[] getImage2 (String hou_id);
}
