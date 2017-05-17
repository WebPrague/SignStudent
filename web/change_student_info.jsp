<%@page import="com.webprague.StudentSign.pojo.User"%>
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
<link href="./css/style_register.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<!-- main -->
		<div class="main">
			<h1>学工助理签到平台-助理个人信息修改</h1>
			<div class="input_form">
				<form id="register_form" method="post" onkeypress="if(event.keyCode==13||event.which==13){ register();}">
                    <input type="hidden" name="type" value="student"/>
                    学号（不可修改）：
                    <input readonly="readonly" placeholder="学号" type="text" name="username" value="${user.username}"/>
                    姓名（不可修改）：
                    <input readonly="readonly" placeholder="姓名" type="text" name="name" value="${user.name }"/>
                    性别：
                    <select name="sex">
                    <%	User user = (User)request.getAttribute("user");%>
                        <option value="">请选择性别</option>
			            <option <%if(user.getSex().equals("1")){%> selected = "selected" <%} %>  value="1" >男</option>
                        <option  <%if(user.getSex().equals("2")){ %> selected = "selected"   <%}%> value="2">女</option>
    	            </select>
                    密码：
                    <input id="password" placeholder="如果不修改请留空" type="password" name="password">
                    重复密码：
                    <input id="re_password" placeholder="重复密码" type="password" name="verifypassword">
                    
                    所属学院：
                    <select name="college" value="请选择学院">
                        <option selected="selected" value="${user.college }">${user.college }</option>
                        <option value="蒙古学学院">蒙古学学院</option>
                        <option value="民族学与社会学学院">民族学与社会学学院</option>
                        <option value="文学与新闻传播学院">文学与新闻传播学院</option>
                        <option value="历史与旅游文化学院">历史与旅游文化学院</option>
                        <option value="哲学学院">哲学学院</option>
                        <option value="经济管理学院">经济管理学院</option>
                        <option value="法学院">法学院</option>
                        <option value="外国语学院">外国语学院</option>
                        <option value="公共管理学院">公共管理学院</option>
                        <option value="马克思主义学院">马克思主义学院</option>
                        <option value="数学科学学院">数学科学学院</option>
                        <option value="电子信息工程学院">电子信息工程学院</option>
                        <option value="化学化工学院">化学化工学院</option>
                        <option value="生命科学学院">生命科学学院</option>
                        <option value="环境与资源学院">环境与资源学院</option>
                        <option value="计算机学院(软件学院)">计算机学院(软件学院)</option>
                        <option value="继续教育学院">继续教育学院</option>
                        <option value="国际教育学院">国际教育学院</option>
                        <option value="艺术学院">艺术学院</option>
                        <option value="交通学院">交通学院</option>
                        <option value="创业学院">创业学院</option>
                        <option value="体育教学部">体育教学部</option>
                        <option value="预科">预科</option>
    	            </select>
                    联系电话：
                    <input placeholder="电话" type="text" name="phonenumber" value="${ user.phoneNumber}">
                    微信：
                    <input placeholder="微信" type="text" name="wechat" value="${user.wechat}">
                    QQ：
                    <input placeholder="QQ" type="text" name="qq" value="${user.qq}">
                    工作地点：
                    <input placeholder="工作地点" type="text" name="workplace" value="${user.workPlace}">
                    工作性质：
                    <input placeholder="工作性质" type="text" name="workcharacter" value="${user.workCharacter}">
                            邮箱：
                    <input placeholder="邮箱" type="text" name="mail" value="${user.mail}">
				</form>
			</div>

			<div class="ckeck-bg">
				<div class="checkbox-form">
                    <div class="check-left">
							<input onclick="location='index_student.java'" type="submit" value="返回主页">
					</div>
					<div class="check-right">
							<input onclick="register()" type="submit" value="确认修改">
					</div>
                    <div class="clearfix"></div>
				</div>
			</div>

		</div>
		<div class="footer">
		</div>
	<!-- //main -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script>
        function register() {
        	password = document.getElementById('password').value;
        	re_password = document.getElementById('re_password').value;
        	if(password!=re_password){
				alert("两次密码输入不一致，请重新输入");
				return false;
			}
            login_form = document.getElementById('register_form');
            login_form.submit();
        }
    </script>

</body>
</html>
