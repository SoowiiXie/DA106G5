package com.orders.model;

import java.sql.*;

public class OrdersVO implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String od_no;
	private String mb_id;
	private Timestamp od_time;
	private Integer od_status;
	private Integer od_totalPrice;
	private String cp_no;
	private Integer od_discount;
	private String od_add;

	public OrdersVO() {
		od_no = "";
		mb_id = "";
		od_time = null;
		od_status = 0;
		od_totalPrice = 0;
		cp_no = "";
		od_discount = 0;
		od_add = "";
	}

	public String getOd_no() {
		return od_no;
	}

	public void setOd_no(String od_no) {
		this.od_no = od_no;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public Timestamp getOd_time() {
		return od_time;
	}

	public void setOd_time(Timestamp od_time) {
		this.od_time = od_time;
	}

	public Integer getOd_status() {
		return od_status;
	}

	public void setOd_status(Integer od_status) {
		this.od_status = od_status;
	}

	public Integer getOd_totalPrice() {
		return od_totalPrice;
	}

	public void setOd_totalPrice(Integer od_totalPrice) {
		this.od_totalPrice = od_totalPrice;
	}

	public String getCp_no() {
		return cp_no;
	}

	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}

	public Integer getOd_discount() {
		return od_discount;
	}

	public void setOd_discount(Integer od_discount) {
		this.od_discount = od_discount;
	}

	public String getOd_add() {
		return od_add;
	}

	public void setOd_add(String od_add) {
		this.od_add = od_add;
	}

}
