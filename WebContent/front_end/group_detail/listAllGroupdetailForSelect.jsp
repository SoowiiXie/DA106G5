<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.group_detail.model.*"%>
<%@ page import="com.grouper.model.*"%>
<%@ page import="com.location.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mb.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%	
	MemberService memberSvc = new MemberService();
	MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
	pageContext.setAttribute("mb_id", memberVO.getMb_id());
	
	List<Grp_detailVO> grp_detailVOList = (List<Grp_detailVO>)request.getAttribute("grp_detailVOList"); //EmpServlet.java(Concroller), 存入req的empVO物件
	pageContext.setAttribute("grp_detailVOList",grp_detailVOList);
	GrouperService grouperSvc = new GrouperService();
	List<GrouperVO> grouperVOList = grouperSvc.getAll();//5
	
// 	GrouperVO grouperVO = grouperSvc.getAll(map)
	
	LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
	
// 	List<Grp_detailVO> list = request.getAttribute();
//     pageContext.setAttribute("list",list);
%>

<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />
<jsp:useBean id="group_detailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />

<html>
<head>
<title>員工資料 - listOneGroup.jsp</title>

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
	width: 600px;
	background-color: white;
	margin: 50px auto 5px auto;
  }
  table, th, td {
    border: 1px solid #000;
  }
  th, td {
    padding: 20px;
    text-align: center;
  }
  #group_table, #group_table tr,#group_table td,#group_table th{
  	width: 1600px;
  	border: 1px solid #000;
  	margin: 50px auto 5px auto;
  }
  body{
  	font-family:Microsoft JhengHei;"
  }
  #group_table th{
  background: #D5E0CC;
  }
</style>

</head>
<body>


<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		 <h4><%=memberVO.getMb_id()%>，摳泥吉娃娃，以下是你參加的揪團</h4><br>
<!-- 		 <h4><a href="group/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table><p> -->
<table id="group_table" style="background-color: rgba(256,256,256,0);">
	<tr>
		<th>會員編號</th>
		<th>揪團編號</th>
		
		<th>發起人會員編號</th>
		<th>地點</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>活動開始時間</th>
		<th>活動結束時間</th>
		<th>揪團標題</th>
		<th>揪團內容</th>
		
		<th>報到狀態</th>
		
	</tr>
<%-- 	${group_detailSvc.all} --%>
<%-- 	<c:forEach var="grp_detailVO" items="${list}"> --%>
<%-- <c:forEach var="grp_detailVO" items="${grp_detailVOList}"> --%>
<c:forEach var="grp_detailVO" items="${group_detailSvc.getAllGrp_detailByMb_id(mb_id)}">
<%--     <c:if test="${grp_detailVO.grp_no==(grp_detailVOList.mb_id)}"><!-- 2 --> --%>
	<tr>		
		<td>${grp_detailVO.mb_id}</td>
<%-- 		<td><%=grp_detailVO.getGrp_no()%></td> --%>
		<td>${grp_detailVO.grp_no}</td>
				
<%-- 		<td>${grpSvc.getOneGroup(grp_detailVOList.grp_no).mb_id}</td> --%>
		<td> ${grpSvc.getOneGroup(grp_detailVO.grp_no).mb_id}</td>
		<td>
<%-- 				<c:forEach var="locationVO" items="${locSvc.all}"> --%>
<%-- 		        <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">  --%>
<%-- 		        	<c:if test="${grouperVO.loc_no==locationVO.loc_no}">   		        				     --%>
<%-- 		                 ${grouperVOlocationVO.loc_address}<br> --%>
<%-- 					${grpSvc.getOneGroup(grp_detailVO.grp_no).loc_no} --%>
<%-- 		       		 </c:if > --%>
<%-- 		        </c:if >         --%>
					${locSvc.getOneLocation(grpSvc.getOneGroup(grp_detailVO.grp_no).loc_no).loc_address}
<%-- 		        </c:forEach> --%>
		</td>
		<td>
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
<%--                     ${grouperVO.grp_applystart}                                            --%>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
<%-- ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_applystart} --%>
<fmt:formatDate value="${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_applystart}" pattern="yyyy-MM-dd hh:mm"/>
<%-- 					${grpSvc.getOneGroup(grp_detailVO.getGrp_no()).grp_applystart } --%>
		</td>
		<td>
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
<%-- ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_applyend} --%>
<fmt:formatDate value="${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_applyend}" pattern="yyyy-MM-dd hh:mm"/>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
		</td>
		<td>
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
<%-- ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_start} --%>
<fmt:formatDate value="${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_start}" pattern="yyyy-MM-dd hh:mm"/>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
<!-- 		</td> -->
		<td>
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
<%-- ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_end} --%>
<fmt:formatDate value="${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_end}" pattern="yyyy-MM-dd hh:mm"/>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
		</td>
		<td>
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
                    ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_name}
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
		</td>
		<td>
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
                    ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_content}
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
		</td>
		
		<%
			Map<Integer,String>status = new HashMap<>();
				status.put(1, "未滿");
				status.put(2, "已滿");
				status.put(3, "成功");
				status.put(4, "取消");
				request.setAttribute("status", new String[]{"","未滿", "已滿", "成功", "取消"});
			%>
			
			
		<td>${status[grp_detailVO.grp_register]}</td>

	</tr>
<%-- 	</c:if> --%>
   </c:forEach>
<%-- </c:forEach> - --%>
</table>

</body>
</html>