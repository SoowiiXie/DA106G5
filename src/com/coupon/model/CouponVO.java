package com.coupon.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class CouponVO implements java.io.Serializable {
	/**
	 * 
	 */

	private String cp_no;
	private String cp_name;
	private Integer cp_price;
	private byte[] cp_pic;
	private String cp_detail;
    private Timestamp cp_time;
    private String cp_javaTime;
	public CouponVO() {

		cp_no = "";
		cp_name = "";
		cp_price = 0;
		cp_pic = null;
		cp_detail="";
		cp_time=null;
		cp_javaTime = null;
	}

	public String getCp_javaTime() {
		return cp_javaTime;
	}

	public void setCp_javaTime(String cp_javaTime) {
		this.cp_javaTime = cp_javaTime;
	}

	public String getCp_detail() {
		return cp_detail;
	}

	public void setCp_detail(String cp_detail) {
		this.cp_detail = cp_detail;
	}

	public Timestamp getCp_time() {
		return cp_time;
	}

	public void setCp_time(Timestamp cp_time) {
		this.cp_time = cp_time;
	}

	private static final long serialVersionUID = 1L;

	public String getCp_no() {
		return cp_no;
	}

	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public byte[] getCp_pic() {
		return cp_pic;
	}

	public void setCp_pic(byte[] cp_pic) {
		this.cp_pic = cp_pic;
	}

	public Integer getCp_price() {
		return cp_price;
	}

	public void setCp_price(Integer cp_price) {
		this.cp_price = cp_price;
	}

	@Override
	public String toString() {
		return "CouponVO [cp_no=" + cp_no + ", cp_name=" + cp_name + ", cp_price=" + cp_price + ", cp_pic="
				+ Arrays.toString(cp_pic) + ", cp_detail=" + cp_detail + ", cp_time=" + cp_time + ", cp_javaTime="
				+ cp_javaTime + "]";
	}
	
}
