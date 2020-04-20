package com.pd_follow.model;

public class Pd_followVO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mb_id;
	private String pd_no;
	

    public Pd_followVO() {
    	mb_id = "";
    	pd_no = "";
    }	
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getPd_no() {
		return pd_no;
	}
	public void setPd_no(String pd_no) {
		this.pd_no = pd_no;
	}
	

	

}
