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
						<li><span class="badge badge-info">1</span><span class="community_font">haha1</span></li>
						<li><span class="badge badge-info">2</span><span class="community_font">haha2</span></li>
                        
                        <c:forEach var="community" items="${tops}" varStatus="loop-count">
                            <c:set var="rank" value="${rank + 1}"/>
                            <li>
                                <span class="badge badge-info">${rank}</span>
                                <span class="community_font">${community.name}</span>
                            </li>
                        </c:forEach>
                        

					</ul>
				</div>
                <p>&nbsp;</p>
				<span style="padding-top:20px"><a href="create_grp.jsp">Create your own group!</a></span>
			</div>
			<div class="span8" style="padding:20px">
				<section>
      				    <form action="#" method="get" class="form-search" >
    						    <div class="input-append">
    							<input class="span12" type="text" size="40"  name = "key">
    							<span class="add-on">
    								<select name="options" style="width:100px;">
    									<option value="0">Group</option>
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
    				<table class="table table-striped">
    					<thead>
    						<tr>
    							<th class="span1"></th>           
    							<th class="span6"></th>
    							<th class="span2"></th>
    							<th class="span2"></th>
    						</tr>
    					</thead>
    					<tr>
    						<td><div class="rep_num" title="20 reply">20</div></td>
    						<td><a href="#">Topic 1</a></td>
    						<td>by user1</td>		
    						<td>time1</td>
    					</tr>
    					<tr>
    						<td><div class="rep_num" title="20 reply">20</div></td>
    						<td><a href="#">Topic 2</a></td>
    						<td>by user2</td>
    						<td>time2</td>
    					</tr>
    					<tr>
    						<td><div class="rep_num" title="20 reply">20</div></td>
    						<td><a href="#">Topic 3</a></td>
    						<td>by user3</td>
    						<td>time3</td>
    					</tr>
    					<tr>
    						<td><div class="rep_num" title="20 reply">20</div></td>
    						<td><a href="#">Topic 4</a></td>
    						<td>by user3</td>
    						<td>time3</td>
    					</tr>
    				</table>
    			</section>
			</div>
		</div>
	</div>
</div>
</body>
</html>
