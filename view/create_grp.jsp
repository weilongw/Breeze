<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="home.do">Home</a> <span class="divider">/</span></li>
    <li><a href="browseCommunity.do">Community</a> <span class="divider">/</span></li>
    <li class="active"><a href="createCommunity.do">New Community</a> <span class="divider">/</span></li>
    </ul>
    </div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span8" style="padding-left:80px">
				<section>
    				<h4 style="margin-left:20px;">Create your own community</h4>
    				<p>&nbsp;</p>
    				<jsp:include page="error.jsp" />
    				<form action="createCommunity.do" method="post" class="form-horizontal">
		    			<div class="control-group">
							<label class="control-label" style="width:120px;">Community name</label>
							<div class="controls">
								<input class="span6" type="text" id="commName" name="name" value="${form.name}" onblur="validate_commName();">&nbsp;<span class="text-error" id="commNameHint"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" style="width:120px;">Related Movie</label>
							<div class="controls">
								<input type="text" class="span6" id="p-ajax-movie" onchange="search_movie('p');">
								<input type="text" class="span2" id="p-ajax-movie-year" onchange="search_movie('p');" placeholder="Year">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" style="width:120px;">Welcome Info</label>
							<div class="controls">
								<textarea class="span8" rows="4" name="info" >${form.info}</textarea>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="hidden" id="p-movie-name" name="relatedMovie" value="">
								<input type="submit" value="Post" class="btn">
							</div>		
						</div>
					</form>
					<input type="hidden" id="r-movie-name" name="dump" value="">
    			</section>
			</div>

			<div class="span4" id="movie">
				 <h4>Here shows the movie you are looking for</h4>
			</div>

		</div>
	</div>
</div>
</body>
</html>