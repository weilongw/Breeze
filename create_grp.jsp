<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span8">
				<section>
    				<h4 style="margin-left:20px;">Create your own group</h4>
    				<p>&nbsp;</p>
    				<form action="#" method="post" class="form-horizontal" name="post-topic-form">
		    			<div class="control-group">
							<label class="control-label" style="width:120px;">Group name</label>
							<div class="controls">
								<input class="span6" type="text" name="groupName" value="${form.groupName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" style="width:120px;">Related Movie</label>
							<div class="controls">
								<input type="text" class="span6" id="p-ajax-movie" onchange="search_movie('p');">
								<input type="text" class="span2" id="p-ajax-movie-year" onchange="search_movie('p');">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" style="width:120px;">Welcome Info</label>
							<div class="controls">
								<textarea class="span8" rows="4" name="postContent" value="${form.postContent}"></textarea>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="hidden" name="creater" value="${sessionScope.user.userName}">
								<input type="submit" value="Post" class="btn">
							</div>		
						</div>
					</form>
    			</section>
			</div>

			<div class="span4" id="movie">

			</div>

		</div>
	</div>
</div>
</body>
</html>