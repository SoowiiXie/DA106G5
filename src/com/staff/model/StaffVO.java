package com.staff.model;

import java.sql.Timestamp;

public class StaffVO implements java.io.Serializable {
	private String staff_id, staff_pwd, staff_name;
	private Timestamp staff_join;
	private Integer staff_status;
	
	public StaffVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_pwd() {
		return staff_pwd;
	}
	public void setStaff_pwd(String staff_pwd) {
		this.staff_pwd = staff_pwd;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public Timestamp getStaff_join() {
		return staff_join;
	}
	public void setStaff_join(Timestamp staff_join) {
		this.staff_join = staff_join;
	}
	public Integer getStaff_status() {
		return staff_status;
	}
	public void setStaff_status(Integer staff_status) {
		this.staff_status = staff_status;
	}
}
