<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="BIG5">
<title>login</title>
<style type="text/css">
		body {
            background-color: rgba(0, 0, 0, 0.3);
            margin: 0px;
        }
		#wrap{
            background-color: rgba(255, 255, 255, 0.6);
            margin: 0px auto;
            width: 350px;
            height: 380px;
            border-radius: 30px;
            text-align:center;
        }
        img {
            padding-top: 20px;
            display: block;
            margin: auto;
            height: 100px;
            width: 100px;
        }
        
		input[type="text"],
        input[type="password"] {
            display: block;
            margin: 20px auto -10px auto;
            height: 30px;
            border-radius: 5px;
            background-image: url(<%=request.getContextPath()%>/front_end/member/login/Login/user5.png);
            background-repeat: no-repeat;
            background-size: 20px;
            background-position: left 3px center;
            padding-left: 30px;
        }

        input[type="password"] {
            background-image: url(<%=request.getContextPath()%>/front_end/member/login/Login/pwd3.png);
            background-size: 20px;
            margin: 20px auto -10px auto;
        }
        
        #login {
            cursor: pointer;
            background: #6EC4DB;
            width: 150px;
            height: 50px;
            color: #fff;
            font-family: calibri;
            font-size: 24px;
            display: block;
            margin: 5px auto 0px auto;
            border-radius: 10px;
            box-shadow: 5px 5px 3px #333;
            letter-spacing: 2px;
            text-shadow: 1px 1px 3px #333;
        }
        
        #login:hover{
            background-color: rgba(100, 185, 209, 1);
            text-shadow: 3px 3px 3px #333;
        }
        p{
        	display:block;
        	margin-top:60px;
        	text-align:center;
        	font-size: 58px;
        	font-weight: bold;
        	font-family: ZhengHei;
        	color: #fff;
        }
        #errorMsgs{
        	display:block;
        	height:40px;
        	margin-left:60px;
        }
        ul{
        	font-size: 15px;
        	font-weight: bold;
        	margin:0px;
        	text-align:left;
        }
</style>
</head>
<body>

	<p>後台管理員登入</p>
	<div id="wrap">
	<img src="<%=request.getContextPath()%>/front_end/member/login/Login/icon5.png">
	
	<form METHOD="POST" action="<%=request.getContextPath()%>/back_end/staff/staff.do">
		<input type="text" name="staff_id" placeholder="請輸入帳號" ><br>
        <input type="password" name="staff_pwd" placeholder="請輸入密碼"><br>
        
        <div id="errorMsgs">
            <c:if test="${not empty errorMsgs}">
				<ul>
				    <c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>		
		</div>
        
        <input id="login" type="submit" value="LOGIN"><br>
        
        <input type="hidden" name="servletPath" value="<%=request.getServletPath()%>"><br>
        <input type="hidden" name="action" value="login">
	</form>
	</div>
</body>
</html>