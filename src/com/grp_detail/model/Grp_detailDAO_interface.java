package com.grp_detail.model;

import java.util.*;

public interface Grp_detailDAO_interface {
          public void insert(Grp_detailVO empVO);
          public void update(Grp_detailVO empVO);
          public void delete(Integer empno);
          public Grp_detailVO findByPrimaryKey(Integer empno);
          public List<Grp_detailVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
