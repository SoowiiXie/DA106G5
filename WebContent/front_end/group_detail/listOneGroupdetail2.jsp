<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.group_detail.model.*"%>
<%@ page import="com.location.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Grp_detailVO grp_detailVO = (Grp_detailVO) request.getAttribute("grp_detailVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
%>

<jsp:useBean id="grp_detailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />

<html>
<head>
<title>員工資料 - listOneGroup.jsp</title>
<style>
	#myGroupDetail{
		width: 100%;
		font-family:Microsoft JhengHei;
		font-size: 22px; 
	}
	#myGroupDetail td, #myGroupDetail th{
		border:1px solid #000;
	}
	#myGroupDetail th{
		background: #D5E0CC;
	}
</style>

</head>
<body bgcolor='white'>

<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>揪團詳細資料</h3> -->
<!-- 		 <h4> -->
<%-- 			<a href="<%= request.getContextPath() %>/front_end/index.jsp?pageRun=group/select_page.jsp"> --%>
<%-- 			<img src="<%= request.getContextPath() %>/front_end/group/images/homeIcon.png" width="75" height="75" border="0"> --%>
<!-- 			回到瀏覽揪團</a> -->
<!-- 		 </h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<table id="myGroupDetail">
	<tr>

		<th>揪團編號</th>
		<th>地點</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>活動開始時間</th>
		<th>活動結束時間</th>
<!-- 		<th>揪團標題</th> -->
		
		
<!-- 		<th>報到狀態</th> -->
		
	</tr>

		<td><%=grp_detailVO.getGrp_no()%></td>	
		<td>
<%-- 				<c:forEach var="locationVO" items="${locSvc.all}"> --%>
<%-- 		        <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">  --%>
<%-- 		        	<c:if test="${grouperVO.loc_no==locationVO.loc_no}">   		        				     --%>
<%-- 		                 ${grouperVOlocationVO.loc_address}<br> --%>
<%-- 		       		 </c:if > --%>
<%-- 		        </c:if >         --%>
					${locSvc.getOneLocation(grpSvc.getOneGroup(grp_detailVO.getGrp_no()).loc_no).loc_address}
<%-- 		        </c:forEach> --%>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
<%--                     ${grouperVO.grp_applystart} --%>
        <fmt:formatDate value="${grouperVO.grp_applystart}" pattern="yyyy-MM-dd H:mm"/>                                                      
                   </c:if>
        </c:forEach>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
<%--                     ${grouperVO.grp_applyend} --%>
		<fmt:formatDate value="${grouperVO.grp_applyend}" pattern="yyyy-MM-dd H:mm"/>                                           
                   </c:if>
        </c:forEach>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
<%--                     ${grouperVO.grp_start} --%>
        <fmt:formatDate value="${grouperVO.grp_start}" pattern="yyyy-MM-dd H:mm"/>                                             
                   </c:if>
        </c:forEach>
		</td>
		<td>
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
<%--                     ${grouperVO.grp_end} --%>
        <fmt:formatDate value="${grouperVO.grp_end}" pattern="yyyy-MM-dd H:mm"/>                                                       
                   </c:if>
        </c:forEach>
		</td>
<!-- 		<td> -->
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}"> --%>
<%--                     ${grouperVO.grp_name}                                            --%>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
<!-- 		</td> -->
		<tr>
				<th colspan="6">揪團內容</th>
		</tr>		
		<td colspan="6">
		<c:forEach var="grouperVO" items="${grpSvc.all}">
                   <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">
                    ${grouperVO.grp_content}                                           
                   </c:if>
        </c:forEach>
		</td>
		
<%-- 		<%  --%>
<!-- // 		Map<Integer,String>register = new HashMap<>(); -->
<!-- // 		register.put(1,"未到"); -->
<!-- // 		register.put(2,"已到"); -->
<!-- // 		request.setAttribute("register", new String[]{"","未到","已到"}); -->
<%-- 		%> --%>
		
<%-- 		<td>${register[grp_detailVO.grp_register]}</td> --%>

	</tr>
	
</table>

</body>
</html>