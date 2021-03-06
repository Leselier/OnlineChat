<%@ page contentType="text/html;charset=utf-8" %>
<%
	String strErr = (String)request.getAttribute("strErr");
	if(strErr == null)
		strErr = "";
%>
<jsp:useBean id="userRaw" type="xcbean.XCUser" scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册 - OnlineChat</title>
	<link rel="stylesheet" type="text/css" href="/static/css/xc_basic.css">
	<link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
	<script src="/static/js/jquery-3.2.1.min.js"></script>
	<script src="/static/layer/layer.js"></script>
	<script>
		function toLogin()
		{
			layer.msg('Congratulations! 注册成功啦〜', {
			  time: 0 //不自动关闭
			  ,btn: ['去登录']
			  ,yes: function(index){
			    layer.close(index);
			    window.location.href = "/";
			  }
			});
		}
	</script>
</head>
<body class="background-gray">
	<header id="header">
		<div>OnlineChat</div>
	</header>
	<%
		if(strErr.equals("success")){
			out.print("<script>toLogin();</script>");
		}
	%>
	<div class="banner banner-text center">
		I Would Like to Talk!
	</div>
	<div id="body">
		<div style="padding-left: 20px;">
			<a href="/" class="link-primary">&lt; 已有帐号？ 去登陆</a>
		</div>
		<div class="background-white round box-shadow" style="width: 35%; margin: 0px auto; padding: 30px;">
			<div class="text-warning text-info"><%= strErr %></div>
			<div style="padding-bottom: 40px;">
				<form action="" method="post">
					<div class="form-ele">用户名</div>
					<input type="text" name="username" class="textbox textbox-block" placeholder="仅限字母、数字，6-20位" value='<jsp:getProperty name="userRaw" property="user_name" />'>

					<div class="form-ele">密码</div>
					<input type="password" name="password" class="textbox textbox-block" placeholder="6-20位">
					<div class="form-ele">重复密码</div>
					<input type="password" name="repassword" class="textbox textbox-block">
					<div class="form-ele">邮箱</div>
					<input type="text" name="email" class="textbox textbox-block" value='<jsp:getProperty name="userRaw" property="user_email" />'>
					<input type="submit" value="注册" class="btn btn-primary btn-block">
				</form>
			</div>
		</div>
	</div>
</body>
</html>