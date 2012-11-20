<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span10" style="padding-left:100px; padding-top:20px; padding-bottom:50px">
				<div class="topic">
					<div class="topic-title">
						<span style="float:left;">hello world</span>
					</div>
					<div class="post-list">
						<div class="span12 post" style="margin-left:0px;">
							<div class="span3 post-author">
								<ul>
									<li>&nbsp;</li>
									<li>
										<img src="img/default.png" style="max-width:130px;max-height:130px;">
									</li>
									<li>&nbsp;</li>
									<li>userName</li>
								</ul>
							</div>
							<div class="span9 post-content">
								<div class="post-content-main">
									<div class="p-content">
										<div class="p-content-top">
										haha
										</div>
										<div class="p-content-bottom">
											<div style="float:right;margin-left:10px">
												<a href="#">reply</a>
											</div>
											<div style="float:right; color:#777777">
												@Sat 2012-12-21
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="span12 post" style="margin-left:0px;">
							<div class="span3 post-author">
								<ul>
									<li>&nbsp;</li>
									<li>
										<img src="img/default.png" style="max-width:130px;max-height:130px;">
									</li>
									<li>&nbsp;</li>
									<li>userName</li>
								</ul>
							</div>
							<div class="span9 post-content">
								<div class="post-content-main">
									<div class="p-content">
										<div class="p-content-top">
										haha
										</div>
										<div class="p-content-bottom">
											<div style="float:right;margin-left:10px">
												<a href="#">reply</a>
											</div>
											<div style="float:right; color:#777777">
												@Sat 2012-12-21
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div style="padding-left:40px;">
						<p>&nbsp;</p>
    				<h4 style="margin-left:20px;">Add a new post</h4>
    				<p>&nbsp;</p>
    				<form action="#" method="post" class="form-horizontal" name="post-topic-form">
						<div class="control-group">
							<label class="control-label" style="width:140px;">Content</label>
							<div class="controls">
								<textarea class="span10" rows="4" name="postContent" value="${form.postContent}"></textarea>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="hidden" name="topicId" value="">
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
