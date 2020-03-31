package com.location.model;

public class LocationVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loc_no;
	private String loc_typeno;
	private String longitude;
	private String latitude;
	private Integer loc_status;
	private String loc_address;
	private byte[] loc_pic;

	public String getLoc_no() {
		return loc_no;
	}

	public void setLoc_no(String loc_no) {
		this.loc_no = loc_no;
	}

	public String getLoc_typeno() {
		return loc_typeno;
	}

	public void setLoc_typeno(String loc_typeno) {
		this.loc_typeno = loc_typeno;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Integer getLoc_status() {
		return loc_status;
	}

	public void setLoc_status(Integer loc_status) {
		this.loc_status = loc_status;
	}

	public String getLoc_address() {
		return loc_address;
	}

	public void setLoc_address(String loc_address) {
		this.loc_address = loc_address;
	}

	public byte[] getLoc_pic() {
		return loc_pic;
	}

	public void setLoc_pic(byte[] loc_pic) {
		this.loc_pic = loc_pic;
	}

}
