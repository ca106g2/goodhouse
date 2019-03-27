package android.com.goodhouse.house.model;

public class HouseVO implements java.io.Serializable{
	private String hou_id;
	private String hou_name;
	private String hou_type;
	private String hou_size;
	private String hou_property;
	private String hou_parkspace;
	private String hou_cook;
	private String hou_managefee;
	private String hou_address;
	private String lan_id;
	private Integer hou_rent;
	private String hou_note;
	
	public HouseVO() {
		
	}
	
	
	public HouseVO(String hou_id, String hou_name, String hou_type, String hou_size, String hou_property,
			String hou_parkspace, String hou_cook, String hou_managefee, String hou_address, String lan_id,
			Integer hou_rent, String hou_note) {
		super();
		this.hou_id = hou_id;
		this.hou_name = hou_name;
		this.hou_type = hou_type;
		this.hou_size = hou_size;
		this.hou_property = hou_property;
		this.hou_parkspace = hou_parkspace;
		this.hou_cook = hou_cook;
		this.hou_managefee = hou_managefee;
		this.hou_address = hou_address;
		this.lan_id = lan_id;
		this.hou_rent = hou_rent;
		this.hou_note = hou_note;
	}
	public HouseVO(String hou_id, String hou_name, String hou_type, Integer hou_rent) {
		super();
		this.hou_id = hou_id;
		this.hou_name = hou_name;
		this.hou_type = hou_type;
		this.hou_rent = hou_rent;
	}


	public String getHou_id() {
		return hou_id;
	}
	public void setHou_id(String hou_id) {
		this.hou_id = hou_id;
	}
	public String getHou_name() {
		return hou_name;
	}
	public void setHou_name(String hou_name) {
		this.hou_name = hou_name;
	}
	public String getHou_type() {
		return hou_type;
	}
	public void setHou_type(String hou_type) {
		this.hou_type = hou_type;
	}
	public String getHou_size() {
		return hou_size;
	}
	public void setHou_size(String hou_size) {
		this.hou_size = hou_size;
	}
	public String getHou_property() {
		return hou_property;
	}
	public void setHou_property(String hou_property) {
		this.hou_property = hou_property;
	}
	public String getHou_parkspace() {
		return hou_parkspace;
	}
	public void setHou_parkspace(String hou_parkspace) {
		this.hou_parkspace = hou_parkspace;
	}
	public String getHou_cook() {
		return hou_cook;
	}
	public void setHou_cook(String hou_cook) {
		this.hou_cook = hou_cook;
	}
	public String getHou_managefee() {
		return hou_managefee;
	}
	public void setHou_managefee(String hou_managefee) {
		this.hou_managefee = hou_managefee;
	}
	public String getHou_address() {
		return hou_address;
	}
	public void setHou_address(String hou_address) {
		this.hou_address = hou_address;
	}
	public String getLan_id() {
		return lan_id;
	}
	public void setLan_id(String lan_id) {
		this.lan_id = lan_id;
	}
	public Integer getHou_rent() {
		return hou_rent;
	}
	public void setHou_rent(Integer hou_rent) {
		this.hou_rent = hou_rent;
	}
	public String getHou_note() {
		return hou_note;
	}
	public void setHou_note(String hou_note) {
		this.hou_note = hou_note;
	}

	
	
	

}
