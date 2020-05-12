<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css" media="screen">
/* #page {
margin-right:8px;
margin-left:-8px;
margin-top:-8px;  
height:2000px;
width:1450px;
background-color:#FBFBFF;
} */
#first { 
height:100px;
width:100%;
background-color:#9CC7F6;
margin:5px 5px 5px 3px;
}


.search_form{
position: absolute;  
margin-top:30px;

}
.backhome{
height:100px;
width:20%; 
background-color:#9CC7F6;
position: static; 
line-height:100px; 
float:left;

}

.managerProduct{
height:100px;
width:20%; 
background-color:#9CC7F6;
position: static; 
line-height:100px; 
float:left;

}
.addProduct{
height:100px;
width:20%; 
background-color:#9CC7F6;
position: static; 
line-height:100px; 
float:left;

}
.managerOrders{
height:100px;
width:20%; 
background-color:#9CC7F6;
position: static; 
line-height:100px; 
float:left;

}
.managerCoupon{
height:100px;
width:20%; 
background-color:#9CC7F6;
position: static; 
line-height:100px; 
float:left;

}



div img{
    width: 100%;
    height:100%;
/*     object-fit:cover; */
}
.Topmenuin{
  position: relative;

}
.aaa{
text-align: center;
width:200px;
height:300px;
}
.bbb{
text-align: center;
width:200px;
height:200x;
}
.Product{
width:300px;
float:left;
padding:0;
margin:5px;
}
.foot{
clear:both;

}
.context{
width:930px;
text-align:center;
margin:10px auto;
}
font{
font-family:Mamelon;

}

</style>

<title>ShopHomeBar</title>
</head>

<body>

<div id="first">
<div>
      <div class="logo">
        <div style="width:150px; float:left;">
         
       
        </div>
        <div>
          <font size="5px" color="#FDFF99" style="font-style:italic;"></font>
        </div>
      </div>
    
    <div class="goToShopHome"></div>
      <div class="backhome" align="center"><a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp" style="text-decoration:none;">
        <font color="#FDFF99" size="5">商城頁面</font></a>
      </div>
    <div class="addProduct" align="center"><a href="<%=request.getContextPath()%>/ProductServlet?action=getIncludePath" style="text-decoration:none;">
      <font color="#FDFF99" size="5">增加商品</font></a></div> 
    <div class="managerProduct" align="center">
      	<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=" style="text-decoration:none;" style="text-decoration:none;">
      	  <font color="#FDFF99" size="5">管理商品</font></a>
    </div> 
    
    <div class="managerOrders" align="center">
      	<a href="<%=request.getContextPath()%>/OrdersServlet?action=getAllList" style="text-decoration:none;">
      	  <font color="#FDFF99" size="5">管理訂單</font></a>
    </div> 
    
    <div class="managerCoupon" align="center">
      	<a href="<%=request.getContextPath()%>/CpGetServlet?action=getCouponManagerIncludePath" style="text-decoration:none;">
      	  <font color="#FDFF99" size="5">管理優惠券</font></a>
    </div> 
 </div>     	
</div>
  
  
  
  
  
  
  
  
<%--     <div id="first">
     <div class="logo" align="center">
     <a href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp"> <img src="<%=request.getContextPath()%>/img/LogoNoBack.png" style="border-radius:5px;"></a></div>
     
     <div class="search_pd" >
      <div class="search_form">
   <font style="font-size:1.0cm;" color="#FDFF99">商城管理</font>
      </div></div>
      <div class="backhome" align="center"><a
		href="<%=request.getContextPath()%>/back_end/product/addProduct.jsp" style="text-decoration:none;"><font color="#FDFF99" >
		增加商品</font></a></div>
      <div class="pd_follow" align="center"><a
		href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=" style="text-decoration:none;"><font color="#FDFF99">管理商品</font></a></div> 
      <div class="shopping_trolley" align="center">
      	<a href="<%=request.getContextPath()%>/OrdersServlet?action=getAllList" style="text-decoration:none;"><font color="#FDFF99">管理訂單
</font></a>
      	</div> 
       <div class="memberLogin" align="center"><a
		href="<%=request.getContextPath()%>/back_end/product/CouponManage.jsp" style="text-decoration:none;"><font color="#FDFF99" >管理優惠券</font></a></div>
      <div class="member" align="center"><font color="#FDFF99">分類管理</font></div>
    </div> --%>


     
 

 <script src="<%=request.getContextPath()%>/plug-in/bootstrap/jquery/jquery-3.4.1.min.js"></script>
 <script src="<%=request.getContextPath()%>/plug-in/Semantic-UI/semantic.min.js"></script>
    <script src="<%=request.getContextPath()%>/plug-in/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/plug-in/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
