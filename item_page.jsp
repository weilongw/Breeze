<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span4 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					                <c:if test="${!empty (posted)}">

					<li class="active"><a href="showItems.do?itemId=${posted.id}"><i class="icon-chevron-right"></i>Item Info</a></li>
				</c:if>
				   <c:if test="${!empty (requested)}">

					<li class="active"><a href="showItems.do?itemId=${requested.id}"><i class="icon-chevron-right"></i>Item Info</a></li>
				</c:if>
					<li><a href="about_movie.jsp"><i class="icon-chevron-right"></i>About Movie</a></li>
				</ul>
			</div>
			<div class="span8" style="padding:20px">
                <jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
                <c:if test="${!empty (posted)}">

                <div class="row-fluid">
  					<div class="span4">
  						<div style="width:240px; height:160px; text-align:center">
							<img src="img/${posted.imgName}" style="max-width:240px; max-height:160px" alt="">
  						</div>
  						<br/><p>Cost: ${posted.credit} credits</p><br/>
  						<p>The poster also accepts exchange..</p><br/>
						<a href="">view details..</a>
  					</div>
  					<div class="span8">
  						<h4>${posted.itemName}</h4>
  						<p>${posted.owner.userName}		
  						<c:if test="${isOwner==0}">			
							<a  href="">Send him/her a message..</a>	
						</c:if></p>
  						<textarea class="span8" rows="4" name="itemDescription">${posted.itemDescription}</textarea>
  						<c:if test="${isOwner == 0}">
 						<table>
 							<tbody>
						    <tr>
						    	<td>
							      	<form action="buyItem.do" method="post" class="form-horizontal" name="post_form">
							      	<input type="hidden" name="itemId" value="${posted.id}">
							      	<input type="hidden" name="buyType" value="1">
	    							<input type="submit" value="Buy with credits" class="btn">
	    							</form>
    							</td>
						      	<td>
						      		<form action="buyItem.do" method="post" class="form-horizontal" name="post_form">
						      		<input type="hidden" name="itemId" value="${posted.id}">
						      		<input type="hidden" name="buyType" value="2">
									<input type="submit" value="Exchange with items" class="btn">
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
							<img src="img/${requested.imgName}" style="max-width:240px; max-height:160px" alt="">
  						</div>
  						<br/><p>Offer: ${requested.credit} credits</p><br/>
  						<p>The poster also offers exchange..</p><br/>
						<a href="">view details..</a>
  					</div>
  					<div class="span8">
  						<h4>${requested.itemName}</h4>
  						<p>${requested.owner.userName}		
  						<c:if test="${isOwner == 0}">			
							<a  href="">Send him/her a message..</a>		
						</c:if></p>
  						<textarea class="span8" rows="4" name="itemDescription">${posted.itemDescription}</textarea>
 						<c:if test="${isOwner == 0}">
 						<table>
 							<tbody>
						    <tr>
						    	<td>
							      	<form action="buyItem.do" method="post" class="form-horizontal" name="post_form">
							      	<input type="hidden" name="itemId" value="${requested.id}">			
							      	<input type="hidden" name="buyType" value="3">
	    							<input type="submit" value="Buy with credits" class="btn">
	    							</form>
    							</td>
						      	<td>
						      		<form action="buyItem.do" method="post" class="form-horizontal" name="post_form">
						      		<input type="hidden" name="itemId" value="${requested.id}">
						      		<input type="hidden" name="buyType" value="4">
									<input type="submit" value="Exchange with items" class="btn">
									</form>
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
					