<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title> Breeze </title>
		<link type="text/css" href="css/bootstrap.css" rel="stylesheet" media="screen">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>	
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="js/breeze.js"></script>
		<style type="text/css">
		.right_part_title{
			background: url("img/bataiTitle.png") repeat-x;
			height:28px;
			width:100%;
			border-bottom:1px #E7E6FA  solid;
		}
		.right_part_title_font{
			display:block;
			float:left;
			margin-top:5px;
			margin-left:8px;
			font-size:14px;
			font-weight:bold;
			color:#515151;
			
		}
		.community_ul{
			list-style-type: none;
			display: block;
			margin-top:8px;
			margin-left:8px;
		
		}	
		.community_ul li{
			float-left;
			line-height:24px;
			background-position:0px 4px;

		}
		.community_font {
			margin-left: 10px;
			vertical-align: middle;
		}
		.rep_num {
			padding:0 4px 0 1px;
			font-size: 13px;
			overflow:hidden;
			width:50px;
			height:23px;
			line-height:23px;
			text-align:center;
			color:#333;
			background:url("img/reply_num_bg.gif") no-repeat;
			margin-left:10px
		}
		.topic {
			float: left;
			border: 1px solid #CCCCCC;
    		width: 900px;
		}
		.topic-title {
			background: none repeat scroll 0 0 #FFFFFF;
    		height: 43px;
    		line-height: 43px;
    		padding: 0 15px;
    		word-break: break-all;
		}
		.topic-title span {
			color: black;
    		font-size: 20px;
    		font-weight: bold;
		    text-align: left;

		}
		.post-list {

			border-color: #999999;
    		border-style: solid;
   			border-width: 1px 0;
    		position: relative;
    		z-index: 1;
		}
		.post {
			#border:1px solid;
			#border: 0 none;
			border-bottom: 1px solid #CCCCCC;
		}
		.post-author {
			
			width:200px;
			height:220px;
			background-color: #EEEEEE	;
			
		}
		.post-author ul{
			list-style-type: none;
			margin-left:35px;
		}
		.post-content-main {
    		padding: 20px 15px 6px;
    		
		}
		.p-content-top {
			height:auto !important;
			min-height: 165px;
		}
		.p-content-bottom {
			#border: 1px,solid;
			height: 28px;
    		line-height: 28px;
		}
		</style>
		
	</head>
	<body>	
		<div style="width:1300px">
		<header class="navbar" style="padding-left:50px;margin-bottom:0" >
			<img src="img/header.jpg">
			<div class="navbar-inner" style="padding-left:100px;">
				<!--div style="width:1300px;height:30px;padding-left:100px"-->
				<a class="brand" href="home.jsp">Breeze</a>
				<ul class="nav pull-right" style="padding-right:80px">
					<li class="divider-vertical"></li>
					<li><a href="browse.do">Market</a></li>
					<li class="divider-vertical"></li>
					<li><a href="browseCommunity.do">Community</a></li>
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
							<li><a tabindex="-1" href="createCommunity.do"> Create Community </a></li>
							<li><a tabindex="-1" href="showMyItems.do"> My item </a></li>
							<li><a tabindex="-1" href="showMyCommunity.do"> My Community</a></li>
							<li><a tabindex="-1" href="showMessage.do"> My Message</a></li>
							<li class="divider"></li>
							<li><a tabindex="-1" href="logout.do"> Sign out </a></li>
						</ul>
					</li>
					<li class="divider-vertical"></li>
					
						<li>
							<a href="showMessage.do"><i class="icon-envelope"></i>&nbsp;&nbsp;<span id="headCount">${sessionScope.user.newMsgCount}</span>
							</a>
						</li>
					
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
				<!--/div-->
			</div>
		</header>