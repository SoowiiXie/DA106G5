package com.rcd_rpt.model;

import java.util.List;

public interface Rcd_rptDAO_interface {
	 public void insert(Rcd_rptVO rcd_rptVO);
     public void update(Rcd_rptVO rcd_rptVO);
     public void delete(String rcd_rpt_no);
     public Rcd_rptVO findByPrimaryKey(String rcd_rpt_no);
     public List<Rcd_rptVO> getAll();
}
