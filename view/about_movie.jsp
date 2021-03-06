<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title> Breeze </title>
		<link type="text/css" href="css/bootstrap.css" rel="stylesheet" media="screen">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>	
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="js/breeze.js"></script>
		<style media="screen" type="text/css">

		.movie tr{
			line-height: 30	px;
		}

		</style>
	</head>
	<body onload="load_movie('${item.relatedMovie}')">	
		<div style="width:1300px;">
		<header class="navbar" style="padding-left:50px;margin-bottom:0">
			<img src="img/header.jpg">
			<div class="navbar-inner" style="padding-left:100px;">
				<!--div style="width:1200px;height:30px;padding-left:100px"-->
				<a class="brand" href="home.do">Breeze</a>
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
							<a href="showMessage.do" title="new message"><i class="icon-envelope"></i>&nbsp;&nbsp;<span id="headCount">${sessionScope.user.newMsgCount}</span>
							</a>

						</li>
						<li class="divider-vertical"></li>
						<li>
						<a href="#" title="credit"><i class="icon-gift"></i>&nbsp;&nbsp;<span>${sessionScope.user.credit}</span></a>
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
					<li><a href="register.do">Sign up</a></li>
					<li class="divider-vertical"></li>
					</c:if>
				</ul>
				<!--/div-->
			</div>
		</header>
		<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="home.do">Home</a> <span class="divider">/</span></li>
    <li class="active"><a href="browse.do">Market</a> <span class="divider">/</span></li>
    <li class="active"><a href="showItems.do?itemId=${item.id}">${item.itemName}</a> <span class="divider">/</span></li>
    </ul>
    </div>
<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<big><a href="postItem.do">Post your own item</a></big>
                    <p>&nbsp;</p>
				<ul class="nav nav-list bs-docs-sidenav">
					                
					<li><a href="showItems.do?itemId=${item.id}"><i class="icon-chevron-right"></i>Item Info</a></li>
					<li class="active"><a href="showMovie.do?itemId=${item.id}"><i class="icon-chevron-right"></i>About Movie</a></li>
				</ul>
			</div>
			<div class="span9" style="padding:20px">
                <jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
                
                <fieldset onload="load_movie('${item.relatedMovie}')">
                	<legend id="legend_title"></legend>
                	<div class="span8">
                	<table class="movie">
                	<span id="pending"></span>
                	<tr id="title"></tr>
                	<tr id="year"></tr>
                	<tr id="rated"></tr>
                	<tr id="released"></tr>
                	<tr id="runtime"></tr>
                	<tr id="genre"></tr>	
                	<tr id="director"></tr>
                	<tr id="writer"></tr>
                	<tr id="actors"></tr>
                	<tr id="plot"></tr>
                	<tr id="imdbRating"></tr>
                	<tr id="imdbVotes"></tr>
                	</table>
                	</div>
                	<div class="span3">
                		<c:choose>
						<c:when test="${empty(item.relatedMovie)}">
						No Related Movie present.
						</c:when>
						<c:otherwise>
						<img src="img/poster/${item.relatedMovie}.jpg" alt="No movie poster available.." id="movie_poster" style="max-height:400px;">
						</c:otherwise>
						</c:choose>
            		</div>
                </fieldset>
            		
            	
            </div>
        </div>
    </div>
</div>
</body>
</html>