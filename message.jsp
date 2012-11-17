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
                <jsp:include page="message_content.jsp"/>
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
                            <div class="span8">
    						<table class="table table-hover">
    							<thead>
    								<tr>
    									<th width="100px">From</th>           
    									<th width="300px">Title</th>
    								</tr>
    							</thead>
    							<tbody>
                                    <c:forEach var="inboxMsg" items="${inbox}">
                                    <tr onclick="show_msg('1','${inboxMsg.id}', '${inboxMsg.sentDate}')">
                                        <td style="vertical-align:middle">${inboxMsg.sender.userName}</td>
                                        
                                        <td>${inboxMsg.title}
                                        </td>
                                    </tr>
                                    </c:forEach>
    							</tbody>
    						</table>
                            </div>
                            <div class="span4">
                                <fieldset>
                                    <legend>Message</legend>
                                    <strong>From:</strong><br/>
                                    <span id="user1"></span><br/>
                                    <strong>Title:</strong><br/>
                                    <span id="title1"></span><br/>
                                    <strong>Content:</strong><br/>
                                    <span id="content1"></span><br/><br/>
                                    <strong>@</strong>
                                    <span id="date1"></span><br/>
                                </fieldset>  
                            </div>
    					</div>
    					<div class="tab-pane" id="tab2">
                            <div class="span8">
   							<table class="table table-hover">
    							<thead>
                                    <tr>
                                        <th width="100px">To</th>
                                        <th width="300px">Title</th>
                                    </tr>
                                </thead>
    							<tbody>
                                    <c:forEach var="sentMsg" items="${sent}">
                                    <tr onclick="show_msg('2','${sentMsg.id}', '${sentMsg.sentDate}')">
                                        <td style="vertical-align:middle">${sentMsg.receiver.userName}</td>
                                        <td style="vertical-align:middle">${sentMsg.title}</td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
    						</table>
                            </div>
                            <div class="span4">
                                <fieldset>
                                    <legend>Message</legend>
                                    <strong>To:</strong><br/>
                                    <span id="user2"></span><br/>
                                    <strong>Title:</strong><br/>
                                    <span id="title2"></span><br/>
                                    <strong>Content:</strong><br/>
                                    <span id="content2"></span><br/><br/>
                                    <strong>@</strong>
                                    <span id="date2"></span><br/>
                                </fieldset>  
                            </div>
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