<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="BIG5">
<title>addMember</title>
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
	<form METHOD="POST" action="member.do" enctype="multipart/form-data">
		<span id="star">*</span>為必填
		<div>
			<span id="star">*</span>
			帳號：<input id="staff_id" type="text" name="staff_id" onblur="checkId()" value="${staffVO.staff_id}">
			<img id="icon"/><span id="check"></span>
		</div>
		
		<span id="star">*</span>
		密碼：<input type="password" name="mb_pwd" value="${memberVO.mb_pwd}"><br>
		
		<span id="star">*</span>
		名字：<input type="text" name="mb_name" value="${memberVO.mb_name}"><br>
		
        <input type="hidden" name="action" value="insert"><br>
        <input type="reset" value="清除">
        <input id="submit" type="submit" value="送出"><br>
        
	</form>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
					
				}
			})
		})
	})
	
</script>
</body>
</html>