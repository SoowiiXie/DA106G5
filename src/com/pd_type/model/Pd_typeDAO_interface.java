package com.pd_type.model;
import java.util.*;

public interface Pd_typeDAO_interface {
         
	public int addProductType(Pd_typeVO pd_type);  //新增商品類別
    public int updateTypeName(Pd_typeVO pd_type);  //修改類別名稱
    public int delete(String pd_typeNo);   //刪除類別名稱
    public Pd_typeVO searchType(String pd_typeNo);//搜尋類別  
    public List<Pd_typeVO>getAll();//列出所有類別
}
	