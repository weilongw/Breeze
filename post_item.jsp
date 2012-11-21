<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					<li class="active"><a href="postItem.do"><i class="icon-chevron-right"></i>Post</a></li>
					<li><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Items</a></li>
					<li><a href="showMessage.do"><i class="icon-chevron-right"></i>My Messages</a></li>
				</ul>
			</div>
			<div class="span6" style="padding:20px">
                <jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
				<div class="tabbable"><!-- Only required for left/right tabs -->
    				<ul class="nav nav-tabs">
    				<c:choose>
                        <c:when test="${empty(requestForm)}">
                    <li class="active">
                        </c:when>
                        <c:otherwise>
                    <li>
                        </c:otherwise>
                    </c:choose>
                        <a href="#tab1" data-toggle="tab">Post</a>
                    </li>
    				<c:choose>
                        <c:when test="${!empty(requestForm)}">
                    <li class="active">
                        </c:when>
                        <c:otherwise>
                    <li>
                        </c:otherwise>
                    </c:choose>
                        <a href="#tab2" data-toggle="tab">Request</a>
                    </li>
                    <li class="disabled"><a href="#">Upload</a></li>
    				</ul>
    				<div class="tab-content">
                        <jsp:include page="post_form.jsp" />
                        <jsp:include page="request_form.jsp" />         
    				</div>
    			</div>
			</div>
            <div class="span3" id="movie">
                <h4>Here shows the movie you are looking for</h4>
            </div>
		</div>
	</div>
</div>
</body>
</html>