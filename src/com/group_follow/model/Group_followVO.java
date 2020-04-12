package com.group_follow.model;
import java.sql.Timestamp;

public class Group_followVO implements java.io.Serializable{
	private String grp_no;
	private String mb_id;
	private String loc_no;
	private Timestamp grp_applystart;
	private Timestamp grp_applyend;
	private Timestamp grp_start;
	private Timestamp grp_end;
	private String grp_name;
	private String grp_content;
	private Integer grp_personmax;
	private Integer grp_personmin;
	private Integer grp_personcount;
	private Integer grp_status;
	private Integer grp_follow;

	public String getGrp_no() {
		return grp_no;
	}
	public void setGrp_no(String grp_no) {
		this.grp_no = grp_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getLoc_no() {
		return loc_no;
	}
	public void setLoc_no(String loc_no) {
		this.loc_no = loc_no;
	}
	public Timestamp getGrp_applystart() {
		return grp_applystart;
	}
	public void setGrp_applystart(Timestamp grp_applystart) {
		this.grp_applystart = grp_applystart;
	}
	public Timestamp getGrp_applyend() {
		return grp_applyend;
	}
	public void setGrp_applyend(Timestamp grp_applyend) {
		this.grp_applyend = grp_applyend;
	}
	public Timestamp getGrp_start() {
		return grp_start;
	}
	public void setGrp_start(Timestamp grp_start) {
		this.grp_start = grp_start;
	}
	public Timestamp getGrp_end() {
		return grp_end;
	}
	public void setGrp_end(Timestamp grp_end) {
		this.grp_end = grp_end;
	}
	public String getGrp_name() {
		return grp_name;
	}
	public void setGrp_name(String grp_name) {
		this.grp_name = grp_name;
	}
	public String getGrp_content() {
		return grp_content;
	}
	public void setGrp_content(String grp_content) {
		this.grp_content = grp_content;
	}
	public Integer getGrp_personmax() {
		return grp_personmax;
	}
	public void setGrp_personmax(Integer grp_personmax) {
		this.grp_personmax = grp_personmax;
	}
	public Integer getGrp_personmin() {
		return grp_personmin;
	}
	public void setGrp_personmin(Integer grp_personmin) {
		this.grp_personmin = grp_personmin;
	}
	public Integer getGrp_personcount() {
		return grp_personcount;
	}
	public void setGrp_personcount(Integer grp_personcount) {
		this.grp_personcount = grp_personcount;
	}
	public Integer getGrp_status() {
		return grp_status;
	}
	public void setGrp_status(Integer grp_status) {
		this.grp_status = grp_status;
	}
	public Integer getGrp_follow() {
		return grp_follow;
	}
	public void setGrp_follow(Integer grp_follow) {
		this.grp_follow = grp_follow;
	}
}