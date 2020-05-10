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

	// lightBox 參數設定
// 		boolean openModal = (boolean) request.getAttribute("openModal");
// 		pageContext.setAttribute("openModal", openModal);
	
%>

<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="grpSvc" scope="page" class="com.grouper.model.GrouperService" />
<jsp:useBean id="group_detailSvc" scope="page" class="com.group_detail.model.Grp_detailService" />

<html>
<head>
<title>員工資料 - listOneGroup.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


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
    color:black;
  }
  h4 {
    
    display: inline;
    font-size: 28px;
  }
</style>

<style>
	#myList{
	background:rgba(84,110,122 ,0.7);
	font-size:16px;
	width:95%;
	}
	#wrapperRight{
	margin:0px;
	padding:0px;
	background-image: url('/DA106_G5/front_end/group/webFront/c4.jpg');
	background-size: cover;
	}
  table {

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
  	border: 1px solid #000;
  	margin: 50px auto 5px auto;
  	font-size: 26px;
  	text-align:left;
  }
  body{
  	font-family:Microsoft JhengHei;
  }
  #group_table th {
  background: #91B493;
  }
  #group_table td {
  background: rgba(255, 255, 255, 0.5);
  }
  #group_table{
 	width:96%;
  }
   #number th{ 
   width:6%; 
   } 
  #follow{
	  width: 200px;
	  height: 60px;		
	  line-height: 60px;
	  font-size: 22px;		
	  background: #F17C67;
	  position: fixed;
	  top:25%;
	  left:80%;
	  border-radius:15%;
  	  margin: 0px;/*才看到到線上客服*/
  	  color:black;
 }
 #name{
 color:black;
 }
	
	#myModalLabel{
	margin:25px
}


</style>

</head>
<div id="myList">
<body>


<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<%-- 		 <h4><%=req.getRequestURI()%></h4><br> --%>
<!-- listAllGroupdetailForSelect.jsp<br> -->
		 <h4 id="name">尊貴的<%=memberVO.getMb_id()%>您好，摳泥吉娃娃，以下是您參加的揪團</h4>
<!-- 		 <h4><a href="group/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table><p> -->
<br>
		<a href="<%= request.getContextPath() %>/front_end/index.jsp?pageRun=group_follow/listAllGroupfollowForSelect.jsp" id="follow" >
		<img src="<%= request.getContextPath() %>/front_end/group/images/followIcon.png" width="50" height="50" border="0">我關注的揪團</a>


<table id="group_table" style="background-color: rgba(256,256,256,0);">
	<tr>
		<th id=number>會員編號</th>
		<th id=number>揪團編號</th>
		
		<th id=number>發起人會員編號</th>
<!-- 		<th id=location>地點</th> -->
<!-- 		<th id=time>報名開始時間</th> -->
<!-- 		<th id=time>報名結束時間</th> -->
<!-- 		<th id=time>活動開始時間</th> -->
<!-- 		<th id=time>活動結束時間</th> -->
		<th id=title>揪團標題</th>
<!-- 		<th id=comten>揪團內容</th> -->
		
<!-- 		<th id=status>報到狀態</th> -->
		
	</tr>
<%-- 	${group_detailSvc.all} --%>
<%-- 	<c:forEach var="grp_detailVO" items="${list}"> --%>
<%-- <c:forEach var="grp_detailVO" items="${grp_detailVOList}"> --%>
<c:forEach var="grp_detailVO" items="${group_detailSvc.getAllGrp_detailByMb_id(mb_id)}">
<%--     <c:if test="${grp_detailVO.grp_no==(grp_detailVOList.mb_id)}"><!-- 2 --> --%>
	<tr>		
		<td>${grp_detailVO.mb_id}</td>
<%-- 		<td><%=grp_detailVO.getGrp_no()%></td> --%>
		<td><A href="<%=request.getContextPath()%>/front_end/group_detail/group_detail.do?grp_no=${grp_detailVO.grp_no}&action=getOne_From2">${grp_detailVO.grp_no}</A></td>
				
<%-- 		<td>${grpSvc.getOneGroup(grp_detailVOList.grp_no).mb_id}</td> --%>
		<td> ${grpSvc.getOneGroup(grp_detailVO.grp_no).mb_id}</td>
<!-- 		<td> -->
<%-- 				<c:forEach var="locationVO" items="${locSvc.all}"> --%>
<%-- 		        <c:if test="${grp_detailVO.grp_no==grouperVO.grp_no}">  --%>
<%-- 		        	<c:if test="${grouperVO.loc_no==locationVO.loc_no}">   		        				     --%>
<%-- 		                 ${grouperVOlocationVO.loc_address}<br> --%>
<%-- 					${grpSvc.getOneGroup(grp_detailVO.grp_no).loc_no} --%>
<%-- 		       		 </c:if > --%>
<%-- 		        </c:if >         --%>
<%-- 					${locSvc.getOneLocation(grpSvc.getOneGroup(grp_detailVO.grp_no).loc_no).loc_address} --%>
<%-- 		        </c:forEach> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
<%--                     ${grouperVO.grp_applystart}                                            --%>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
<%-- ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_applystart} --%>
<%-- <fmt:formatDate value="${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_applystart}" pattern="yyyy-MM-dd H:mm"/> --%>
<%-- 					${grpSvc.getOneGroup(grp_detailVO.getGrp_no()).grp_applystart } --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
<%-- ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_applyend} --%>
<%-- <fmt:formatDate value="${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_applyend}" pattern="yyyy-MM-dd H:mm"/> --%>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
<%-- ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_start} --%>
<%-- <fmt:formatDate value="${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_start}" pattern="yyyy-MM-dd H:mm"/> --%>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
<!-- <!-- 		</td> --> 
<!-- 		<td> -->
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
<%-- ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_end} --%>
<%-- <fmt:formatDate value="${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_end}" pattern="yyyy-MM-dd H:mm"/> --%>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
<!-- 		</td> -->
		<td>
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
                    ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_name}
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
		</td>
<!-- 		<td> -->
<%-- 		<c:forEach var="grouperVO" items="${grpSvc.all}"> --%>
<%--                    <c:if test="${grp_detailVO.mb_id==grouperVO.mb_id}"> --%>
<%--                     ${grpSvc.getOneGroup(grp_detailVO.grp_no).grp_content} --%>
<%--                    </c:if> --%>
<%--         </c:forEach> --%>
<!-- 		</td> -->
		
		<%
			Map<Integer,String>status = new HashMap<>();
				status.put(1, "未滿");
				status.put(2, "已滿");
				status.put(3, "成功");
				status.put(4, "取消");
				request.setAttribute("status", new String[]{"","未滿", "已滿", "成功", "取消"});
			%>
			
			
<%-- 		<td>${status[grp_detailVO.grp_register]}</td> --%>
<%-- 		<td>${grouperVO.grp_personmax > groupdetailSvc.getTotalPeople(grouperVO.getGrp_no())?"未滿":"人數已滿"}</td> --%>

	</tr>
<%-- 	</c:if> --%>
   </c:forEach>
<%-- </c:forEach> - --%>
</table>

<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
            			
			<div class="modal-header">
			<h3 class="modal-title" id="myModalLabel" >揪團詳細</h3>	
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                
            </div>

			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="listOneGroupdetail2.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================= -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
<!--                 <button type="button" class="btn btn-primary">Save changes</button> -->
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>

</body>
</div>
</html>