<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouper.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    GrouperService grpSvc = new GrouperService();
    List<GrouperVO> list = grpSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����u��� - listAllGroup.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����θ�� - listAllGroup.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>���νs��</th>
		<th>�o�_�H�|���s��</th>
		<th>�a�нs��</th>
		<th>���W�}�l�ɶ�</th>
		<th>���W�����ɶ�</th>
		<th>���ʶ}�l�ɶ�</th>
		<th>���ʵ����ɶ�</th>
		<th>���μ��D</th>
		<th>���Τ��e</th>
		<th>�H�ƤW��</th>
		<th>�H�ƤU��</th>
		<th>�ثe�H��</th>
		<th>���Ϊ��A</th>
		<th>���`���μƶq</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="grouperVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${grouperVO.Grp_no}</td>
			<td>${grouperVO.Mb_id}</td>
			<td>${grouperVO.Loc_no}</td>
			<td>${grouperVO.Grp_applystart}</td>
			<td>${grouperVO.Grp_applyend}</td>
			<td>${grouperVO.Grp_start}</td> 
			<td>${grouperVO.Grp_end}</td>
			<td>${grouperVO.Grp_name}</td>
			<td>${grouperVO.Grp_content}</td>
			<td>${grouperVO.Grp_personmax}</td>
			<td>${grouperVO.Grp_personmin}</td>
			<td>${grouperVO.Grp_personcount}</td>
			<td>${grouperVO.Grp_status}</td> 
			<td>${grouperVO.Grp_follow}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="grp_no"  value="${grouperVO.grp_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="grp_no"  value="${grouperVO.grp_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>