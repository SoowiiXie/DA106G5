<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.record.model.*"%>

<%
  RecordVO recordVO = (RecordVO) request.getAttribute("recordVO");
%>
<%-- <%= recordVO==null	 %>--${record.rcd_no }-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>紀錄資料新增 - addRecord.jsp</title>

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

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>員工資料新增 - addrecord.jsp</h3></td><td> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<!-- <h3>資料新增:</h3> -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="record.do" name="form1">
<table>
<!-- 	<tr> -->
<!-- 		<td>上傳日期:</td> -->
<!-- 		<td><input name="rcd_uploadtime" id="f_date1" type="text"></td>html5(type="date")不用的原因1.瀏覽器支援度不同(IE不行)2.需要花很多時間做限制日期 -->
<!-- 	</tr> -->
	<div class="form-group m-1">
				<label style="font-size: 1.2rem;">內容： </label>
				<input type="TEXT" name="rcd_content" size="45" value="" placeholder="描述這段運動紀錄" style="font-size: 1.2rem; width:17rem;"/>
	</div>
	<jsp:useBean id="pathSvc" scope="page" class="com.path.model.PathService" />
<!-- 	<tr> -->
<!-- 		<td>路徑編號:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="path_no"> -->
<%-- 			<c:forEach var="pathVO" items="${pathSvc.all}"> --%>
<%-- 				<option value="${pathVO.path_no}" ${(recordVO.path_no==pathVO.path_no)? 'selected':'' } >${pathVO.path_no} --%>
<%-- 			</c:forEach>						<!-- 上面為什麼不用EL:因為第一次是空值產生500,不加的話每次都回到財務部 --> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
	<jsp:useBean id="mbSvc" scope="page" class="com.mb.model.MemberService" />
<!-- 	<tr> -->
<!-- 		<td>上傳會員編號:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="mb_id"> -->
<%-- 			<c:forEach var="MemberVO" items="${mbSvc.all}"> --%>
<%-- 				<option value="${MemberVO.mb_id}" ${(recordVO.mb_id==MemberVO.mb_id)? 'selected':'' } >${MemberVO.mb_id} --%>
<%-- 			</c:forEach>						<!-- 上面為什麼不用EL:因為第一次是空值產生500,不加的話每次都回到財務部 --> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
	<div class="form-group m-1">
			<label style="font-size: 1.2rem;">運動剪影： </label>
			<input type="file" name="path_pic" id="upfile1" style="font-size: 1.2rem;"/>
	</div>
	<img alt="" src="" id="rcd_insert_pic" style="height:5rem;">
</table>
<br>
	<div class="fblightbox-footer bg-white">
		<input type="hidden" name="action" value="insert">
		<input type="hidden" name="mb_id" value="" id="mb_id4rcd">
		<input type="submit" value="送出新增" class="fbbutton" id="record_submit">
		<a href="#" id="close" class="fbbutton fbclose">先不送了</a>
	</div>
</FORM>
</body>
	
<script>
// 		var x = new FileReader;
		var y = new FileReader;
// 		document.forms[0].elements[0].onchange = function() {
// 			x.readAsDataURL(this.files[0]);
// 		}
// 		$("#upfile1").onchange = function() {
// 			x.readAsDataURL(this.files[0]);
// 		}
// 		x.onloadend = function() {
// 			$("#rcd_insert_pic").src = this.result;
// 			console.log(x.herf);
// 		}
		
		document.getElementById('upfile1').onchange = function() {
			y.readAsDataURL(this.files[0]);
		}
		y.onloadend = function() {
			document.getElementById('rcd_insert_pic').src = this.result;
		}
		
		
		
// 		document.forms[0].elements[1].onchange = function() {
// 			y.readAsDataURL(this.files[0]);
// 		}
// 		y.onloadend = function() {
// 			document.images[0].src = this.result;
// 		}
		
</script>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date rcd_uploadtime = null;
  try {
	   rcd_uploadtime = recordVO.getRcd_uploadtime();
   } catch (Exception e) {
	   rcd_uploadtime = new java.sql.Date(System.currentTimeMillis());
   }
%>
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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%="rcd_uploadtime"%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>