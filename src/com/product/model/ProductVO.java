package com.product.model;

public class ProductVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String pd_no;
	private String pd_name;
	private Integer pd_price;
	private byte[] pd_pic;  //尚未上傳圖片
	private String pd_detail;
	private Integer pd_status;
	private String pd_typeNo;
	private String pd_statusName;
	public String getPd_statusName() {
		return pd_statusName;
	}
	public void setPd_statusName(String pd_statusName) {
		this.pd_statusName = pd_statusName;
	}
	private String pd_typeNoName;
	
	public String getPd_typeNoName() {
		return pd_typeNoName;
	}
	public void setPd_typeNoName(String pd_typeNoName) {
		this.pd_typeNoName = pd_typeNoName;
	}
	private String pd_pic_path; //放置路徑用，非表格欄位
	
	
	public String getPd_pic_path() {
		return pd_pic_path;
	}
	public void setPd_pic_path(String pd_pic_path) {
		this.pd_pic_path = pd_pic_path;
	}
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
	public String getPd_detail() {
		return pd_detail;
	}
	public void setPd_detail(String pd_datail) {
		this.pd_detail = pd_datail;
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
