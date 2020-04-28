package com.grouper.model;

import java.util.*;

public interface GrouperDAO_interface {
          public void insert(GrouperVO grouperVO);
          public void update(GrouperVO grouperVO);
          public void delete(String grp_no);
          public GrouperVO findByPrimaryKey(String grp_no);          
          public List<GrouperVO> getAll();
          public List<GrouperVO> getAllByMb_id(String mb_id);          
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
        public List<GrouperVO> getAll(Map<String, String[]> map); 
}
