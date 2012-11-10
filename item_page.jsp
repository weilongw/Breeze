<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span4 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li class="active"><a href="item_page.jsp"><i class="icon-chevron-right"></i>Item Info</a></li>
					<li><a href="about_movie.jsp"><i class="icon-chevron-right"></i>About Movie</a></li>
				</ul>
			</div>
			<div class="span8" style="padding:20px">
                <jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
                <c:if test="${!empty (posted)}">

                <div class="row-fluid">
  					<div class="span4">
  						<img src="http://placehold.it/150x100" alt="">
  						<br/><p>Cost: ${posted.credit} credits</p><br/>
  						<p>The poster also accepts exchange..</p><br/>
						<a href="">view details..</a>
  					</div>
  					<div class="span8">
  						<h4>${posted.itemName}</h4>
  						<p>${posted.owner.userName}		<a  href="">Send him/her a message..</a></p>
  						<textarea class="span8" rows="4" name="itemDescription">${posted.itemDescription}</textarea>
 						<input type="hidden" name="type" value="1"><br/>
 						<table>
 							<tbody>
						    <tr>
						    	<td>
							      	<form action="" method="post" class="form-horizontal" name="post_form">
	    							<input type="submit" value="Buy with credits" class="btn">
	    							</form>
    							</td>
						      	<td>
						      		<form action="" method="post" class="form-horizontal" name="post_form">
									<input type="submit" value="Exchange with items" class="btn">
									</form>
						      	</td>
						    </tr>
  							</tbody>   					
    					</table>
  					</div>
				</div>
			</c:if>
			    <c:if test="${!empty (requested)}">

				<div class="row-fluid">
  					<div class="span4">
  						<img src="http://placehold.it/150x100" alt="">
  						<br/><p>Offer: ${requested.credit} credits</p><br/>
  						<p>The poster also offers exchange..</p><br/>
						<a href="">view details..</a>
  					</div>
  					<div class="span8">
  						<h4>${requested.itemName}</h4>
  						<p>${requested.owner.userName}			<a  href="">Send him/her a message..</a></p>
  						<textarea class="span8" rows="4" name="itemDescription">${posted.itemDescription}</textarea>
 						<input type="hidden" name="type" value="1"><br/>
 						<table>
 							<tbody>
						    <tr>
						    	<td>
							      	<form action="" method="post" class="form-horizontal" name="post_form">
	    							<input type="submit" value="Exchange for credits" class="btn">
	    							</form>
    							</td>
						      	<td>
						      		<form action="" method="post" class="form-horizontal" name="post_form">
									<input type="submit" value="Exchange with items" class="btn">
									</form>
						      	</td>
						    </tr>
  							</tbody>   					
    					</table>
  					</div>
				</div>
			</c:if>
			</div>
		</div>
	</div>
					