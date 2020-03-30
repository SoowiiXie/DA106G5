package com.xuan.ntf.model;

public class NotifyVO implements java.io.Serializable {
	private String ntf_no, mb_id, ntf_content;
	private Integer ntf_status;
	
	public NotifyVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNtf_no() {
		return ntf_no;
	}

	public void setNtf_no(String ntf_no) {
		this.ntf_no = ntf_no;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public String getNtf_content() {
		return ntf_content;
	}

	public void setNtf_content(String ntf_content) {
		this.ntf_content = ntf_content;
	}

	public Integer getNtf_status() {
		return ntf_status;
	}

	public void setNtf_status(Integer ntf_status) {
		this.ntf_status = ntf_status;
	}
	
}
