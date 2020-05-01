package com.location.model;

public class LocationJsonVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double lat;
	private Double lng;
	private String icon;
	private String adr;
	private String pic;
	private String loc_no4json;
	
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getLoc_no4json() {
		return loc_no4json;
	}
	public void setLoc_no4json(String loc_no4json) {
		this.loc_no4json = loc_no4json;
	}
	
}
