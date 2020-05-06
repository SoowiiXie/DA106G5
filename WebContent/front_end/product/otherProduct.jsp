<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
       


	<%
		 ProductVO productVO = (ProductVO)request.getAttribute("productVO");
	
	        if(productVO!=null){
	        	String pd_no = productVO.getPd_no();
	    		ProductService productService = new ProductService();
	    		ProductVO getProductInformation = productService.findOneProduct(pd_no);
	    		List<ProductVO> thisProductTypelist = productService
	    				.useTypeSearchProducts(getProductInformation.getPd_typeNo());

	    		List<String> otherProductNo = null;
	    		otherProductNo = new ArrayList<String>();

	    		for (int i = 0; i < 3; i++) {

	    			int randonProduct = (int) (Math.random() * thisProductTypelist.size());

	    			String oederPd_no = thisProductTypelist.get(randonProduct).getPd_no();

	    			if (oederPd_no.equals(pd_no)) {

	    				thisProductTypelist.remove(randonProduct);

	    				i--;
	    			} else if (!oederPd_no.equals(pd_no)) {

	    				otherProductNo.add(oederPd_no);

	    				thisProductTypelist.remove(randonProduct);

	    			}

	    		}
	    		
	    		pageContext.setAttribute("otherProductNo", otherProductNo);
	        }
	
	%>
<c:forEach var="otherProductNoVO" items="${otherProductNo}">
		<div class="context" style="margin-top:100px; background-color:#858796;">
			<table border="1" class="Product" >

				<tr>
					<td class="aaa"><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${otherProductNoVO.pd_no}'><img
							src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${otherProductNoVO.pd_no}"
							></a></td>
				</tr>
				<tr>
					<td class="PdName"><a
						href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${otherProductNoVO.pd_no}'>${otherProductNoVO.pd_name}</a></td>
				<tr>
					<td class="PdPrice"><font color="red"><b>${otherProductNoVO.pd_price} 元</b></font></td>

				</tr>
				<tr>
					<td class="PdPrice">${pd_typeService.searchType(otherProductNoVO.pd_typeNo).pd_typeName}</td>
				</tr>

			</table>
		</div>
	</c:forEach> 



</body>
</html>