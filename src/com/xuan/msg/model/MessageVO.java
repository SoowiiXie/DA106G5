package com.xuan.msg.model;

import java.sql.Timestamp;

public class MessageVO implements java.io.Serializable {
	private String msg_no, mb_id_1, mb_id_2, msg_content;
	private Timestamp msg_time;
	private Integer msg_status;
	
	public MessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMsg_no() {
		return msg_no;
	}

	public void setMsg_no(String msg_no) {
		this.msg_no = msg_no;
	}

	public String getMb_id_1() {
		return mb_id_1;
	}

	public void setMb_id_1(String mb_id_1) {
		this.mb_id_1 = mb_id_1;
	}

	public String getMb_id_2() {
		return mb_id_2;
	}

	public void setMb_id_2(String mb_id_2) {
		this.mb_id_2 = mb_id_2;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	public Timestamp getMsg_time() {
		return msg_time;
	}

	public void setMsg_time(Timestamp msg_time) {
		this.msg_time = msg_time;
	}

	public Integer getMsg_status() {
		return msg_status;
	}

	public void setMsg_status(Integer msg_status) {
		this.msg_status = msg_status;
	}
	
}
