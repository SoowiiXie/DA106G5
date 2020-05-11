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

<style type="text/css" media="screen"> 

.buttonBar{
width:100%;
background-color:#3960D0;
height:100px;
margin-top:100;
}
</style>

<title>${productVO.pd_name}</title>
</head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<body>
<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />
<div style="width:100%; height:100px; border-style: solid; border-color:red; "></div>
<div style="width:100%; height:100px; border-style: solid; border-color:red; "></div>

<div style="border-style: solid; border-color:black; height:700px; width:70%; margin-left:15%;">
<div style="border-style: solid; border-color:red; width:70%; height:700px; margin-left:15%;">
<div id="picimformation" style="	float:left; border-style: solid; border-color:green; width:500px; height:100%;">
<div style="width:60%;height:60%; border-style: solid; border-color:red; margin-left:20%;overflow:hidden;" >
  <div id="mainPic" style=" overflow:hidden;  margin-top:20%;">
       <img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}" alt="">
  </div>
    </div>
       <div style="width:100%;height:30%;overflow:hidden;" >   
           <div style="overflow:hidden; width:30%;height:100%; background-color:red; float:left; margin:10px 1.25% 10px 2.5%;" >
           <img onclick="showBig()" id="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}&&action=pd_pic"  
           src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}&&action=pd_pic" alt="" >
           </div>
           <div style="width:30%;height:100%;  background-color:black;float:left; margin:10px 1% 10px 1.25%;">
           <img onclick="showBig()" id="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic2" 
           src="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic2" alt="">
           </div>
           <div  style="width:30%;height:100%; border-color:green; background-color:#3960D0;float:left;margin:10px 2.5% 10px 1%;" >
           <img onclick="showBig()" id="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic3" 
           src="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic3" alt=""></div>
       </div>    
</div>
<div style="border-style: solid; border-color:;">
<form method="POST" action="<%=request.getContextPath()%>/ShoppingServlet" name="form1">
  <table border="1" >
    <tr>
      <td width="100" height="40" align="right">商品名稱：</td>
      <td width="300" height="40" align="left">${productVO.pd_name}</td>
    </tr>
    <tr>
      <td width="100" height="40" align="right">商品價格：</td>
      <td width="300" height="40" align="left"><font size="7">${productVO.pd_price}</font></td>
   </tr>
   <tr>
   
      <td width="100" height="40" align="right">商品數量：</td>
      
      <td width="300" height="40" align="left"><select name="pd_quantity">
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
     <td align="right">商品尺寸：</td>
     <td align="left"><select size="1" name="pd_typeSize">
         <c:forEach var="pd_typeSize" items="${sizeList}" > 
          <option value="${pd_typeSize}">${pd_typeSize} Size
         </c:forEach>   
       </select></td>
   </tr>
   <tr>
     <td width="100" height="40" style="vertical-align:text-top;" align="right">商品詳述：</td>
     <td width="300" height="192" style="vertical-align:text-top;align:" align="left">${productVO.pd_detail}</td>
   </tr>
   <tr>
     <td width="100" height="40" align="right">商品收藏：</td>
     <td width="300" height="40" align="left">收藏連結</td>
   </tr>
   </table>
       <table style="width:400px; height:80px;" border="1">
         <tr>
           <td>
            
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
		  <td>
		  
		  
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

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	

	 


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

<%-- <div style="width:400px;height:500px; background-color:blue; margin-top:200px;" >


    <div id="mainPic" style="width:60%;height:60%; background-color:green;margin-left:20%;">
       <img  style="width:100%; height:100%;margin:auto;"src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}" alt="">
    </div>
    
       <div style="width:100%;height:30%;">   
           <div  style="width:30%;height:100%; background-color:red; float:left; margin:10px 1.25% 10px 2.5%" >
           <img onclick="showBig()" id="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}&&action=pd_pic"  
           src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}&&action=pd_pic" alt="" >
           </div>
           <div    style="width:30%;height:100%;  background-color:black;float:left; margin:10px 1% 10px 1.25%">
           <img onclick="showBig()" id="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic3" 
           src="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic3" alt="">
           </div>
           <div  style="width:30%;height:100%; border-color:green; background-color:#3960D0;float:left;margin:10px 2.5% 10px 1%" >
           <img onclick="showBig()" id="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic4" 
           src="<%= request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic4" alt=""></div>
       </div>    
</div> --%>

</body>
</html>