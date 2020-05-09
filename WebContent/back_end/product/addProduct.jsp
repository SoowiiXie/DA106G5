<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.pd_type.model.*"%>
<%@ page import="java.util.*"%>

<%  //圖片路徑初始化
    String imgStr = request.getContextPath() + "/NoData/null2.jpg";
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	if(productVO != null && productVO.getPd_pic() != null){  // 錯誤處理回來後，若先前有上傳圖片，則顯示原圖片
		imgStr = "data:image/png;base64," + Base64.getEncoder().encodeToString(productVO.getPd_pic());
	}
%>


<%
	Pd_typeService pd_typeService = new Pd_typeService();

	List<Pd_typeVO> list = pd_typeService.getAll();

	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商家商品</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css" media="screen">


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
  
  }
  
  .buttonBar{
width:100%;
background-color:#FF004C;
height:100px;
margin-top:100;
}

</style>
<jsp:include page="ShopManagerBar.jsp" />
</head>
<body>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<div style="width:100%; margin-top:50px;" align="center" >
<font size="+3">增加商品：</font>
		<hr>
  <div class="zxc" style=" width:100%; margin-top:50px;">
  <form method="POST"
		action="<%=request.getContextPath()%>/ProductServlet" name="form1"
		enctype="multipart/form-data">
    <table border="1">
      <tr bgcolor="#999999">
        <th width="20%">項目名稱</th>
          <th width="80%">輸入欄位</th>
      </tr>
      <tr>
        <td bgcolor=#9CC7F6>商品名稱：</td>
        <td bgcolor=#9CC7F6><input type="TEXT" name="pd_name" value="${productVO.pd_name}"></td>
      </tr>
      <tr>
        <td bgcolor=#9CC7F6>商品價格：</td>
        <td bgcolor=#9CC7F6><input type="TEXT" name="pd_price" value="${productVO.pd_price}"></td>
      </tr>
      <tr >
	    <td style="height:100px;" bgcolor=#9CC7F6>商品詳述：</td>
	    <td style="height:100px;" bgcolor=#9CC7F6><textarea style="height:100px; width:300px;" name="pd_detail">${productVO.pd_detail}</textarea></td>					
      </tr>
      <tr>
		<td bgcolor=#9CC7F6>產品商品分類：</td>
		  <td bgcolor=#9CC7F6>
		    <select size="1" name="pd_typeNo">
			  <c:forEach var="pd_typeVO" items="${list}">
			    <option value="${pd_typeVO.pd_typeNo}" ${(productVO.pd_typeNo==pd_typeVO.pd_typeNo)? 'selected':''}>${pd_typeVO.pd_typeName}
			  </c:forEach>
		    </select>
		  </td>
	   <tr>
	     <td bgcolor=#9CC7F6 rowspan="2">商品圖片：</td>
		 <td bgcolor=#9CC7F6><input type="file" name="pd_pic" onchange="setImg(this)"> 
		
		 </td>
	</tr>
		 <tr>
						<td style="width:450px; height:450;"><img style="max-width:100%;" id="pd_pic" src="<%=imgStr%>">
						<!-- 第一次有送出照片，錯誤回來後沒有再選擇照片時，用picBase64送出 -->
						<%if(productVO != null && productVO.getPd_pic() != null){ %>
			<input type="hidden" name="picBase64" value="<%=Base64.getEncoder().encodeToString(productVO.getPd_pic())%>">
		<%}; %>
						
			</td>
	</tr>				
					
					
    </table>
    	<input type="hidden" name="action" value="addProduct"> 
		<input type="hidden" name="includePath" value="${incluePath}">
		<input type="submit" name="Submit" value="增加商品">
	</form>
  </div>
</div>

<%-- 
	<form method="POST"
		action="<%=request.getContextPath()%>/ProductServlet" name="form1"
		enctype="multipart/form-data">
		<table style="width:50%; flaot:left;">

			<tr>
				<td bgcolor=#9CC7F6>商品名稱：</td>
				<td bgcolor=#9CC7F6><input type="TEXT" name="pd_name"
					value="${productVO.pd_name}"></td>
			</tr>
			<tr>
				<td bgcolor=#9CC7F6>商品價格：</td>
				<td bgcolor=#9CC7F6><input type="TEXT" name="pd_price"
					value="${productVO.pd_price}"></td>
			</tr>
			<tr>
				<td bgcolor=#9CC7F6>商品詳述：</td>
				<td bgcolor=#9CC7F6><textarea name="pd_detail">${productVO.pd_detail}</textarea></td>					
					
			</tr>

		
			<tr>
					<td bgcolor=#9CC7F6>產品商品分類：</td>
					<td bgcolor=#9CC7F6><select size="1" name="pd_typeNo">
							<c:forEach var="pd_typeVO" items="${list}">
								<option value="${pd_typeVO.pd_typeNo}" ${(productVO.pd_typeNo==pd_typeVO.pd_typeNo)? 'selected':''}>${pd_typeVO.pd_typeName}
							</c:forEach>
					</select></td>


			</tr>
			<tr>
				<td bgcolor=#9CC7F6>商品圖片</td>
				<td><input type="file" name="pd_pic" onchange="setImg(this)">
						<img width = "200px" id="pd_pic" src="<%=imgStr%>">
						<!-- 第一次有送出照片，錯誤回來後沒有再選擇照片時，用picBase64送出 -->
						<%if(productVO != null && productVO.getPd_pic() != null){ %>
			<input type="hidden" name="picBase64" value="<%=Base64.getEncoder().encodeToString(productVO.getPd_pic())%>">
		<%}; %>
						</td>
			</tr>

			


		</table>

		<input type="hidden" name="action" value="addProduct"> 
		<input type="hidden" name="includePath" value="${incluePath}">
		<input type="submit" name="Submit" value="增加商品">
	</form>
 --%>


	<script>
	function setImg(input){
  		if(input.files && input.files[0]){
  			var reader = new FileReader();
  			reader.onload = function (e) {
    			document.getElementById("pd_pic").setAttribute("src", e.target.result);
    		}
    	reader.readAsDataURL(input.files[0]);
  		}
	}
	</script>





<div class="buttonBar" style="margin-top:100px"></div>


</body>
</html>