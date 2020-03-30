package com.vain.product.model;
import java.util.*;
//此為尚未上傳圖片版本
public interface ProductDAO_interface {
	public int insert(ProductVO productVO);  //輸入商品資料
    public int update(ProductVO productVO);  //更改商品資料
    public int delete(String pd_no); //刪除商品資料
    public int changeStatus(ProductVO productVO);//修改商品狀態
    public ProductVO findByPrimaryKey(String pd_no);//搜尋該商品資料
    public List<ProductVO> getAll();//搜尋所有商品資料
    public List<ProductVO> useTypeSearchProducts(String pd_typeNo); //用類別搜尋所有資料
    
    
    
    
    
}
	