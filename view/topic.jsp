<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="home.do">Home</a> <span class="divider">/</span></li>
    <li><a href="browseCommunity.do">Community</a> <span class="divider">/</span></li>
    <li><a href="viewCommunity.do?name=${topic.ownerGroup.name}">${topic.ownerGroup.name}</a> <span class="divider">/</span></li>
    <li><a href="viewTopic.do?topicId=${topic.id}">${topic.title}</a> <span class="divider">/</span></li>
    </ul>
    </div>
<jsp:include page="error.jsp" />
<jsp:include page="success.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span10" style="padding-left:200px; padding-top:20px; padding-bottom:50px">
				<div class="topic">
					<div class="topic-title">
						<span style="float:left;">${topic.title}</span>
					</div>
					<div class="post-list">
						<c:forEach var="post" items="${posts}">
						<div class="span12 post" style="margin-left:0px;">
							<div class="span3 post-author">
								<ul>
									<li>&nbsp;</li>
									<li>
										<img src="img/user/${post.poster.userPhoto}" style="max-width:130px;max-height:130px;">
									</li>
									<li>&nbsp;</li>
									<li>${post.poster.userName}</li>
								</ul>
							</div>
							<div class="span9 post-content">
								<div class="post-content-main">
									<div class="p-content">
										<div class="p-content-top">
											${post.content}
											</div>
										<div class="p-content-bottom">
											<div style="float:right;margin-left:10px">
												<a href="#pform" onclick="smartReply('${post.poster.userName}');">reply</a>
											</div>
											<div style="float:right; color:#777777">
												@${post.postDate}
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>

					</div>
					<div style="padding-left:40px;">
						<p>&nbsp;</p>
    				<h4 style="margin-left:20px;">Add a new post</h4>
    				<p>&nbsp;</p>
    				<form action="newPost.do" method="post" class="form-horizontal" id="pform" name="postForm">
						<div class="control-group">
							<label class="control-label" style="width:140px;">Content</label>
							<div class="controls">
								<textarea class="span10" rows="4" name="postContent" id="content" value="${form.postContent}"></textarea>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="hidden" name="topicId" value="${topic.id}">
								<input type="submit" value="Post" class="btn">
							</div>		
						</div>
					</form>
    				</div>

				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
