package com.cp_get.model;

import java.sql.Timestamp;

public class Cp_getVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mb_id;
	private String cp_no;
	private Integer cp_status;
	private Timestamp cp_getTime;
	private String cp_javaGetTime;

	
	public Cp_getVO() {
		mb_id = "";
		cp_no = "";
		cp_status = 0;
		cp_getTime = null;
		cp_javaGetTime="";
	}
	
	public String getCp_javaGetTime() {
		return cp_javaGetTime;
	}

	public void setCp_javaGetTime(String cp_javaGetTime) {
		this.cp_javaGetTime = cp_javaGetTime;
	}

	

	public Timestamp getCp_getTime() {
		return cp_getTime;
	}

	public void setCp_getTime(Timestamp cp_getTime) {
		this.cp_getTime = cp_getTime;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public String getCp_no() {
		return cp_no;
	}

	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}

	public Integer getCp_status() {
		return cp_status;
	}

	public void setCp_status(Integer cp_status) {
		this.cp_status = cp_status;
	}

}
