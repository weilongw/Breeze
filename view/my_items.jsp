<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="view/home.jsp">Home</a> <span class="divider">/</span></li>
    <li><a href="showProfile.do">Personal Account</a> <span class="divider">/</span></li>
    <li class="active"><a href="showMyItems.do">My items</a> <span class="divider">/</span></li>
    </ul>

    </div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					
					<li class="active"><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Item</a></li>
					<li><a href="showMyCommunity.do"><i class="icon-chevron-right"></i>My Community</a></li>
					<li><a href="showMessage.do"><i class="icon-chevron-right"></i>My Message</a></li>
				</ul>
			</div>
			<div class="span8" style="padding:20px">
				<jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
				<div class="tabbable"> <!-- Only required for left/right tabs -->
    				<ul class="nav nav-tabs">
    				<li class="active"><a href="#tab1" data-toggle="tab">Posted</a></li>
    				<li><a href="#tab2" data-toggle="tab">Requested</a></li>
    				<li><a href="#tab3" data-toggle="tab">Pending</a></li>
    				<li><a href="#tab4" data-toggle="tab">Completed</a></li>
    				</ul>
    				<div class="tab-content">
    					<div class="tab-pane active" id="tab1">
    						<ul class="thumbnails">

								

								<c:forEach var="myPostedItem" items="${myPostedItems}"> 

    							<li class="span4" style="margin-left:0">
									<div class="thumbnail" style="height:300;">
										<div style="width:240px;height:160px; text-align:center">
											<a href="showItems.do?itemId=${myPostedItem.id}">
										<img src="img/item/${myPostedItem.imgName}" style="max-width:240px; max-height:160px; vertical-align:middle" alt=""></a>
										</div>
										<div class="caption">
										<a href="showItems.do?itemId=${myPostedItem.id}"><h3>${myPostedItem.itemName}</h3></a>
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
							
							</ul>
						</div>
    					<div class="tab-pane" id="tab2">
   							<ul class="thumbnails">
								<c:forEach var="myRequestedItem" items="${myRequestedItems}"> 
    							<li class="span4" style="margin-left:0">
									<div class="thumbnail" style="height:300;">
										<div style="width:240px;height:160px; text-align:center">
											<a href="showItems.do?itemId=${myRequestedItem.id}">
										<img src="img/item/${myRequestedItem.imgName}" style="max-width:240px; max-height:160px" alt=""></a>
										</div>
										<div class="caption">
										<a href="showItems.do?itemId=${myRequestedItem.id}"><h3>${myRequestedItem.itemName}</h3></a>
										<p>${fn:substring(myRequestedItem.itemDescription,0,30)}</p>
										<c:if test="${myRequestedItem.status==0}">
										<form action="closeItem.do" method="post" onsubmit="return confirm('Do you really want to close this item?');">
											<input type="hidden" name="itemId" value="${myRequestedItem.id}">
											<input type="submit" value="close" class="btn">
										</form>
										</c:if>
										</div>
									</div>
								</li>
								</c:forEach>
							</ul>
    					</div>
    					<div class="tab-pane" id="tab3">
   							<ul class="thumbnails">
								<c:forEach var="myPendingItem" items="${myPendingItems}"> 
    							<li class="span4" style="margin-left:0">
									<div class="thumbnail" style="height:300;">
										<div style="width:240px;height:160px; text-align:center">
											<a href="showItems.do?itemId=${myPendingItem.id}">
										<img src="img/item/${myPendingItem.imgName}" style="max-width:240px; max-height:160px" alt=""></a>
										</div>
										<div class="caption">
										<a href="showItems.do?itemId=${myPendingItem.id}"><h3>${myPendingItem.itemName}</h3></a>
										<p>${fn:substring(myPendingItem.itemDescription,0,30)}</p>
										</div>
									</div>
								</li>
								</c:forEach>
							</ul>
    					</div>
    					<div class="tab-pane" id="tab4">
   							<section>
   								<h4>My Finished Items</h4>
   								<table class="table table-striped">
   									<thead>
   										<tr>
   											<th>Item</th>
   											<th>Poster</th>
   											<th>Responder</th>
   											<th>Type</th>
   											<th>Ended at</th>
   										</tr>
   									</thead>
   									<c:forEach var="myFinishedItem" items="${myFinishedItems}">
   										<tr>
   											<td><a href="showItems.do?itemId=${myFinishedItem.item.id}">${myFinishedItem.item.itemName}</a></td>
   											<td>${myFinishedItem.poster.userName}</td>
   											<td>${myFinishedItem.responder.userName}</td>
   											<td>${map[myFinishedItem.respondType]}</td>
   											<td><fmt:formatDate value="${myFinishedItem.endDate}" type="both" dateStyle="short" /></td>
   										</tr>
   									</c:forEach>
   								</table>
   							</section>
   							<section style="border-top:1px solid #999999">
   								<p>&nbsp;</p>
   								<h4>My Closed Items</h4>
   								<table class="table table-striped">
   									<thead>
   										<tr>
   											<th>Item</th>
   											<th>Type</th>
   											<th>Ended at</th>
   										</tr>
   									</thead>
   									<c:forEach var="myClosedItem" items="${myClosedItems}">
   										<tr>
   											<td><a href="showItems.do?itemId=${myClosedItem.item.id}">${myClosedItem.item.itemName}</a></td>
   											<td>${closedMap[myClosedItem.item.type]}</td>
   											<td><fmt:formatDate value="${myClosedItem.endDate}" type="both" dateStyle="short" /></td>
   										</tr>
   									</c:forEach>
   								</table>
   							</section>
    					</div>
    				</div>
    			</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
