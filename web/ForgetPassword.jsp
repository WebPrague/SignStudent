<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>学工助理签到平台 V1.0</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="./css/style_login.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<!-- main -->
		<div class="main">
			<h1>密码找回</h1>
			<div class="input_form">
				<form id="login_form" action="ForgetPassword.action" method="post" onkeypress="if(event.keyCode==13||event.which==13){ send();}">
					<input type="text" name="username" id="username" value="" placeholder="请输入账号" >
					<br>
					<br>
					<input type="text" name="mail" id="mail" value="" placeholder="请输入邮箱" >
				</form>
			</div>

			<div class="ckeck-bg">
				<div class="checkbox-form">
					<div class="check-left">
							<input type="submit" onclick="login()" value="返回登录">
					</div>
					<div class="check-right">
							<input onclick="send()"  type="submit" value="发送邮件">
					</div>
					<div class="clearfix"> </div>
				</div>
			</div> 
		</div>
		<div class="footer">
		</div>
	<!-- //main -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script>
        function login() {
       	window.location.href = "login.java";
        }
        function send() {
       	//window.location.href = "login.java";
       	login_form = document.getElementById('login_form');
            login_form.submit();
        }
    </script>

    ${msg }
</body>
</html>