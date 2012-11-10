<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "databean.*" %>
<html>
	<head>
		<title> Breeze </title>
		<link type="text/css" href="css/bootstrap.css" rel="stylesheet" media="screen">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>	
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="breeze.js"></script>
	</head>
	<body style="padding-top : 60px">	
		<div style="width:1300px;">
		<header class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div style="width:1200px;height:30px;padding-left:100px">
				<a class="brand" href="browse.do">Breeze</a>
				<ul class="nav pull-right" style="padding-right:80px">
					<li class="divider-vertical"></li>
					<li><a href="browse.do">Home</a></li>
					<li class="divider-vertical"></li>
					<c:if test="${!empty(sessionScope.user)}">
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							Hi, ${sessionScope.user.userName}
							<b class="caret"></b>
						</a>
						
						<ul class="dropdown-menu">
							<li><a tabindex="-1" href="showProfile.do"> Profile </a></li>
							<li><a tabindex="-1" href="postItem.do"> Post item </a></li>
							<li><a tabindex="-1" href="showMyItems.do"> My items </a></li>
							<li><a tabindex="-1" href="showMessage.do"> My Message</a></li>
							<li class="divider"></li>
							<li><a tabindex="-1" href="logout.do"> Sign out </a></li>
						</ul>
					</li>
					<li class="divider-vertical"></li>
					</c:if>
					<c:if test="${empty(sessionScope.user)}">
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							Sign in<b class="caret"></b>
						</a>
						<div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;"> 
							<form action="login.do" method="post" class='form-horizontal'>
								<p><input type='text' class='span3' style="height:30px; padding:5px;" name="userName" placeholder="Username"></p>

								<p><input type='password' class='span3' style="height:30px" placeholder="Password" name="password"></p>
								<input class="btn btn-info" style="clear: left; width: 100%; height: 32px; font-size: 13px;" type="submit" name="commit" value="Sign In" />
							</form>
						</div>
					</li>
					<li class="divider-vertical"></li>
					<li><a href="register.jsp">Sign up</a></li>
					<li class="divider-vertical"></li>
					</c:if>
				</ul>
				</div>
			</div>
		</header>