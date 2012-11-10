<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="top.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
      			<div class="span3 bs-docs-sidebar">
        			<ul class="nav nav-list bs-docs-sidenav">
          				<li><a href="#"><i class="icon-chevron-right"></i>Poster</a></li>
          				<li><a href="#"><i class="icon-chevron-right"></i> DVD</a></li>
          				<li><a href="#"><i class="icon-chevron-right"></i> Props</a></li>
        			</ul>
      			</div>
      			<div class="span8" style="padding:20px">
      				<section>
      				    <form action="search.do" method="get" class="form-search" >
    						    <div class="input-append">
    							<input class="span12" type="text" size="40"  name = "key">
    							<span class="add-on">
    								<select name="options" style="width:100px;">
    									<option value="0">All</option>
    									<option value="1">Posted</option>
    									<option value="2">Wanted</option>
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
    					<ul class="thumbnails">

    						<c:if test="${!empty (allItemList)}">
    						<c:forEach var="item" items="${allItemList}">  
    						<li class="span4" style="margin-left:0">
                            <div class="thumbnail">
                                <div style="width:240px; height:160px; text-align:center">
                                    <img src="img/${item.imgName}" style="max-width:240px; max-height:160px; vertical-align:middle" alt="">
                                </div>
									<h3>${item.itemName}</h3>
									<p>caption...</p>
								</div>
							</li>
						</c:forEach>
						</c:if>

				<c:if test="${!empty (search_result)}">
    						<c:forEach var="item" items="${search_result}">  
    						<li class="span4" style="margin-left:0">
                            <div class="thumbnail">
                                <div style="width:240px; height:160px; text-align:center">
                                    <img src="img/${item.imgName}" style="max-width:240px; max-height:160px; vertical-align:middle" alt="">
                                </div>
									<h3>${item.itemName}</h3>
									<p>caption...</p>
								</div>
							</li>
						</c:forEach>
						</c:if>





						</ul>
    				</section>
      			</div>
      		</div>
      	</div>
      </div>
	</body>
</html>
