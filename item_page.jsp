<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
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
			<div class="span8" style="padding:20px">
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
						<a href="#" onclick="show_xchg('${posted.exchangeItemDescription}')">view details..</a>
						</c:if>
  					</div>
  					<div class="span8">
  						<h4>${posted.itemName} (Selling) </h4>
  						<p>${posted.owner.userName}		
  						<c:if test="${isOwner==0}">			
							<a  href="redirectSend.do?receiver=${posted.owner.userName}&title=About ${posted.itemName}">Send him/her a message..</a>	
						</c:if></p>
						<fieldset>
							<legend>Item Description</legend>
							<big>${posted.itemDescription}</big>
						</fieldset>
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
						<a href="#" onclick="show_xchg('${requested.exchangeItemDescription}')">view details..</a>
						</c:if>
  					</div>
  					<div class="span8">
  						<h4>${requested.itemName} (Wanted) </h4>
  						<p>${requested.owner.userName}		
  						<c:if test="${isOwner == 0}">			
							<a  href="redirectSend.do?receiver=${requested.owner.userName}&title=About ${requested.itemName}">Send him/her a message..</a>		
						</c:if></p>
  						<fieldset>
							<legend>Item Description</legend>
							<big>${requested.itemDescription}</big>
						</fieldset><br/>
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
					