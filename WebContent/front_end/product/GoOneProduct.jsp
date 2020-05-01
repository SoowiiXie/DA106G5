<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>


<%
	 ProductVO productVO = (ProductVO) request.getAttribute("productVO");  
%>
 
<jsp:useBean id="pd_typeService" scope="page" class="com.pd_type.model.Pd_typeService" />
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="UTF-8">
<style type="text/css" media="screen"> 
.third{
margin-top:70px; 
height:500px;
width:100%;  
background-color:#FBFBFF;

}
.pd_mainPhoto{
margin-left: 150px;
width: 500px;
height: 500px;
background-color:#FFD9EC;  

}
.pd_littlePhoto{
margin-top:328px;
width: 500px;
height:150px;
background-color:#B3D9D9; 
text-align: "center";

}
.pd_information{
margin-top:-500px;
margin-left: 650px;
width: 650px;
height: 500px;
background-color:#FFDAC8;

}
.pd_name{
width: 650px;
height: 50px;
background-color:#FFF4C1;
overflow: hidden;
} 
.pd_name_title{
width: 100px;
height: 50px;
background-color:#B3D9D9;
float: left;
}
.pd_name_Imformation{
width: 550px;
height: 50px;
background-color:#FFDAC8;
margin-left:100px; 

}
.pd_information_price{
width: 650px;
height: 50px;
background-color:#EBD6D6;


}
.pd_price_title{
width: 100px;
height: 50px;
background-color:#B3D9D9;
float: left;

}
.pd_price{
width: 550px;
height: 50px;
background-color:#FF77FF;
margin-left:100px; 
vertical-align: middle;
}
.pd_information_color{
width: 650px;
height:50px;
background-color:#A23400;
}
.pd_color_title{
width: 100px;
height:50px;
background-color:#FF9797;
float: left;
}
.pd_colorChoose{
width: 550px;
height:50px;
background-color:#AAAAFF;
margin-left:100px;
}
.pd_information_size{
width: 650px;
height:50px;
background-color:#A23400;
}
.pd_size_title{
width: 100px;
height:50px;
background-color:#FFC78E;
float: left;
}
.pd_sizeChoose{
width: 550px;
height:50px;
background-color:#80FFFF;
margin-left:100px;
}
.pd_information_detail{
width: 650px;
height: 125px;
background-color:#EBD6D6;
}
.pd_detail_title{
width: 100px;
height:125px;
background-color:#FFC78E;
float: left;
}
.pd_detailChoose{
width: 550px;
height:122px;
background-color:#80FFFF;
margin-left:100px;
}
.pd_information_count{
 width:650px;
height:75px;
background-color:#E2C2DE;
} 
.pd_count_title{
width:100px;
height:75px;
background-color:#FFAD86;
float: left;
} 
.pd_count_qty{
width:550px;
height:75px;
background-color:#FFB5B5;
margin-left:100px;
text-align: center;
} 
.shopping_information_trolleyOrBuy{
 width:650px;
height:100px;
background-color:#FF9797;
} 
.shopping_trolleyOrBuy_trolley{
width:325px;
height:100px;
background-color:#FFFF6F;
float: left;
text-align: center;

}
.shopping_trolleyOrBuy_buy{
margin-left: 325px;
width:325px;
height:100px;
background-color:#EAC100;
text-align: center;
}
.qty {
  width: 40px;
  height: 35px;
  text-align: center;
  border: 0;
  border-top: 1px solid #aaa;
  border-bottom: 1px solid #aaa;
}

input.qtyplus {
  width: 25px;
  height: 35px;
  border: 1px solid #aaa;
  background: #f8f8f8;
}

input.qtyminus {
  width: 25px;
  height: 35px;
  border: 1px solid #aaa;
  background: #f8f8f8;
}

</style>
<title>${productVO.pd_name}</title>
</head>
<body>
<jsp:include page="/front_end/product/ShopHomeBar.jsp" flush="ture" />
<br>
<br>


   
<div style="float:left">
  <table border="1" style="margin-top:200px;">
    <tr>
      <td width="500" height="500">
        <div align="center" style="float:left" >
          <img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}" style="width:100% ;height:100% align:center;">
        </div>
      </td>
    </tr>
  </table>
