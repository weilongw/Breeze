<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3" style="padding:10px;padding-top:20px;">
				<div id="rez_part" style="margin-top:10px;border:1px #E7E6FA solid;">
					<div class="right_part_title"> 
						<span class="right_part_title_font">Top Community</span>
					</div>
                    <c:set var="rank" value="0" scope="page"/>
					<ul class="community_ul">
                        <c:forEach var="community" items="${top_C}">
                            <c:set var="rank" value="${rank + 1}"/>
                            <li>
                                <span class="badge badge-info">${rank}</span>
                                <span class="community_font">${community.name}</span>
                            </li>
                        </c:forEach>
                        

					</ul>
				</div>
                <p>&nbsp;</p>
				<span style="padding-top:20px"><a href="createCommunity.do">Create your own group!</a></span>
			</div>
			<div class="span8" style="padding:20px">
				<section>
      				    <form action="communitySearch.do" method="get" class="form-search" >
    						    <div class="input-append">
    							<input class="span12" type="text" size="40"  name = "key" value="${form.key}">
    							<span class="add-on">
    								<select name="options" selected="1" style="width:120px;">
    									<option value="0">Community</option>
    									<option value="1">Topic</option>
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
                                    <th class="span2">Poster</th>
                                    <th class="span2">Time</th>
                                </tr>
                                
                            </thead>
                                <c:forEach var="topic" items="${top_T}">
                                    <tr>
                                    <td>
                                        <div class="rep_num" title="${topic.replyCount} reply">
                                            ${topic.replyCount}
                                        </div>
                                    </td>
                                    <td><a href="#">${topic.title}</a></td>
                                    <td>by ${topic.poster.userName}</td>
                                    <td>@ ${topic.postDate}</td>
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
                                    <th class="span2">Poster</th>
                                    <th class="span2">Time</th>
                                </tr> 
                            </thead>
                                <c:forEach var="topic" items="${search_T}">
                                    <tr>
                                    <td>
                                        <div class="rep_num" title="${topic.replyCount} reply">
                                            ${topic.replyCount}
                                        </div>
                                    </td>
                                    <td><a href="#">${topic.title}</a></td>
                                    <td>by ${topic.poster.userName}</td>
                                    <td>@ ${topic.postDate}</td>
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
