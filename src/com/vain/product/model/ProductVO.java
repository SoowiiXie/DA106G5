package com.vain.product.model;

public class ProductVO implements java.io.Serializable{
	/**
	 * 
	 */
	
	private String pd_no;
	private String pd_name;
	private Integer pd_price;
	private byte[] pd_pic;  //尚未上傳圖片
	private String pd_datail;
	private Integer pd_status;
	private String pd_typeNo;
	
	
	public String getPd_no() {
		return pd_no;
	}
	public void setPd_no(String pd_no) {
		this.pd_no = pd_no;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public Integer getPd_price() {
		return pd_price;
	}
	public void setPd_price(Integer pd_price) {
		this.pd_price = pd_price;
	}
	public byte[] getPd_pic() {
		return pd_pic;
	}
	public void setPd_pic(byte[] pd_pic) {
		this.pd_pic = pd_pic;
	}
	public String getPd_datail() {
		return pd_datail;
	}
	public void setPd_datail(String pd_datail) {
		this.pd_datail = pd_datail;
	}
	public Integer getPd_status() {
		return pd_status;
	}
	public void setPd_status(Integer pd_status) {
		this.pd_status = pd_status;
	}
	public String getPd_typeNo() {
		return pd_typeNo;
	}
	public void setPd_typeNo(String pd_typeNo) {
		this.pd_typeNo = pd_typeNo;
	}
	
	
	

	
	
    	

}
