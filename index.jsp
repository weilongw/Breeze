<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="top.jsp" />
    <div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="#">Home</a> <span class="divider">/</span></li>
    <li class="active"><a href="browse.do">Market</a> <span class="divider">/</span></li>
    
    </ul>
    </div>
		<div class="container-fluid">
			<div class="row-fluid">
      			<div class="span3 bs-docs-sidebar">
        			<ul class="nav nav-list bs-docs-sidenav">
          				<li><a href="showCategory.do?category=Poster"><i class="icon-chevron-right"></i>Poster</a></li>
          				<li><a href="showCategory.do?category=DVD"><i class="icon-chevron-right"></i>DVD</a></li>
          				<li><a href="showCategory.do?category=Prop"><i class="icon-chevron-right"></i>Prop</a></li>
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
                                  <a href="showItems.do?itemId=${item.id}"><img src="img/item/${item.imgName}" style="max-width:240px; max-height:160px; vertical-align:middle" alt=""></a>
                                </div>
									<a href="showItems.do?itemId=${item.id}"><h3>${item.itemName}</h3></a>
									<p>${fn:substring(item.itemDescription,0,30)}</p>
								</div>
							</li>
						</c:forEach>
						</c:if>
						</ul>
    				</section>
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
      			</div>
      		</div>
      	</div>
      </div>
	</body>
</html>