</div>
<div style="margin-top:200px;">
<form method="POST" action="<%=request.getContextPath()%>/ShoppingServlet" name="form1">
  <table  border="1">
    <tr>
      <td width="100" height="40" align="right">商品名稱：</td>
      <td width="300" height="40" align="left">籃球</td>
    </tr>
    <tr>
      <td width="100" height="40" align="right">商品價格：</td>
      <td width="300" height="40" align="left"><font size="7">8888元</font></td>
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
     <td width="100" height="40" align="right">商品尺寸：</td>
     <td width="300" height="40" align="left"><select size="1" name="pd_typeSize">
         <c:forEach var="pd_typeSize" items="${sizeList}" > 
          <option value="${pd_typeSize}">${pd_typeSize} Size
         </c:forEach>   
       </select></td>
   </tr>
   <tr>
     <td width="100" height="40" style="vertical-align:text-top;" align="right">商品詳述：</td>
     <td width="300" height="192" style="vertical-align:text-top;align:" align="left">每位跑者的實力不一、狀態各異，Garmin 最懂你心，發展全系列運動穿戴。從使用方便的 GPS 訓練到高階的鐵人三項強力培訓，Garmin 有如陪訓員般貼心守護。您只需設定目標，便可勇往直前。</td>
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
 
<br> 

<br>
<br>
<br>
 ${addType_follow}
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="POST" action="<%=request.getContextPath()%>/ShoppingServlet" name="form1">
		<table>
            <tr>
            <td width="100px">商品圖片：</td>
             <td width="100px"><img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}" width="300px"></td>
			</tr>
			<tr>
				<td width="100">商品類別：</td>
				<td width="100">${pd_typeService.searchType(productVO.pd_typeNo).pd_typeName}</td>
			</tr>


			<tr>
				<td width="100">商品名稱：</td>
				<td width="100">${productVO.pd_name}</td>
			</tr>
			<tr>
				<td width="100">商品價格：</td>
				<td width="100">${productVO.pd_price}</td>
			</tr>
			<tr>
				<td width="100">商品詳述：</td>
				<td width="400">${productVO.pd_detail}</td>
			</tr>
			
			<tr>
			<td width="100">商品尺寸：</td>
			<td>      <select size="1" name="pd_typeSize">
         <c:forEach var="pd_typeSize" items="${sizeList}" > 
          <option value="${pd_typeSize}">${pd_typeSize}
         </c:forEach>   
       </select></td>
			
			</tr>
			
			
			<tr>
				<td width="100">數量：</td>
				<td><select name="pd_quantity">
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
				</select>
			</tr>

		</table>
		<input class="btn btn-secondary btn-lg" type="submit" name="Submit" 
							value="加入購物車" style="background: #3960D0"> <input
			type="hidden" name="pd_no" value="${productVO.pd_no}"> <input
			type="hidden" name="pd_name" value="${productVO.pd_name}"> <input
			type="hidden" name="pd_price" value="${productVO.pd_price}">
		<input type="hidden" name="pd_typeNo" value="${productVO.pd_typeNo}">
		<input type="hidden" name="pd_typeSize" value="${pd_typeSize}">
		<input type="hidden" name="action" value="AddProductToCar">
	</form>
	
	<form method="POST" action="<%=request.getContextPath()%>/Pd_followServlet" name="form1">
	<input type="hidden" name="pd_no" value="${productVO.pd_no}"> 
	<input type="hidden" name="pd_name" value="${productVO.pd_name}"> 
	<input type="hidden" name="action" value="AddPd_follow">
	<input class="btn btn-secondary btn-lg" type="submit" name="Submit" 
							value="加入商品收藏" style="background: #3960D0">
	
	</form>
<a href="<%=request.getContextPath()%>/front_end/product/MemberLookSelfPd_follow.jsp">會員的商品收藏</a>
<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp?">回商城首頁</a>
	
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