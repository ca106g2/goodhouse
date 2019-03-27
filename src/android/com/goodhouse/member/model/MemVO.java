package android.com.goodhouse.member.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class MemVO  implements Serializable{

	private String mem_id;
	private String mem_name;
	private Date mem_birthday;
	private String mem_password;
	private String  mem_address;
	private String mem_zipcode;
	private Integer mem_telephone;
	private Integer  mem_phone;
	private String mem_email;
	private String mem_status;
	private Integer good_total;
	private String mem_sex;
	
	
	
	public MemVO() {
		super();
	}
	public MemVO(String mem_id, String mem_name, Date mem_birthday, String mem_password, String mem_address,
			String mem_zipcode, Integer mem_telephone, Integer mem_phone, String mem_email, String mem_status,
			Integer good_total, String mem_sex) {
		super();
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.mem_birthday = mem_birthday;
		this.mem_password = mem_password;
		this.mem_address = mem_address;
		this.mem_zipcode = mem_zipcode;
		this.mem_telephone = mem_telephone;
		this.mem_phone = mem_phone;
		this.mem_email = mem_email;
		this.mem_status = mem_status;
		this.good_total = good_total;
		this.mem_sex = mem_sex;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public Date getMem_birthday() {
		return mem_birthday;
	}
	public void setMem_birthday(Date mem_birthday) {
		this.mem_birthday = mem_birthday;
	}
	public String getMem_password() {
		return mem_password;
	}
	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}
	public String getMem_address() {
		return mem_address;
	}
	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}
	public Integer getMem_telephone() {
		return mem_telephone;
	}
	public void setMem_telephone(Integer mem_telephone) {
		this.mem_telephone = mem_telephone;
	}
	public Integer getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(Integer mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_status() {
		return mem_status;
	}
	public void setMem_status(String mem_status) {
		this.mem_status = mem_status;
	}
		public Integer getGood_total() {
		return good_total;
	}
	public void setGood_total(Integer good_total) {
		this.good_total = good_total;
	}
	public String getMem_sex() {
		return mem_sex;
	}
	public void setMem_sex(String mem_sex) {
		this.mem_sex = mem_sex;
	}
	

	
	
	
}
