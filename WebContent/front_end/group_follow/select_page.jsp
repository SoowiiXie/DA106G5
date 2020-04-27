<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Groupfollow: Home</title>

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
   <tr><td><h3>IBM Groupfollow: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Groupfollow: Home</p>

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
  <li><a href='listAllGroupfollow.jsp'>List</a> all Groups.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="group_follow.do" >
        <b>輸入揪團編號 (如grp00001):</b>
        <input type="text" name="grp_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <h3>數量查詢:</h3>

  <jsp:useBean id="group_followSvc" scope="page" class="com.group_follow.model.Group_followService" />
   
  <li>
     <FORM METHOD="post" ACTION="group_follow.do" >
       <b>選擇揪團編號:</b>
       <select size="1" name="grp_no">
         <c:forEach var="Group_followVO" items="${group_followSvc.all}" > 
          <option value="${Group_followVO.grp_no}">${Group_followVO.grp_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  <li>
    <FORM METHOD="post" ACTION="group_follow.do" >
        <b>輸入揪團編號 (如grp00001):</b>
        <input type="text" name="grp_no">
        <input type="hidden" name="action" value="getGroupFollowCount">
        <input type="submit" value="送出">
    </FORM>
  </li>
   <li>
    <FORM METHOD="post" ACTION="group_follow.do" >
        <b>輸入會員編號 :</b>
        <input type="text" name="mb_id">
        <input type="hidden" name="action" value="getPeopleFollowCount">
        <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<h3>揪團管理</h3>

<ul>
  <li><a href='addGroupfollow.jsp'>Add</a> a new Groupfollow.</li>
</ul>

</body>
</html>