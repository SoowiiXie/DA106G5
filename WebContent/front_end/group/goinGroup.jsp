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
<title>加入揪團 - goinGroup.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>揪團資料修改 - update_group_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
<table>
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
	<tr>
		<td>地標編號:</td><td><%=grouperVO.getLoc_no()%></td>
		<td><input type="hidden" name="loc_no" size="45"
			 value="<%= (grouperVO==null)? "loc00003" : grouperVO.getLoc_no()%>" /></td>
	</tr>
	
	<tr>
		<td>報名開始時間:</td>
		<td><input name="grp_applystart" id="a_date1" type="hidden"></td>
	</tr>
	
	<tr>
		<td>報名結束時間:</td>
		<td><input name="grp_applyend" id="a_date2" type="hidden"></td>
	</tr>
	
	<tr>
		<td>活動開始時間:</td>
		<td><input name="grp_start" id="s_date1" type="hidden"></td>
	</tr>
	
	<tr>
		<td>活動結束時間:</td>
		<td><input name="grp_end" id="s_date2" type="hidden"></td>
	</tr>
	
	<tr>
		<td>揪團標題:</td><td><%=grouperVO.getGrp_name()%>入</td>
		<td><input type="hidden" name="grp_name" size="45"
			 value="<%= (grouperVO==null)? "晨跑" : grouperVO.getGrp_name()%>" /></td>
	</tr>
	
	<tr>
		<td>揪團內容:</td><td><%=grouperVO.getGrp_content()%></td>
		<td><input type="hidden" name="grp_content" size="45 rows="5""
			 value="<%= (grouperVO==null)? "別睡了，起床" : grouperVO.getGrp_content()%>" /></td>
	</tr>
	
	<tr>
		<td>揪團人數上限:</td><td><%=grouperVO.getGrp_personmax()%></td>
		<td><input type="hidden" name="grp_personmax" size="45"
			 value="<%= (grouperVO==null)? "25" : grouperVO.getGrp_personmax()%>" /></td>
	</tr>
	
	<tr>
		<td>揪團人數下限:</td><td><%=grouperVO.getGrp_personmin()%></td>
		<td><input type="hidden" name="grp_personmin" size="45"
			 value="<%= (grouperVO==null)? "5" : grouperVO.getGrp_personmin()%>" /></td>
	</tr>
	
	<tr>
		<td>目前報名人數:</td>
		<td><input type="text" name="grp_personcount" size="45"
			 value="<%= grouperVO.getGrp_personcount()>grouperVO.getGrp_personmax()? "人數已滿" : grouperVO.getGrp_personcount()+1%>"/></td>
	</tr>
	
	<tr>
		<td>揪團狀態:</td><td><%=grouperVO.getGrp_status()%></td>
		<td><input type="hidden" name="grp_status" size="45"
			 value="<%= (grouperVO==null)? "1" : grouperVO.getGrp_status()%>" /></td>
	</tr>
	<tr>
		<td>揪團追蹤人數:</td><td><%=grouperVO.getGrp_follow()%></td>
		<td><input type="hidden" name="grp_follow" size="45"
			 value="<%= (grouperVO==null)? "80" : grouperVO.getGrp_follow()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="grp_no" value="<%=grouperVO.getGrp_no()%>">
<button type="submit" <%= grouperVO.getGrp_personcount()>grouperVO.getGrp_personmax()?"disabled":""%>><%= grouperVO.getGrp_personcount()>grouperVO.getGrp_personmax()?"人數已滿":"加入揪團"%></button></FORM>
</body>



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
		       format:'Y-m-d H:i:s',       //format:'Y-m-d H:i:s',
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
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
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
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
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
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
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