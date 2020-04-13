package com.group_follow.model;

import java.util.*;

public interface Group_followDAO_interface {
          public void insert(Group_followVO group_followVO);
          public void update(Group_followVO group_followVO);
          public void delete(String grp_no);
          public Group_followVO findByPrimaryKey(String grp_no);
          
          public List<Group_followVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
