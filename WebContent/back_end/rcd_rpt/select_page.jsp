<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>

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
   <tr><td><h3>IBM RCD_RPT: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

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
  <li><a href='listAllRcd_rpt.jsp'>List</a> all Emps.  <br><br></li>
  	
  
  <li>
    <FORM METHOD="post" ACTION="rcd_rpt.do" >
        <b>輸入紀錄編號 (如rcdr00001):</b>
        <input type="text" name="rcd_rpt_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="RcdrptSvc" scope="page" class="com.rcd_rpt.model.Rcd_rptService" />
   
  <li>
     <FORM METHOD="post" ACTION="rcd_rpt.do" >
       <b>選擇紀錄檢舉編號:</b>
       <select size="1" name="rcd_rpt_no">
         <c:forEach var="rcd_rptVO" items="${RcdrptSvc.all}" > 
          <option value="${rcd_rptVO.rcd_rpt_no}">${rcd_rptVO.rcd_rpt_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addRcd_rpt.jsp'>Add</a> a new record.</li>
</ul>

</body>
</html>