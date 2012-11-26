<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="home.do">Home</a> <span class="divider">/</span></li>
    <li><a href="showProfile.do">Personal Account</a> <span class="divider">/</span></li>
    <li class="active"><a href="showMyItems.do">My items</a> <span class="divider">/</span></li>
    </ul>
    </div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					
					<li><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Item</a></li>
					<li class="active"><a href="showMyCommunity.do"><i class="icon-chevron-right"></i>My Community</a></li>
					<li><a href="showMessage.do"><i class="icon-chevron-right"></i>My Message</a></li>
					
				</ul>
			</div>
			<div class="span9" style="padding:20px">
				<jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
				<div class="tabbable"> <!-- Only required for left/right tabs -->
    				<ul class="nav nav-tabs">
    				<li class="active"><a href="#tab1" data-toggle="tab">Joined Communities</a></li>
    				<li><a href="#tab2" data-toggle="tab">My topics</a></li>
    				</ul>
    				<div class="tab-content">
    					<div class="tab-pane active" id="tab1">
    						<c:choose>
						<c:when test="${!empty(myComm)}">
                        
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th class="span1">Member</th>
                                    <th class="span5">Name</th>
                                    <th class="span2">Creater</th>
                                    <th class="span2">Topics</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="comm" items="${myComm}">
                                    <tr>
                                    <td>
                                        <div class="rep_num" title="${comm.userCount} members">
                                            ${comm.userCount}
                                        </div>
                                    </td>
                                    <td><a href="viewCommunity.do?name=${comm.name}">${comm.name}</a></td>
                                    <td>by ${comm.creater.userName}</td>
                                    <td>${comm.topicCount}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                    <h3>No joined community.</h3>
                </c:otherwise>
            </c:choose>
						</div>
    					<div class="tab-pane" id="tab2">
    						<c:choose>
   							<c:when test="${!empty(myTopic)}">
                        <table class="table table-striped">
                            
                            <thead>
                                <tr>
                                    <th class="span1">Reply</th>
                                    <th class="span5">Title</th>
                                    <th class="span2">Community</th>
                                    <th class="span2">Poster</th>
                                    <th class="span3">Time</th>
                                </tr> 
                            </thead>
                                <c:forEach var="topic" items="${myTopic}">
                                    <tr>
                                    <td>
                                        <div class="rep_num" title="${topic.replyCount} reply">
                                            ${topic.replyCount}
                                        </div>
                                    </td>
                                    <td><a href="viewTopic.do?topicId=${topic.id}">${topic.title}</a></td>
                                    <td><a href="viewCommunity.do?name=${topic.ownerGroup.name}">${topic.ownerGroup.name}</a></td>
                                    <td>by ${topic.poster.userName}</td>
                                    <td>@<fmt:formatDate value="${topic.postDate}" type="both" dateStyle="short"/></td>
                                    </tr>
                                </c:forEach>
                           
                        </table>
                    </c:when>
                    <c:otherwise>
                    <h4>No topic post.</h4>
                </c:otherwise>
            </c:choose>
    					</div>
    					
    					</div>
    				</div>
    			</div>
			</div>
		</div>
