<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="org.json.*"%>
<%@ page import="com.cmt.model.CmtVO"%>
<%@ page import="com.cmt.model.CmtService"%>
<%@ page import="com.cmt.model.*"%>
<%@ page import="com.record.model.*"%>
<%@ page import="com.msg.model.*"%>
<%@ page import="com.live.model.*"%>
<%@ page import="com.mb.model.*"%>
<%@ page import="com.location.model.*"%>
<%
	MemberService memberSvc = new MemberService();
	MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
	//登入畫面壞掉時用，其餘時候註解起來
// 	String mb_id=(String)session.getAttribute("mb_id");
// 	if(mb_id==null || "".equals(mb_id) || memberVO==null){
// 		session.setAttribute("mb_id","anjavababy520");
// 		session.setAttribute("memberVO",memberSvc.getOneMember(mb_id));
// 	}
	//正式上線時用
// 	if(memberVO==null){
// 		//還沒登入的話
// 		response.sendRedirect(request.getContextPath()+"/front_end/member/login.jsp");
// 	}else{
		//用memberVO先取得會常使用到的mb_id
		pageContext.setAttribute("mb_id", memberVO.getMb_id());
		//拿出所有紀錄
		RecordService recordSvc = new RecordService();
		List<RecordVO> list = recordSvc.getByMb_id((String)pageContext.getAttribute("mb_id"));
		pageContext.setAttribute("list", list);
		
		//拿出四個直播
		LiveService liveSvc = new LiveService();
		List<LiveVO> liveList = liveSvc.getAllTake4();
		pageContext.setAttribute("liveList", liveList);

		//拿出四則訊息
		MessageService messageSvc = new MessageService();
		List<MessageVO> messageList = messageSvc.getAllByMb_id_2((String)pageContext.getAttribute("mb_id"));
		pageContext.setAttribute("messageList", messageList);

		//拿到本頁資訊設參數
		String meOrFollow = request.getParameter("meOrFollow");
		if (meOrFollow=="follow"){
			meOrFollow = "personal_page/personal_page.jsp";
		}
		//給<c:if>裡的EL
		pageContext.setAttribute("meOrFollow", meOrFollow);
%>
<!--會員Service -->
<jsp:useBean id="memberSvcEL" scope="page" class="com.mb.model.MemberService" />
<!--紀錄Service -->
<jsp:useBean id="messageSvcEL" scope="page" class="com.msg.model.MessageService" />
<!--紀錄Service -->
<jsp:useBean id="recordSvcEL" scope="page" class="com.record.model.RecordService" />
<!--路徑Service -->
<jsp:useBean id="pathSvcEL" scope="page" class="com.path.model.PathService" />
<!--按讚Service -->
<jsp:useBean id="thumbSvcEL" scope="page" class="com.thumb.model.ThumbService" />
<!--meTooService -->
<jsp:useBean id="meTooSvcEL" scope="page" class="com.metoo.model.MeTooService" />
<!--留言Service -->
<jsp:useBean id="cmtSvcEL" scope="page"	class="com.cmt.model.CmtService" />
<!--訂單Service -->
<jsp:useBean id="ordersSvcEL" scope="page"	class="com.orders.model.OrdersService" />
<!-- <div class="col-3">&nbsp&nbsp個人頁面</div> -->
<nav aria-label="breadcrumb" class="col-12 my_breadcrumb">
	<ol class="breadcrumb m-0">
		<li class="breadcrumb-item"><a
			href="<%=request.getContextPath()%>/front_end/member/login.jsp">登入畫面</a></li>
		<li class="breadcrumb-item active" aria-current="page">個人頁面</li>
	</ol>
</nav>

<div class="w-100"></div>
<!-- 內容左邊-直播 -->
<div id="contentLeft" class="col-3 navbar-nav">
	<c:forEach var="liveVO" items="${liveList}">
	<div class="live btn btn-secondary">
		${liveVO.live_no} <img
			src="<%= request.getContextPath() %>/DBGifReader4Live?live_no=${liveVO.live_no}"
			class="liveImg" alt="live image">
	</div>
	</c:forEach>
	<div class="live btn btn-secondary">
		<div class="liveImg">查看全部</div>
	</div>
</div>

<!-- 內容中間-紀錄 -->
<c:choose>
<c:when test="${ meOrFollow == 'follow'}">
<jsp:include page="../personal_page_ws/personal_page_follow.jsp" />
</c:when>

<c:otherwise>
<jsp:include page="../personal_page_ws/personal_page_me.jsp" />
</c:otherwise>
</c:choose>

<!-- 內容右邊-上傳紀錄 -->
<div id="contentRight" class="col-3">
	<a href="" class="btn btn-primary col-11 btnAddRecord"> 
		<b>上傳紀錄</b>
	</a>
	<br>
	<br>
	<div class="sidebar-statis card--5 shadow-z1 bg-white col-11 rounded p-1">
		<h4 class="nake-title--sidebar medium d-inline-block mt-3 ml-4">Runn Able 官方Line</h4>
		<div class="statis-chart">
			<!-- <canvas id="week_chart" width="300" height="200"></canvas> -->
			<img src="<%=request.getContextPath()%>/img/lineAddFriend.PNG"
				alt="" class="col-12">
		</div>
	</div>
</div>