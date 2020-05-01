<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.* "%>
<%@ page import="com.pd_follow.model.*, com.product.model.* "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css" media="screen">
.aaa {
	text-align: center;
	width: 200px;
	height: 300px;
}

.div img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.followlist {
	cellpadding: "10";
	cellspacing: "5";
	padding-bottom: 100px;
	
}

  table {

	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	
  }
  table, th, td {
    border: 3px solid 	#FFFFFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
  .buttonBar{
width:100%;
background-color:#3960D0;
height:100px;
margin-top:100;
}
#Footer {
　height: 100px;
　position: relative;
　margin-top: -100px;
}
</style>
<%
	String mb_id = (String) session.getAttribute("mb_id");
	Pd_followService pd_followService = new Pd_followService();

	List<Pd_followVO> list = pd_followService.searchMemberAllPdFollow(mb_id);

	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="productService" scope="page"
	class="com.product.model.ProductService" />
<jsp:useBean id="pd_typeService" scope="page"
	class="com.pd_type.model.Pd_typeService" />
<meta charset="UTF-8">
<title>商品收藏</title>
</head>
<body>
	<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />
	  
		<div style="width:100%; height:100px; border-style:solid;"></div>
		  <div align="center">
		    <font size="+3">您的商品收藏內容如下：</font>
		  </div>
		<Hr>
	<div  style=" border-style:solid; vertical-align:bottom;" >
	<div>
	<table style="margin-left:460px;" >
	<tr>
	<td style="Vertical-align:bottom;"><%@ include file="pagePd_follow1.file"%><%@ include file="pagePd_follow2.file"%></td>
	</tr></table>
	</div>
	</div>
		
	
	
	
	<div class="listFloow" style=" border-style:solid; height:600px">
	
		<form method="POST"
			action="<%=request.getContextPath()%>/Pd_followServlet" name="form1">
			<table class="followlist" border="1" cellpadding="3" cellspacing="3" style="border: 3px solid #FFFFFF; margin-left:460px;">

				<tr bgcolor="#999999" align="center">
					<th width="100" ><font color="black">商品圖片</font></th>

					<th width="100" align="center" ><font color="black">商品名稱</font></th>
					<th width="100" align="center"><font color="black">商品價格</font></th>
					<th width="100"></th>


				</tr>

				<c:forEach var="pd_followVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">

					<tr bgcolor=#C4E1FF>
						<td class="aaa" style="width: 50px; height: 125px;"><a
							href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${pd_followVO.pd_no}'><img
								src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${pd_followVO.pd_no}"
								></a></td>
						<td><a
							href='<%=request.getContextPath()%>/ShoppingServlet?action=findOneProduct&pd_no=${pd_followVO.pd_no}'>${productService.findOneProduct(pd_followVO.pd_no).pd_name}</a></td>
						<td align="center"><font color="black">${productService.findOneProduct(pd_followVO.pd_no).pd_price}元</font></td>
						<td align="center"><input class="btn btn-primary btn-sm" type="submit" name="Submit" 
							value="刪除" style="background: #3960D0"> <input type="hidden" name="pd_no"
							value="${pd_followVO.pd_no}"> <input type="hidden"
							name="action" value="DeleteOnePd_follow"></td>

					</tr>

				</c:forEach>
			</table>
		
		</form>
	</div>
	 <div id="Footer" class="buttonBar" style="margin-top:100px;" ></div>
</body>
</html>