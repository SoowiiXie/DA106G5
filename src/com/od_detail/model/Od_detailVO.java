package com.od_detail.model;

public class Od_detailVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String od_no;
	private String pd_no;
	private Integer od_amount;
	private Integer od_price;
	private String pd_size;

	public Od_detailVO() {

		od_no = "";
		pd_no = "";
		od_amount = 0;
		od_price = 0;
		pd_size = "";

	}

	public String getPd_size() {
		return pd_size;
	}

	public void setPd_size(String pd_size) {
		this.pd_size = pd_size;
	}

	public String getOd_no() {
		return od_no;
	}

	public void setOd_no(String od_no) {
		this.od_no = od_no;
	}

	public String getPd_no() {
		return pd_no;
	}

	public void setPd_no(String pd_no) {
		this.pd_no = pd_no;
	}

	public Integer getOd_amount() {
		return od_amount;
	}

	public void setOd_amount(Integer od_amount) {
		this.od_amount = od_amount;
	}

	public Integer getOd_price() {
		return od_price;
	}

	public void setOd_price(Integer od_price) {
		this.od_price = od_price;
	}

}
