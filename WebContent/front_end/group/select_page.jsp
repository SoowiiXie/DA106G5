<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouper.model.GrouperVO"%>
<%@ page import="com.grouper.model.*"%>
<html>
<head>
<title>IBM Group: Home</title>

<%
  GrouperVO grouperVO = (GrouperVO) request.getAttribute("grouperVO");
%>

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
  <li><a href='listAllGroup.jsp'>揪團列表</a><br><br></li>
  
  
<!--   <li> -->
<!--     <FORM METHOD="post" ACTION="group.do" > -->
<!--         <b>輸入揪團編號 (如grp00001):</b> -->
<!--         <input type="text" name="grp_no"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->

<%--   <jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" /> --%>
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="group.do" > -->
<!--        <b>選擇揪團編號:</b> -->
<!--        <select size="1" name="grp_no"> -->
<%--          <c:forEach var="GrouperVO" items="${grpSvc.all}" >  --%>
<%--           <option value="${GrouperVO.grp_no}">${GrouperVO.grp_no} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
</ul>

<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do" name="form1">
        <b><font color=blue>萬用複合查詢:</font></b> <br>
        <b>輸入揪團編號:</b>
        <input type="text" name="grp_no" value="grp00001"><br>
           
        <b>輸入會員編號:</b>
        <input type="text" name="mb_id"><br>
        
<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" />        
       
        <b>輸入地標:</b>
       <select size="1" name="loc_no" >
          <option value="">
         <c:forEach var="LocationVO" items="${locationSvc.all}" > 
          <option value="${LocationVO.loc_no}">${LocationVO.loc_address}
         </c:forEach>   
       </select><br>
           
<!--         <b>揪團報名開始時間:</b> -->
<!-- 	    <input name="grp_applystart" id="a_date1" type="text"><br> -->
	    
<!-- 	    <b>揪團報名結束時間:</b> -->
<!-- 	    <input name="grp_applyend" id="a_date2" type="text"><br> -->
	    
<!-- 	    <b>揪團開始時間:</b> -->
<!-- 	    <input name="grp_start" id="s_date1" type="text"><br> -->
	    
<!-- 	    <b>揪團結束時間:</b> -->
<!-- 	    <input name="grp_end" id="s_date2" type="text"><br> -->
	    
	    <b>輸入揪團標題:</b>
        <input type="text" name="grp_name"><br>
       
        <b>輸入揪團內容:</b>
        <input type="text" name="grp_content"><br>
	   
<!-- 	    <b>輸入揪團人數上限:</b> -->
<!--         <input type="text" name="grp_personmax"><br> -->
           
<!--         <b>輸入揪團人數下限:</b> -->
<!--         <input type="text" name="grp_personmin"><br> -->
       
        <b>揪團人數:</b>
        <input type="text" name="grp_personcount"><br>
   		
<!--    		<b>揪團狀態:</b> -->
<!-- 		<td><INPUT TYPE="checkbox" NAME="grp_status" VALUE="1">未滿</td><br> -->
<!-- 		<td><INPUT TYPE="checkbox" NAME="grp_status" VALUE="2">已滿</td><br> -->
<!-- 		<td><INPUT TYPE="checkbox" NAME="grp_status" VALUE="3">取消</td><br> -->
<!-- 		<td><INPUT TYPE="checkbox" NAME="grp_status" VALUE="4">成功</td><br> -->
       
        <b>揪團關注人數:</b>
        <input type="text" name="grp_follow"><br>
                
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listGrouper_ByCompositeQuery">
     </FORM>
  </li>
</ul>

<h3>揪團管理</h3>

<ul>
  <li><a href='addGroup.jsp'>成立</a>揪團</li>
