<<<<<<< HEAD
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM GroupDetail: Home</title>

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
   <tr><td><h3>IBM GroupDetail: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM GroupDetail: Home</p>

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
  <li><a href='listAllGroupdetail.jsp'>List</a> all GroupDetails.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="group_detail.do" >
        <b>輸入會員編號 :</b>
        <input type="text" name="mb_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="grpdetailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />
   
  <li>
     <FORM METHOD="post" ACTION="group_detail.do" >
       <b>選擇揪團編號:</b>
       <select size="1" name="mb_id">
         <c:forEach var="Grp_detailVO" items="${grpdetailSvc.all}" > 
          <option value="${Grp_detailVO.grp_no}">${Grp_detailVO.grp_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<h3>揪團管理</h3>

<ul>
  <li><a href='addGroupdetail.jsp'>Add</a> a new Groupdetail.</li>
</ul>

</body>
=======
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Group: Home</title>

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
   <tr><td><h3>IBM Group: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Group: Home</p>

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
  <li><a href='listAllGroup.jsp'>List</a> all Groups.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="group.do" >
        <b>輸入揪團編號 (如grp00001):</b>
        <input type="text" name="grp_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />
   
  <li>
     <FORM METHOD="post" ACTION="group.do" >
       <b>選擇揪團編號:</b>
       <select size="1" name="grp_no">
         <c:forEach var="GrouperVO" items="${grpSvc.all}" > 
          <option value="${GrouperVO.grp_no}">${GrouperVO.grp_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<h3>揪團管理</h3>

<ul>
  <li><a href='addGroup.jsp'>Add</a> a new Group.</li>
</ul>

</body>
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
</html>