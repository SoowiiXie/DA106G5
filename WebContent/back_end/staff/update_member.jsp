 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>update_member</title>
<style>
	#wrap{
		font-family: 'Mamelon';
		
		background-color: rgba(156, 199, 246, 0.6);
        margin: 3vh auto 0px auto;
        width: 30vw;
        height: 80vh;
        border-radius: 5vw;
        text-align:center;
	}
	#table{
		margin:0px auto 0px 3vw;
		font-size: 3vh;
		color:#333;
		table-layout: fixed;
	}
	tr{
		height:4vh;
	}
	#firstTr{
		height:7vh;
		vertical-align:bottom;
	}
	.inputBox{
		height: 3.5vh;
		width: 12vw;
        border-radius: 1vh;
        padding-left: 10px;
        
        font-size: 2vh;
        color:#333;
	}
	.email{
		width: 15vw;
	}
	#select{
		height: 3.5vh;
		width: 5vw;
        border-radius: 1vh;
        align:center;
        
        font-family: 'Mamelon';
        font-size: 2vh;
        color:#333;
	}
	#file{
		height: 3.5vh;
		width: 5vw;
		border-radius: 1vh;
		
		font-family: 'Mamelon';
        font-size: 2vh;
        color:#333;
	}
	.btn{
		font-size: 2.8vh;
		color: #fff;
		font-family: 'Mamelon';
		
		background-color: #60A5F3;
		border-radius: 2vh;
		margin-top:2.5vh;
		margin-left:3vw;
 		height: 6vh; 
 		width: 8vw; 
	}
	#errorMsgs{
       	margin-left:3vw;
       	font-size: 2.5vh;
    }
    #ul{
       	margin:0px;
       	text-align:left;
    }
</style>
</head>
<body>
	<div id="wrap">
	<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/member.do" enctype="multipart/form-data">
	<table id="table">
		<tr id="firstTr">
			<td align="right">帳號：</td><td>${memberVO.mb_id}</td>
		</tr>
		<tr>
			<td align="right">密碼：</td><td><input class="inputBox" type="password" name="mb_pwd" value="${memberVO.mb_pwd}"></td>
		</tr>
		<tr>
			<td align="right">名字：</td><td><input class="inputBox" type="text" name="mb_name" value="${memberVO.mb_name}"></td>
		</tr>
		<tr>
			<td align="right">性別：</td>
			<td>
				<c:forEach var="genderMapEntry" items="${memberGender}">
					<label>
					<input type="radio" name="mb_gender" value="${genderMapEntry.key}" ${memberVO.mb_gender==genderMapEntry.key?"checked":""}>
			    	${genderMapEntry.value}
			    	</label>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td align="right">生日：</td><td><input class="inputBox" type="text" id="f_date" name="mb_birthday" value="${memberVO.mb_birthday}"></td>
		</tr>
		<tr>
			<td align="right">e-mail：</td><td><input class="inputBox email" type="text" name="mb_email" value="${memberVO.mb_email}"></td>
		</tr>
		<tr>
			<td align="right">等級：</td><td>${memberVO.mb_lv}</td>
		</tr>
		<tr>
			<td align="right">被檢舉次數：</td><td>${memberVO.mb_rpt_times}</td>
		</tr>
		<tr>
			<td align="right">Status：</td>
			<td>
				<select id="select" name="mb_status">
					<c:forEach var="map" items="${memberStatus}">
						<option value="${map.key}" ${memberVO.mb_status==map.key?'selected':''}>${map.value}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">大頭照：</td><td><input id="file" type="file" name="mb_pic" onchange="setImg(this)"></td>
		</tr>
		<tr>
			<td align="right">預覽圖片：</td><td><img id="mb_pic" src="<%= request.getContextPath()%>/MemberPicReader?mb_id=${memberVO.mb_id}" width="100px"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="hidden" name="mb_id" value="${memberVO.mb_id}">
				<input type="hidden" name="mb_rpt_times" value="${memberVO.mb_rpt_times}">
				<input type="hidden" name="mb_lv" value="${memberVO.mb_lv}">
				
				<input type="hidden" name="includePath" value="${incluePath}">
				
		        <input type="hidden" name="backPath" value="/back_end/staff/listAllMember.jsp?mb_id=">
		        <button class="btn" type="submit" name="action" value="back">返回</button>
			
				<button class="btn" type="submit" name="action" value="back_end_update">修改</button>
			</td>
		</tr>
		<tr>
			<td colspan="2">
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