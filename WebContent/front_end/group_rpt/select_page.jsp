<<<<<<< HEAD
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

<p>This is the Home page for IBM Group: Home</p>

<h3>è³‡æ–™æŸ¥è©¢:</h3>
	
<%-- éŒ¯èª¤è¡¨åˆ— --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">è«‹ä¿®æ­£ä»¥ä¸‹éŒ¯èª¤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllEmp.jsp'>List</a> all Groups.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="group.do" >
        <b>è¼¸å…¥æªåœ˜ç·¨è™Ÿ (å¦‚grp00001):</b>
        <input type="text" name="Grp_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="é€å‡º">
    </FORM>
  </li>

  <jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />
   
  <li>
     <FORM METHOD="post" ACTION="group.do" >
       <b>é¸æ“‡æªåœ˜ç·¨è™Ÿ:</b>
       <select size="1" name="grp_no">
         <c:forEach var="GrouperVO" items="${grpSvc.all}" > 
          <option value="${GrouperVO.grp_no}">${GrouperVO.grp_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="é€å‡º">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="group.do" >
       <b>é¸æ“‡å“¡å·¥å§“å:</b>
       <select size="1" name="grp_no">
         <c:forEach var="GrouperVO" items="${grpSvc.all}" > 
          <option value="${GrouperVO.grp_no}">${GrouperVO.grp_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="é€å‡º">
     </FORM>
  </li>
</ul>


<h3>å“¡å·¥ç®¡ç†</h3>

<ul>
  <li><a href='addGroup.jsp'>Add</a> a new Group.</li>
</ul>

</body>
=======
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
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

<h3>¸ê®Æ¬d¸ß:</h3>
	
<%-- ¿ù»~ªí¦C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">½Ğ­×¥¿¥H¤U¿ù»~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllEmp.jsp'>List</a> all Groups.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="group.do" >
        <b>¿é¤J´ª¹Î½s¸¹ (¦pgrp00001):</b>
        <input type="text" name="Grp_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="°e¥X">
    </FORM>
  </li>

  <jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />
   
  <li>
     <FORM METHOD="post" ACTION="group.do" >
       <b>¿ï¾Ü´ª¹Î½s¸¹:</b>
       <select size="1" name="grp_no">
         <c:forEach var="GrouperVO" items="${grpSvc.all}" > 
          <option value="${GrouperVO.grp_no}">${GrouperVO.grp_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="°e¥X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="group.do" >
       <b>¿ï¾Ü­û¤u©m¦W:</b>
       <select size="1" name="grp_no">
         <c:forEach var="GrouperVO" items="${grpSvc.all}" > 
          <option value="${GrouperVO.grp_no}">${GrouperVO.grp_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="°e¥X">
     </FORM>
  </li>
</ul>


<h3>­û¤uºŞ²z</h3>

<ul>
  <li><a href='addGroup.jsp'>Add</a> a new Group.</li>
</ul>

</body>
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
</html>