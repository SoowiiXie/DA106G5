<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Group_RPT: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Group_GPR: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM GroupGPR: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllGroupRpt.jsp'>List</a> all Groups.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="group_rpt.do" >
        <b>輸入揪團檢舉編號 (如grr00001):</b>
        <input type="text" name="group_rpt_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="group_rptSvc" scope="page" class="com.group_rpt.model.Group_rptService" />
   
  <li>
     <FORM METHOD="post" ACTION="group_rpt.do" >
       <b>選擇揪團檢舉編號:</b>
       <select size="1" name="group_rpt_no">
         <c:forEach var="Group_rptVO" items="${group_rptSvc.all}" > 
          <option value="${Group_rptVO.group_rpt_no}">${Group_rptVO.group_rpt_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>  



<h3>揪團檢舉管理</h3>

<ul>
  <li><a href='addGroupRpt.jsp'>新增</a> 一則新的揪團檢舉.</li>
</ul>

</body>