</ul>
<!-- <ul> -->
<!--   <li><a href='goinGroup.jsp'>加入</a>揪團</li> -->
<!-- </ul> -->
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp grp_applystart = null;
  try {
	  grp_applystart = grouperVO.getGrp_applystart();
   } catch (Exception e) {
	   grp_applystart = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Timestamp grp_applyend = null;
  try {
	  grp_applyend = grouperVO.getGrp_applyend();
   } catch (Exception e) {
	  grp_applyend = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Timestamp grp_start = null;
  try {
	  grp_start = grouperVO.getGrp_start();
   } catch (Exception e) {
	  grp_start = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Timestamp grp_end = null;
  try {
	  grp_end = grouperVO.getGrp_end();
   } catch (Exception e) {
	  grp_end = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<%-- <%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%> 
<%-- <%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%> 
<%-- <%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%> 

<!-- <!-- <style> --> 
<!-- /*   .xdsoft_datetimepicker .xdsoft_datepicker { */ -->
<!-- /*            width:  300px;   /* width:  300px; */ */ -->
<!-- /*   } */ -->
<!-- /*   .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box { */ -->
<!-- /*            height: 151px;   /* height:  151px; */ */ -->
<!-- /*   } */ -->
<!-- <!-- </style> --> 

<!-- <!-- <script> --> 
<!-- //         $.datetimepicker.setLocale('zh'); -->
<!-- //         $('#a_date1').datetimepicker({ -->
<!-- //         	theme: '',              //theme: 'dark', -->
<!-- // 		       timepicker:true,       //timepicker:true, -->
<!-- // 		       step: 10,				                //step: 60 (這是timepicker的預設間隔60分鐘) -->
<!-- // 		       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s', -->
<%-- <%-- 		   value: '<%=grp_applystart%>', // value:   new Date(),	        --%> 
<!-- //         	   onShow:function(){ -->
<!-- // 			   this.setOptions({ -->
<!-- // 			    maxDate:$('#a_date2').val()?$('#a_date2').val():false -->
<!-- // 			   }) -->
<!-- // 			  },			   -->
<!-- //         }); -->
        
<!-- //         $.datetimepicker.setLocale('zh'); -->
<!-- //         $('#a_date2').datetimepicker({ -->
<!-- // 	       theme: '',              //theme: 'dark', -->
<!-- // 	       timepicker:true,       //timepicker:true, -->
<!-- // 	       step: 10,				                //step: 60 (這是timepicker的預設間隔60分鐘) -->
<!-- // 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s', -->
<%-- <%-- 	   value: '<%=grp_applyend%>', // value:   new Date(), --%> 
<!-- // 		   onShow:function(){ -->
<!-- // 			   this.setOptions({ -->
<!-- // 			    minDate:$('#a_date1').val()?$('#a_date1').val():false -->
<!-- // 			   }) -->
<!-- // 			  }, -->
<!-- //         }); -->
        
<!-- //         $.datetimepicker.setLocale('zh'); -->
<!-- //         $('#s_date1').datetimepicker({ -->
<!-- // 	       theme: '',              //theme: 'dark', -->
<!-- // 	       timepicker:true,       //timepicker:true, -->
<!-- // 	       step: 10,				                //step: 60 (這是timepicker的預設間隔60分鐘) -->
<!-- // 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',  -->
<%-- <%-- 	   value: '<%=grp_start%>', // value:   new Date(), --%> 
<!-- // 		   onShow:function(){ -->
<!-- // 			   this.setOptions({ -->
<!-- // 				minDate:$('#a_date2').val()?$('#a_date2').val():false, -->
<!-- // 			    maxDate:$('#s_date2').val()?$('#s_date2').val():false -->
<!-- // 			   }) -->
<!-- // 			  }, -->
<!-- //         }); -->
        
<!-- //         $.datetimepicker.setLocale('zh'); -->
<!-- //         $('#s_date2').datetimepicker({ -->
<!-- // 	       theme: '',              //theme: 'dark', -->
<!-- // 	       timepicker:true,       //timepicker:true, -->
<!-- // 	       step: 10,				                //step: 60 (這是timepicker的預設間隔60分鐘) -->
<!-- // 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s', -->
<%-- <%-- 	   value: '<%=grp_end%>', // value:   new Date(),           --%> 
<!-- // 		   onShow:function(){ -->
<!-- // 			   this.setOptions({ -->
<!-- // 			    minDate:$('#s_date1').val()?$('#s_date1').val():false -->
<!-- // 			   }) -->
<!-- // 			  }, -->
<!-- //         }); -->
         
<!-- <!-- </script> --> 
</html>