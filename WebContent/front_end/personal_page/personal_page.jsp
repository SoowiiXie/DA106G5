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

		//取出所有地標
		LocationService locationSvc = new LocationService();
		List<LocationVO> locationList = locationSvc.getAll();
		request.setAttribute("locationList", locationList);
		JSONArray locationJSON = locationSvc.getAllJSON();
		request.setAttribute("locationJSON", locationJSON);
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
<div id="contentMiddle" class="btn-group row col-6">
	<!-- 分頁按鈕 -->
	<div class="btn-group col-12" id="contentTop">
		<a href="index.html" class="btn btn-primary"> <b>紀錄</b>
		</a> <a href="index.html" class="btn bg-white"> <b>追蹤</b>
		</a>
	</div>
	<c:forEach var="recordVO" items="${list}">
		<!--一則紀錄 -->
		<div class="container bg-white m-3 rounded p-0 ">
			<div class="d-inline-block mt-3 ml-3">
				<div>
					<!--會員照片-->
					<img class="img-profile rounded-circle" height=60rem; width=60rem;
						src="<%= request.getContextPath() %>/MemberPicReader?mb_id=${recordVO.mb_id}" />
					<!--會員姓名-->
					<span class="ml-2 d-none d-lg-inline text-gray-900"
						style="font-size: 1.2rem;">${memberSvcEL.getOneMember(recordVO.mb_id).mb_name}</span>
				</div>
				<!-- 紀錄上傳時間 -->
				<div>
					<span class="ml-5 d-none d-lg-inline text-gray-500">${recordVO.rcd_uploadtime}</span>
				</div>
			</div>
			<!-- 路徑圖片 -->
			<div style="overflow: hidden; height: 15rem;"
				class="mx-auto my-2 col-12">
				<img
					src="<%= request.getContextPath() %>/DBGifReader4Path?path_no=${recordVO.path_no}"
					class="rounded mx-auto d-block pathImg" alt="Responsive image">
			</div>
			<!-- 紀錄內容 -->
			<span class="ml-3 d-none d-lg-inline text-gray-900"
				style="font-size: 1.2rem;">${recordVO.rcd_content}</span>
			<div class="w-100">
				<div class="col-5 form-inline">
					<!-- 按讚按鈕 -->
					<div style="margin-bottom: 0px;">
						<c:if
							test="${thumbSvcEL.countOneThumb(recordVO.rcd_no , mb_id)==1}">
							<input class="my-2 mr-1 thumbBtn" type="image" name="submit_Btn"
								src="<%=request.getContextPath()%>/img/thumbColor.png"
								style="height: 2rem;">
						</c:if>
						<c:if
							test="${thumbSvcEL.countOneThumb(recordVO.rcd_no , mb_id)==0}">
							<input class="my-2 mr-1 thumbBtn" type="image" name="submit_Btn"
								src="<%=request.getContextPath()%>/img/thumb.png"
								style="height: 2rem;">
						</c:if>
						<input type="hidden" name="rcd_no" value="${recordVO.rcd_no}"
							class="rcd_no"> <input type="hidden" name="mb_id"
							value="${mb_id}" class="mb_id"> <input type="hidden"
							name="action" value="insert">
					</div>
					<span class="text-primary">${thumbSvcEL.countAllThumbs(recordVO.rcd_no)}</span>
					<!-- meToo按鈕 -->
					<div style="margin-bottom: 0px;">
						<c:if
							test="${meTooSvcEL.countOneMeToo(recordVO.rcd_no , mb_id)==1}">
							<input class="my-2 mr-1 meTooBtn" type="image" name="submit_Btn"
								src="<%=request.getContextPath()%>/img/yaColor.png"
								style="height: 2.2rem;">
						</c:if>
						<c:if
							test="${meTooSvcEL.countOneMeToo(recordVO.rcd_no , mb_id)==0}">
							<input class="my-2 mr-1 meTooBtn" type="image" name="submit_Btn"
								src="<%=request.getContextPath()%>/img/ya.png"
								style="height: 2.2rem;">
						</c:if>
						<input type="hidden" name="rcd_no" value="${recordVO.rcd_no}"
							class="rcd_no"> <input type="hidden" name="mb_id"
							value="${mb_id}" class="mb_id"> <input type="hidden"
							name="action" value="insert">
					</div>
					<span class="mr-2 text-success">${meTooSvcEL.countAllMeToos(recordVO.rcd_no)}</span>
					<!-- 留言按鈕-->
					<div style="margin-bottom: 0px;">
						<input class="my-2 mr-2 ml-1 cmtBtn" type="image"
							name="submit_Btn"
							src="<%=request.getContextPath()%>/img/comment.png"
							style="height: 2rem;">
					</div>
					<span>${cmtSvcEL.countAllCmts(recordVO.rcd_no)}</span>
				</div>
				<!-- 新增留言 -->
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/cmt/cmt.do"
					class="col-11 mx-auto my-2 bg-gray-200 rounded-lg">
					<img class="img-profile rounded-circle my-2 ml-1 mr-2"
						height=60rem; width=60rem;
						src="<%= request.getContextPath() %>/MemberPicReader?mb_id=${mb_id}" />
					<span class='text-primary ml-1 mr-2' style="font-size: 1.2rem;">${memberSvcEL.getOneMember(mb_id).mb_name}</span>
					<input type="text" class="bg-gray-100 w-50" placeholder=" 新留言..."
						name="cmt_content"> <input type="hidden" name="rcd_no"
						value="${recordVO.rcd_no}" class="rcd_no"> <input
						type="hidden" name="mb_id" value="${mb_id}" class="mb_id">
					<input type="hidden" name="action" value="insert"> <input
						class="align-middle mx-2 sendBtn my-2" type="image"
						name="submit_Btn"
						src="<%=request.getContextPath()%>/img/send.png"
						style="height: 2rem;">
				</FORM>
				<!-- 所有留言內容 -->
				<div class="cmtDiv" style="display: none">
					<c:forEach var="cmtVO"
						items="${cmtSvcEL.getByRcd_no(recordVO.rcd_no)}">
						<c:if test="${cmtVO.cmt_status==1}">
							<div class='col-11 mx-auto my-2 bg-gray-200 rounded-lg oneCmtDiv'>
								<img class='img-profile rounded-circle my-2 mx-1' height=60rem;
									width=60rem;
									src='<%= request.getContextPath() %>/MemberPicReader?mb_id=${cmtVO.mb_id}' />
								<span class='text-primary col-2 mx-auto'
									style="font-size: 1.2rem;">${memberSvcEL.getOneMember(cmtVO.mb_id).mb_name}</span>
								<span class='text-dark col-2 mx-auto' style="font-size: 1.2rem;">${cmtVO.cmt_content}</span>
								<c:if test='${mb_id==cmtVO.mb_id}'>
									<!-- 修改留言 -->
									<div style="display: inline;">
										<input style='display: none; height: 1rem; opacity: 0.5;'
											class='flagBtn' type='image' name='submit_Btn'
											src='<%=request.getContextPath()%>/img/pen.png'> <input
											type="hidden" name="cmt_no" value="${cmtVO.cmt_no}"
											class="cmt_no"> <input type="hidden" name="action"
											value="ajaxGetOne4Update">
									</div>
								</c:if>
								<!-- 刪除留言 -->
								<c:if test='${mb_id==cmtVO.mb_id}'>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/cmt/cmt.do"
										class="form-horizontal" style="display: inline;">
										<input type="hidden" name="cmt_no" value="${cmtVO.cmt_no}"
											class="cmt_no"> <input type="hidden" name="action"
											value="fakeDelete"> <input
											style='display: none; height: 1rem; opacity: 0.5; float: right; margin: 1rem 0;'
											class='garbageBtn' type='image' name='submit_Btn'
											src='<%=request.getContextPath()%>/img/garbage.png'>
									</FORM>
								</c:if>
								<!-- 檢舉留言 -->
								<c:if test='${mb_id!=cmtVO.mb_id}'>
									<div style="display: inline;">
										<input style='display: none; height: 1rem; opacity: 0.5;'
											class='flagBtn' type='image' name='submit_Btn'
											src='<%=request.getContextPath()%>/img/flag.png'> <input
											type="hidden" name="cmt_no" value="${cmtVO.cmt_no}"
											class="cmt_no"> <input type="hidden" name="action"
											value="insert">
									</div>
								</c:if>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<div id="contentRight" class="col-3">
	<a href="index.html" class="btn btn-primary col-11"> <b>上傳紀錄</b>
	</a><br>
	<br>
	<div
		class="sidebar-statis card--5 shadow-z1 bg-white col-11 rounded p-1">
		<h4 class="nake-title--sidebar medium d-inline-block mt-3 ml-4">Runn
			Able 官方Line</h4>
		<div class="statis-chart">
			<!-- <canvas id="week_chart" width="300" height="200"></canvas> -->
			<img src="<%=request.getContextPath()%>/img/lineAddFriend.PNG"
				alt="" class="col-12">
		</div>
	</div>
</div>