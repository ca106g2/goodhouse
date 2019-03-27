package android.com.goodhouse.landlord.model;

import java.sql.Date;
import java.util.List;



public class LanService {
	
	private LanDAO_interface dao;
	
	public LanService(){
		
		dao = new LanDAO();
	}
	
	public LanVO addLan(String mem_id, String lan_receipt, String lan_account,String lan_accountstatus ) {
		LanVO lanVO = new LanVO();
		
		lanVO.setMem_id(mem_id );
		lanVO.setLan_receipt( lan_receipt);
		lanVO.setLan_account(lan_account);
		lanVO.setLan_accountstatus(lan_accountstatus);
		
		dao.insert(lanVO);
		
		return lanVO;
	}
	public LanVO updateLan(String mem_id, String lan_receipt,String lan_account, String lan_accountstatus ,String lan_id) {
		LanVO lanVO = new LanVO();
		
		lanVO.setMem_id(mem_id );
		lanVO.setLan_receipt( lan_receipt);
		lanVO.setLan_account(lan_account);
		lanVO.setLan_accountstatus(lan_accountstatus);
		
		lanVO.setLan_id(lan_id);
		
		dao.update(lanVO);
		return lanVO;
	}
	
	public void deleteLan(String lan_id) {
		dao.delete(lan_id);
		}
	
	public LanVO getOneLan(String lan_id) {
		return dao.findByPrimaryKey(lan_id);
		}
	
	public List <LanVO> getAll(){
		return dao.getAll();
	}
	public LanVO getOneLanByMemId(String mem_id) {
		return dao.findByMem_id(mem_id);
	}
	
	public boolean isLandlord(String mem_id) {
		return isLandlord(mem_id);
	}
	
	
}


