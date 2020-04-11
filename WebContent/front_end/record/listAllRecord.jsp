<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.record.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    RecordService recordSvc = new RecordService();
    List<RecordVO> list = recordSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>紀錄編號</th>
		<th>上傳時間</th>
		<th>紀錄內容</th>
		<th>總按讚數</th>
		<th>總metoo數</th>
		<th>紀錄狀態</th>
		<th>紀錄編號</th>
		<th>會員編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="recordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${recordVO.rcd_no}</td>
			<td><fmt:formatDate value="${recordVO.rcd_uploadtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td><!-- 取代${empVO.hiredate} -->
			<td>${recordVO.rcd_content}</td>
			<td>${recordVO.rcd_thumb_amount}</td>
			<td>${recordVO.rcd_metoo_amount}</td>
			<td>${recordVO.rcd_status}</td> 
			<td>${recordVO.path_no}</td>
			<td>${recordVO.mb_id}</td>
			<td>

			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/record/record.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="rcd_no"  value="${recordVO.rcd_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/record/record.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="rcd_no"  value="${recordVO.rcd_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>