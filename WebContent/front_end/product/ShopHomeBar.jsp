<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ page import="com.product.model.*"%>
<%@ page import="com.pd_type.model.*"%>
<%@ page import="java.util.*"%>
     
     <%
	ProductService productService = new ProductService();

	List<ProductVO> list = productService.listOnTheMarket(2);

	Collections.reverse(list);

	pageContext.setAttribute("list", list); //for EL
%>
<!DOCTYPE html>
<html>
<head>
<!-- Custom fonts for this template-->
	<link href="<%= request.getContextPath() %>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />

	<!-- Custom styles for this template-->
	<link href="<%= request.getContextPath() %>/css/sb-admin-2.min.css" rel="stylesheet" />
	
	<!-- modal -->
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	
	<!-- 會員智慧搜尋 -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<!-- switch button -->
	<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
	



<meta charset="UTF-8">
<style type="text/css" media="screen">
#page {
margin-right:8px;
margin-left:-8px;
margin-top:-8px;  
height:2000px;
width:1450px;
background-color:#FBFBFF;
}
#first {
margin-right:8px;
margin-left:-8px;
margin-top:-8px;  
height:100px;
width:100%;
background-color:#3960D0;

}
.logo{
margin-left:100px;
height:100px;
width:200px; 
background-color:#3960D0;
position: static; 
line-height:100px; 
float:left;
}
.search_pd{
margin-left:20px;
height:100px;
width:350px; 
background-color:#3960D0;
float:left;
position: relative;
}
.search_form{
position: absolute;  
margin-top:54px;
}
.backhome{
margin-left:130px;
height:100px;
width:100px; 
background-color:#3960D0;
position: static; 
line-height:100px; 
float:left;

}

.transaction_record{
height:100px;
width:100px; 
background-color:#3960D0;
position: static; 
line-height:100px; 
float:left;

}

