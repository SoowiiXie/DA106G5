<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cp_get.model.*, java.util.List"%>


<%
	String mb_id = (String)session.getAttribute("mb_id");

	Cp_getService cp_getService = new Cp_getService();
	Cp_getVO cp_getVO = new Cp_getVO();
	cp_getVO.setMb_id(mb_id);
	cp_getVO.setCp_status(1);
	List<Cp_getVO> list = cp_getService.listAmemberCpGetStatus(cp_getVO);
	
	pageContext.setAttribute("list", list );
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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



<table >
<tr>  
<th width="100">會員帳號</th>
<th width="100">可使用的優惠券</th>
</tr>
<tr>
<td width="100">${mb_id}</td>


<c:forEach var="cp_getVO" items="${list}">

<td width="100">${cp_getVO.cp_no}</td>


</c:forEach>
</tr>
</table>



       <select size="1" name="cp_non">
         <c:forEach var="cp_getVO2" items="${list}" > 
          <option value="${cp_getVO2.cp_no}">${cp_getVO2.cp_no}
         </c:forEach>   
       </select>

<%-- <table>
<tr>
<% for(Cp_getVO cp_getVO : list){%>
<td><%=cp_getVO.getCp_no()%></td>
<%}%>
</tr>
</table> --%>

<a href="<%=request.getContextPath()%>/front_end/product/ShopHome.jsp">回商城首頁</a>

</body>
</html>