    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:choose>
        <c:when test="${empty(requestForm)}">
    <div class="tab-pane active" id="tab1">
        </c:when>
    <c:otherwise>
    <div class="tab-pane" id="tab1">
    </c:otherwise>
    </c:choose>
		<h4>Sharing is a bless</h4><br/>
		<form action="postItem.do" method="post" class="form-horizontal" name="post_form">
		    <div class="control-group">
				<label class="control-label">Item name</label>
					<div class="controls">
					<input class="span4" type="text" name="itemName" value="${postForm.itemName}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Item Description</label>
					<div class="controls">
					<textarea class="span8" rows="4" name="itemDescription">${postForm.itemDescription}</textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					<input type="checkbox" name="forCredit" value="" onclick="enable_text('post_credit', this.checked)">
				</label>
				<div class="controls" style="padding-top:2;">
					I wish to sell this item for
					<input id="post_credit" class="span2" type="text" name="credit" value="${postForm.credit}" disabled>
					credits.
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					<input type="checkbox" name="forExchange" value="" onclick="enable_text('post_exchange', this.checked)">
				</label>
				<div class="controls" style="padding-top:6	;">
					I wish to exchange this item with something as follows:
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					Exchange Description:
				</label>
				<div class="controls" style="padding-top:6	;">
					<textarea id="post_exchange" class="span8" rows="4" name="exchangeDescription" disabled>${postFrom.exchangeDescription}</textarea>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
				<input type="hidden" name="postType" value="1">
				<input type="submit" value="Post" class="btn">
				</div>		
			</div>
	</form>

</div>