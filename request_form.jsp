<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${!empty(requestForm)}">
<div class="tab-pane active" id="tab2">
</c:when>
<c:otherwise>
<div class="tab-pane" id="tab2">
</c:otherwise>
</c:choose>

	<h4>Ask for anything you want</h4><br/>
<form action="postItem.do" method="post" class="form-horizontal">
    <div class="control-group">
		<label class="control-label">Item name</label>
			<div class="controls">
			<input class="span4" type="text" name="itemName" value="${requestForm.itemName}">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">Item Description</label>
			<div class="controls">
			<textarea class="span8" rows="4" name="itemDescription">${requestForm.itemDescription}</textarea>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">
			<input type="checkbox" name="forCredit" value="" onclick="enable_text('request_credit', this.checked)">
		</label>
		<div class="controls" style="padding-top:2;">
			I wish to buy this item with
			<input id="request_credit" class="span2" type="text" name="credit" value="${requestForm.credit}" disabled>
			credits.
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">
			<input type="checkbox" name="forExchange" value="" onclick="enable_text('request_exchange', this.checked)">
		</label>
		<div class="controls" style="padding-top:6	;">
			I can provide something as follows to exchange with this item:
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">
			Exchange Description:
		</label>
		<div class="controls" style="padding-top:6	;">
			<textarea id="request_exchange" class="span8" rows="4" name="exchangeDescription" disabled>${requestFrom.exchangeDescription}</textarea>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
		<input type="hidden" name="postType" value="2">
		<input type="submit" value="Post" class="btn">
		</div>		
	</div>
</form>
</div>