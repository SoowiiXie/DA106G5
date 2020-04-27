package com.group_follow.model;

import java.util.*;

import com.group_detail.model.Grp_detailVO;

public interface Group_followDAO_interface {
          public void insert(Group_followVO group_followVO);
          public void update(Group_followVO group_followVO);
          public void delete(String grp_no);
          public List<Group_followVO> findByPrimaryKey(String mb_id);
          public Group_followVO findByPrimaryKeyByMb_id(String mb_id);                    
          public List<Group_followVO> getAll();
          //查詢該揪團有多少人
          public int totalFollowPeople(String grp_no);
          //查詢該會員參加多少揪團
          public int totalFollowGroup(String mb_id);
          
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
