package com.que_rpt.model;

public class Question_RPTVO implements java.io.Serializable {
	private String question_no, mb_id, question_content;
	private Integer question_status;
	
	public Question_RPTVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getQuestion_no() {
		return question_no;
	}
	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public Integer getQuestion_status() {
		return question_status;
	}
	public void setQuestion_status(Integer question_status) {
		this.question_status = question_status;
	}
	
}
