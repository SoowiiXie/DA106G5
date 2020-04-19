package com.pd_type.model;


public class Pd_typeVO implements java.io.Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
  private String pd_typeNo;
  private String pd_typeName;
  private byte[] cp_pic;
  
  public Pd_typeVO() {
	  pd_typeNo = "";
	  pd_typeName = "";
	  cp_pic = null;
  }
  
public String getPd_typeNo() {
	return pd_typeNo;
}
public void setPd_typeNo(String pd_typeNo) {
	this.pd_typeNo = pd_typeNo;
}
public String getPd_typeName() {
	return pd_typeName;
}
public void setPd_typeName(String pd_typeName) {
	this.pd_typeName = pd_typeName;
}
public byte[] getCp_pic() {
	return cp_pic;
}
public void setCp_pic(byte[] cp_pic) {
	this.cp_pic = cp_pic;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
  

  


  
	
	
}
