package com.product.model;
import java.util.*;


//此為尚未上傳圖片版本
public interface ProductDAO_interface {
	public int addProduct(ProductVO productVO);  //輸入商品資料
    public int updateProductInformation(ProductVO productVO);  //更改商品資料
    public int deleteProduct(String pd_no); //刪除商品資料
    public int changeStatus(ProductVO productVO);//修改商品狀態
    public ProductVO findOneProduct(String pd_no);//搜尋該商品資料
    public List<ProductVO> getAll();//搜尋所有商品資料
    public List<ProductVO> useTypeSearchProducts(String pd_typeNo); //用類別搜尋所有資料
    public List<ProductVO> listOnTheMarket(Integer pd_status); //用商品狀態搜尋物品
	public List<ProductVO> superGetAll(Map<String, String[]> map);//萬用複合查詢 
    public int updatePic(String pd_no, String pic_path); //上傳圖片
    public List<ProductVO> OnMarketWithPd_typeNO(Integer pd_status, String pd_typeNo); //用商品狀態以及商品種類列出商品
    public byte[] search_pd_pic(String pd_no);//用商品編號回傳該圖片byte陣列
   
 
}
	