package com.group_rpt.model;

import java.util.*;

public interface Group_rptDAO_interface {
          public void insert(Group_rptVO group_rptVO);
          public void update(Group_rptVO group_rptVO);
          public void delete(String group_rpt_no);
          public Group_rptVO findByPrimaryKey(String group_rpt_no);
          public List<Group_rptVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