.shopping_trolley{
height:100px;
width:100px; 
background-color:#3960D0;
position: static; 
line-height:100px; 
float:left;

}
.pd_follow{
height:100px;
width:100px; 
background-color:#3960D0;
position: static; 
line-height:100px; 
float:left;

}
.memberLogin{
height:100px;
width:100px; 
background-color:#3960D0;
position: static; 
line-height:100px; 
float:left;

}
.member{
margin-left:1300px;
height:100px;
width:100px; 
background-color:#3960D0;
position: static; 
line-height:100px;
}
.second{
margin-top: 10px;
height:50px;
width:1440px;  
background-color:#D2E9FF;
position:absolute; 
text-align:center;

}
.Topmenu{
  width:12.5%;
  height:42px;
  background-color:#3960D0;
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
    object-fit:cover;
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
     <div class="logo" align="center">
     <a href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp"> <img src="<%=request.getContextPath()%>/img/LogoNoBack.png" style="border-radius:5px;"></a></div>
     <div class="search_pd" >
      <form class="search_form" action="XXX">
        <input type="text" name="search" placeholder="欲搜尋的商品" style="width:250px;height:40px;border-radius:5px;">
        <input type="submit" value="搜尋"style="width:50px;height:40px; border-radius:5px;">  
      </form></div>
      <div class="backhome" align="center"><a
		href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp" style="text-decoration:none;"><font color="white" >
		回到首頁</font></a></div>
      <div class="pd_follow" align="center"><a
		href="<%=request.getContextPath()%>/front_end/product/MemberLookSelfCoupon.jsp" style="text-decoration:none;"><font color="white">我的收藏</font></a></div> 
      <div class="shopping_trolley" align="center">
      	<a href="<%=request.getContextPath()%>/front_end/product/ProductCart.jsp" style="text-decoration:none;"><font color="white">購物車
      	<c:if test="${buylistCount!=0}">
	                  
	                  
	                   <span class="badge badge-pill badge-info">${buylistCount}</span>
  </c:if></font></a>
      	</div> 
      	<div class="transaction_record" align="center"><a
		href="<%=request.getContextPath()%>/front_end/product/MemberTransaction_record.jsp" style="text-decoration:none;"><font color="white" >交易紀錄</font></a></div>
       <div class="memberLogin" align="center"><a
		href="<%=request.getContextPath()%>/front_end/product/MemberLookSelfCoupon.jsp" style="text-decoration:none;"><font color="white" >我的優惠券</font></a></div>
      <div class="member" align="center"><font color="white">${mb_id}</font></div>
    </div>
    <div class="bar_lift"></div>
    <div class="second">
     <div class="Topmenu">
        Men<br>
        <div class="Topmenuin">
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00001" 
        style="text-decoration:none;"><font color="white">服飾-男上身</font></a></span>
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00002" 
        style="text-decoration:none;"><font color="white">服飾-男下身</font></a></span>
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00003"
        style="text-decoration:none;"><font color="white">服飾-男鞋</font></a></span>
        </div>
        </div>
        <div class="Topmenu">
        Women<br>
        <div class="Topmenuin">
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00004"
        style="text-decoration:none;"><font color="white">服飾-女上身</font></a></span>
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00005"
        style="text-decoration:none;"><font color="white">服飾-女下身</font></a></span>
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00006"
        style="text-decoration:none;"><font color="white">服飾-女鞋</font></a></span>
        </div>
        </div>
        <div class="Topmenu">
        Kids<br>
        <div class="Topmenuin">
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00007"
        style="text-decoration:none;"><font color="white">服飾-兒童上身</font></a></span>
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00008"
        style="text-decoration:none;"><font color="white">服飾-兒童下身</font></a></span>
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00009"
        style="text-decoration:none;"><font color="white">服飾-兒童鞋類</font></a></span>
        </div>
        </div>
        <div class="Topmenu">
        Accessories<br>
        <div class="Topmenuin">
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00010"
        style="text-decoration:none;"><font color="white">配件-護具</font></a></span>
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00011"
        style="text-decoration:none;"><font color="white">配件-包款</font></a></span>
        <span><a href="<%=request.getContextPath()%>/FrontEndProductServlet?action=List_OnMarket_Pd_typeNo&&pd_typeNo=PTN00012"
        style="text-decoration:none;"><font color="white">配件-3C</font></a></span>
        </div>
        </div>
        <div class="Topmenu">
        Health Food<br>
        <div class="Topmenuin">
        <span>能量飲料</span>
        <span>乳清</span>
        <span>減脂餐</span>
        </div>
        </div> 
     
    </div>
 
<div class="third" align="center" ><img src="<%=request.getContextPath()%>/img/ProductAD.jpg" alt="跑馬燈圖片"></div> 
<%--     <div class="carouselExampleSlidesOnly col-10 mx-auto" id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel" style="height:50rem;">
      <div class="carousel-inner">
        <div class="carousel-item active">
           <img src="<%=request.getContextPath()%>/img/ProductAD.jpg" class="d-block w-100" alt="跑馬燈圖片1">
        </div>
        <div class="carousel-item">
           <img src="<%=request.getContextPath()%>/img/ProductAd2.png" class="d-block w-100" alt="跑馬燈圖片2">
        </div>
         <div class="carousel-item">
           <img src="<%=request.getContextPath()%>/img/ProductAd3.jpg" class="d-block w-100" alt="跑馬燈圖片3">
        </div>
      </div>
    </div>
  <script src="<%=request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>

<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/bootstrap.min.js"></script>

    	<!-- Bootstrap core JavaScript-->
	<script src="<%= request.getContextPath() %>/vendor/jquery/jquery.min.js"></script>
	<script src="<%= request.getContextPath() %>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="<%= request.getContextPath() %>/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%= request.getContextPath() %>/js/sb-admin-2.min.js"></script>
	
	<!-- switch button -->
	<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	
	<!-- jquery -->
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
  --%>
</body>

</html>
