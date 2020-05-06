<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <style type="text/css">
        body {
            background-image: url(<%=request.getContextPath()%>/front_end/member/login/Login/running.jpg);
            background-repeat: no-repeat;
            background-size: cover;
        }

		#wrapAll{
			margin: 20vh auto;
			width:fit-content;
		}
		
        #wrap,
        #wrap2 {
            float: left;
            background-color: rgba(255, 255, 255, 0.6);
            
            width: 18vw;
            height: 55vh;
            border-radius: 30px;
        }
        #wrap{
        	margin-left: 5vw;
        }

        img {
            padding-top: 2vh;
            display: block;
            margin: auto;
            height: 10vh;
            width: 5vw;
        }

        img+p {
            margin: 0px;
            text-align: center;
            font-family: calibri;
            font-size: 4vh;
            color: #666;
        }

        input[type="text"],
        input[type="password"] {
            display: block;
            margin: 2.2vh auto -1.1vh auto;
            height: 2.8vh;
            border-radius: 0.8vh;
            background-image: url(<%=request.getContextPath()%>/front_end/member/login/Login/user5.png);
            background-repeat: no-repeat;
            background-size: 2.1vh;
            background-position: left 3px center;
            padding-left: 3vh;
        }

        input[type="password"] {
            background-image: url(<%=request.getContextPath()%>/front_end/member/login/Login/pwd3.png);
        }

        #login {
            cursor: pointer;
            background: #6EC4DB;
            width: 8vw;
            height: 5vh;
            color: #fff;
            font-family: calibri;
            font-size: 2.8vh;
            display: block;
            margin: 1vh auto 0px auto;
            border-radius: 1vh;
            box-shadow: 5px 5px 3px #333;
            letter-spacing: 0.2vw;
            text-shadow: 1px 1px 3px #333;
        }
		#wrap_bottom{
			width:fit-content;
			margin: 2.8vh auto 0px auto;
		}
        #div1,
        #div2 {
            float: left;
            width: 6vw;
            height: 3.3vh;
            background-color: rgba(110, 195, 219, 0.7);
            border-radius: 5px;
            box-shadow: 2px 3px 3px #333;
            cursor: pointer;

            color: #fff;
            font-weight: bold;
            font-size: 2vh;
            font-family: ZhengHei;
            text-align: center;
            line-height: 3.3vh;
            text-shadow: 1px 1px 3px #333;
        }

        #div2 {
             margin-left: 1vw; 
        }

        #div1:hover,
        #div2:hover,
        #login:hover {
            background-color: rgba(100, 185, 209, 1);
            text-shadow: 3px 3px 3px #333;
        }

        #shopping_cart,
        #group_img {
            display: block;
            margin: 3vh auto;
            height: 11.2vh;
            width: 7vw;
        }

        #group_img {
            width: auto;
        }

        #shop,
        #group {
            display: block;
            margin: 1vh auto 0px auto;
            width: 10vw;
            height: 3.8vh;
            background-color: rgba(250, 124, 146, 0.7);
            border-radius: 1vh;
            box-shadow: 5px 5px 3px #333;
            cursor: pointer;


            color: #fff;
            font-weight: bold;
            font-size: 2.3vh;
            font-family: ZhengHei;
            letter-spacing: 0.3vw;
            text-shadow: 2px 2px 3px #333;
        }

        #group {
            background-color: rgba(71, 207, 115, 0.7);

        }
        
        #errorMsgs{
        	display:block;
        	height:3.5vh;
        	margin-left:3vw;
        }
        ul{
        	margin:0px;
        }
        li{
        	font-size: 1.5vh;
        	font-weight: bold;
        	margin-top: 0.5vh;
        }
    </style>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script>
			//  改EL
		$(document).ready(function(){
			<c:if test="${empty errorMsgs && 'insert'.equals(param.action)}">
				Swal.fire({
				  icon: 'success',
				  title: '驗證信已寄出',
				  text: '請至註冊信箱收信'
				})
			</c:if>
		});
		
	</script>
</head>

<body>
    <div id="wrapAll">
        <div id="wrap2">
            <div>
                <img src="<%=request.getContextPath()%>/front_end/member/login/Login/shopping_cart3.png" id="shopping_cart">
                <input id="shop" type="button" value="商城"><br>
            </div>

            <div>
                <img src="<%=request.getContextPath()%>/front_end/member/login/Login/group5.png" id="group_img">
                <input id="group" type="button" value="揪團"><br>
            </div>
        </div>

        <div id="wrap">
        <form METHOD="POST" action="<%=request.getContextPath()%>/front_end/member/member.do">
            <img src="<%=request.getContextPath()%>/front_end/member/login/Login/icon5.png">
            <p>Sign In</p>
            <input name="mb_id" type="text" placeholder="請輸入帳號"><br>
            <input name="mb_pwd" type="password" placeholder="請輸入密碼"><br>
            <input type="hidden" name="servletPath" value="<%=request.getServletPath()%>">
       		<input type="hidden" name="action" value="getOne_For_Display">
        
            
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
        </form>
            <div id="wrap_bottom">
	            <a href="<%=request.getContextPath()%>/front_end/member/addMember.jsp"><div id="div1">會員註冊</div></a>
	            <div id="div2">忘記密碼</div><br>
            </div>
            
        </div>
    </div>

</body>

</html>
