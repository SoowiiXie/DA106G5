<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>update_staff</title>
<style>
	#wrap{
		font-family: 'Mamelon';
		
		background-color: rgba(156, 199, 246, 0.6);
        margin: 5vh auto 0px auto;
        width: 30vw;
        height: 65vh;
        border-radius: 5vw;
        text-align:center;
	}
	#title{
		font-family: 'italics_hollow';
		font-weight:bold;
		line-height:15vh;
		font-size: 5.5vh;
		color:#0373f0;
		border-bottom:3px solid #0373f0;
	}
	#table{
		margin:0px auto;
		font-size: 3vh;
		color:#333;
	}
	tr{
		height:6vh;
	}
	#staff_pwd,#staff_name{
		height: 3.5vh;
		width: 10vw;
        border-radius: 1vh;
        padding-left: 10px;
        
        font-size: 2.5vh;
        color:#333;
	}
	#select{
		height: 4vh;
		width: 5vw;
        border-radius: 1vh;
        align:center;
        
        font-family: 'Mamelon';
        font-size: 2.5vh;
        color:#333;
	}
	.btn{
		font-size: 2.8vh;
		color: #fff;
		font-family: 'Mamelon';
		
		background-color: #60A5F3;
		border-radius: 2vh;
		margin-top:2.5vh;
 		height: 6vh; 
 		width: 8vw; 
	}
	#errorMsgs{
       	margin-left:3vw;
       	font-size: 2.5vh;
    }
    #ul{
       	margin:0px;
       	text-align:left;
    }
</style>
</head>
<body>
	<div id="wrap">
	<span id="title">管理員資料修改</span>

	<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
	<table id="table">
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
		<tr>
			<td align="right">Status：</td>
			<td>
				<select id="select" name="staff_status">
					<c:forEach var="map" items="${staffStatus}">
						<option value="${map.key}" ${staffVO.staff_status==map.key?'selected':''}>${map.value}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<input type="hidden" name="includePath" value="${incluePath}">
				<input type="hidden" name="staff_id" value="${staffVO.staff_id}">
				<input type="hidden" name="staff_join" value="${staffVO.staff_join}">
				
		<!-- 		backPath 後面的 ?staff_id= 是為了讓回去時不讓該管理員顯示顏色(因為forward會自動傳參數，所以要讓參數傳不回去) -->
		        <input type="hidden" name="backPath" value="/back_end/staff/listAllStaff.jsp?staff_id=">
				<button class="btn" type="submit" name="action" value="back">返回</button>
			</td>
			<td>
				<button class="btn" type="submit" name="action" value="update">送出</button><br>
				
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<%-- 錯誤表列 --%>
				<div id="errorMsgs">
				<c:if test="${not empty errorMsgs}">
					<ul id="ul">
					    <c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				</div>
			</td>
		</tr>
		
    
    </table>    
	</form>
	</div>
</body>
</html>