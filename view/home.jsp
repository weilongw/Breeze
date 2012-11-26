<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="top.jsp" />
    <div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="view/home.jsp">Home</a> <span class="divider">/</span></li>
    </ul>
    </div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div style="width:850px; padding-left:230; height:320px">
                    <jsp:include page="error.jsp" />
                    <jsp:include page="success.jsp" />
				<div id="myCarousel" class="carousel slide">
    			<!-- Carousel items -->
    				<div class="carousel-inner">
    					<div class="active item">
    						<img src="img/what_we_do.jpg">
    						<div class="carousel-caption">
    							<h4>What we do</h4>
    							<p> We hope to bring movie fans a brand new experience to enjoy the movies they like.  </p>
    						</div>
    					</div>
    					<div class="item">
    						<img src="img/people.svg" style="max-height:316px">
    						<div class="carousel-caption">
    							<h4>Community </h4>
    							<p> Here, you can share your opinions about movies with other people.</p>
    						</div>
    					</div>
    					<div class="item">
    						<img src="img/prop1.jpg" style="max-height:316px; padding-left:230">
    						<div class="carousel-caption">
    							<h4>Market </h4>
    							<p> If you like to collect movie accessories, posters, props, etc, this is the place for you.  </p>
    						</div>

    					</div>
    					<div class="item">
    						<table><tr>
    						<td><img src="img/poster1.jpg" style="max-height:316px; "></td>
    						<td><img src="img/poster2.jpg" style="max-height:316px; "></td>
    						<td><img src="img/poster3.jpg" style="max-height:316px; "></td>
    						<td><img src="img/poster4.jpg" style="max-height:316px; "></td>
    						</tr></table>
    						<div class="carousel-caption">
    							<h4>Before you start </h4>
    							<p> Wish you a wonderdul journey through our site and enjoy yourself ! </p>
    						</div>

    					</div>
    				</div>
    			<!-- Carousel nav -->
    				<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
    				<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    			</div>
    			</div>
    			 <div class="container" style="height:400px">
                    
    				<div class="marketing">
    					<h1>Introducing Breeze</h1>
    					<c:choose>
    					<c:when test="${empty(sessionScope.user)}">
    						<p class="marketing-byline">Not a user? <a href="register.do">Sign up now!</a></p>
    					</c:when>
    					<c:otherwise>
    						<p class="marketing-byline">View your profile, <a href="showProfile.do">GO!</a></p>
    					</c:otherwise>
    					</c:choose>
    					<div class="row-fluid">
    						<div class="span5" style="padding-left:100px">
    							<img src="img/propshop.jpg" style="max-height:325">
    							<h2><a href="browse.do">Market</a></h2>
    							<p>If you are a huge movie fan and like to collect those magic stuffs in movie, this will make a perfect place for you. It provides a place where collectors can post or request any items they want and exchange with each other. </p>
    						</div>
    						<div class="span5" style="margin-left: 100px; padding-left:80px">
    							<img src="img/forum.jpg" style="max-height:325">
    							<h2><a href="browseCommunity.do">Community</a></h2>
    							<p>After watching a movie, have you ever had the feeling to grab someone and talks about it? This forum like community will allow you to spit out opinions on any topic on any movie. We hope you can also make new friends while chatting online.</p>
    						</div>
    					</div>
    				</div>

    			</div>
    			
			</div>
		</div>
	</div>
</body>
</html>