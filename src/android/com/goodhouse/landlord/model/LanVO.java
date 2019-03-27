package android.com.goodhouse.landlord.model;

import java.io.Serializable;

public class LanVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String lan_id;
	private String mem_id;
	private String lan_receipt;
	private String lan_account;
	private String lan_accountstatus;
	
	
	
	
	
	
	public LanVO() {
		super();
	}
	public LanVO(String lan_id, String mem_id, String lan_receipt, String lan_account, String lan_accountstatus) {
		super();
		this.lan_id = lan_id;
		this.mem_id = mem_id;
		this.lan_receipt = lan_receipt;
		this.lan_account = lan_account;
		this.lan_accountstatus = lan_accountstatus;
	}
	public String getLan_id() {
		return lan_id;
	}
	public void setLan_id(String lan_id) {
		this.lan_id = lan_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getLan_receipt() {
		return lan_receipt;
	}
	public void setLan_receipt(String lan_receipt) {
		this.lan_receipt = lan_receipt;
	}
	public String getLan_account() {
		return lan_account;
	}
	public void setLan_account(String lan_account) {
		this.lan_account = lan_account;
	}
	public String getLan_accountstatus() {
		return lan_accountstatus;
	}
	public void setLan_accountstatus(String lan_accountstatus) {
		this.lan_accountstatus = lan_accountstatus;
	}
	
	
	
	
	
	
	
	
}
