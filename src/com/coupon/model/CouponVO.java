package com.coupon.model;

public class CouponVO implements java.io.Serializable {
	/**
	 * 
	 */

	private String cp_no;
	private String cp_name;
	private Integer cp_price;
	private byte[] cp_pic;

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

}
