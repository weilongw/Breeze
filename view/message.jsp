<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="home.do">Home</a> <span class="divider">/</span></li>
    <li><a href="showProfile.do">Personal Account</a> <span class="divider">/</span></li>
    <li class="active"><a href="showMessage.do">Message Box</a> <span class="divider">/</span></li>
    </ul>
    </div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					
					<li><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Item</a></li>
                    <li><a href="showMyCommunity.do"><i class="icon-chevron-right"></i>My Community</a></li>
					<li class="active"><a href="showMessage.do"><i class="icon-chevron-right"></i>My Message</a></li>
				</ul>
			</div>
            
			<div class="span9" style="padding:20px">
                <jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
                <jsp:include page="message_content.jsp"/>
				<div class="tabbable"> <!-- Only required for left/right tabs -->
    				<ul class="nav nav-tabs">
                    <c:choose>
                        <c:when test="${(empty(form)) && (empty(success)) }">
                    <li class="active">
                        </c:when>
                        <c:otherwise>
                    <li>
                        </c:otherwise>
                    </c:choose>
                        <a href="#tab1" data-toggle="tab">Inbox</a>
                    </li>
                    <c:choose>
                        <c:when test="${(!empty(form)) && (!empty(success)) }">
                        <li class="active">
                        </c:when>
                        <c:otherwise>
                        <li>
                        </c:otherwise>
                    </c:choose>
                        <a href="#tab2" data-toggle="tab">Sent</a>
                    </li>
                    <c:choose>
                        <c:when test="${(!empty(form)) && (empty(success))}">
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
                        <c:when test="${(empty(form)) && (empty(success)) }">
                        <div class="tab-pane active" id="tab1">

                        </c:when>
                        <c:otherwise>
                        <div class="tab-pane" id="tab1">
                        </c:otherwise>
                        </c:choose>
                            <div class="span8">
                            <div style="height:270px">
    						<table class="table table-hover">
    							<thead>
    								<tr>
    									<th width="100px">From</th>           
    									<th width="300px">Title</th>
    								</tr>
    							</thead>
    							<tbody>
                                    <c:forEach var="inboxMsg" items="${inbox}" begin="${inboxStart}" end="${inboxEnd}">
                                    <c:choose>
                                    <c:when test="${inboxMsg.hasRead == 0}">
                                        <tr id="tr${inboxMsg.id}" style="font-weight:bold" onclick="show_msg('1','${inboxMsg.id}', '${inboxMsg.sentDate}', '${inboxMsg.hasRead}');">
                                    </c:when>
                                    <c:otherwise>
                                        <tr id="tr${inboxMsg.id}" style="font-weight:normal" onclick="show_msg('1','${inboxMsg.id}', '${inboxMsg.sentDate}', '${inboxMsg.hasRead}');">
                                    </c:otherwise>
                                    </c:choose>
                                    
                                        <td style="vertical-align:middle">${inboxMsg.sender.userName}</td>
                                        
                                        <td>${inboxMsg.title}
                                        </td>
                                    </tr>
                                    </c:forEach>
    							</tbody>
    						</table>
                            </div>
                            
                            <div class="pagination">
                                <ul>
                                    <li>
                                        <a href="pageMsg.do?inbox=1&sent=${sentPageCurrent}&render=0">
                                            |&lt;
                                        </a>
                                    </li>
                                    
                                    <c:choose>
                                    <c:when test="${inboxPageCurrent == 1}">
                                        <li class="disabled">
                                            <a href="#">
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="pageMsg.do?inbox=${inboxPageCurrent - 1}&sent=${sentPageCurrent}&render=0">
                                    </c:otherwise>
                                    </c:choose>
                                            Prev
                                    </a>
                                    </li>
                                    <c:forEach begin="${inboxPageStart}" end="${inboxPageEnd}" varStatus="loop">
                                        <c:choose>
                                            <c:when test="${inboxPageCurrent == loop.index}">
                                        <li class="disabled">
                                        <a href="#">${loop.index}
                                            </c:when>
                                            <c:otherwise>
                                        <li>
                                        <a href="pageMsg.do?inbox=${loop.index}&sent=${sentPageCurrent}&render=0">${loop.index}
                                            </c:otherwise>
                                        </c:choose>
                                        </a>                                    
                                        </li>
                                    </c:forEach>
                                    <c:choose>
                                    <c:when test="${inboxPageCurrent == inboxPageCount}">
                                        <li class="disabled">
                                            <a href="#">
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="pageMsg.do?inbox=${inboxPageCurrent + 1}&sent=${sentPageCurrent}&render=0">
                                    </c:otherwise>
                                    </c:choose>
                                            Next
                                    </a>
                                    </li>
                                    <li>
                                    <a href="pageMsg.do?inbox=${inboxPageCount}&sent=${sentPageCurrent}&render=0">&gt;|</a>
                                    </li>
                                </ul>
                                    
                                </div>
                            </div>
                            

                            <div class="span4">
                                <fieldset>
                                    <legend>Message<small id="reply-btn" style="float:right">reply</small></legend>
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
                        <c:choose>
                        <c:when test="${(!empty(form)) && (!empty(success)) }">
                        <div class="tab-pane active" id="tab2">

                        </c:when>
                        <c:otherwise>
                        <div class="tab-pane" id="tab2">
                        </c:otherwise>
                        </c:choose>
    					
                            <div class="span8">
                            <div style="height:270px">
   							<table class="table table-hover">
    							<thead>
                                    <tr>
                                        <th width="100px">To</th>
                                        <th width="300px">Title</th>
                                    </tr>
                                </thead>
    							<tbody>
                                    <c:forEach var="sentMsg" items="${sent}" begin="${sentStart}" end="${sentEnd}">
                                    <tr onclick="show_msg('2','${sentMsg.id}', '${sentMsg.sentDate}')">
                                        <td style="vertical-align:middle">${sentMsg.receiver.userName}</td>
                                        <td style="vertical-align:middle">${sentMsg.title}</td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
    						</table>
                            </div>
                            <div class="pagination">
                                <ul>
                                    <li>
                                        <a href="pageMsg.do?inbox=${inboxPageCurrent}&sent=1&render=1">
                                            |&lt;
                                        </a>
                                    </li>
                                    
                                    <c:choose>
                                    <c:when test="${sentPageCurrent == 1}">
                                        <li class="disabled">
                                            <a href="#">
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="pageMsg.do?inbox=${inboxPageCurrent}&sent=${sentPageCurrent - 1}&render=1">
                                    </c:otherwise>
                                    </c:choose>
                                            Prev
                                    </a>
                                    </li>
                                    <c:forEach begin="${sentPageStart}" end="${sentPageEnd}" varStatus="loop">
                                        <c:choose>
                                            <c:when test="${sentPageCurrent == loop.index}">
                                        <li class="disabled">
                                            <a href="#">${loop.index}
                                            </c:when>
                                            <c:otherwise>
                                        <li>
                                            <a href="pageMsg.do?inbox=${inboxPageCurrent}&sent=${loop.index}&render=1">${loop.index}
                                            </c:otherwise>
                                        </c:choose>                                    
                                        </a> 
                                        </li>
                                    </c:forEach>
                                    <c:choose>
                                    <c:when test="${sentPageCurrent == sentPageCount}">
                                        <li class="disabled">
                                            <a href="#">
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="pageMsg.do?inbox=${inboxPageCurrent}&sent=${sentPageCurrent + 1}&render=1">
                                    </c:otherwise>
                                    </c:choose>
                                            Next
                                    </a>
                                    </li>
                                    <li>
                                    <a href="pageMsg.do?inbox=${inboxPageCurrent}&sent=${sentPageCount}&render=1">&gt;|</a>
                                    </li>
                                </ul>

                            </div>
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
                        <c:when test="${(!empty(form)) && (empty(success))}">
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
