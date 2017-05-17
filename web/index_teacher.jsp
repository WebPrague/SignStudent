<%@page import="com.webprague.StudentSign.service.UserService"%>
<%@page import="com.webprague.StudentSign.pojo.User"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.webprague.StudentSign.pojo.SignIn"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>学生助理签到平台</title>
        <link href="./css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
        <style>
            body {
                min-height: 2000px;
                padding-top: 70px;
            }
        </style>
        <script src="./js/jquery.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="./DatePicker/WdatePicker.js"></script>
    </head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">学工助理签到平台</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="change_teacher_info.java">您好！${name} </a></li>
                <li><a href="login.java">退出</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            选择指定日期：
            <div class="input-group">
                <form method="get" id="date_form">
                    <input name='date' id="dateinfo" type="text" class="form-control" onClick="WdatePicker()">
                </form>
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="data_picked()">查询指定日期</button>
                    <button class="btn btn-default" type="button" onclick="show_all()">显示全部记录</button>
                </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->
        <div class="col-lg-6 right">
            <br>
            <div class="input-group">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="show_all()">显示所有</button>
                 </span>
                <span class="input-group-btn">
                    <button class="btn btn-default" onclick="search_stu_num()" type="button">搜索</button>
                </span>
                <form method="get" id="stu_num_form">
                    <input type="text" class="form-control" name="stu_num" placeholder="请输入要搜索的学号">
                </form>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->
    </div>
</div>


<div class="container" style="margin-top: 20px;">
    <table class="table table-bordered table-striped">
        <tr>
            <td>学号</td>
            <td>姓名</td>
            <td>签到时间</td>
            <td>签离时间</td>
            <td>工作时长</td>
            <td>工作记录</td>
            <td>审核状态</td>
        </tr>
        <%List<SignIn>signerList = (List<SignIn>)request.getAttribute("teacher_list"); %>
		<%
		for(int i=0;i<signerList.size();i++){
		%>
            <tr>
                <td><a href="" data-toggle="modal" data-target="#Modal<%=signerList.get(i).getId() %>"><%=signerList.get(i).getUsername()%></a></td>
                <%String student_name = signerList.get(i).getUsername();
                	User student_user = new User();
                	student_user = UserService.student_info(student_name);
                %>
                <div class="modal fade bs-example-modal-lg" id="Modal<%=signerList.get(i).getId() %>" role="dialog" aria-label="myMoalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog ">

                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h3 class="modal-title" style="color:#000">学工助理个人信息</h3>
                            </div>

                            <div class="modal-body">
                                <p>学号：<%=student_user.getUsername() %> </p>
                                <p>姓名：<%=student_user.getName() %></p>
                                <p>性别：<%if(student_user.getSex().equals("1")){ %>男<%}else{ %>女<%} %></p>
                                <p>学院：<%=student_user.getCollege() %></p>
                                <p>电话：<%=student_user.getPhoneNumber() %></p>
                                <p>微信：<%=student_user.getWechat() %></p>
                                <p>QQ：<%=student_user.getQq() %></p>
                                <p>工作地点：<%=student_user.getWorkPlace() %></p>
                                <p>工作性质：<%=student_user.getWorkCharacter() %></p>
                            </div>

                            <div class="modal-footer" ng-controller="car">
                                <button type="button" class="btn btn-primary"
                                        data-dismiss="modal">关闭
                                </button>
                            </div>

                        </div>

                    </div>
                </div>
                <td><%=signerList.get(i).getName() %></td>
                <td><%=signerList.get(i).getSigninTime() %></td>
                <td><%=signerList.get(i).getSignoutTime() %></td>
                <%long time =signerList.get(i).getSignoutTime().getTime()- signerList.get(i).getSigninTime().getTime(); 
                long time_total=time/1000;
                long seconds = time_total%60;
                String seconds_result;
                String hour_result;
                String minutes_result;
                if(seconds<=9){
                	 seconds_result = "0"+ String.valueOf(seconds);
                }else{
                	 seconds_result = String.valueOf(seconds);
                }
                long minutes_temp = time_total/60;
                long hour = minutes_temp/60;
                if(hour<=9){
                	 hour_result = "0"+ String.valueOf(hour);
                }else{
                	 hour_result = String.valueOf(hour);
                }
                long real_minutes = minutes_temp+minutes_temp%60;
                if(real_minutes<=9){
                	 minutes_result = "0"+String.valueOf(real_minutes);
                }else{
                	 minutes_result = String.valueOf(real_minutes);
                }
                String time1 = hour_result + ":" + minutes_result + ":" +seconds_result;
                %>

                <td><%=time1 %></td>
                <td><a href="" data-toggle="modal" data-target="#myModal<%=signerList.get(i).getId()%>">工作记录</a></td>
                <div class="modal fade bs-example-modal-lg" id="myModal<%=signerList.get(i).getId() %>" role="dialog"
                     aria-label="myMoalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-lg">

                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h3 class="modal-title" style="color:#000">工作记录</h3>
                            </div>

                            <div class="modal-body">
                                <div class="jumbotron">
                                    <h3><%=signerList.get(i).getRemark()%> </h3>
                                </div>
                            </div>

                            <div class="modal-footer" ng-controller="car">
                                <button type="button" class="btn btn-primary"
                                        data-dismiss="modal">关闭
                                </button>
                            </div>

                        </div>

                    </div>
                </div>

                <td>
                    <button  class="btn btn-primary" data-toggle="modal" data-target="#buttonModal<%=signerList.get(i).getId() %>"><%=signerList.get(i).getStatus()%> 
                        </button>
                </td>
                   <div class="modal fade bs-example-modal-lg" id="buttonModal<%=signerList.get(i).getId() %>" role="dialog" aria-label="myMoalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog ">

                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h3 class="modal-title" style="color:#000">审核情况</h3>
                            </div>

                            <div class="modal-body">
                                <div align="center">
                                    <button type="button" onclick="audit_success(<%=signerList.get(i).getId() %>)" class="btn btn-danger btn-lg" data-dismiss="modal">审核通过
                                    </button>
                                    <button type="button" onclick="audit_fail(<%=signerList.get(i).getId() %>)" class="btn btn-danger btn-lg" data-dismiss="modal">审核未通过
                                    </button>
                                </div>
                            </div>

                            <div class="modal-footer" ng-controller="car">

                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>

                        </div>

                    </div>
                </div>
            </tr>
		<%} %>
    </table>

