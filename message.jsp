<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					<li><a href="postItem.do"><i class="icon-chevron-right"></i>Post</a></li>
					<li><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Items</a></li>
					<li class="active"><a href="showMessage.do"><i class="icon-chevron-right"></i>My Messages</a></li>
				</ul>
			</div>
			<div class="span8" style="padding:20px">
                <jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
				<div class="tabbable"> <!-- Only required for left/right tabs -->
    				<ul class="nav nav-tabs">
                    <c:choose>
                        <c:when test="${empty(form)}">
                    <li class="active">
                        </c:when>
                        <c:otherwise>
                    <li>
                        </c:otherwise>
                    </c:choose>
                        <a href="#tab1" data-toggle="tab">Inbox</a>
                    </li>
    				<li><a href="#tab2" data-toggle="tab">Sent</a></li>
                    <c:choose>
                        <c:when test="${!empty(form)}">
                    <li class="active">
                        </c:when>
                        <c:otherwise>
                    <li>
                        </c:otherwise>
                    </c:choose>
                        <a href="#tab3" data-toggle="tab">Compose</a>
                    </li>
    				</ul>
    				<div class="tab-content">
                        <c:choose>
                        <c:when test="${empty(form)}">
                        <div class="tab-pane active" id="tab1">
                        </c:when>
                        <c:otherwise>
                        <div class="tab-pane" id="tab1">
                        </c:otherwise>
                        </c:choose>
    						<table class="table table-hover">
    							<thead>
    								<tr>
    									<th>From</th>
                                        <th>Title</th>
    									<th>When</th>
    								</tr>
    							</thead>
    							<tbody>
                                    <c:forEach var="inboxMsg" items="${inbox}">
                                    <tr>
                                        <td style="vertical-align:middle">${inboxMsg.sender.userName}</td>
                                        <td style="vertical-align:middle">${inboxMsg.title}</td>
                                        <td>${inboxMsg.sentDate}
                                            <button class="btn">read</button>
                                        </td>
                                    </tr>
                                    </c:forEach>
    							</tbody>
    						</table>
    					</div>
    					<div class="tab-pane" id="tab2">
   							<table class="table table-hover">
    							<thead>
    								<tr>
    									<th>To</th>
                                        <th>Title</th>
    									<th>When</th>
    								</tr>
    							</thead>
    							<tbody>
                                    <c:forEach var="sentMsg" items="${sent}">
                                    <tr>
                                        <td style="vertical-align:middle">${sentMsg.receiver.userName}</td>
                                        <td style="vertical-align:middle">${sentMsg.title}</td>
                                        <td>${sentMsg.sentDate}
                                            <button class="btn">read</button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
    						</table>
    					</div>
                        <c:choose>
                        <c:when test="${!empty(form)}">
                        <div class="tab-pane active" id="tab3">
                        </c:when>
                        <c:otherwise>
                        <div class="tab-pane" id="tab3">
                        </c:otherwise>
                        </c:choose>
   							<form action="composeMessage.do" method="post" class="form-horizontal">
   								<div class="control-group">
   									<label class="control-label">To (username)</label>
    								<div class="controls">  								
    								<input type="text" class="span4" name="receiver" value="${form.receiver}">
    								</div>		
    							</div>
                                <div class="control-group">
                                    <label class="control-label">Title</label>
                                    <div class="controls">                              
                                    <input type="text" class="span8" name="title" value="${form.title}">
                                    </div>      
                                </div>
    							<div class="control-group">
   									<label class="control-label">Content</label>
    								<div class="controls">  								
    								<textarea class="span8" rows="4" name="content">${form.content}</textarea>
    								</div>		
    							 </div>
    							<div class="control-group">
    								<div class="controls">
    								<input type="hidden" name="sender" value="${sessionScope.user.userName}">
    								<input type="submit" value="Send" class="btn">
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