<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>update_staff</title>
</head>
<body>
	<h2>管理員資料修改</h2>
	
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
	
		帳號：${staffVO.staff_id}<br>
		密碼：<input type="password" name="staff_pwd" value="${staffVO.staff_pwd}"><br>
		名字：<input type="text" name="staff_name" value="${staffVO.staff_name}"><br>
		加入時間：${staffVO.staff_join}<br>
		
		狀態：
		<select name="staff_status">
			<c:forEach var="map" items="${staffStatus}">
				<option value="${map.key}" ${staffVO.staff_status==map.key?'selected':''}>${map.value}</option>
			</c:forEach>
		</select>
		
		<input type="hidden" name="includePath" value="${incluePath}">
		<input type="hidden" name="staff_id" value="${staffVO.staff_id}">
		<input type="hidden" name="staff_join" value="${staffVO.staff_join}">
		
        <a href="listAllStaff.jsp"><input type ="button" value="返回"></a>
        <button type="submit" name="action" value="update">送出</button><br>
        
	</form>
	
</body>
</html>