<nav>
  <%Integer pageCount = (Integer)request.getAttribute("page_count"); %>
  <%Integer pageNumber = (Integer)request.getAttribute("page_number"); %>
  <ul class="pagination">
    <li <%if(pageNumber==1){ %>class="disabled"<%} %>><a href="index_teacher.java?page=<%=pageNumber-1%>">&laquo;</a></li>
    <%for(int i = 1 ; i<=pageCount;i++ ){ %>
    <li <%if(pageNumber == i){ %>class="active"<%} %>><a href="index_teacher.java?page=<%=i %>"><%=i %></a></li>
    <%} %>
    <li <%if(pageNumber==pageCount){ %>class="disabled"<%} %>><a href="index_teacher.java?page=<%=pageNumber+1%>">&raquo;</a></li>
  </ul>
</nav>

</div>
<script>
    function data_picked() {
        var date_form = document.getElementById('date_form');
        date_form.submit();
    }
    function show_all() {
        window.location.href = "index_teacher.java";
    }
    function search_stu_num() {
        var date_form = document.getElementById('stu_num_form');
        date_form.submit();
    }
    function audit_confirm(id) {
        if (window.confirm('请选择是否通过审核？\n确定：通过审核，取消：审核不通过')) {
            window.location.href = "audit?action=audit_success&id=" + id;
        } else {
            window.location.href ="audit?action=audit_fail&id=" + id;
        }
    }
    
    function audit_success(id) {
    	window.location.href = "audit?action=audit_success&id=" + id;
//        if (window.confirm('确认通过审核？')) {
            
 //       }
    }
    function audit_fail (id) {
    	window.location.href = "audit?action=audit_fail&id=" + id;
        //if (window.confirm('确认不通过审核？')) {
            
        //}
    }
</script>

</body>
</html>
