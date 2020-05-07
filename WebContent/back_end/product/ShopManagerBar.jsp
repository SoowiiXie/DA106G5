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
background-color:#FF004C;
/* border-style: solid;
border-color:#3960D0; */
 */
}
.logo{
height:100px;
width:30%; 
background-color:#FF004C;
position: static; 
line-height:100px; 
float:left;
/* border-style: solid;
border-color:#3960D0;  */
}
.goToShopHome{
height:100px;
width:15%; 
background-color:#FF004C;
float:left;
position: relative;
/* border-style: solid;
border-color:#3960D0;  */
}
.search_form{
position: absolute;  
margin-top:30px;
/* border-style: solid;
border-color:#3960D0;  */
}
.backhome{
margin-left:10%;
height:100px;
width:8%; 
background-color:#FF004C;
position: static; 
line-height:100px; 
float:left;
/* border-style: solid;
border-color:#3960D0;
 */
}

.managerProduct{
height:100px;
width:8%; 
background-color:#FF004C;
position: static; 
line-height:100px; 
float:left;
/* border-style: solid;
border-color:#3960D0; */
}
.addProduct{
height:100px;
width:8%; 
background-color:#FF004C;
position: static; 
line-height:100px; 
float:left;
/* border-style: solid;
border-color:#3960D0; */
}
.managerOrders{
height:100px;
width:8%; 
background-color:#FF004C;
position: static; 
line-height:100px; 
float:left;
/* border-style: solid;
border-color:#3960D0; */
}
.managerCoupon{
height:100px;
width:8%; 
background-color:#FF004C;
position: static; 
line-height:100px; 
float:left;
/* border-style: solid;
border-color:#3960D0; */
}
.member{
margin-left:1300px;
height:100px;
width:7%; 
background-color:#FF004C;
position: static; 
line-height:100px;
/* border-style: solid;
border-color:#3960D0; */
}
.second{
margin-top: 10px;
height:50px;
width:100%;  
background-color:#FF004C;
position:absolute; 
text-align:center;
/* border-style: solid;
border-color:#3960D0; */
}
.Topmenu{
  width:12.5%;
  height:42px;
  background-color:#FF004C;
  color:#FFF6F6;
  font-size:22px;
  padding-top:7px;
  text-align:center;
  /*border-radius:5px;*/
  overflow:hidden;
  line-height: 43px;
  transition:all 0.8s; /*all代表多個*/
  display:inline-block;
  vertical-align:top;
  
 
}
.Topmenu:hover{
  height:178px;
  cursor:pointer;
 /*   background-color: green;*/
/*    color:cyan;*/
}
.Topmenu span{
  display: block;
  font-size: 14px;
  letter-spacing: 1px;
  transition: all 0.8s;  /*transition 需要 hover觸發*/
     color:#fff;
     border:1px #FFF7FB solid;
}
.Topmenu span:hover{
  /*color:cyan;
  text-shadow: 2px 2px 2px #000;*/
}

.bar_lift{
margin-top: 10px;
height:50px;
width:25%;  
background-color:#D3FF93;
float:left;


}
.third{
margin-top:70px; 
height:400px;
width:100%;  
background-color:#FBFBFF;
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


</style>

<title>ShopHomeBar</title>
</head>

<body>

<div id="first">
      <div class="logo">
        <div style="width:150px; float:left;">
         
       
        </div>
        <div>
          <font size="5px" color="white" style="font-style:italic;"></font>
        </div>
      </div>
    
    <div class="goToShopHome"></div>
      <div class="backhome" align="center"><a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp" style="text-decoration:none;">
        <font color="white" size="3">商城頁面</font></a>
      </div>
    <div class="addProduct" align="center"><a href="<%=request.getContextPath()%>/ProductServlet?action=getIncludePath" style="text-decoration:none;">
      <font color="white" size="3">增加商品</font></a></div> 
    <div class="managerProduct" align="center">
      	<a href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=" style="text-decoration:none;" style="text-decoration:none;">
      	  <font color="white" size="3">管理商品</font></a>
    </div> 
    
    <div class="managerOrders" align="center">
      	<a href="<%=request.getContextPath()%>/OrdersServlet?action=getAllList" style="text-decoration:none;">
      	  <font color="white" size="3">管理訂單</font></a>
    </div> 
    
    <div class="managerCoupon" align="center">
      	<a href="<%=request.getContextPath()%>/CpGetServlet?action=getCouponManagerIncludePath" style="text-decoration:none;">
      	  <font color="white" size="3">管理優惠券</font></a>
    </div> 
      	
</div>
  
  
  
  
  
  
  
  
<%--     <div id="first">
     <div class="logo" align="center">
     <a href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp"> <img src="<%=request.getContextPath()%>/img/LogoNoBack.png" style="border-radius:5px;"></a></div>
     
     <div class="search_pd" >
      <div class="search_form">
   <font style="font-size:1.0cm;" color="white">商城管理</font>
      </div></div>
      <div class="backhome" align="center"><a
		href="<%=request.getContextPath()%>/back_end/product/addProduct.jsp" style="text-decoration:none;"><font color="white" >
		增加商品</font></a></div>
      <div class="pd_follow" align="center"><a
		href="<%=request.getContextPath()%>/ProductServlet?action=searchTypeList&&pd_typeNo=" style="text-decoration:none;"><font color="white">管理商品</font></a></div> 
      <div class="shopping_trolley" align="center">
      	<a href="<%=request.getContextPath()%>/OrdersServlet?action=getAllList" style="text-decoration:none;"><font color="white">管理訂單
</font></a>
      	</div> 
       <div class="memberLogin" align="center"><a
		href="<%=request.getContextPath()%>/back_end/product/CouponManage.jsp" style="text-decoration:none;"><font color="white" >管理優惠券</font></a></div>
      <div class="member" align="center"><font color="white">分類管理</font></div>
    </div> --%>


     
 

 <script src="<%=request.getContextPath()%>/plug-in/bootstrap/jquery/jquery-3.4.1.min.js"></script>
 <script src="<%=request.getContextPath()%>/plug-in/Semantic-UI/semantic.min.js"></script>
    <script src="<%=request.getContextPath()%>/plug-in/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/plug-in/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
