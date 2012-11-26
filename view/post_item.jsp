<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="home.jsp">Home</a> <span class="divider">/</span></li>
    <li><a href="showProfile.do">Personal Account</a> <span class="divider">/</span></li>
    <li class="active"><a href="postItem.do">New Item</a> <span class="divider">/</span></li>
    </ul>
    </div>
	<div class="container-fluid">
		<div class="row-fluid">
			
			<div class="span8" style="padding-left:80px">
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
            <div class="span4" id="movie" style="padding-top:50px;">
                <h4>Here shows the movie you are looking for</h4>
            </div>
		</div>
	</div>
</div>
</body>
</html>