<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta charset="BIG5">
<title>addStaff</title>
<style>
	#wrap{
		font-family: 'Mamelon';
		
		background-color: rgba(156, 199, 246, 0.6);
        margin: 10vh auto 0px auto;
        width: 30vw;
        height: 60vh;
        border-radius: 5vw;
        text-align:center;
	}
	#addStaff_title{
		font-family: 'italics_hollow';
		font-weight:bold;
		line-height:15vh;
		font-size: 5.5vh;
		color:#0373f0;
		border-bottom:3px solid #0373f0;
	}
	#addStaff_table{
		margin:0px auto;
		font-size: 3vh;
		color:#333;
	}
	tr{
		
		height:6vh;
	}
	td{
		text-align:center;
	}
	
	
	#staff_id,#staff_pwd,#staff_name{
		height: 3.5vh;
		width: 10vw;
        border-radius: 1vh;
        padding-left: 10px;
        
        font-size: 2.5vh;
        color:#333;
	}
	.tdBtn{
		width:10vw;
	}
	.btn{
		font-size: 2.8vh;
		color: #fff;
		font-family: 'Mamelon';
		
		background-color: #60A5F3;
		border-radius: 2vh;
		margin-top:2.5vh;
 		height: 6vh; 
 		width: 8vw; 
	}
	#icon,#check{
		vertical-align:middle;
	}
	#check{
		font-size: 2.5vh;
	}
	#errorMsgs{
       	margin-left:3vw;
    }
    #ul{
       	margin:0px;
       	text-align:left;
    }
	
</style>

</head>
<body>


	<div id="wrap">
	<span id="addStaff_title">新增管理員</span>
	
	<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
	<table id="addStaff_table">
		<%-- 用requestScope是因為sessionScope存著登入管理員資訊，第一次進來時會抓到登入管理員的資料 --%>
		<tr>
			<td colspan="2">
				帳號：<input id="staff_id" type="text" name="staff_id" value="${requestScope.staffVO.staff_id}"><br>
				<img id="icon"/><span id="check"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				密碼：<input id="staff_pwd" type="password" name="staff_pwd" value="${requestScope.staffVO.staff_pwd}">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				名字：<input id="staff_name" type="text" name="staff_name" value="${requestScope.staffVO.staff_name}"><br>
				<%-- 錯誤表列 --%>
				<div id="errorMsgs">
				<c:if test="${not empty errorMsgs}">
					<ul id="ul">
					    <c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td class="tdBtn">  <!-- 返回上一個include的路徑 -->
				<input type="hidden" name="includePath" value="${incluePath}">
				<input type="hidden" name="backPath" value="/back_end/staff/listAllStaff.jsp">
        		<button class="btn" type="submit" name="action" value="back">返回</button>
			</td>
			<td class="tdBtn">
				<button class="btn" id="submit" type="submit" name="action" value="insert">送出</button>
			</td>
		</tr>
    </table>
	</form>
	</div>

<script
  src="https://code.jquery.com/jquery-3.5.0.js"
  integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
  crossorigin="anonymous">
</script>

<script>  
// AJAX 測試會員帳號

	$(document).ready(function(){
		$("#staff_id").on("blur",function(){
			$.ajax({
				type:"GET",
				url: "staff.do",
				data:{
					"action":"check_id",
					"staff_id":$(this).val()
				},
				dataType: "json",
				success: function(data){
					$("#check").html(data.result);
					$("#icon").css("height","32");
					$("#icon").css("width","25");
					
					if(data.result != "此帳號可以使用"){
						$("#check").css("color","red");
						$("#icon").attr("src","<%=request.getContextPath()%>/back_end/staff/images/no.png");
						$("#submit").attr("disabled","disabled");
					}else{
						$("#check").css("color","black");
						$("#icon").attr("src","<%=request.getContextPath()%>/back_end/staff/images/ok.png");
						$("#submit").removeAttr("disabled");
					}
				},
				error: function(){alert("AJAX-class發生錯誤囉!")}
			});
		});
	});
	
</script>
</body>
</html>