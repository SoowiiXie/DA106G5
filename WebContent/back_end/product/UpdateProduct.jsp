<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.pd_type.model.*"%>
<%
        String whichPage  = (String)request.getAttribute("whichPage");
	ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	productVO.getPd_status();
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
<title>${productVO.pd_name}</title>
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
	<div style="width:100%;" align="center">

	  <div class="zxc" style=" width:100%; ">
<jsp:include page="ShopManagerBar.jsp"/>
	<div style="margin-top:50px;">
	<font size="+3">修改商品：</font>
		<hr>
	
	<form method="POST"
		action="<%=request.getContextPath()%>/ProductServlet" name="form1"
		enctype="multipart/form-data">
		
	<div style="margin-top:50px;">
		<table style="width:60%;">
			<tr bgcolor="#999999"> 
				<td width="20%">商品編號:<font color=red><b>*</b></font></td>
				<td width="80%">${productVO.pd_no}</td>
			</tr>

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
				<td style="height:100px;" bgcolor=#9CC7F6>商品詳述：</td>
				<td style="height:100px;" bgcolor=#9CC7F6><textarea  style="height:100px; width:300px;" name="pd_detail"
					>${productVO.pd_detail}</textarea></td>
			</tr>

			<tr>
				<td style="height:100px;" bgcolor=#9CC7F6>產品商品分類：</td>
				
				<td style="height:100px;" bgcolor=#9CC7F6><select size="1" name="pd_typeNo">
						<c:forEach var="pd_typeVO" items="${list}">
							<option value="${pd_typeVO.pd_typeNo}" ${(productVO.pd_typeNo==pd_typeVO.pd_typeNo)? 'selected':''}>${pd_typeVO.pd_typeName}
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td bgcolor=#9CC7F6>商品分類狀態：</td>
				<td bgcolor=#9CC7F6><select size="1" name="pd_status">
						<option value="1" ${productVO.pd_status==1?'selected':''}>下架</option>
						<option value="2" ${productVO.pd_status==2?'selected':''}>上架</option>

				</select></td>
			</tr>

			<tr>
				<td bgcolor=#9CC7F6> 商品圖片</td>
				<td bgcolor=#9CC7F6><input type="file" name="pd_pic" onchange="setImg(this)">
				
			</td>
			<tr>
			<td bgcolor=#9CC7F6></td>
			<td bgcolor=#9CC7F6><img  style="height:300px; width:300px;" id="pd_pic" src="<%=request.getContextPath()%>/ProductPicReader?pd_no=${productVO.pd_no}"></td>
			</tr>
			<tr>
			<td bgcolor=#9CC7F6></td>
			<td bgcolor=#9CC7F6><img  style="height:300px; width:300px;" id="pd_pic" src="<%=request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic2"></td>
			</tr>
			
				<tr>
			<td bgcolor=#9CC7F6></td>
			<td bgcolor=#9CC7F6><img  style="height:300px; width:300px;" id="pd_pic" src="<%=request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic3"></td>
			</tr>
			<tr>
			<td bgcolor=#9CC7F6></td>
			<td bgcolor=#9CC7F6><img  style="height:300px; width:300px;" id="pd_pic" src="<%=request.getContextPath()%>/ProductPicReader2?pd_no=${productVO.pd_no}&&action=pd_pic4"></td>
			</tr>


		</table>
		<input type="hidden" name="action" value="updateProduct"> <input
			type="hidden" name="pd_no" value="${productVO.pd_no}"> 
			<input type = "hidden" name="whichPage" value = "<%=whichPage%>">
			<input
			type="submit" name=送出修改>
			</div>
	</form>
</div>
</div>
</div>
	<br>
	<br>
	<a
		href="<%=request.getContextPath()%>/back_end/product/ShopManager.jsp">回管理商城首頁</a>


	<script>
	
	// 預覽圖片
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
</body>
</html>