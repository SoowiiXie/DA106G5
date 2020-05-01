<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>update_self</title>
<style>
	#wrap{
		font-family: 'Mamelon';
		
		background-color: rgba(156, 199, 246, 0.6);
        margin: 10vh auto 0px auto;
        width: 30vw;
        height: 50vh;
        border-radius: 5vw;
        text-align:center;
	}
	#self_title{
		font-family: 'italics_hollow';
		font-weight:bold;
		line-height:15vh;
		font-size: 5.5vh;
		color:#0373f0;
		border-bottom:3px solid #0373f0;
	}
	#self_table{
		margin:0px auto;
		font-size: 3vh;
		color:#333;
	}
	#staff_pwd,#staff_name{
		height: 3.5vh;
		width: 10vw;
        border-radius: 1vh;
        padding-left: 10px;
        
        font-size: 2.5vh;
        color:#333;
	}
	#btnSubmit{
		font-size: 2.8vh;
		line-height:1vh;
		color: #fff;
		font-family: 'Mamelon';
		background-color: #60A5F3;
		border-radius: 2vh;
		
 		height: 6vh; 
 		width: 8vw; 
	}
	#tdSubmit{
/* 		border:1px solid #000; */
		height: 12vh; 
	}
</style>
</head>
<body>
	<div id="wrap">
	<span id="self_title">個人資料修改</span>
	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
		    <c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
	<table id="self_table">
	<tr>
		<td align="right">帳號：</td><td>${staffVO.staff_id}</td>
	</tr>
	<tr>
		<td align="right">密碼：</td><td><input id="staff_pwd" type="password" name="staff_pwd" value="${staffVO.staff_pwd}"></td>
	</tr>
	<tr>
		<td align="right">名字：</td><td><input id="staff_name" type="text" name="staff_name" value="${staffVO.staff_name}"></td>
	</tr>
	<tr>
		<td align="right">加入時間：</td><td><fmt:formatDate value="${staffVO.staff_join}" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr><td colspan="2" align="center" id="tdSubmit">	
		<input type="hidden" name="staff_join" value="${staffVO.staff_join}">
		<input type="hidden" name="staff_id" value="${staffVO.staff_id}">
		<input type="hidden" name="staff_status" value="${staffVO.staff_status}">
		
		<input type="hidden" name="includePath" value="${incluePath}">
        <button id="btnSubmit" type="submit" name="action" value="update">送出修改</button>
    </td></tr>
    </table>    
	</form>
	</div>
</body>
</html>