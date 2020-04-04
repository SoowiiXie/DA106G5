package com.live.model;

import java.sql.Date;

public class LiveVO {
	
	private String live_no;
	private String mb_id;
	private String live_content;
	private Integer live_status;
	private Date live_startteaser;
	private Date live_start;
	private byte[] live_store;
	
	
	public LiveVO() {
		super();
	}


	public LiveVO(String live_no, String mb_id, String live_content, Integer live_status, Date live_startteaser,
			Date live_start, byte[] live_store) {
		super();
		this.live_no = live_no;
		this.mb_id = mb_id;
		this.live_content = live_content;
		this.live_status = live_status;
		this.live_startteaser = live_startteaser;
		this.live_start = live_start;
		this.live_store = live_store;
	}


	public String getLive_no() {
		return live_no;
	}


	public void setLive_no(String live_no) {
		this.live_no = live_no;
	}


	public String getMb_id() {
		return mb_id;
	}


	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}


	public String getLive_content() {
		return live_content;
	}


	public void setLive_content(String live_content) {
		this.live_content = live_content;
	}


	public Integer getLive_status() {
		return live_status;
	}


	public void setLive_status(Integer live_status) {
		this.live_status = live_status;
	}


	public Date getLive_startteaser() {
		return live_startteaser;
	}


	public void setLive_startteaser(Date live_startteaser) {
		this.live_startteaser = live_startteaser;
	}


	public Date getLive_start() {
		return live_start;
	}


	public void setLive_start(Date live_start) {
		this.live_start = live_start;
	}


	public byte[] getLive_store() {
		return live_store;
	}


	public void setLive_store(byte[] live_store) {
		this.live_store = live_store;
	}
	
	
	

}
