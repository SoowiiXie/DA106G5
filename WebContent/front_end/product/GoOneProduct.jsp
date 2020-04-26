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
<jsp:include page="/front_end/product/ShopHomeBar3.jsp" flush="ture" />
<br>
<br>

   <div class="third">
    <div class="pd_mainPhoto"><img src="<%= request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}">
</div>
    <div class="pd_information">
      <div class="pd_name"><div class="pd_name_title" align="right"><p style="margin-top:15px;">商品名稱：</p></div><div class="pd_name_Imformation"><font size="+3">${productVO.pd_name}</font></div></div>
      <div class="pd_information_price">
        <div  class="pd_price_title" align="right" ><p style="margin-top:15px;">售價：</p></div>
        <div class="pd_price"> ${productVO.pd_price}元</div>
    
      <div class="pd_information_color">
        <div class="pd_color_title" align="right" >
          <p style="margin-top:15px;">收藏商品：</p>
        </div>
        <div class="pd_colorChoose">
       <div style="float:left;"> 
	<form method="POST" action="<%=request.getContextPath()%>/Pd_followServlet" name="form1">
	<input type="hidden" name="pd_no" value="${productVO.pd_no}"> 
	<input type="hidden" name="pd_name" value="${productVO.pd_name}"> 
	<input type="hidden" name="action" value="AddPd_follow">
	<input type="submit" name="Submit" value="加入商品收藏">
	
	</form>
	</div>
	 ${addType_follow}
         </div>
        </div>
      <div class="pd_information_size">
        <div class="pd_size_title" align="right">
          <p style="margin-top:15px;">商品尺寸：</p>	
         </div>
        <div class="pd_sizeChoose">  
        <form>
           <select size="1" name="pd_typeSize">
         <c:forEach var="pd_typeSize" items="${sizeList}" > 
          <option value="${pd_typeSize}">${pd_typeSize}
         </c:forEach>   
       </select>
         </form>
        </div>
        </div>
        
              <div class="pd_information_detail">
        <div class="pd_detail_title" align="right">
          <p style="margin-top:15px;">商品詳述：</p>
         </div>
        <div class="pd_detailChoose">  
       ${productVO.pd_detail}
       
        </div>
        </div>
        
        
      <div class="pd_information_count"><div class="pd_count_title" align="right"><p style="margin-top:25px;">商品數量：</p></div><div class="pd_count_qty"><form id='myform' method='POST' action='#'>
    <label for=""></label><input type='button' value='-' class='qtyminus' field='quantity'><!--
    --><input type='text' name='quantity' value='0' class='qty'><!--
--><input type='button' value='+' class='qtyplus' field='quantity'>件
</form></div></div>
      <div class="shopping_information_trolleyOrBuy">
        <div class="shopping_trolleyOrBuy_trolley"><form action="XXX"><button style="margin-top:30px;">加入購物</button></form></div>
        <div class="shopping_trolleyOrBuy_buy"><form action="XXX"><button style="margin-top:30px;">直接購買</button></form></div>
        
        </div>  
    </div>
    </div>
    </div>
<div class="sixth">商品內容 </div>
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
		<input type="submit" name="Submit" value="加入購物車"> <input
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
	<input type="submit" name="Submit" value="加入商品收藏">
	
	</form>
<a href="<%=request.getContextPath()%>/front_end/product/MemberLookSelfPd_follow.jsp">會員的商品收藏</a>
<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp?">回商城首頁</a>
	


</body>
</html>