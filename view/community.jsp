<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="home.jsp">Home</a> <span class="divider">/</span></li>
    <li class="active"><a href="browseCommunity.do">Community</a> <span class="divider">/</span></li>
    </ul>
    </div>
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="span3" style="padding:10px;padding-top:20px; padding-left:50px;">
                <span style="padding-top:20px"><big><a href="createCommunity.do">Create your own group!</a></big></span>
            <p>&nbsp;</p>
				<div id="rez_part" style="margin-top:10px;border:1px #E7E6FA solid;height:300px">
					<div class="right_part_title"> 
						<span class="right_part_title_font">Top Community</span>
					</div>
                    <c:set var="rank" value="0" scope="page"/>
					<ul class="community_ul">
                        <c:forEach var="community" items="${top_C}">
                            <c:set var="rank" value="${rank + 1}"/>
                            <li>
                                <span class="badge badge-info">${rank}</span>
                                <span class="community_font"><a href="viewCommunity.do?name=${community.name}">${community.name}</a></span>
                            </li>
                        </c:forEach>
                        

					</ul>
				</div>
                
				
			</div>
			<div class="span9" style="padding:20px">
				<section>
      				    <form action="communitySearch.do" method="get" class="form-search" >
    						    <div class="input-append">
    							<input class="span12" type="text" size="40"  name = "key" value="${searchForm.key}">
    							<span class="add-on">
    								<select name="options" selected="1" style="width:120px;">
                                        <c:choose>
                                        <c:when test="${searchForm.options == '0'}">
    									<option value="0" selected>Community</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="0">Community</option>
                                        </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                        <c:when test="${searchForm.options == '1'}">
                                        <option value="1" selected>Topic</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="1">Topic</option>
                                        </c:otherwise>
                                        </c:choose>
    									
    								</select>
    							</span>
    							<input class="btn" type="submit" value="Search">
    							</div>
    					</form>
    			</section>
    			<hr/>
    			<jsp:include page="error.jsp" />
    			<jsp:include page="success.jsp" />
    			<section>
                    <h4>${title}</h4>
                    <c:if test="${!empty(top_T)}">
                        <table class="table table-striped">
                          
                            <thead>
                                <tr>
                                    <th class="span1">Reply</th>
                                    <th class="span6">Title</th>                                    
                                    <th class="span2">Community</th>
                                    <th class="span2">Poster</th>
                                    <th class="span3">Time</th>
                                </tr>
                                
                            </thead>
                                <c:forEach var="topic" items="${top_T}">
                                    <tr>
                                    <td>
                                        <div class="rep_num" title="${topic.replyCount} reply">
                                            ${topic.replyCount}
                                        </div>
                                    </td>
                                    <td><a href="viewTopic.do?topicId=${topic.id}">${topic.title}</a></td>
                                    <td><a href="viewCommunity.do?name=${topic.ownerGroup.name}">${topic.ownerGroup.name}</a></td>
                                    <td>by ${topic.poster.userName}</td>
                                    <td>@<fmt:formatDate value="${topic.postDate}" type="both" dateStyle="short" /></td>
                                    </tr>
                                </c:forEach>
                            
                        </table>
                    </c:if>
                    <c:if test="${!empty(search_T)}">
                        <table class="table table-striped">
                            
                            <thead>
                                <tr>
                                    <th class="span1">Reply</th>
                                    <th class="span6">Title</th>
                                    <th class="span2">Community</th>
                                    <th class="span2">Poster</th>
                                    <th class="span3">Time</th>
                                </tr> 
                            </thead>
                                <c:forEach var="topic" items="${search_T}">
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
                    </c:if>
                    <c:if test="${!empty(search_C)}">
                        
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
                                <c:forEach var="comm" items="${search_C}">
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
                    </c:if>

    			</section>
			</div>
		</div>
	</div>
</div>
</body>
</html>
