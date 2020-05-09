package com.product.model;

import java.util.List;
import java.util.Map;



public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {

		dao = new ProductDAO();

	}

	public ProductVO addProduct(String pd_name, Integer pd_price, String pd_detail, String pd_typeNo ,byte[] pd_pic) {

		ProductVO productVO = new ProductVO();

		productVO.setPd_name(pd_name);
		productVO.setPd_price(pd_price);
		productVO.setPd_detail(pd_detail);
		productVO.setPd_typeNo(pd_typeNo);
		productVO.setPd_pic(pd_pic);
		dao.addProduct(productVO);
		
		return productVO;
		

	}
	
	public ProductVO addProduct3(ProductVO productVO) {

		ProductVO productVO2 = new ProductVO();

		productVO2.setPd_name(productVO.getPd_name());
		productVO2.setPd_price(productVO.getPd_price());
		productVO2.setPd_detail(productVO.getPd_detail());
		productVO2.setPd_typeNo(productVO.getPd_typeNo());
		productVO2.setPd_pic(productVO.getPd_pic());
		productVO2.setPd_pic2(productVO.getPd_pic2());
		productVO2.setPd_pic3(productVO.getPd_pic3());
		productVO2.setPd_pic4(productVO.getPd_pic4());
		dao.addProduct(productVO);
		
		return productVO;
		

	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
		
	}
	
	public ProductVO updateProduct(String pd_no, String pd_name, Integer pd_price, String pd_detail, String pd_typeNo,Integer pd_status, byte[] pd_pic) {
		
		ProductVO productVO = new ProductVO();
		productVO.setPd_no(pd_no);
		productVO.setPd_name(pd_name);
		productVO.setPd_price(pd_price);
		productVO.setPd_detail(pd_detail);
		productVO.setPd_typeNo(pd_typeNo);
		productVO.setPd_status(pd_status);
		productVO.setPd_pic(pd_pic);
		dao.updateProductInformation(productVO);
		return productVO;
	}
	
	public ProductVO findOneProduct(String pd_no) {
		return dao.findOneProduct(pd_no);
	}
	
	public void deleteProduct(String pd_no) {
		dao.deleteProduct(pd_no);
		
	}
	
	public List<ProductVO> listOnTheMarket(Integer pd_status){
		return dao.listOnTheMarket(pd_status);
		
	}
	
	 public List<ProductVO> useTypeSearchProducts(String pd_typeNo){
		 
		 return dao.useTypeSearchProducts(pd_typeNo);
	 }
	 public List<ProductVO> OnMarketWithPd_typeNO(Integer pd_status, String pd_typeNo){
		 
		       return dao.OnMarketWithPd_typeNO(pd_status, pd_typeNo);
	 }
	
	 public byte[] search_pd_pic(String pd_no) {
		 
		 return dao.search_pd_pic(pd_no);
	 }
public int changeStatus(ProductVO productVO) {
		 
		 return dao.changeStatus(productVO);
		  
	 }
public List<ProductVO> superGetAll(Map<String, String[]> map){
	
	 return dao.superGetAll(map);
}
}
