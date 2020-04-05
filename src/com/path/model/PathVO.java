package com.path.model;

import java.sql.Timestamp;

public class PathVO {
	private String path_no;
	private String path_name;
	private Integer path_difficulty;
	private Integer path_popular;
	private Timestamp path_start;
	private Timestamp path_end;
	private byte[] path_pic;
	private Double path_distance;
	private Integer path_status;
	private String path_kml;
	private Double path_lng;
	private Double path_lat;
	
	public PathVO(){
		super();
	}

	public String getPath_no() {
		return path_no;
	}

	public void setPath_no(String path_no) {
		this.path_no = path_no;
	}

	public String getPath_name() {
		return path_name;
	}

	public void setPath_name(String path_name) {
		this.path_name = path_name;
	}

	public Integer getPath_difficulty() {
		return path_difficulty;
	}

	public void setPath_difficulty(Integer path_difficulty) {
		this.path_difficulty = path_difficulty;
	}

	public Integer getPath_popular() {
		return path_popular;
	}

	public void setPath_popular(Integer path_popular) {
		this.path_popular = path_popular;
	}

	public Timestamp getPath_start() {
		return path_start;
	}

	public void setPath_start(Timestamp path_start) {
		this.path_start = path_start;
	}

	public Timestamp getPath_end() {
		return path_end;
	}

	public void setPath_end(Timestamp path_end) {
		this.path_end = path_end;
	}

	public byte[] getPath_pic() {
		return path_pic;
	}

	public void setPath_pic(byte[] path_pic) {
		this.path_pic = path_pic;
	}

	public Double getPath_distance() {
		return path_distance;
	}

	public void setPath_distance(Double path_distance) {
		this.path_distance = path_distance;
	}

	public Integer getPath_status() {
		return path_status;
	}

	public void setPath_status(Integer path_status) {
		this.path_status = path_status;
	}

	public String getPath_kml() {
		return path_kml;
	}

	public void setPath_kml(String path_kml) {
		this.path_kml = path_kml;
	}

	public Double getPath_lng() {
		return path_lng;
	}

	public void setPath_lng(Double path_lng) {
		this.path_lng = path_lng;
	}

	public Double getPath_lat() {
		return path_lat;
	}

	public void setPath_lat(Double path_lat) {
		this.path_lat = path_lat;
	}
	
	
}
