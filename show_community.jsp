<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3" style="padding:10px;padding-top:20px;">
				<div id="rez_part" style="margin-top:10px;border:1px #E7E6FA solid; height:190px">
					<div class="right_part_title"> 
						<span class="right_part_title_font">About Community</span>
						<a href="#" style="float:right; margin-top:5px; margin-right:8px">Join!</a>
					</div>
					<ul class="community_ul">
						<li><span class="community_font">Creater: Super user</span></li>
						<li><span class="community_font">@ 2012-12-21</span></li>
						<li><span class="community_font">Welcome msg</span></li>
					</ul>
				</div>
				
			</div>
			<div class="span6" style="padding-top:20px">
				<section style="padding-left:60px;">
      				    <form action="#" method="get" class="form-search" >
    						    <div class="input-append">
    							<input class="span12" type="text" size="40"  name = "key">
    							
    							<input class="btn" type="submit" value="Search">
    							</div>
    					</form>
    			</section>
    			<hr/>
    			<jsp:include page="error.jsp" />
    			<jsp:include page="success.jsp" />
    			<section>
    				<table class="table table-striped">
    					<thead>
    						<tr>
    							<th class="span1"></th>           
    							<th class="span6"></th>
    							<th class="span2"></th>
    							<th class="span2"></th>
    						</tr>
    					</thead>
    					<tr>
    						<td><div class="rep_num" title="20 reply">20</div></td>
    						<td><a href="#">Topic 1</a></td>
    						<td>by user1</td>		
    						<td>time1</td>
    					</tr>
    					<tr>
    						<td><div class="rep_num" title="20 reply">20</div></td>
    						<td><a href="#">Topic 2</a></td>
    						<td>by user2</td>
    						<td>time2</td>
    					</tr>
    					<tr>
    						<td><div class="rep_num" title="20 reply">20</div></td>
    						<td><a href="#">Topic 3</a></td>
    						<td>by user3</td>
    						<td>time3</td>
    					</tr>
    					<tr>
    						<td><div class="rep_num" title="20 reply">20</div></td>
    						<td><a href="#">Topic 4</a></td>
    						<td>by user3</td>
    						<td>time3</td>
    					</tr>
    				</table>
    			</section>
    			<hr/>
    			<section>
    				<h4 style="margin-left:20px;">Add a new topic</h4>
    				<p>&nbsp;</p>
    				<form action="#" method="post" class="form-horizontal" name="post-topic-form">
		    			<div class="control-group">
							<label class="control-label" style="width:120px;">Title</label>
							<div class="controls">
								<input class="span10" type="text" name="topicName" value="${form.topicName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" style="width:120px;">Content</label>
							<div class="controls">
								<textarea class="span10" rows="4" name="postContent" value="${form.postContent}"></textarea>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="hidden" name="communityName" value="">
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
						<a href="#" style="float:right; margin-top:5px; margin-right:8px">Imdb link?</a>
					</div>
					<ul class="community_ul">
						<li><span class="community_font">Title : batman</span></li>
						<li>
							<div style="width:280px;height:350px; text-align:center">
								<img src="http://ia.media-imdb.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg" style="max-width:280px; max-height:350px; vertical-align:middle" alt="">
							</div>
						</li>
						<li><span class="community_font">Year</span></li>
						<li><span class="community_font">Rated</span></li>
						<li><span class="community_font">Released</span></li>
						<li><span class="community_font">Runtime</span></li>
						<li><span class="community_font">Genre</span></li>
						<li><span class="community_font">Director</span></li>
						<li><span class="community_font">Write</span></li>
						<li><span class="community_font">Actors</span></li>
						<li><span class="community_font">Plot</span></li>
						<li><span class="community_font">Imdb Rating</span></li>
						<li><span class="community_font">Imdb Votes</span></li>


					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>
