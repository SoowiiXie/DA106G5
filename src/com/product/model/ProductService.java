package com.product.model;

import java.util.List;



public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {

		dao = new ProductDAO();

	}

	public ProductVO addProduct(String pd_name, Integer pd_price, String pd_detail, String pd_typeNo) {

		ProductVO productVO = new ProductVO();

		productVO.setPd_name(pd_name);
		productVO.setPd_price(pd_price);
		productVO.setPd_detail(pd_detail);
		productVO.setPd_typeNo(pd_typeNo);
		dao.addProduct(productVO);
		
		return productVO;
		

	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
		
	}
	
	public ProductVO updateProduct(String pd_no, String pd_name, Integer pd_price, String pd_detail, String pd_typeNo,Integer pd_status) {
		
		ProductVO productVO = new ProductVO();
		productVO.setPd_no(pd_no);
		productVO.setPd_name(pd_name);
		productVO.setPd_price(pd_price);
		productVO.setPd_detail(pd_detail);
		productVO.setPd_typeNo(pd_typeNo);
		productVO.setPd_status(pd_status);
		dao.updateProductInformation(productVO);
		return productVO;
	}
	
	public ProductVO findOneProduct(String pd_no) {
		return dao.findOneProduct(pd_no);
	}
	
	public void deleteProduct(String pd_no) {
		dao.deleteProduct(pd_no);
	}
	
}
