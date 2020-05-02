<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouper.model.GrouperVO"%>
<%@ page import="com.grouper.model.*"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/group/webFront/groupIndex.css">
<title>IBM Group: Home</title>

<%
	GrouperVO grouperVO = (GrouperVO) request.getAttribute("grouperVO");
%>

<style>
#service {
	width: 60px;
	height: 60px;
	border-radius: 30px;
	border: 2px #fff solid;
	background-color: #4E73DF;
	line-height: 60px;
	font-size: 14px;
	color: #fff;
	text-align: center;
	position: fixed;
	top: 80%;
	left: 85%;
	margin: 0px;
}

#fieldset, input {
	font-family: microsoft jhengHei;
}

#divAll {
	background-image: url('/DA106_G5/front_end/group/webFront/c5.jpg');
	width: 100%;
	height: 100%;
	background-size: cover;
	overflow: hidden;
}
</style>
</head>
<body>
	<div id="divAll">
		<table id="table-1">

		</table>
		<div id="form-main">
			<div id="form-div">


				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


				<li><a href='<%= request.getContextPath() %>/front_end/index.jsp?pageRun=group/listAllGroup.jsp'>揪團列表</a><br>
				<br></li>

				<li><a href='<%= request.getContextPath() %>/front_end/index.jsp?pageRun=group/addGroup.jsp'>成立揪團</a></li>

				<ul>
					<li>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/front_end/group/group.do"
							name="form1">
							<b><font color=blue>查詢揪團</font></b> <br> <b>輸入揪團編號:</b> <input
								type="text" name="grp_no"
								class="validate[required,custom[onlyLetter],length[0,100]] feedback-input"
								placeholder="請輸入查詢的揪團編號" id="name" /><br> <b>輸入會員編號:</b> <input
								type="text" name="mb_id"
								class="validate[required,custom[onlyLetter],length[0,100]] feedback-input"
								placeholder="請輸入查詢的會員編號" id="name" /><br>

							<jsp:useBean id="locationSvc" scope="page"
								class="com.location.model.LocationService" />

							<b>選擇揪團地點:</b> <select size="1" name="loc_no"
								class="validate[required,custom[onlyLetter],length[0,100]] feedback-input"
								placeholder="請輸入查詢的揪團地點" id="name" />
							<option value="">
								<c:forEach var="LocationVO" items="${locationSvc.all}">
									<option value="${LocationVO.loc_no}">${LocationVO.loc_address}
								</c:forEach> </select><br>

<!--   <li><a href='addGroup.jsp'>成立揪團</a></li> -->

								<!--         <b>揪團報名開始時間:</b> -->
								<!-- 	    <input name="grp_applystart" id="a_date1" type="text"><br> -->
								<!-- 	    <b>揪團報名結束時間:</b> -->
								<!-- 	    <input name="grp_applyend" id="a_date2" type="text"><br> -->
								<!-- 	    <b>揪團開始時間:</b> -->
								<!-- 	    <input name="grp_start" id="s_date1" type="text"><br> -->
								<!-- 	    <b>揪團結束時間:</b> -->
								<!-- 	    <input name="grp_end" id="s_date2" type="text"><br> -->


								<b>輸入揪團標題:</b> <input type="text" name="grp_name"
									class="validate[required,custom[onlyLetter],length[0,100]] feedback-input"
									placeholder="請輸入查詢的揪團標題" id="name" /><br> <b>輸入揪團內容:</b>
								<input type="text" name="grp_content"
									class="validate[required,custom[onlyLetter],length[0,100]] feedback-input"
									placeholder="請輸入查詢的揪團內容" id="name" /><br>

								<!-- 	    <b>輸入揪團人數上限:</b> -->
								<!--         <input type="text" name="grp_personmax"><br> -->
								<!--         <b>輸入揪團人數下限:</b> -->
								<!--         <input type="text" name="grp_personmin"><br> --> 
<!-- 									<b>參加揪團人數:</b> -->
<!-- 								<input type="text" name="grp_personcount" -->
<!-- 									class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" -->
<!-- 									placeholder="請輸入查詢的揪團人數" id="name" /><br> -->

								<!--    		<b>揪團狀態:</b> -->
								<!-- 		<td><INPUT TYPE="checkbox" NAME="grp_status" VALUE="1">未滿</td><br> -->
								<!-- 		<td><INPUT TYPE="checkbox" NAME="grp_status" VALUE="2">已滿</td><br> -->
								<!-- 		<td><INPUT TYPE="checkbox" NAME="grp_status" VALUE="3">取消</td><br> -->
								<!-- 		<td><INPUT TYPE="checkbox" NAME="grp_status" VALUE="4">成功</td><br> -->

<!-- 								<b>揪團關注人數:</b> <input type="text" name="grp_follow" -->
<!-- 									class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" -->
<!-- 									placeholder="請輸入查詢的關注人數" id="name" /><br> -->
       

								<div class="submit">
									<input type="submit" value="SEND" id="button-blue" /> <input
										type="hidden" name="action"
										value="listGrouper_ByCompositeQuery">
									<div class="ease"></div>
						</FORM>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>























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
