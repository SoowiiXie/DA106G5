package android.com.record.model;

import java.sql.Date;
import java.util.List;

import com.cmt.model.CmtVO;

public class RecordVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rcd_no;
	private Date rcd_uploadtime;
	private String rcd_content;
	private Integer rcd_thumb_amount;
	private Integer rcd_metoo_amount;
	private Integer rcd_status;
	private String path_no;
	private String mb_id;
	private List<CmtVO> comment;
	
	public RecordVO(){
		super();
	}

	public String getRcd_no() {
		return rcd_no;
	}

	public void setRcd_no(String rcd_no) {
		this.rcd_no = rcd_no;
	}

	public Date getRcd_uploadtime() {
		return rcd_uploadtime;
	}

	public void setRcd_uploadtime(Date rcd_uploadtime) {
		this.rcd_uploadtime = rcd_uploadtime;
	}

	public String getRcd_content() {
		return rcd_content;
	}

	public void setRcd_content(String rcd_content) {
		this.rcd_content = rcd_content;
	}

	public Integer getRcd_thumb_amount() {
		return rcd_thumb_amount;
	}

	public void setRcd_thumb_amount(Integer rcd_thumb_amount) {
		this.rcd_thumb_amount = rcd_thumb_amount;
	}

	public Integer getRcd_metoo_amount() {
		return rcd_metoo_amount;
	}

	public void setRcd_metoo_amount(Integer rcd_metoo_amount) {
		this.rcd_metoo_amount = rcd_metoo_amount;
	}

	public Integer getRcd_status() {
		return rcd_status;
	}

	public void setRcd_status(Integer rcd_status) {
		this.rcd_status = rcd_status;
	}

	public String getPath_no() {
		return path_no;
	}

	public void setPath_no(String path_no) {
		this.path_no = path_no;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public List<CmtVO> getComment() {
		return comment;
	}

	public void setComment(List<CmtVO> comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RecordVO [rcd_no=" + rcd_no + ", rcd_uploadtime=" + rcd_uploadtime + ", rcd_content=" + rcd_content
				+ ", rcd_thumb_amount=" + rcd_thumb_amount + ", rcd_metoo_amount=" + rcd_metoo_amount + ", rcd_status="
				+ rcd_status + ", path_no=" + path_no + ", mb_id=" + mb_id + "]";
	}

	
	
}
