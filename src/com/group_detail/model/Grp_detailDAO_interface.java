package com.group_detail.model;

import java.util.*;

public interface Grp_detailDAO_interface {
          public void insert(Grp_detailVO grp_detailVO);
          public void update(Grp_detailVO grp_detailVO);
          public void delete(String mb_id);
          public Grp_detailVO findByPrimaryKey(String mb_id);
          public List<Grp_detailVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map);  
}