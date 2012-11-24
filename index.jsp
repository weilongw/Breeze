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
          				<li><a href="search.do?key=&options=0&category=1&page=1"><i class="icon-chevron-right"></i>Poster</a></li>
          				<li><a href="search.do?key=&options=0&category=2&page=1"><i class="icon-chevron-right"></i>DVD</a></li>
          				<li><a href="search.do?key=&options=0&category=3&page=1"><i class="icon-chevron-right"></i>Prop</a></li>
        			</ul>
      			</div>
      			<div class="span8" style="padding:20px">
      				<section>
      				    <form action="search.do" method="get" class="form-search" >
    						    <div class="input-append">
                                <span class="add-on">
                                    <select name="category" style="width:120px;">
                                        <c:choose>
                                        <c:when test="${form.category == '0'}">
                                        <option value="0" selected>All Categories</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="0">All Categories</option>
                                        </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                        <c:when test="${form.category == '1'}">
                                        <option value="1" selected>Poster</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="1">Poster</option>
                                        </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                        <c:when test="${form.category == '2'}">
                                        <option value="2" selected>DVD</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="2">DVD</option>
                                        </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                        <c:when test="${form.category == '3'}">
                                        <option value="3" selected>Prop</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="3">Prop</option>
                                        </c:otherwise>
                                        </c:choose>
                                        
                                    </select>
                                </span>
    							<input class="span9" type="text" size="40"  name = "key" value="${form.key}">
    							<span class="add-on">
    								<select name="options" style="width:100px;">
                                        <c:choose>
                                        <c:when test="${form.options == '0'}">
                                        <option value="0" selected>All Types</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="0">All Types</option>
                                        </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                        <c:when test="${form.options == '1'}">
                                        <option value="1" selected>Posted</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="1">Posted</option>
                                        </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                        <c:when test="${form.options == '2'}">
                                        <option value="2" selected>Wanted</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="2">Wanted</option>
                                        </c:otherwise>
                                        </c:choose>
    									
    								</select>
    							</span>
                                
                                <input type="hidden" name="page" value="1">
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
    						<c:forEach var="item" items="${allItemList}" begin="${itemStart}" end="${itemEnd}">  
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
                                <a href="search.do?key=${form.key}&options=${form.options}&category=${form.category}&page=1">
                                    |&lt;
                                </a>
                            </li>            
                            <c:choose>
                            <c:when test="${itemPageCurrent == 1}">
                                <li class="disabled">
                                    <a href="#">
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="search.do?key=${form.key}&options=${form.options}&category=${form.category}&page=${itemPageCurrent - 1}">
                            </c:otherwise>
                            </c:choose>
                                    Prev
                                    </a>
                                </li>
                             <c:forEach begin="${itemPageStart}" end="${itemPageEnd}" varStatus="loop">
                                <c:choose>
                                <c:when test="${itemPageCurrent == loop.index}">
                                    <li class="disabled">
                                        <a href="#">${loop.index}
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="search.do?key=${form.key}&options=${form.options}&category=${form.category}&page=${loop.index}">${loop.index}
                                </c:otherwise>
                                </c:choose>
                                        </a>                                    
                                    </li>
                            </c:forEach>
                            <c:choose>
                            <c:when test="${itemPageCurrent == itemPageCount}">
                                <li class="disabled">
                                    <a href="#">
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="search.do?key=${form.key}&options=${form.options}&category=${form.category}&page=${itemPageCurrent + 1}">
                            </c:otherwise>
                            </c:choose>
                                    Next
                                    </a>
                                </li>
                                <li>
                                    <a href="search.do?key=${form.key}&options=${form.options}&category=${form.category}&page=${itemPageCount}">&gt;|</a>
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
