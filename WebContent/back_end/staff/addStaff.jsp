<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta charset="BIG5">
<title>addStaff</title>
<style>

	#icon{
		vertical-align:middle
	}
	
	#mb_pic{
		width:200px;
		height:200px;
	}
	
	#star{
		color:red;
		font-weight:bold;
	}
	
</style>
<script
  src="https://code.jquery.com/jquery-3.5.0.js"
  integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
  crossorigin="anonymous">
</script>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<h2>新增管理員</h2>
	<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
		<span id="star">*</span>為必填
		<div>
			<span id="star">*</span> 		
			<%-- 用requestScope是因為sessionScope存著登入管理員資訊，第一次進來時會抓到登入管理員的資料 --%>
			帳號：<input id="staff_id" type="text" name="staff_id" value="${requestScope.staffVO.staff_id}">
			<img id="icon"/><span id="check"></span>
		</div>
		
		<span id="star">*</span>
		密碼：<input type="password" name="staff_pwd" value="${requestScope.staffVO.staff_pwd}"><br>
		
		<span id="star">*</span>
		名字：<input type="text" name="staff_name" value="${requestScope.staffVO.staff_name}"><br>
		
        <input type="hidden" name="includePath" value="${incluePath}">
<!--         !!!!!!!!!!!!!!!! -->

<!-- 返回上一個include的路徑 -->
        <input type="hidden" name="backPath" value="/back_end/staff/addStaff.jsp">
        <button type="submit" name="action" value="back">返回</button>
        
        <button id="submit" type="submit" name="action" value="insert">送出</button><br>
        
	</form>

<script>  

// AJAX 測試會員帳號

	$(document).ready(function(){
		$("#staff_id").on("blur",function(){
			$.ajax({
				type:"GET",
				url: "staff.do",
				data:{
					"action":"check_id",
					"staff_id":$(this).val()
				},
				dataType: "json",
				success: function(data){
					$("#check").html(data.result);
					$("#icon").css("height","32");
					$("#icon").css("width","25");
					
					if(data.result != "此帳號可以使用"){
						$("#check").css("color","red");
						$("#icon").attr("src","<%=request.getContextPath()%>/back_end/staff/images/no.png");
						$("#submit").attr("disabled","disabled");
					}else{
						$("#check").css("color","black");
						$("#icon").attr("src","<%=request.getContextPath()%>/back_end/staff/images/ok.png");
						$("#submit").removeAttr("disabled");
					}
				},
				error: function(){alert("AJAX-class發生錯誤囉!")}
			});
		});
	});
	
</script>
</body>
</html>