<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="home.do">Home</a> <span class="divider">/</span></li>
    <li class="active"><a href="browse.do">Market</a> <span class="divider">/</span></li>
    <c:choose>
    <c:when test="${!empty(posted)}">
    	<li class="active"><a href="showItems.do?itemId=${posted.id}">${posted.itemName}</a> <span class="divider">/</span></li>
    </c:when>
    <c:otherwise>
    	<li class="active"><a href="showItems.do?itemId=${requested.id}">${requested.itemName}</a> <span class="divider">/</span></li>
    </c:otherwise>
	</c:choose>
    </ul>
    </div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
				<c:if test="${!empty (posted)}">

					<li class="active"><a href="showItems.do?itemId=${posted.id}"><i class="icon-chevron-right"></i>Item Info</a></li>
					<li><a href="showMovie.do?itemId=${posted.id}"><i class="icon-chevron-right"></i>About Movie</a></li>
				</c:if>
				<c:if test="${!empty (requested)}">
					<li class="active"><a href="showItems.do?itemId=${requested.id}"><i class="icon-chevron-right"></i>Item Info</a></li>
					<li><a href="showMovie.do?itemId=${requested.id}"><i class="icon-chevron-right"></i>About Movie</a></li>
				</c:if>
					
				</ul>
			</div>
			<div class="span9" style="padding:20px">
                <jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
                <c:if test="${!empty (posted)}">

                <div class="row-fluid">
  					<div class="span4">
  						<div style="width:240px; height:160px; text-align:center">
							<img src="img/item/${posted.imgName}" style="max-width:240px; max-height:160px" alt="">
  						</div>
  						<br/>
  						<c:if test="${posted.credit != -1}">
  						<p>Cost: ${posted.credit} credits</p><br/>
  						</c:if>
  						<c:if test="${!empty (posted.exchangeItemDescription)}">
  						<p>The poster also accepts exchange..</p><br/>
						<a href="#" onclick="show_xchg()">view details..</a>
						</c:if>
  					</div>
  					<div class="span8">
  						<dl style="font-size:1.2em;" class="dl-horizontal">
  							<dt>Item name:</dt>
  							<dd>${posted.itemName}</dd>
  							<dt>Item type: </dt>
  							<dd>Selling</dd>
  							<dt>Category:</dt>
  							<c:choose>
  							<c:when test="${posted.category==1}">	
  							<dd>POSTER</dd>
  							</c:when>
  							<c:when test="${posted.category==2}">
  							<dd>DVD</dd>
  							</c:when>		
  							<c:otherwise>
  							<dd>PROP</dd>
  							</c:otherwise>
  							</c:choose>
  							<dt>Poster :</dt>
  							<dd> ${posted.owner.userName} &nbsp;&nbsp;<c:if test="${isOwner==0}">			
							<a  href="redirectSend.do?receiver=${posted.owner.userName}&title=About ${posted.itemName}">Send him/her a message..</a>	
							</c:if></dd>
							<dt>Item Description: </dt>
							<dd>${posted.itemDescription}</dd>
							<dt id="xchg_title"></dt>
							<dd id="xchg"></dd>

  						</dl>
  					
						<input type="hidden" id="xchgMsg" value="${posted.exchangeItemDescription}">
						<br/>
  						<div id="xchg"></div>
  						<c:if test="${isOwner == 0 && posted.status == 0}">
 						<table>
 							<tbody>
						    <tr>
						    	<td>
						    		<c:if test="${posted.credit != -1}">
							      	<form action="buyItem.do" method="post" class="form-horizontal" name="post_form">
							      	<input type="hidden" name="itemId" value="${posted.id}">
							      	<input type="hidden" name="buyType" value="1">
	    							<input type="submit" value="Buy with credits" class="btn">
	    							</form>
	    							</c:if>	
    							</td>
						      	<td>
						      		<c:if test="${!empty (posted.exchangeItemDescription)}">
						      		<form action="buyItem.do" method="post" class="form-horizontal" name="post_form">
						      		<input type="hidden" name="itemId" value="${posted.id}">
						      		<input type="hidden" name="buyType" value="2">
									<input type="submit" value="Exchange with items" class="btn">
									</c:if>
									</form>
						      	</td>
						    </tr>
  							</tbody>   					
    					</table>
    				</c:if>
  					</div>
				</div>
			</c:if>
			    <c:if test="${!empty (requested)}">

				<div class="row-fluid">
  					<div class="span4">
  						<div style="width:240px; height:160px; text-align:center">
							<img src="img/item/${requested.imgName}" style="max-width:240px; max-height:160px" alt="">
  						</div>
  						<br/>
  						<c:if test="${requested.credit != -1}">
  						<p>Offer: ${requested.credit} credits</p><br/>
  						</c:if>
  						<c:if test="${!empty (requested.exchangeItemDescription)}">
  						<p>The poster also offers exchange..</p><br/>
						<a href="#" onclick="show_xchg()">view details..</a>
						</c:if>
  					</div>
  					<div class="span8">
  						<dl style="font-size:1.2em;" class="dl-horizontal">
  							<dt>Item name:</dt>
  							<dd>${requested.itemName}</dd>
  							<dt>Item type: </dt>
  							<dd>Wanted</dd>
  							<dt>Category:</dt>
  							<c:choose>
  							<c:when test="${requested.category==1}">	
  							<dd>POSTER</dd>
  							</c:when>
  							<c:when test="${requested.category==2}">
  							<dd>DVD</dd>
  							</c:when>		
  							<c:otherwise>
  							<dd>PROP</dd>
  							</c:otherwise>
  							</c:choose>
  							<dt>Poster :</dt>
  							<dd> ${requested.owner.userName} &nbsp;&nbsp;<c:if test="${isOwner==0}">			
							<a  href="redirectSend.do?receiver=${requested.owner.userName}&title=About ${requested.itemName}">Send him/her a message..</a>	
							</c:if></dd>
							<dt>Item Description: </dt>
							<dd>${requested.itemDescription}</dd>
							<dt id="xchg_title"></dt>
							<dd id="xchg"></dd>

  						</dl>
						<input type="hidden" id="xchgMsg" value="${requested.exchangeItemDescription}">
  						<div id="xchg"></div>
 						<c:if test="${isOwner == 0 && requested.status == 0}">
 						<table>
 							<tbody>
						    <tr>
						    	<td>
						    		<c:if test="${requested.credit != -1}">
							      	<form action="buyItem.do" method="post" class="form-horizontal" name="post_form">
							      	<input type="hidden" name="itemId" value="${requested.id}">			
							      	<input type="hidden" name="buyType" value="3">
	    							<input type="submit" value="Exchange with credits" class="btn">
	    							</form>
	    							</c:if>
    							</td>
						      	<td>
						      		<c:if test="${!empty(requested.exchangeItemDescription)}">
						      		<form action="buyItem.do" method="post" class="form-horizontal" name="post_form">
						      		<input type="hidden" name="itemId" value="${requested.id}">
						      		<input type="hidden" name="buyType" value="4">
									<input type="submit" value="Exchange with items" class="btn">
									</form>
									</c:if>
						      	</td>
						    </tr>
  							</tbody>   					
    					</table>
    				</c:if>
  					</div>
				</div>
			</c:if>
			</div>
		</div>
	</div>
					