<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <style type="text/css">
        body {
            background-image: url(login/Login/running.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            

            margin: 100px auto;
            width: 850px;
            height: auto;
        }

        #wrap,
        #wrap2 {
            float: left;
            background-color: rgba(255, 255, 255, 0.6);
            margin: 0px 30px;
            width: 350px;
            height: 550px;
            border-radius: 30px;
        }

        img {
            padding-top: 20px;
            display: block;
            margin: auto;
            height: 100px;
            width: 100px;
        }

        img+p {
            margin: 0px;
            text-align: center;
            font-family: calibri;
            font-size: 5vh;
            color: #666;
        }

        input[type="text"],
        input[type="password"] {
            display: block;
            margin: 20px auto -10px auto;
            height: 30px;
            border-radius: 5px;
            background-image: url(login/Login/user5.png);
            background-repeat: no-repeat;
            background-size: 20px;
            background-position: left 3px center;
            padding-left: 30px;
        }

        input[type="password"] {
            background-image: url(login/Login/pwd3.png);
            background-size: 20px;
        }

        #login {
            cursor: pointer;
            background: #6EC4DB;
            width: 150px;
            height: 50px;
            color: #fff;
            font-family: calibri;
            font-size: 3.2vh;
            display: block;
            margin: 5px auto 0px auto;
            border-radius: 10px;
            box-shadow: 5px 5px 3px #333;
            letter-spacing: 2px;
            text-shadow: 1px 1px 3px #333;
        }

        #div1,
        #div2 {
            float: left;
            margin-top: 30px;
            margin-left: 75px;
            width: 90px;
            height: 30px;
            background-color: rgba(110, 195, 219, 0.7);
            border-radius: 5px;
            box-shadow: 2px 3px 3px #333;
            cursor: pointer;

            color: #fff;
            font-weight: bold;
            font-size: 2.2vh;
            font-family: ZhengHei;
            text-align: center;
            line-height: 30px;
            text-shadow: 1px 1px 3px #333;
        }

        #div2 {
            margin-left: 20px;
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
            margin: 30px auto;
            height: 120px;
            width: 120px;
        }

        #group_img {
            width: auto;
        }

        #shop,
        #group {
            display: block;
            margin: 30px auto 0px auto;
            width: 200px;
            height: 40px;
            background-color: rgba(250, 124, 146, 0.7);
            border-radius: 10px;
            box-shadow: 5px 5px 3px #333;
            cursor: pointer;


            color: #fff;
            font-weight: bold;
            font-size: 2.3vh;
            font-family: ZhengHei;
            letter-spacing: 5px;
            text-shadow: 2px 2px 3px #333;
        }

        #group {
            background-color: rgba(71, 207, 115, 0.7);

        }
        
        #errorMsgs{
        	display:block;
        	height:40px;
        	margin-left:60px;
        }
        ul{
        	font-size: 2vh;
        	font-weight: bold;
        	margin:0px;
        }
    </style>
    <script>

    </script>
</head>

<body>
    <div id="wrapAll">
        <div id="wrap2">
            <div>
                <img src="login/Login/shopping_cart3.png" id="shopping_cart">
                <input id="shop" type="button" value="商城"><br>
            </div>

            <div>
                <img src="login/Login/group5.png" id="group_img">
                <input id="group" type="button" value="揪團"><br>
            </div>
        </div>

        <div id="wrap">
        	<form METHOD="POST" action="member.do">
            <img src="login/Login/icon5.png">
            <p>Sign In</p>
            <input name="mb_id" type="text" placeholder="請輸入帳號"><br>
            <input name="mb_pwd" type="password" placeholder="請輸入密碼"><br>
            
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
            <a href="addMember.jsp"><div id="div1">會員註冊</div></a>
            <div id="div2">忘記密碼</div><br>
            
            <input type="hidden" name="servletPath" value="<%=request.getServletPath()%>">
       		<input type="hidden" name="action" value="getOne_For_Display">
            </form>
        </div>
    </div>

</body>

</html>
