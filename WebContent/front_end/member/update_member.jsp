<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>update_member</title>
</head>
<style>
	#wrapAll{
		margin: 10vh auto 0px auto; 
		background-color: rgba(49, 88, 202, 0.3);
            
        width: 35vw;
        height: 75vh;
        border-radius: 30px;
	}
	#update_table{
		margin:5vh auto 0px auto;
		
		border:1px solid #000;
		color: #333;
		width:fit-content;
		background-color:rgba(49, 88, 202, 0);
		
		font-size: 2.5vh;
		font-weight:bold;
		font-family:ZhengHei;
		line-height:3vh;
		text-align:right;
	}
	#update_table td{
		border:1px solid #000;
		padding:1vh;
	}
	#update_btn{
		width:6vw;
		height:5vh;
		border-radius: 1.5vh;
		box-shadow: 2px 3px 3px #333;
		background-color: rgba(110, 195, 219, 0.7);
		cursor: pointer;
		
		color: #fff;
		font-weight: bold;
        font-size: 2vh;
        font-family: ZhengHei;
        text-align: center;
        line-height: 3.3vh;
        text-shadow: 1px 1px 3px #333;
	}
	
	.update_first_td{
		text-align: right;
	}
	.update_second_td{
		text-align:left;
	}
	#td_btn{
		height:7vh;
	}
</style>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
		<!-- <div class="col-3">&nbsp&nbsp個人頁面</div> -->
	<nav aria-label="breadcrumb" class="col-12 my_breadcrumb">
		<ol class="breadcrumb m-0">
			<li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/front_end/member/login.jsp">登入畫面</a></li>
			<li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/front_end/member/login.jsp">個人頁面</a></li>
			<li class="breadcrumb-item active" aria-current="page">修改個人資料</li>
		</ol>
	</nav>

	<div id="wrapAll">
	<form METHOD="POST" action="<%=request.getContextPath()%>/front_end/member/member.do" enctype="multipart/form-data">
		<table id="update_table">
		<tr>
			<td class="update_first_td">帳號：</td>
			<td class="update_second_td">${memberVO.mb_id}</td>
		</tr>
		<tr>
			<td class="update_first_td">密碼：</td>
			<td class="update_second_td"><input type="password" name="mb_pwd" value="${memberVO.mb_pwd}"></td>
		</tr>
		<tr>
			<td class="update_first_td">名字：</td>
			<td class="update_second_td"><input type="text" name="mb_name" value="${memberVO.mb_name}"></td>
		</tr>
		<tr>
			<td class="update_first_td">性別：</td>
			<td class="update_second_td">
				<c:forEach var="genderMapEntry" items="${memberGender}">
					<label>
					<input type="radio" name="mb_gender" value="${genderMapEntry.key}" ${memberVO.mb_gender==genderMapEntry.key?"checked":""}>
			    	${genderMapEntry.value}
			    	</label>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td class="update_first_td">生日：</td>
			<td class="update_second_td"><input type="text" id="f_date" name="mb_birthday" value="${memberVO.mb_birthday}"></td>
		</tr>
		<tr>
			<td class="update_first_td">e-mail：</td>
			<td class="update_second_td"><input type="text" name="mb_email" value="${memberVO.mb_email}"></td>
		</tr>
		<tr>
			<td class="update_first_td">大頭照：</td>
			<td class="update_second_td"><input type="file" name="mb_pic" onchange="setImg(this)"></td>
		</tr>
		<tr>
			<td class="update_first_td">預覽圖片：</td>
			<td class="update_second_td"><img id="mb_pic" src="<%= request.getContextPath()%>/MemberPicReader?mb_id=${memberVO.mb_id}" width="100px"></td>
		</tr>
		<tr>
			<td colspan="2" id="td_btn">
				<input type="hidden" name="mb_id" value="${memberVO.mb_id}"><br>
				<input type="hidden" name="mb_lv" value="${memberVO.mb_lv}"><br>
				<input type="hidden" name="mb_rpt_times" value="${memberVO.mb_rpt_times}"><br>
				<input type="hidden" name="mb_status" value="${memberVO.mb_status}"><br>
				
				<input type="hidden" name="servletPath" value="<%=request.getServletPath()%>">
				<input type="hidden" name="includePath" value="/front_end/member/update_member.jsp">
		        <button id="update_btn" type="submit" name="action" value="update">修改</button>
			</td>
		</tr>
        </table>
	</form>
	</div>
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
	<script>
	
	// 預覽圖片
	function setImg(input){
  		if(input.files && input.files[0]){
  			var reader = new FileReader();
  			reader.onload = function (e) {
    			document.getElementById("mb_pic").setAttribute("src", e.target.result);
    		}
    	reader.readAsDataURL(input.files[0]);
  		}
	}
	
	// 日期
	
 	$.datetimepicker.setLocale('zh');
        $('#f_date').datetimepicker({
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
        });
	</script>
</body>
</html>