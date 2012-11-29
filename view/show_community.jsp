<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<body onload="load_movie('${comm.relatedMovie}')">	
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
    <li><a href="browseCommunity.do">Community</a> <span class="divider">/</span></li>
    <li class="active"><a href="viewCommunity.do?name=${comm.name}">${comm.name}</a> <span class="divider">/</span></li>
    </ul>
    </div>
<div class="container-fluid">
	
		<div class="row-fluid">
			<div class="span3" style="padding:10px;padding-top:20px; padding-left:50px">
				<div id="rez_part" style="margin-top:10px;border:1px #E7E6FA solid; height:190px">
					<div  class="right_part_title"> 
						<span class="right_part_title_font">About Community</span>
						<div id="decide_join">
						<c:if test="${!empty(sessionScope.user)}">
							<c:if test="${joining == 0}">
							<a href="joinCommunity.do?name=${comm.name}" style="float:right; margin-top:5px; margin-right:8px">Join!</a>
							</c:if>
							<c:if test="${joining == 1}">					
							<a href="unjoinCommunity.do?name=${comm.name}" style="float:right; margin-top:5px; margin-right:8px">Unjoin!</a>
							</c:if>
						</c:if>
						</div>
					</div>
					<ul class="community_ul">
						<li><span class="community_font">Name: ${comm.name}</span></li>
						<li><span class="community_font">Creater : ${comm.creater.userName}</span></li>
						<li><span class="community_font">Created @: <fmt:formatDate value="${comm.createdAt}" type="both" dateStyle="short"/></span></li>
						<li><span class="community_font">Info : ${comm.info}</span></li>
						<li><span class="community_font">Member : ${comm.userCount}</span></li>
					</ul>
				</div>
				<p>&nbsp;</p>
				<div>
					<c:choose>
					<c:when test="${empty(comm.relatedMovie)}">
						No Related Movie present.
					</c:when>
					<c:otherwise>
						<img src="img/poster/${comm.relatedMovie}.jpg" alt="No movie poster available.." id="movie_poster" style="max-height:400px;">
					</c:otherwise>
					</c:choose>
				</div>
				
			</div>
			<div class="span6" style="padding-top:20px">
				<section style="padding-left:70px;">
      				    <form action="searchTopic.do" method="get" class="form-search" >
    						    <div class="input-append">
    							<input class="span12" type="text" size="40"  name = "key" value="${sForm.key}">
    							<input type="hidden" name="commName" value="${comm.name}">
    							<input class="btn" type="submit" value="Search">
    							</div>
    					</form>
    			</section>
    			<hr/>
    			<jsp:include page="error.jsp" />
    			<jsp:include page="success.jsp" />
    			<div id="decide_join_message"></div>
    			<section>
    				<c:choose>

    				<c:when test="${!empty(topics)}">

    				<table class="table table-striped">
    					<thead>
    						<tr>
    							<th class="span1">Reply</th>           
    							<th class="span6">Title</th>
    							<th class="span2">Poster</th>
    							<th class="span4">Time</th>
    						</tr>
    					</thead>
    					<c:forEach var="topic" items="${topics}">
    						<tr>
    							<td>
    								<div class="rep_num" title="${topic.replyCount} replies">
    									${topic.replyCount}
    								</div>
    							</td>
    							<td><a href="viewTopic.do?topicId=${topic.id}">${topic.title}</a></td>
    							<td>by ${topic.poster.userName}</td>
    							<td><fmt:formatDate value="${topic.postDate}" type="both" dateStyle="short"/></td>
    						</tr>
    					</c:forEach>
    					
    				</table>
    				</c:when>
    				<c:otherwise>
    					<h4>No topic is found.</h4>
    				</c:otherwise>
    			</c:choose>


    			</section>
    			<hr/>
    			<section>
    				<h4 style="margin-left:20px;">Add a new topic</h4>
    				<p>&nbsp;</p>
    				<form action="newTopic.do" method="post" class="form-horizontal" name="post-topic-form">
		    			<div class="control-group">
							<label class="control-label" style="width:120px;">Title</label>
							<div class="controls">
								<input class="span10" type="text" name="title" value="${form.title}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" style="width:120px;">Content</label>
							<div class="controls">
								<textarea class="span10" rows="4" name="content">${form.content}</textarea>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="hidden" name="communityName" value="${comm.name}">
								<input type="submit" value="Post" class="btn">
							</div>		
						</div>
					</form>
    			</section>

			</div>
			<div class="span3" style="padding-top:20px;width=100px;">
				<div id="rez_part" style="margin-top:10px;border:1px #E7E6FA solid;">
					<div class="right_part_title"> 
						<span class="right_part_title_font">About Movie</span>
						<c:if test="${!empty(comm.relatedMovie)}">
						<a href="http://www.imdb.com/title/${comm.relatedMovie}/" target="_blank" style="float:right; margin-top:5px; margin-right:8px">Imdb link</a>
						</c:if>
					</div>
					<div class="community_ul">
						
						<fieldset>
                		<legend id="legend_title"></legend>
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
                		</fieldset>
                	</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>
