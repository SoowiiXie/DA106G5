package com.mb_follow.model;

public class Mb_followVO implements java.io.Serializable{
	private String mb_id;
	private String mb_id_followed;
	
	
	public Mb_followVO() {
		super();
	}




	public Mb_followVO(String mb_id, String mb_id_followed) {
		super();
		this.mb_id = mb_id;
		this.mb_id_followed = mb_id_followed;
	}




	public String getMb_id() {
		return mb_id;
	}




	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}




	public String getMb_id_followed() {
		return mb_id_followed;
	}




	public void setMb_id_followed(String mb_id_followed) {
		this.mb_id_followed = mb_id_followed;
	}
	
	
	
	

}
