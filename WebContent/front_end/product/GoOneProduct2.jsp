<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>


<%
	 ProductVO productVO = (ProductVO) request.getAttribute("productVO");  

      request.setAttribute("productVO", productVO);
%>
 
<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${productVO.pd_name}</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<style type="text/css" media="screen"> 

.buttonBar{
width:100%;
background-color:#3960D0;
height:100px;
margin-top:100;
}
</style>

</head>

<body>
<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />

<div style="width:100%; height:100px;"></div>
<div style="width:100%; height:100px;"></div>


<div style="height:700px;">
<div id="picimformation" style="float:left; margin-left:20%;width:30%; height:100%;">
<div style="width:80%;height:60%;margin-left:10%;overflow:hidden;">
  <div id="mainPic" style=" height:100%;overflow:hidden;">
       <img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}" alt="">
  </div>
    </div>
       <div style="width:100%;height:30%;float:left;overflow:hidden" >
           <div style="float:loft;">
           <div style="width:30%;height:100%; overflow:hidden;float:left; margin:10px 1% 10px 1.25%;" >
           <img onclick="showBig()" id="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}&&action=pd_pic"  
           src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}&&action=pd_pic" alt="" >
           </div></div>
            <div style="float:loft;">
           <div style="width:30%;height:100%; overflow:hidden;float:left; margin:10px 1% 10px 1.25%;">
           <img onclick="showBig()" id="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic2" 
           src="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic2" alt="">
           </div></div>
           <div style="float:loft;">
           <div  style="width:30%;height:100%; overflow:hidden;float:left;margin:10px 2.5% 10px 1%;" >
           <img onclick="showBig()" id="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic3" 
           src="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic3" alt="">
           </div>
           </div>
       </div>    
</div>
<div id="picimformation" style="float:right; margin-right:20%;width:30%; height:100%;">
   <form method="POST" action="<%=request.getContextPath()%>/ShoppingServlet" name="form1">
  <table style="width:100%;height:500px; ">
    <tr>
      <td width="10%" height="10%" align="right"><font color="black"size="5">商品名稱：</font></td>
      <td align="left"><font color="black"size="5">${productVO.pd_name}</font></td>
    </tr>
    <tr>
      <td width="20%" height="20%" align="right"><font color="black" size="5">商品價格：</font></td>
      <td height="20%" align="left"><font size="7" color="red">${productVO.pd_price}元</font></td>
   </tr>
   <tr>
   
      <td height="10%" align="right"><font color="black" size="5">商品數量：</font></td>
      
      <td  height="10%" align="left"><select name="pd_quantity">
						<option value="1">1
						<option value="2">2
						<option value="3">3
						<option value="4">4
						<option value="5">5
						<option value="6">6
						<option value="7">7
						<option value="8">8
						<option value="9">9
						<option value="10">10
				</select></td>
   </tr>

   <tr>
     <td align="right" height="10%"><font color="black"size="5">商品尺寸：</font></td>
     <td align="left" height="10%"><select size="1" name="pd_typeSize">
         <c:forEach var="pd_typeSize" items="${sizeList}" > 
          <option value="${pd_typeSize}">${pd_typeSize} Size
         </c:forEach>   
       </select></td>
   </tr>
   
      <tr>
     <td width="20%" height="10%" align="right"><font color="black"size="5">商品類別：</font></td>
     <td width="300" height="40" align="left"><font color="black"size="5">${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</font></td>
   </tr>
   
   <tr>
     <td  style="vertical-align:text-top;" align="right"><font color="black"size="5">商品詳述：</font></td>
     <td  style="vertical-align:text-top;align:" align="left"><font color="black"size="5">${productVO.pd_detail}</font></td>
   </tr>

   </table>
       <table style="width:100%;; height:80px;">
         <tr>
           <td align="center" >
            
                 <input class="btn btn-secondary btn-lg" type="submit" name="Submit" 
							value="加入購物車" style="background: #3960D0"> 
                 <input type="hidden" name="pd_no" value="${productVO.pd_no}"> 
                 <input type="hidden" name="pd_name" value="${productVO.pd_name}"> 
                 <input type="hidden" name="pd_price" value="${productVO.pd_price}">
		         <input type="hidden" name="pd_typeNo" value="${productVO.pd_typeNo}">
		         <input type="hidden" name="pd_typeSize" value="${pd_typeSize}">
		         <input type="hidden" name="action" value="AddProductToCar">
		    
		  </td>
</form>
		  <td align="center">
		  
		  
	<form method="POST" action="<%=request.getContextPath()%>/Pd_followServlet" name="form1">
	<input type="hidden" name="pd_no" value="${productVO.pd_no}"> 
	<input type="hidden" name="pd_name" value="${productVO.pd_name}"> 
	<input type="hidden" name="action" value="AddPd_follow">
	<input class="btn btn-secondary btn-lg" type="submit" name="Submit" 
							value="加入商品收藏" style="background: #3960D0">
	
	</form>
	      </td>
       </tr>
    </table>
</div>
</div>

<script>
		function showBig(){
			var picinfo=event.target.id;
			var bigPic=document.getElementById('mainPic').innerHTML="<img src="+picinfo+">"
		}
	</script>

  
  <div class="buttonBar" style="margin-top:100px"></div>

<script>

var pd_follow= ${param.pd_follow};
if (pd_follow == true){
 Swal.fire(
   '',  
   '商品收藏成功!',
   'success'
   )
}
</script>

<script>
var pd_follow= ${param.pd_follow};
if (pd_follow == false){
 Swal.fire(
   '',  
   '商品取消收藏!',
   'success'
   )
}
</script>
<script>
var addToShopCar= ${param.addToShopCar};
if (addToShopCar == true){
 Swal.fire(
   '',  
   '加入購物車!',
   'success'
   )
}
</script> 

</body>
</html>