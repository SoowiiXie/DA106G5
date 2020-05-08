<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouper.model.GrouperVO"%>
<%@ page import="com.grouper.model.GrouperService"%>
<%@ page import="com.grouper.model.*"%>

<%
	GrouperVO grouperVO = (GrouperVO) request.getAttribute("grouperVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>揪團資料修改 - update_group_input.jsp</title>

<style>
  table#table-1 {
	background-color:rgba(256,256,256,0);;
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
      #groupUpdatediv{
      position:absolute;
      top: 15%;
      right: 60%;
      width: 35%;
      background-image: url('/DA106_G5/front_end/group/webFront/c7.jpg');
      
      }
	  #groupUpdate {
		width: 100%;
		background-color: #FFFFFF;
		margin-top: 2px;
		margin-bottom: 2px;
		text-align:left;
	  }
 #groupUpdate  table, #groupUpdate th, #groupUpdate td {
 	width: 100%;
 	text-align:left;
    border: 0px solid #CCCCFF;
    
  }
  #groupUpdate th, #groupUpdate td {
  	width: 100%;
    padding: 3px;
  }
</style>

<style type="text/css">
   grp_content{
	height:30px;
  }
</style>

</head>
<div id="groupUpdatediv">
<body bgcolor='white'>

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>揪團資料修改 - update_group_input.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="group.do" name="form1">
<table id="groupUpdate">

	<tr>
		<td>揪團名稱:</td><td><%=grouperVO.getGrp_no()%></td>
		<td><input type="hidden" name="grp_no" size="45" 
			 value="<%= (grouperVO==null)? "gro00010" : grouperVO.getGrp_no()%>" /></td>
	</tr>
	<tr>
		<td>發起人會員編號:</td><td><%=grouperVO.getMb_id()%></td>
		<td><input type="hidden" name="mb_id" size="45"
			 value="<%= (grouperVO==null)? "Tommy" : grouperVO.getMb_id()%>" /></td>
	</tr>
	<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
	<tr>
		<td>地點:<font color=red><b></b></font></td>
		<td><select size="1" name="loc_no">
			<c:forEach var="locVO" items="${locSvc.all}">
				<option value="${grouperVO.loc_no}" ${(grouperVO.loc_no==locVO.loc_no)? 'selected':'' } >${locVO.loc_address}
			</c:forEach>
		</select></td>
	</tr>

	<tr>
		<td>報名開始時間:</td>
		<td><input name="grp_applystart" id="a_date1" type="text"></td>
	</tr>
	
	<tr>
		<td>報名結束時間:</td>
		<td><input name="grp_applyend" id="a_date2" type="text"></td>
	</tr>
	
	<tr>
		<td>活動開始時間:</td>
		<td><input name="grp_start" id="s_date1" type="text"></td>
	</tr>
	
	<tr>
		<td>活動結束時間:</td>
		<td><input name="grp_end" id="s_date2" type="text"></td>
	</tr>
	
	<tr>
		<td>揪團標題:</td>
		<td><input type="TEXT" name="grp_name" size="20"
			 value="<%= (grouperVO==null)? "晨跑" : grouperVO.getGrp_name()%>" /></td>
	</tr>
	
	<tr>
		<td>揪團內容:</td>
		<td><textarea type="textarea" name="grp_content" class="grp_content" rows="5"
			 value="<%= (grouperVO==null)? "別睡了，起床" : grouperVO.getGrp_content()%>"></textarea></td>
	</tr>
	
	<tr>
		<td>揪團人數上限:</td>
		<td><input type="TEXT" name="grp_personmax" size="20"
			 value="<%= (grouperVO==null)? "25" : grouperVO.getGrp_personmax()%>" /></td>
	</tr>
	
	<tr>
		<td>揪團人數下限:</td>
		<td><input type="TEXT" name="grp_personmin" size="20"
			 value="<%= (grouperVO==null)? "5" : grouperVO.getGrp_personmin()%>" /></td>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td>揪團人數:</td> -->
<!-- 		<td><input type="TEXT" name="grp_personcount" size="20" -->
<%-- 			 value="<%= (grouperVO==null)? "18" : grouperVO.getGrp_personcount()%>" /></td> --%>
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<td>揪團狀態:</td> -->
<!-- 		<td><input type="TEXT" name="grp_status" size="20" -->
<%-- 			 value="<%= (grouperVO==null)? "1" : grouperVO.getGrp_status()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>揪團追蹤人數:</td> -->
<!-- 		<td><input type="TEXT" name="grp_follow" size="20" -->
<%-- 			 value="<%= (grouperVO==null)? "80" : grouperVO.getGrp_follow()%>" /></td> --%>
<!-- 	</tr> -->
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="grp_no" value="<%=grouperVO.getGrp_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</div>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
		
<script>
// 設定時間彼此關係及時間表格內容
$(function(){  
	
		$.datetimepicker.setLocale('zh');
	    $('#a_date1').datetimepicker({
        	theme: '',             		   //theme: 'dark',
		       timepicker:true,     	   //timepicker:true,
		       step: 10,				   //step: 60 (這是timepicker的預設間隔60分鐘)
		       format:'Y-m-d H:i',       //format:'Y-m-d H:i:s',
			   value: '<%=grouperVO.getGrp_applystart()%>', // value:   new Date(),	       
        	   onShow:function(){
			   this.setOptions({
			    maxDate:$('#a_date2').val()?$('#a_date2').val():false
			   })
			  },			  
        });
        
        $.datetimepicker.setLocale('zh');
        $('#a_date2').datetimepicker({
	       theme: '',            	  	 //theme: 'dark',
	       timepicker:true,      		 //timepicker:true,
	       step: 10,				     //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
 		   value: '<%=grouperVO.getGrp_applyend()%>', // value:   new Date(),
		   onShow:function(){
			   this.setOptions({
			    minDate:$('#a_date1').val()?$('#a_date1').val():false
			   })
			  },
        });
        
        $.datetimepicker.setLocale('zh');
        $('#s_date1').datetimepicker({
	       theme: '',           	     //theme: 'dark',
	       timepicker:true,      		 //timepicker:true,
	       step: 10,				  	 //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
	 	   value: '<%=grouperVO.getGrp_start()%>', // value:   new Date(),
		   onShow:function(){
			   this.setOptions({
				minDate:$('#a_date2').val()?$('#a_date2').val():false,
			    maxDate:$('#s_date2').val()?$('#s_date2').val():false
			   })
			  },
        });
        
        $.datetimepicker.setLocale('zh');
        $('#s_date2').datetimepicker({
	       theme: '',             		 //theme: 'dark',
	       timepicker:true,      		 //timepicker:true,
	       step: 10,					 //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
		   value: '<%=grouperVO.getGrp_end()%>', // value:   new Date(),          
		   onShow:function(){
			   this.setOptions({
			    minDate:$('#s_date1').val()?$('#s_date1').val():false
			   })
			  },
        });
});     

</script>
</html>