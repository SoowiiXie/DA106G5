package com.mb.model;

import java.sql.Date;
import java.util.Arrays;

public class MemberVO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mb_id, mb_pwd, mb_name, mb_email;
	private Integer mb_gender, mb_lv, mb_rpt_times, mb_status;
	private Date mb_birthday;
	private byte[] mb_pic, mb_line_pic;
	private String mb_line_id, mb_line_display, mb_line_status;
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_pwd() {
		return mb_pwd;
	}
	public void setMb_pwd(String mb_pwd) {
		this.mb_pwd = mb_pwd;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public Integer getMb_gender() {
		return mb_gender;
	}
	public void setMb_gender(Integer mb_gender) {
		this.mb_gender = mb_gender;
	}
	public Integer getMb_lv() {
		return mb_lv;
	}
	public void setMb_lv(Integer mb_lv) {
		this.mb_lv = mb_lv;
	}
	public Integer getMb_rpt_times() {
		return mb_rpt_times;
	}
	public void setMb_rpt_times(Integer mb_rpt_times) {
		this.mb_rpt_times = mb_rpt_times;
	}
	public Integer getMb_status() {
		return mb_status;
	}
	public void setMb_status(Integer mb_status) {
		this.mb_status = mb_status;
	}
	public Date getMb_birthday() {
		return mb_birthday;
	}
	public void setMb_birthday(Date mb_birthday) {
		this.mb_birthday = mb_birthday;
	}
	public byte[] getMb_pic() {
		return mb_pic;
	}
	public void setMb_pic(byte[] mb_pic) {
		this.mb_pic = mb_pic;
	}
	public String getMb_line_id() {
		return mb_line_id;
	}
	public void setMb_line_id(String mb_line_id) {
		this.mb_line_id = mb_line_id;
	}
	public byte[] getMb_line_pic() {
		return mb_line_pic;
	}
	public void setMb_line_pic(byte[] mb_line_pic) {
		this.mb_line_pic = mb_line_pic;
	}
	public String getMb_line_display() {
		return mb_line_display;
	}
	public void setMb_line_display(String mb_line_display) {
		this.mb_line_display = mb_line_display;
	}
	public String getMb_line_status() {
		return mb_line_status;
	}
	public void setMb_line_status(String mb_line_status) {
		this.mb_line_status = mb_line_status;
	}
	@Override
	public String toString() {
		return "MemberVO [mb_id=" + mb_id + ", mb_pwd=" + mb_pwd + ", mb_name=" + mb_name + ", mb_email=" + mb_email
				+ ", mb_gender=" + mb_gender + ", mb_lv=" + mb_lv + ", mb_rpt_times=" + mb_rpt_times + ", mb_status="
				+ mb_status + ", mb_birthday=" + mb_birthday + ", mb_pic=" + Arrays.toString(mb_pic) + ", mb_line_id="
				+ mb_line_id + ", mb_line_pic=" + mb_line_pic + ", mb_line_display=" + mb_line_display
				+ ", mb_line_status=" + mb_line_status + "]";
	}
		
}
