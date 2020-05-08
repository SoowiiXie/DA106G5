package com.thumb.model;



public class ThumbFollowVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rcd_no;
	private String mb_id;
	private boolean hasFollow;
	
	public ThumbFollowVO(){
		super();
	}

	public String getRcd_no() {
		return rcd_no;
	}

	public void setRcd_no(String rcd_no) {
		this.rcd_no = rcd_no;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public boolean isHasFollow() {
		return hasFollow;
	}

	public void setHasFollow(boolean hasFollow) {
		this.hasFollow = hasFollow;
	}

	@Override
	public String toString() {
		return "ThumbFollowVO [rcd_no=" + rcd_no + ", mb_id=" + mb_id + ", hasFollow=" + hasFollow + "]";
	}

}
