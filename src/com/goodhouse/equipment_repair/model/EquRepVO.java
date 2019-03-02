package com.goodhouse.equipment_repair.model;

import java.lang.reflect.Array;
import java.sql.Date;

public class EquRepVO implements java.io.Serializable {

	private String equ_rep_id;
	private String hou_id;
	private String mem_id;
	private String lan_id;
	private Date equ_rep_accetime;
	private String equ_rep_staff;
	private String equ_rep_staffphone;
	private String equ_rep_event;
	private byte[] equ_rep_picture;
	private String equ_rep_descri;
	private String equ_rep_status;
	private Date equ_rep_expectime;
	private Date equ_rep_finish;

	public String getEqu_rep_id() {
		return equ_rep_id;
	}

	public void setEqu_rep_id(String equ_rep_id) {
		this.equ_rep_id = equ_rep_id;
	}

	public String getHou_id() {
		return hou_id;
	}

	public void setHou_id(String hou_id) {
		this.hou_id = hou_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getLan_id() {
		return lan_id;
	}

	public void setLan_id(String lan_id) {
		this.lan_id = lan_id;
	}

	public Date getEqu_rep_accetime() {
		return equ_rep_accetime;
	}

	public void setEqu_rep_accetime(Date equ_rep_accetime) {
		this.equ_rep_accetime = equ_rep_accetime;
	}

	public String getEqu_rep_staff() {
		return equ_rep_staff;
	}

	public void setEqu_rep_staff(String equ_rep_staff) {
		this.equ_rep_staff = equ_rep_staff;
	}

	public String getEqu_rep_staffphone() {
		return equ_rep_staffphone;
	}

	public void setEqu_rep_staffphone(String equ_rep_staffphone) {
		this.equ_rep_staffphone = equ_rep_staffphone;
	}

	public String getEqu_rep_event() {
		return equ_rep_event;
	}

	public void setEqu_rep_event(String equ_rep_event) {
		this.equ_rep_event = equ_rep_event;
	}

	public byte[] getEqu_rep_picture() {
		return equ_rep_picture;
	}

	public void  setEqu_rep_picture(byte[] equ_rep_picture) {
		this.equ_rep_picture = equ_rep_picture;
	}

	public String getEqu_rep_descri() {
		return equ_rep_descri;
	}

	public void setEqu_rep_descri(String equ_rep_descri) {
		this.equ_rep_descri = equ_rep_descri;
	}

	public String getEqu_rep_status() {
		return equ_rep_status;
	}

	public void setEqu_rep_status(String equ_rep_status) {
		this.equ_rep_status = equ_rep_status;
	}

	public Date getEqu_rep_expectime() {
		return equ_rep_expectime;
	}

	public void setEqu_rep_expectime(Date equ_rep_expectime) {
		this.equ_rep_expectime = equ_rep_expectime;
	}

	public Date getEqu_rep_finish() {
		return equ_rep_finish;
	}

	public void setEqu_rep_finish(Date equ_rep_finish) {
		this.equ_rep_finish = equ_rep_finish;
	}

}
//git上傳註解用無意義