<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					<li><a href="postItem.do"><i class="icon-chevron-right"></i>Post</a></li>
					<li class="active"><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Items</a></li>
					<li><a href="showMessage.do"><i class="icon-chevron-right"></i>My Messages</a></li>
				</ul>
			</div>
			<div class="span8" style="padding:20px">
				<jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
				<div class="tabbable"> <!-- Only required for left/right tabs -->
    				<ul class="nav nav-tabs">
    				<li class="active"><a href="#tab1" data-toggle="tab">Posted</a></li>
    				<li><a href="#tab2" data-toggle="tab">Requested</a></li>
    				</ul>
    				<div class="tab-content">
    					<div class="tab-pane active" id="tab1">
    						<ul class="thumbnails">

								<c:if test="${!empty (myPostedItems)}">

								<c:forEach var="myPostedItem" items="${myPostedItems}"> 

    							<li class="span4" style="margin-left:0">
									<div class="thumbnail" style="height:300;">
										<div style="width:240px;height:160px; text-align:center">
										<img src="img/${myPostedItem.imgName}" style="max-width:240px; max-height:160px; vertical-align:middle" alt="">
										</div>
										<div class="caption">
										<h3>${myPostedItem.itemName}</h3>
										<p>${fn:substring(myPostedItem.itemDescription,0,30)}</p>
										<c:if test="${myPostedItem.status == 0}">
										<form action="closeItem.do" method="post" onsubmit="return confirm('Do you really want to close this item?');">
											<input type="hidden" name="itemId" value="${myPostedItem.id}">
											<input type="submit" value="close" class="btn">
										</form>
										</c:if>
										</div>
									</div>
								</li>
								</c:forEach>
							</c:if>
</ul>
</div>




    					<div class="tab-pane" id="tab2">
   							<ul class="thumbnails">
   								<c:if test="${!empty (myRequestedItems)}">

								<c:forEach var="myRequestedItem" items="${myRequestedItems}"> 
    							<li class="span4" style="margin-left:0">
									<div class="thumbnail" style="height:300;">
										<div style="width:240px;height:160px; text-align:center">
										<img src="img/${myRequestedItem.imgName}" style="max-width:240px; max-height:160px" alt="">
										</div>
										<div class="caption">
										<h3>${myRequestedItem.itemName}</h3>
										<p>${fn:substring(myRequestedItem.itemDescription,0,30)}</p>
										<c:if test="${myRequestedItem.status==0}">
										<form action="closeItem.do" method="post">
											<input type="hidden" name="itemId" value="${myRequestedItem.id}">
											<input type="submit" value="close" class="btn">
										</form>
										</c:if>
										</div>
									</div>
								</li>
								</c:forEach>
							</c:if>
								
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
