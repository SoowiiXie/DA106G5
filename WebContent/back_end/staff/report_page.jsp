<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>Runnable 後台系統</title>
<style type="text/css">
	#rpt_top_bar{
		height:10vh;
	}
	.rpt_class{
		float:left;
		line-height:6vh;
		height: 6vh;
		width: 10vw;
		margin:0px auto 0px 2.5vw;
		
		font-family:'JhengHei';
		font-size: 2.5vh;
		font-weight:bold;
		text-align:center;
		color: #0373f0;
		border-radius: 50px;
		border: 4px solid #f3f8fe;
		border-bottom: 4px solid #0373f0;
	}
	.rpt_class:hover{
		font-size: 3.5vh;
	}
	#report_content{
		position: fixed;
		top: 23vh;
		left: 15vw;
		
		height:76.8vh;
		width: 84vw;
		
 		overflow-y:scroll; 
     	overflow-x:hidden; 
	}

</style>
</head>
<body>
	    <div id="rpt_top_bar">
			
			<c:forEach var="map" items="${rptType}">
			<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
				<div class="rpt_class" onclick="javascript:this.parentNode.submit();">${map.value}</div>
				<input type="hidden" name="rpt_type" value="${map.key}">
				<input type="hidden" name="action" value="select_rpt">
				<input type="hidden" name="includePath" value="${includePath}">
			</form>
			</c:forEach>	
			
	    </div>
	    <div id="report_content">
	    	
			<c:if test="${not empty rpt_includePath}">
				<jsp:include page="${rpt_includePath}" flush="true"/>
			</c:if>
			
<!-- 			被送出的檔案要導回原畫面時，被送出的檔案需加上 -->
<%-- 			<input type="hidden" name="includePath" value="${includePath}"> --%>
<%-- 			<input type="hidden" name="rpt_includePath" value="${rpt_includePath}"> --%>
<!-- 			在 Controller 要 setAttribute includePath 與 rpt_includePath -->
			
	    </div>
</body>
</html>